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
import com.jorge.app.ccm.models.Vehicle;

import java.util.ArrayList;
import java.util.Iterator;

public class AdapterSessionHistoric extends BaseAdapter {

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

    public AdapterSessionHistoric(final Context context, TextView textView, ListView listView) {
        this.context = context;
        this.textView = textView;
        this.listView = listView;
        this.controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( context );
        user = new User(  );

        listIntemSessions = new ArrayList<>(  );

        //Eventos de cambios sobre el adaptador
        controllerDBSessionsHistoric.getDatabaseReference().addChildEventListener( new ChildEventListener() {
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
        this.controllerDBSessionsHistoric.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if( dataSnapshot.exists() ) {
                    setArrayAdapterHistoric( dataSnapshot );
                }
                else{
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

        imageViewLogoVehicle.setImageResource( sessionDriving.getVehicle().getLogoVehicle() );
        textView_registrationNumber.setText( sessionDriving.getVehicle().getRegistrationNumber() );
        textView_date.setText( sessionDriving.getSession().getDate() );
        textView_hours.setText( sessionDriving.getSession().getHours() );
        textView_typeSesion.setText( sessionDriving.getSession().getTypeSesion() );
        Glide.with( context ).load( sessionDriving.getUser().getPhotoUriString() ).into( imageView_drivind );


        return convertView;
    }

    /**
     * @Jorge.HL
     */

    public void setArrayAdapterHistoric(DataSnapshot dataSnapshot){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();

        do{
            listIntemSessions.add( new SessionDriving( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        if ( this.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje

        }else{
            listView.setAdapter(this);
        }

    }

    public ArrayList<SessionDriving> getListIntemSesions() {
        return listIntemSessions;
    }

    public void readerIdsUsers(){
        // Cargo array adapte
        this.controllerDBSessionsHistoric.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                setArrayAdapterHistoric( dataSnapshot );

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}