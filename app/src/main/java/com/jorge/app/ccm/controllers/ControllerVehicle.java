package com.jorge.app.ccm.controllers;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jorge.app.ccm.ui.session.SesionDriving;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

public class ControllerVehicle {

    private Context context;
    private final DatabaseReference DB_RF;
    private final DatabaseReference DB_RF_STATUS;
    private final DatabaseReference DB_RF_SESIONS;

    public ControllerVehicle( final Context context ) {
        this.context = context;
        DB_RF = FirebaseDatabase.getInstance().getReference( "VehiclesDB" );
        DB_RF_STATUS = DB_RF.child( "Status" );
        DB_RF_SESIONS = DB_RF.child( "Sesions" );
    }

    public DatabaseReference getDB_RF() {
        return DB_RF;
    }

    public DatabaseReference getDB_RF_STATUS() {
        return DB_RF_STATUS;
    }

    public DatabaseReference getDB_RF_SESIONS() {
        return DB_RF_SESIONS;
    }

    public void setVehicle(Vehicle vehicle ){
        this.DB_RF_STATUS.child( vehicle.getRegistrationNumber() ).setValue( vehicle );
    }

    public void removeVehicle( Vehicle vehicle ){
        this.DB_RF_STATUS.child( vehicle.getRegistrationNumber() ).removeValue();
    }

    public void updateVehicle( Vehicle vehicle ){
        this.DB_RF_STATUS.child( vehicle.getRegistrationNumber() ).setValue( vehicle );
    }

    public void setSesion(SesionDriving sesionDriving){
        this.DB_RF_SESIONS.child( sesionDriving.getVehicle().getRegistrationNumber() ).setValue( sesionDriving );
    }


}
