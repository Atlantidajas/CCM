package com.jorge.app.ccm.ui.vehicles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.app.ccm.R;

import java.util.ArrayList;

public class AdapterVehicle extends BaseAdapter {

    private Context context;
    private int resorce;
    private ArrayList<Vehicle> vehicles;

    public AdapterVehicle(Context context, int resorce, ArrayList<Vehicle> vehicles) {
        this.context = context;
        this.resorce = resorce;
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

        Vehicle inten = (Vehicle) getItem(position);

        convertView = LayoutInflater.from( context ).inflate(R.layout.item_list_view_vehicles, null );

        ImageView imageView_image = convertView.findViewById( this.resorce );
        imageView_image.setImageResource( inten.getLogoVehicle() );

        TextView textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_vehicles );
        textView_registrationNumber.setText( inten.getRegistrationNumber() );

        TextView textView_brand = convertView.findViewById( R.id.textView_brand_item_vehicles );
        textView_brand.setText( inten.getBrand() );

        TextView textView_model = convertView.findViewById( R.id.textView_model_item_vehicles );
        textView_model.setText( inten.getModel() );

        return convertView;
    }
}
