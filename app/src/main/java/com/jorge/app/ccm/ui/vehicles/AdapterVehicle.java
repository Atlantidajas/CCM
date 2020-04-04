package com.jorge.app.ccm.ui.vehicles;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Jorge.HL
 */


public class AdapterVehicle extends BaseAdapter {

    private Context context;
    ArrayList<Vehicle> listIntemVehicles = new ArrayList<Vehicle>();
    private TextView textView;
    private ListView listView;
    private Vehicle vehicle;

    public AdapterVehicle(Context context, TextView textView, ListView listView) {
        this.context = context;
        this.textView = textView;
        this.listView = listView;
        onclickItemList();
    }

    @Override
    public int getCount() {
        return listIntemVehicles.size();
    }

    @Override
    public Object getItem(int position) {
        return listIntemVehicles.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        this.vehicle = (Vehicle) getItem(position);

        convertView = LayoutInflater.from( context ).inflate(R.layout.vehicle_item_list_view, parent, false );

        ImageView imageView_image = convertView.findViewById( R.id.imageView_image_item_vehicles );
        imageView_image.setImageResource( vehicle.getLogoVehicle() );

        TextView textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_vehicles );
        textView_registrationNumber.setText( vehicle.getRegistrationNumber() );

        TextView textView_brand = convertView.findViewById( R.id.textView_brand_item_vehicles );
        textView_brand.setText( vehicle.getBrand() );

        TextView textView_model = convertView.findViewById( R.id.textView_model_item_vehicles );
        textView_model.setText( vehicle.getModel() );

        TextView textView_dateITV = convertView.findViewById( R.id.textView_dateITV_item_vehicles );

        //Obtengo fecha del sistema
       Calendar currentDate;
       currentDate = Calendar.getInstance();
       int day = currentDate.get(Calendar.DAY_OF_MONTH);
       int month = 1+ currentDate.get(Calendar.MONTH);
       int year = currentDate.get(Calendar.YEAR);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); //Para declarar valores en nuevos objetos date, usa el mismo formato date que usaste al crear las fechas
            Date dateRegistry = sdf.parse(vehicle.getDateITV()); //date1 es el 23 de febrero de 1995
            Date dateSystemCurrent = sdf.parse( day + "-" + month + "-" + year); //date2 es el 31 de octubre de 2001


            //Si la fecha del sistema es mayor que la fecha del registro fecha proxima ITV modifico el TextVies
            if ( dateRegistry.compareTo(dateSystemCurrent) <= 0 ){
                textView_dateITV.setTextColor( Color.RED );
                textView_dateITV.setTypeface(null, Typeface.BOLD);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        textView_dateITV.setText( vehicle.getDateITV() );

        ImageView imageDriving = convertView.findViewById( R.id.imageView_driving_item_vehicles );
        imageDriving.setImageResource( R.mipmap.ic_launcher_driving_gray );


        if ( vehicle.getDriving() == 1 ){
            imageDriving.setImageResource( R.mipmap.ic_launcher_driving_green );
        }

        return convertView;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @Jorge.HL
     */

    public void setArrayAdapter(DataSnapshot dataSnapshot){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
        do{
            listIntemVehicles.add( new Vehicle( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        if ( this.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje
            textView.setText( "No hay vehículos en la lista" );
        }else{
            listView.setAdapter(this);
        }
    }

    public void onclickItemList(){
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    int posicion, long id) {
                // Muestro mensaje de que ha pulsado sobre usuario.
               System.out.println( "*************************************Pulsado inten ListView"+
                       vehicle.getDateITV());
            }
        });
    }


}