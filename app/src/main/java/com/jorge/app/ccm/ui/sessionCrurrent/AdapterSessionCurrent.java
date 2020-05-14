package com.jorge.app.ccm.ui.sessionCrurrent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSessionsCurrents;
import com.jorge.app.ccm.models.SessionDriving;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jorge.HL
 */

public class AdapterSessionCurrent extends BaseAdapter {

    private Context context;
    private ArrayList<SessionDriving> listIntemSessions = new ArrayList<SessionDriving>();
    private TextView textView;
    private ListView listView;
    private SessionDriving sessionDriving;
    private ControllerDBSessionsCurrents controllerDBSessionsCurrents;

    public AdapterSessionCurrent(){}

    public AdapterSessionCurrent(final Context context, TextView textView, ListView listView) {
        this.context = context;
        this.textView = textView;
        this.listView = listView;
        this.controllerDBSessionsCurrents = new ControllerDBSessionsCurrents( context );

        //Eventos de cambios sobre el adaptador
        controllerDBSessionsCurrents.getDatabaseReference().addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getListIntemSesions().clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getListIntemSesions().clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                getListIntemSesions().clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getListIntemSesions().clear();
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        // Cargo array adapte
        this.controllerDBSessionsCurrents.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    setArrayAdapterSessionCurrent( dataSnapshot );
                }
                else {
                    Toast.makeText( context, R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        return listIntemSessions.size();
    }

    @Override
    public Object getItem(int position) {
        return listIntemSessions.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        this.sessionDriving = (SessionDriving) getItem(position);

        convertView = LayoutInflater.from( context ).inflate(R.layout.list_item_view_session, parent, false );

        ImageView imageViewLogoVehicle = convertView.findViewById( R.id.imageView_image_item_sessions );
        imageViewLogoVehicle.setImageResource( sessionDriving.getVehicle().getLogoVehicle() );

        TextView textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_sessions );
        textView_registrationNumber.setText( sessionDriving.getVehicle().getRegistrationNumber() );

        TextView textView_date = convertView.findViewById( R.id.textView_session_date_item_sessions );
        textView_date.setText( sessionDriving.getSession().getDate() );

        TextView textView_hours = convertView.findViewById( R.id.textView_session_hours_item_sessions );
        textView_hours.setText( sessionDriving.getSession().getHours() );

        TextView textView_typeSesion = convertView.findViewById( R.id.textView_session_type_item_sessions );
        textView_typeSesion.setText( sessionDriving.getSession().getTypeSesion() );

        ImageView imageView_drivind = convertView.findViewById( R.id.imageView_driving_item_sessions );
        Glide.with( context ).load( sessionDriving.getUser().getPhotoUriString() ).into(imageView_drivind);

        return convertView;
    }

    /**
     * @Jorge.HL
     */

    public void setArrayAdapterSessionCurrent(DataSnapshot dataSnapshot){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
        do{
            listIntemSessions.add( new SessionDriving( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        if ( this.getCount() <= 0 ){//<-- Controlo que tenga al menos exista una sesión registrada, en caso contrario muestro mensaje
            textView.setText( "No hay sesiones en la lista" );
        }else{
            listView.setAdapter(this);
        }

    }

    public ArrayList<SessionDriving> getListIntemSesions() {
        return listIntemSessions;
    }
}