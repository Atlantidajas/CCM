package com.jorge.app.ccm.ui.vehicleStatus;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.jorge.app.ccm.R;

import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.utils.DateHoursUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Jorge.HL
 * Adaptador para vehiclesStatus
 */

public class AdapterVehicleStatus extends BaseAdapter {

    private Context context;
    private ArrayList<Vehicle> listIntemVehicles;
    private Vehicle vehicle;

    public AdapterVehicleStatus(){}

    public AdapterVehicleStatus(final Context context ) {
        this.context = context;
    }

    public void setListIntemVehicles( ArrayList<Vehicle>vehicles ){
        this.listIntemVehicles = vehicles;
    }

    @Override
    public int getCount() {
        return listIntemVehicles.size();
    }

    public void updateAdapter(){
        this.listIntemVehicles.clear();
        notifyDataSetChanged();
    }

    /**
     * Retorna un vehículo seleccionado de la lista
     * @param position posición de la lista
     * @return vehículo según la posición
     */
    @Override
    public Vehicle getItem(int position) {
        return listIntemVehicles.get( position );
    }

    /**
     * Retorna un id de un vehículo seleccionado de la lista
     * @param position posición de la lista
     * @return id del vehículo según la posición
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Varga las vistas
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        this.vehicle = (Vehicle) getItem(position);

        convertView = LayoutInflater.from( context ).inflate(R.layout.list_item_view_vehicle_status, parent, false );

        ImageView imageView_image = convertView.findViewById( R.id.imageView_image_item_vehicles );
        imageView_image.setImageResource( vehicle.getVehiclelogo() );

        TextView textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_vehicles );
        textView_registrationNumber.setText( vehicle.getVehicleRegistrationNumber() );

        TextView textView_brand = convertView.findViewById( R.id.textView_brand_item_vehicles );
        textView_brand.setText( vehicle.getVehicleBrand() );

        TextView textView_model = convertView.findViewById( R.id.textView_model_item_vehicles );
        textView_model.setText( vehicle.getVehicleModel() );

        TextView textView_dateITV = convertView.findViewById( R.id.textView_dateITV_item_vehicles );

        //Obtengo fecha del sistema
       Calendar currentDate;
       currentDate = Calendar.getInstance();
       int day = currentDate.get(Calendar.DAY_OF_MONTH);
       int month = 1+ currentDate.get(Calendar.MONTH);
       int year = currentDate.get(Calendar.YEAR);

        try {
            DateHoursUtil dateHoursUtil = new DateHoursUtil();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date dateRegistry = sdf.parse(vehicle.getVehicleDateITV());
            Date dateSystemCurrent = sdf.parse( day + "-" + month + "-" + year);

            //Si la fecha del sistema es mayor que la fecha del registro fecha proxima ITV modifico el TextVies
            if ( dateRegistry.compareTo(dateSystemCurrent) <= 0 ){
                textView_dateITV.setTextColor( Color.RED );
                textView_dateITV.setTypeface(null, Typeface.BOLD);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        textView_dateITV.setText( vehicle.getVehicleDateITV() );

        //Muestro icono de vehículo libre.
        ImageView imageDriving = convertView.findViewById( R.id.imageView_driving_item_vehicles );
        imageDriving.setBackgroundColor( Color.BLUE );
        imageDriving.setImageResource( R.drawable.ic_stat_driving_car );


        //Muestro el logo o inicial del cliente si inicio sesión con este vehículo
        if ( vehicle.getVehicleDriving() == 1 ){
            imageDriving.setBackgroundColor( Color.GRAY );
        }

        return convertView;
    }

    /**
     * Retorna todos los vehículos cargados
     * @return ArrayList <Vehicles> Vehicles
     */
    public ArrayList<Vehicle> getListIntemVehicles() {
        return listIntemVehicles;
    }

}