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
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicle;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;
import com.jorge.app.ccm.ui.user.User;
import com.jorge.app.ccm.utils.DateHoursUtil;

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
    private ControllerVehicle controllerVS;
    private ValueEventListener valueEventListener;
    private DatabaseReference dbRF;
    private ArrayList<Vehicle> listIntemVehicles = new ArrayList<Vehicle>();
    private TextView textView;
    private ListView listView;
    private Vehicle vehicle;
    private User userLoging;

    public AdapterVehicle(Context context, TextView textView, ListView listView) {
        this.context = context;
        this.textView = textView;
        this.listView = listView;
        this.userLoging = new User();
        controllerVS = new ControllerVehicle( context );
        this.readVehicles();
        dbRF = controllerVS.getDB_RF_STATUS();
        dbRF.addValueEventListener( valueEventListener );

    }

    public ControllerVehicle getControllerVS() {
        return controllerVS;
    }

    public DatabaseReference getDbRF() {
        return dbRF;
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
            DateHoursUtil dateHoursUtil = new DateHoursUtil();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date dateRegistry = sdf.parse(vehicle.getDateITV());
            Date dateSystemCurrent = sdf.parse( day + "-" + month + "-" + year);

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
        imageDriving.setBackgroundColor( Color.GRAY );

        Glide.with(context).load(userLoging.photoUri()).into(imageDriving);


        if ( vehicle.getDriving() == 1 ){
            imageDriving.setBackgroundColor( Color.GREEN );
        }

        return convertView;
    }


    public void readVehicles() {

        //Lamada función buscar vehículos
        this.valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    setArrayAdapter(dataSnapshot);
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
            listIntemVehicles.add( new Vehicle( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        if ( this.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje
            textView.setText( "No hay vehículos en la lista" );
        }else{
            listView.setAdapter(this);
        }
    }

    public ValueEventListener getValueEventListener() {
        return valueEventListener;
    }

    public ArrayList<Vehicle> getListIntemVehicles() {
        return listIntemVehicles;
    }
}