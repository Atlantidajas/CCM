package com.jorge.app.ccm.controllers;


import android.content.Context;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

import java.util.EventListener;

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

    public void writeNewVehicle(final Vehicle vehicle ) {

        DatabaseReference vehicles = this.databaseRfVehicles.child( vehicle.getRegistrationNumber() );
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    String resut = String.valueOf( dataSnapshot.child("registrationNumber").getValue() );
                    if( resut.equals( vehicle.getRegistrationNumber() ) ){
                       resultOperationWrite = 0;
                       return;
                    }
                    resultOperationWrite = 1;
                    return;

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                resultOperationWrite = 2;
                return;
            }
        });

    }

    public int getResultOperationWrite() {
        return resultOperationWrite;
    }
}
