package com.jorge.app.ccm.ui.session;

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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicle;
import com.jorge.app.ccm.ui.user.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jorge.HL
 */

public class AdapterSession extends BaseAdapter {

    private Context context;
    private ControllerVehicle controllerVehicle;
    private ValueEventListener valueEventListener;
    private DatabaseReference dbRF;
    private ArrayList<SesionDriving> listIntemSessions = new ArrayList<SesionDriving>();
    private TextView textView;
    private ListView listView;
    private SesionDriving session;

    public AdapterSession(Context context, TextView textView, ListView listView) {
        this.context = context;
        this.textView = textView;
        this.listView = listView;
        controllerVehicle = new ControllerVehicle( context );
        this.readSesions();
        dbRF = controllerVehicle.getDB_RF_SESIONS();
        dbRF.addValueEventListener( valueEventListener );
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        this.session = (SesionDriving) getItem(position);

        convertView = LayoutInflater.from( context ).inflate(R.layout.session_item_list_view, parent, false );

        ImageView imageViewLogoVehicle = convertView.findViewById( R.id.imageView_image_item_sessions );
        imageViewLogoVehicle.setImageResource( session.getVehicle().getLogoVehicle() );

        TextView textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_sessions );
        textView_registrationNumber.setText( session.getVehicle().getRegistrationNumber() );

        TextView textView_date = convertView.findViewById( R.id.textView_session_date_item_sessions );
        textView_date.setText( session.getDate() );

        TextView textView_hours = convertView.findViewById( R.id.textView_session_hours_item_sessions );
        textView_hours.setText( session.getHours() );

        ImageView imageView_drivind = convertView.findViewById( R.id.imageView_driving_item_sessions );
        Glide.with( context ).load( session.getUser().getPhotoUriString() ).into(imageView_drivind);

        return convertView;
    }


    public void readSesions() {

        //Lamada función buscar vehículos
        this.valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshotSesion) {
                if (dataSnapshotSesion.exists()) {
                    setArrayAdapter(dataSnapshotSesion);
                }
                else {
                    Toast.makeText( context, R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    /**
     * @Jorge.HL
     */

    public void setArrayAdapter(DataSnapshot dataSnapshot){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
        do{
            listIntemSessions.add( new SesionDriving( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        if ( this.getCount() <= 0 ){//<-- Controlo que tenga al menos exista una sesión registrada, en caso contrario muestro mensaje
            textView.setText( "No hay sesiones en la lista" );
        }else{
            listView.setAdapter(this);
        }
    }

    public ValueEventListener getValueEventListener() {
        return valueEventListener;
    }

    public ArrayList<SesionDriving> getListIntemSesions() {
        return listIntemSessions;
    }
}