package com.jorge.app.ccm.ui.vehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicles;


import java.util.ArrayList;
import java.util.Iterator;

public class VehiclesListActivity extends AppCompatActivity {

    private ControllerVehicles controllerVehicles;
    private TextView textView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);

        //Connect
        this.controllerVehicles = new ControllerVehicles();
        //readVehicleForReference("1234HFT");
        readVehicles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.vehicles_toolbar, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.resgistreVehicle ) {//<-- Crear vehículo
            Intent intent= new Intent (VehiclesListActivity.this, RegistreVehicleActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }

    public void readVehicleForReference( String refence ) {
        //Lamada función buscar vehículo por matrícula
        DatabaseReference vehicleRF = controllerVehicles.getVehicleRf( refence );

        ProgressBar progressBar;

        vehicleRF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    setArrayAdapter(dataSnapshot);
                } else {
                    Toast.makeText(getApplicationContext(), "No se han obtenido resultado. Error en consulta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readVehicles() {
        //Lamada función buscar vehículos
        DatabaseReference vehicles = controllerVehicles.getVehicle();
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    setArrayAdapter(dataSnapshot);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se han obtenido resultado. Error en consulta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setArrayAdapter( DataSnapshot dataSnapshot ){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
        ArrayList<Vehicle> listIntemVehicles = new ArrayList<Vehicle>();

        do{
            listIntemVehicles.add( new Vehicle( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        final AdapterVehicle arrayAdapter = new AdapterVehicle( getApplicationContext(), R.id.imageView_image_item_vehicles, listIntemVehicles );
        this.textView = this.findViewById(R.id.textView_vehicles);
        this.listView = this.findViewById(R.id.listView_vehicles);

        if ( arrayAdapter.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje
            textView.setText( "No hay vehículos en la lista" );
        }else{
            listView.setAdapter(arrayAdapter);
        }


    }
}
