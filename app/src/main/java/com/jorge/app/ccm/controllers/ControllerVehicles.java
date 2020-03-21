package com.jorge.app.ccm.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

public class ControllerVehicles {

    private DatabaseReference databaseRfVehicles;
    private DatabaseReference vehicleRf;

    public ControllerVehicles() {
        databaseRfVehicles = FirebaseDatabase.getInstance().getReference("Vehicles");
    }

    public DatabaseReference getDatabaseRfVehicles() {
        return databaseRfVehicles;
    }

    public DatabaseReference getVehicleRf( String reference ) {
        return vehicleRf = this.databaseRfVehicles.child( reference );
    }

    public DatabaseReference getVehicle() {
        return vehicleRf = this.databaseRfVehicles;
    }

    public void writeNewVehicle(final int logoVehicle, final String registrationNumber, final String brand, final String model, final Context context) {

        DatabaseReference vehicles = this.databaseRfVehicles.child( registrationNumber );
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String resut = String.valueOf( dataSnapshot.child("registrationNumber").getValue() );

                    //Compara la matricula introducida con las que existe en la base de datos.
                    if ( resut.equals( registrationNumber ) ){

                        final AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                        alerta.setTitle(R.string.alert_title_notice);
                        alerta.setMessage(R.string.alert_message_a_license_plate_already_exists);
                        alerta.setCancelable(false);
                        alerta.setPositiveButton(R.string.alert_positive_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();
                    }
                } else {

                    Vehicle vehicle = new Vehicle( logoVehicle,
                            registrationNumber,
                            brand,
                            model );

                    databaseRfVehicles.child( registrationNumber ).setValue(vehicle);
                    Toast.makeText( context , "No se han obtenido resultado. Error en consulta", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
