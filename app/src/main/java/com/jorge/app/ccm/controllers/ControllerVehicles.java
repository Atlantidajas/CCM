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
    private boolean operatingResult = false;


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

    public void writeNewVehicle(final Vehicle vehicle) {



        DatabaseReference vehicles = this.databaseRfVehicles;
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String resut = "";
                    resut = String.valueOf( dataSnapshot.child(vehicle.getRegistrationNumber()).getValue() );
                    //Compara la matricula introducida con las que existe en la base de datos.

                    if( resut == vehicle.getRegistrationNumber() ){
                        System.out.println("Repetido-------------------------------------------------");
                        System.out.println("Repetido-------------------------------------------------");
                        System.out.println("Repetido-------------------------------------------------");
                    }
                    else{
                        databaseRfVehicles.child(vehicle.getRegistrationNumber()).setValue(vehicle);
                    }

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( databaseError + "<----------------------------------------------------------" );
            }
        });
    }

    public DatabaseReference getVehicle() {
        return vehicleRf = this.databaseRfVehicles;
    }

    public boolean isOperatingResult() {
        return operatingResult;
    }
}
