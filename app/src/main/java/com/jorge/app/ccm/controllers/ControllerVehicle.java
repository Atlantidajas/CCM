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
    private ChildEventListener childEventListener;

    public ControllerVehicle( final Context context ) {
        this.context = context;
        DB_RF = FirebaseDatabase.getInstance().getReference( "VehiclesDB" );
        DB_RF_STATUS = DB_RF.child( "Status" );
        DB_RF_SESIONS = DB_RF.child( "Sesions" );
        DB_RF_STATUS.addChildEventListener( getChildEventListener() );
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

    public ChildEventListener getChildEventListener() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText( context, "Modificado", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText( context, "Eliminado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText( context, "Movido", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
    };
        return childEventListener;
    }
}
