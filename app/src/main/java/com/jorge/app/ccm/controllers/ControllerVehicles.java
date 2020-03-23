package com.jorge.app.ccm.controllers;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

        DatabaseReference vehicles = this.databaseRfVehicles.child( vehicle.getRegistrationNumber() );
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String resut = String.valueOf( dataSnapshot.child("registrationNumber").getValue() );

                    //Compara la matricula introducida con las que existe en la base de datos.
                    if ( resut.equals( vehicle.getRegistrationNumber() ) ){
                       // WindowsNoticeNoRegistryVehicle winNoRegistreNumber = new WindowsNoticeNoRegistryVehicle( getSupportFragmentManager() );
                    }
                } else {

                    databaseRfVehicles.child( vehicle.getRegistrationNumber() ).setValue(vehicle);
                    Vehicle vehicleIn = new Vehicle(
                            vehicle.getLogoVehicle(),
                            vehicle.getRegistrationNumber(),
                            vehicle.getBrand(),
                            vehicle.getModel());

                    databaseRfVehicles.child( vehicle.getRegistrationNumber() ).setValue(vehicleIn);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
