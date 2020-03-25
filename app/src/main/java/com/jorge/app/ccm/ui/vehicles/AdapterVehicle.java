package com.jorge.app.ccm.ui.vehicles;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.R;

import java.util.ArrayList;
import java.util.Iterator;

public class AdapterVehicle extends BaseAdapter {

    private Context context;
    private ArrayList<Vehicle> vehicles;

    public AdapterVehicle(Context context, ArrayList<Vehicle> vehicles) {
        this.context = context;
        this.vehicles = vehicles;
    }

    @Override
    public int getCount() {
        return vehicles.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicles.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Vehicle vehicle = (Vehicle) getItem(position);

        convertView = LayoutInflater.from( context ).inflate(R.layout.item_list_view_vehicles, parent, false );

        ImageView imageView_image = convertView.findViewById( R.id.imageView_image_item_vehicles );
        imageView_image.setImageResource( vehicle.getLogoVehicle() );

        TextView textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_vehicles );
        textView_registrationNumber.setText( vehicle.getRegistrationNumber() );

        TextView textView_brand = convertView.findViewById( R.id.textView_brand_item_vehicles );
        textView_brand.setText( vehicle.getBrand() );

        TextView textView_model = convertView.findViewById( R.id.textView_model_item_vehicles );
        textView_model.setText( vehicle.getModel() );

        return convertView;
    }


    private void setArrayAdapter(DataSnapshot dataSnapshot, TextView textView, ListView listView){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
        ArrayList<Vehicle> listIntemVehicles = new ArrayList<Vehicle>();

        do{
            listIntemVehicles.add( new Vehicle( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        AdapterVehicle arrayAdapter = new AdapterVehicle(
                context,
                listIntemVehicles );

        if ( arrayAdapter.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje
            textView.setText( "No hay vehículos en la lista" );
        }else{
            listView.setAdapter(arrayAdapter);
        }

    }
}