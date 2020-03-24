package com.jorge.app.ccm.controllers;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.WindowsNoticeNoRegistryVehicle;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

import java.util.EventListener;

public class ControllerVehicles extends AppCompatActivity {

    private DatabaseReference databaseRfVehicles;
    private DatabaseReference vehicleRf;
    private boolean operatingResult;

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

    public void writeNewVehicle( final Vehicle vehicle) {


        final DatabaseReference vehiclesDB = this.databaseRfVehicles;

        vehiclesDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshotVehicles) {

                // Get Post object and use the values to update the UI
                Vehicle post = dataSnapshotVehicles.getValue(Vehicle.class);
                // [START_EXCLUDE]
                vehicle.setRegistrationNumber( post.getRegistrationNumber() );
                vehicle.setLogoVehicle(post.getLogoVehicle());
                vehicle.setBrand(post.getBrand());
                vehicle.setModel(post.getModel());
                // [END_EXCLUDE]
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message

                System.out.println( databaseError.getMessage() );


                // [END_EXCLUDE]
            }
        });
    }
}
