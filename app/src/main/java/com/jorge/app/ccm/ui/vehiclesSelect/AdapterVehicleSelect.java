package com.jorge.app.ccm.ui.vehiclesSelect;

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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.models.Vehicle;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jorge.HL
 */

public class AdapterVehicleSelect extends BaseAdapter {

    private Context context;
    private ArrayList<Vehicle> listIntemVehicles = new ArrayList<Vehicle>();
    private TextView textView;
    private ListView listView;
    private Vehicle vehicle;
    ControllerDBStatus controllerDBStatus;

    public AdapterVehicleSelect(final Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
        this.controllerDBStatus = new ControllerDBStatus( context );


        //Eventos de cambios sobre el adaptador
        controllerDBStatus.getDatabaseReference().addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getListIntemVehicles().clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getListIntemVehicles().clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                getListIntemVehicles().clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getListIntemVehicles().clear();
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        //Cargo con los datos de la db el adapter
        controllerDBStatus.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    setArrayAdapterVehicle(dataSnapshot);
                }
                else {
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

        convertView = LayoutInflater.from( context ).inflate(R.layout.list_item_view_vehicle_select, parent, false );

        ImageView imageView_image = convertView.findViewById( R.id.imageView_image_item_vehicles );
        imageView_image.setImageResource( vehicle.getVehiclelogo() );

        TextView textView_registrationNumber = convertView.findViewById( R.id.textView_registrationNumber_item_vehicles );
        textView_registrationNumber.setText( vehicle.getVehicleRegistrationNumber() );

        TextView textView_brand = convertView.findViewById( R.id.textView_brand_item_vehicles );
        textView_brand.setText( vehicle.getVehicleBrand() );

        TextView textView_model = convertView.findViewById( R.id.textView_model_item_vehicles );
        textView_model.setText( vehicle.getVehicleModel() );

        return convertView;
    }


    /**
     * @Jorge.HL
     */

    public void setArrayAdapterVehicle(DataSnapshot dataSnapshot){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
        do{
            listIntemVehicles.add( new Vehicle( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        if ( this.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje
            Toast.makeText( context, R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
        }else{
            listView.setAdapter(this);
        }
    }

    public ArrayList<Vehicle> getListIntemVehicles() {
        return listIntemVehicles;
    }
}