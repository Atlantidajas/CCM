package com.jorge.app.ccm.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.ui.vehicles.Vehicle;


public class ControllerVehicles extends AppCompatActivity {

    private DatabaseReference databaseRfVehicles;
    private DatabaseReference vehicleRf;
    private boolean operatingResult = true;


    public ControllerVehicles() {
        databaseRfVehicles = FirebaseDatabase.getInstance().getReference("Vehicles");
    }

    public DatabaseReference getDatabaseRfVehicles() {
        return databaseRfVehicles;
    }

    public DatabaseReference getVehicleRf( String reference ) {
        return vehicleRf = this.databaseRfVehicles.child( reference );
    }

    public void setOperatingResult(boolean operatingResult) {
        this.operatingResult = operatingResult;
    }

    private boolean verifyRegistration(final Vehicle vehicle) {

        DatabaseReference vehicles = this.databaseRfVehicles.child( vehicle.getRegistrationNumber() );
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String resut = String.valueOf( dataSnapshot.child("registrationNumber").getValue() );
                    //Compara la matricula introducida con las que existe en la base de datos.
                    if ( resut.equals( vehicle.getRegistrationNumber() ) ){
                        operatingResult = false;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( databaseError + "<----------------------------------------------------------" );
            }
        });
        return operatingResult;
    }

    public boolean writeNewVehicle( final Vehicle vehicle) {


            DatabaseReference vehicles = this.databaseRfVehicles.child(vehicle.getRegistrationNumber());
            vehicles.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {


                        Vehicle vehicleIn = new Vehicle(
                                vehicle.getLogoVehicle(),
                                vehicle.getRegistrationNumber(),
                                vehicle.getBrand(),
                                vehicle.getModel());

                        databaseRfVehicles.child(vehicle.getRegistrationNumber()).setValue(vehicleIn);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        return operatingResult;
    }
    public DatabaseReference getVehicle() {
        return vehicleRf = this.databaseRfVehicles;
    }
}
