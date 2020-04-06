package com.jorge.app.ccm.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.snapshot.ChildrenNode;
import com.jorge.app.ccm.ui.user.UserSesionVehicle;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

public class ControllerVehicle {

    private DatabaseReference databaseReference;
    private DatabaseReference childVehiclesStatus;
    private DatabaseReference childVehicleSesions;


    public ControllerVehicle() {
        databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" );
        childVehiclesStatus = databaseReference.child( "Status" );
        childVehicleSesions = databaseReference.child( "Sesions" );

    }

    public void newResgistryVehicle( Vehicle vehicle ){
        childVehiclesStatus.child( vehicle.getRegistrationNumber() ).setValue( vehicle );//<- De id uso la matrícula
    }

    public void newSesionsVehicleResgistry( UserSesionVehicle sesionVehicle ){
         childVehicleSesions.child( sesionVehicle.getVehicle().getRegistrationNumber() ).setValue( sesionVehicle );
    }

    public DatabaseReference getChildVehiclesStatus() {
        return childVehiclesStatus;
    }

    public DatabaseReference getChildVehicleSesions() {
        return childVehicleSesions;
    }
}
