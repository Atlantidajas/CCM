package com.jorge.app.ccm.gadget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.models.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SpinnerVehiclesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String TAG = "SpinnerVehiclesActivity";
    Spinner spinnerVehiclesDB;
    String[] strFrutas;
    List<String> listaVehicles;
    ArrayAdapter<String> comboAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_vehicles);

        loadVehiclesDB();

        listaVehicles = new ArrayList<>();
        //Arreglo con nombre de frutas
        strFrutas = new String[] {"Pera", "Manzana", "Fresa", "Sandia", "Mango"};


        spinnerVehiclesDB = (Spinner) findViewById(R.id.spinner_registration_number_vehicles);
        spinnerVehiclesDB.setOnItemSelectedListener(this);
        listaVehicles = new ArrayList<>();
        Collections.addAll(listaVehicles, strFrutas);
        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listaVehicles);
        spinnerVehiclesDB.setAdapter(comboAdapter);
    }

    public void loadVehiclesDB(){

        ControllerDBStatus controllerDBStatus = new ControllerDBStatus( getApplicationContext() );

        controllerDBStatus.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists() ){

                    Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();

                    do{

                        listaVehicles.add( new Vehicle( dataSnapshots.next() ).getRegistrationNumber() );

                    }while (dataSnapshots.hasNext());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}