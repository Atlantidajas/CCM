package com.jorge.app.ccm.ui.sessionCrurrent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.models.session.SesionDriving;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jorge.HL
 */

public class AdapterSessionCurrent extends BaseAdapter {

    private Context context;
    private ArrayList<SesionDriving> listIntemSessions = new ArrayList<SesionDriving>();
    private TextView textView;
    private ListView listView;
    private SesionDriving session;

    public AdapterSessionCurrent(){}

    public AdapterSessionCurrent(Context context, TextView textView, ListView listView) {
        this.context = context;
        this.textView = textView;
        this.listView = listView;
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

        this.session = (SesionDriving) getItem(position);

        convertView = LayoutInflater.from( context ).inflate(R.layout.list_item_view_session, parent, false );

        ImageView imageViewLogoVehicle = convertView.findViewById( R.id.imageView_image_item_sessions );
        imageViewLogoVehicle.setImageResource( session.getVehicle().getLogoVehicle() );

        TextView textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_sessions );
        textView_registrationNumber.setText( session.getVehicle().getRegistrationNumber() );

        TextView textView_date = convertView.findViewById( R.id.textView_session_date_item_sessions );
        textView_date.setText( session.getDate() );

        TextView textView_hours = convertView.findViewById( R.id.textView_session_hours_item_sessions );
        textView_hours.setText( session.getHours() );

        TextView textView_typeSesion = convertView.findViewById( R.id.textView_session_type_item_sessions );
        textView_typeSesion.setText( session.getTypeSesion() );

        ImageView imageView_drivind = convertView.findViewById( R.id.imageView_driving_item_sessions );
        Glide.with( context ).load( session.getUser().getPhotoUriString() ).into(imageView_drivind);

        return convertView;
    }

    /**
     * @Jorge.HL
     */

    public void setArrayAdapterSessionCurrent(DataSnapshot dataSnapshot){

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


    public ArrayList<SesionDriving> getListIntemSesions() {
        return listIntemSessions;
    }
}