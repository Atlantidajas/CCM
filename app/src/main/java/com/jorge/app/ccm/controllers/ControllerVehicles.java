package com.jorge.app.ccm.controllers;


import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

public class ControllerVehicles {

    private DatabaseReference databaseRfVehicles;
    private DatabaseReference vehicleRf;
    private int resultOperationWrite;

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

    public int writeNewVehicle(final Vehicle vehicle ) {

        DatabaseReference vehicles = this.databaseRfVehicles.child( vehicle.getRegistrationNumber() );
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String resut = String.valueOf( dataSnapshot.child("registrationNumber").getValue() );


                    //Compara la matricula introducida con las que existe en la base de datos.
                    if ( resut.equals( vehicle.getRegistrationNumber()) ){

                        System.out.println("*******************************************************************");
                        System.out.println(resut);
                        System.out.println("*******************************************************************");
                        resultOperationWrite = 0;

                    }
                } else {

                    Vehicle newVehicle = new Vehicle( vehicle.getLogoVehicle(),
                            vehicle.getRegistrationNumber(),
                            vehicle.getBrand(),
                            vehicle.getModel() );

                    databaseRfVehicles.child( vehicle.getRegistrationNumber() ).setValue(newVehicle);
                    resultOperationWrite = 1;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return resultOperationWrite;
    }
}
