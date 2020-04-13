package com.jorge.app.ccm.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jorge.app.ccm.ui.vehicles.Vehicle;


public class ControllerVehicle {

    private DatabaseReference DB_RF;
    private DatabaseReference DB_RF_STATUS;

    public ControllerVehicle() {
        DB_RF = FirebaseDatabase.getInstance().getReference( "VehiclesDB" );
        DB_RF_STATUS = DB_RF.child( "Status" );
    }

    public DatabaseReference getDB_RF() {
        return DB_RF;
    }

    public DatabaseReference getDB_RF_STATUS() {
        return DB_RF_STATUS;
    }

    public void setVehicle( Vehicle vehicle ){
        this.DB_RF_STATUS.child( vehicle.getRegistrationNumber() ).setValue( vehicle );
    }

    public void removeVehicle( Vehicle vehicle ){
        this.DB_RF_STATUS.child( vehicle.getRegistrationNumber() ).removeValue();
    }

}
