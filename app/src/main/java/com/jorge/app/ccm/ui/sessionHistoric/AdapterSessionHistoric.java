package com.jorge.app.ccm.ui.sessionHistoric;

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
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.models.User;

import java.util.ArrayList;
import java.util.Iterator;

public class AdapterSessionHistoric extends BaseAdapter {

    private String TAG;
    private ImageView imageViewLogoVehicle;
    private TextView textView_registrationNumber;
    private TextView textView_date;
    private TextView textView_hours;
    private TextView textView_typeSesion;
    ImageView imageView_drivind;
    private Context context;
    private ArrayList<SessionDriving> listIntemSessions;
    private TextView textView;
    private ListView listView;
    private SessionDriving sessionDriving;
    private ControllerDBSessionsHistoric controllerDBSessionsHistoric;
    private User user;


    public AdapterSessionHistoric(){}

    public AdapterSessionHistoric(final Context context, TextView textView, ListView listView, String TAG) {
        this.context = context;
        this.textView = textView;
        this.listView = listView;
        this.TAG = TAG;
        this.controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( context, TAG );
        user = new User( true );

        listIntemSessions = new ArrayList<>(  );

        //Eventos de cambios sobre el adaptador
        controllerDBSessionsHistoric.getDatabaseReferenceSessionsHistoric().addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listIntemSessions.clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listIntemSessions.clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                listIntemSessions.clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listIntemSessions.clear();
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        // Cargo array adapte
        this.controllerDBSessionsHistoric.getDatabaseReferenceSessionsHistoric().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if( dataSnapshot.exists() ) {
                    setArrayAdapterHistoric( dataSnapshot );
                }
                else{
                    //Muestro mensaje si no hay ningún registro, por si es el admin el que consulta
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

            imageViewLogoVehicle = convertView.findViewById( R.id.imageView_image_item_sessions );
            textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_sessions );
            textView_date = convertView.findViewById( R.id.textView_session_date_item_sessions );
            textView_hours = convertView.findViewById( R.id.textView_session_hours_item_sessions );
            textView_typeSesion = convertView.findViewById( R.id.textView_session_type_item_sessions );
            imageView_drivind = convertView.findViewById( R.id.imageView_driving_item_sessions );

            imageViewLogoVehicle.setImageResource( sessionDriving.getVehiclelogo() );
            textView_registrationNumber.setText( sessionDriving.getVehicleRegistrationNumber() );
            textView_date.setText( sessionDriving.getSessionDate() );
            textView_hours.setText( sessionDriving.getSessionHours() );
            textView_typeSesion.setText( sessionDriving.getSessionTypeSesion() );
            Glide.with( context ).load( sessionDriving.getUserPhotoUriString() ).into( imageView_drivind );

        return convertView;
    }

    /**
     * @Jorge.HL
     */

    public void setArrayAdapterHistoric(DataSnapshot dataSnapshot){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();

        do{
            SessionDriving sessionDriving = new SessionDriving( dataSnapshots.next() );

            if( sessionDriving.getIdUser().equals( user.getIdUser() ) ) {

                listIntemSessions.add( sessionDriving );

            }


        }while (dataSnapshots.hasNext());

        if ( this.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje
            //Muestro mensaje si no hay ningún registro, de este usuario
            Toast.makeText( context, R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();

        }else{
            listView.setAdapter(this);
        }

    }

    public ArrayList<SessionDriving> getListIntemSesions() {
        return listIntemSessions;
    }

}