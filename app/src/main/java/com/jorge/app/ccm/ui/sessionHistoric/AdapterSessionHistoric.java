package com.jorge.app.ccm.ui.sessionHistoric;

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
import com.jorge.app.ccm.models.SesionDriving;

import java.util.ArrayList;
import java.util.Iterator;

public class AdapterSessionHistoric extends BaseAdapter {
    private Context context;
    private ArrayList<SesionDriving> listIntemSessions = new ArrayList<SesionDriving>();
    private TextView textView;
    private ListView listView;
    private SesionDriving sessionDriving;

    public AdapterSessionHistoric(){}

    public AdapterSessionHistoric(Context context, TextView textView, ListView listView) {
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
    public View getView(int position, View convertView, ViewGroup parent ) {

        this.sessionDriving = (SesionDriving) getItem(position);

        convertView = LayoutInflater.from( context ).inflate( R.layout.list_item_view_session, parent, false );

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

    public void setArrayAdapterHistoric(DataSnapshot dataSnapshot){

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