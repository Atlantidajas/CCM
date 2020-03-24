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

    public String writeNewVehicle(final Vehicle vehicle){

        String result = "";
        DatabaseReference vehiclesDB = this.databaseRfVehicles;
        result = String.valueOf(vehiclesDB.child(vehicle.getRegistrationNumber()).child("registrationNumber").child(vehicle.getRegistrationNumber() ));
        if( result != vehicle.getRegistrationNumber() ){

            vehiclesDB.addValueEventListener( new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Vehicle regitryVehicle = new Vehicle(
                        vehicle.getLogoVehicle(),
                        vehicle.getRegistrationNumber(),
                        vehicle.getBrand(),
                        vehicle.getModel());
                        databaseRfVehicles.child(vehicle.getRegistrationNumber()).setValue(regitryVehicle);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println(  "Error ------> Error ----> Error -----> " + databaseError.getMessage() + "<-------------------");
                }
            });
            result = "1";
        }if( result == vehicle.getRegistrationNumber() ){
            result = "0";
        }
        return result;
    }
    public DatabaseReference getVehicle() {
        return vehicleRf = this.databaseRfVehicles;
    }
}
