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
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.session.AdapterSession;
import com.jorge.app.ccm.ui.session.SesionDriving;
import com.jorge.app.ccm.ui.vehicles.AdapterVehicle;
import com.jorge.app.ccm.ui.vehicles.Vehicle;


public class ControllerVehicle {

    private Context context;
    private final DatabaseReference DB_RF;
    private final DatabaseReference DB_RF_STATUS;
    private final DatabaseReference DB_RF_SESIONS_HISTORIC;
    private final DatabaseReference DB_RF_SESIONS_CURRENT;
    private ValueEventListener valueEventListenerStatus;
    private ChildEventListener childEventListenerStatus;
    private ValueEventListener valueEventListenerSesions;
    private ChildEventListener childEventListenerSesions;

    private int messageOnChildChangedChildEvent = R.string.toast_message_update_generic;
    private int messageOnChildRemovedChildEvent = R.string.toast_message_delete_generic;
    private int messageOnChildMovedChildEvent = R.string.toast_message_moved_generic;

    public ControllerVehicle(final Context context ) {
        this.context = context;
        DB_RF = FirebaseDatabase.getInstance().getReference( "VehiclesDB" );
        DB_RF_STATUS = DB_RF.child( "Status" );
        DB_RF_SESIONS_HISTORIC = DB_RF.child( "SesionsHistoric" );
        DB_RF_SESIONS_CURRENT = DB_RF.child("SesionsCurrents");
        valueEventListenerSesions = null;
        childEventListenerStatus = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText( context, messageOnChildChangedChildEvent, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText( context, messageOnChildRemovedChildEvent, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText( context, messageOnChildMovedChildEvent, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        };
    }

    public DatabaseReference getDB_RF() {
        return DB_RF;
    }

    public DatabaseReference getDB_RF_STATUS() {
        return DB_RF_STATUS;
    }

    public DatabaseReference getDB_RF_SESIONS_HISTORIC() {
        return DB_RF_SESIONS_HISTORIC;
    }

    public DatabaseReference getDB_RF_SESIONS_CURRENT() {
        return DB_RF_SESIONS_CURRENT;
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

    public void setSesion(final SesionDriving sesionDriving ) {
        DatabaseReference databaseReference = DB_RF_SESIONS_CURRENT.child( sesionDriving.getUser().getIdUser() );
        setValueEventListernetSesions( databaseReference, sesionDriving );
        databaseReference.addValueEventListener( valueEventListenerSesions );
    }

    private void setValueEventListernetSesions( DatabaseReference dbRf, final SesionDriving sesionDriving ){

        valueEventListenerSesions =
        dbRf.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    SesionDriving resultSesionCurrent = new SesionDriving( dataSnapshot );

                    // Si la acción que se pretende es iniciar pero ya hay una sesión de este usuario iniciada.
                    if ((resultSesionCurrent.getTypeSesion().equals( "Start" )) && (sesionDriving.getTypeSesion().equals( "Start" ))) {
                        Toast.makeText( context, "Debe cerrar sesión abierta antes de iniciar otra", Toast.LENGTH_SHORT ).show();
                    }
                    // Si está iniciada pero se pretende cerrar
                    if ((resultSesionCurrent.getTypeSesion().equals( "Start" )) && (sesionDriving.getTypeSesion().equals( "End" ))) {
                        DB_RF_SESIONS_HISTORIC.child( sesionDriving.getUser().getIdUser() + "_" +
                                sesionDriving.getDate() + "_" + sesionDriving.getHours() + "_" +
                                sesionDriving.getTypeSesion() ).setValue( sesionDriving );//<-- Cambio a cerrada sesión current
                        DB_RF_STATUS.child( sesionDriving.getVehicle().getRegistrationNumber() ).child( "driving" ).setValue( 0 );//<-- Paso a liberado
                        DB_RF_STATUS.child( sesionDriving.getVehicle().getRegistrationNumber() ).child( "typeSesion" ).setValue( "End" );//<-- Paso a liberado
                    }
                } else {
                    // Si no existe es que no está iniciada
                    DB_RF_SESIONS_CURRENT.child( sesionDriving.getUser().getIdUser() ).setValue( sesionDriving );//<-- Guardo sesion current por si alguien pretende iniciar mientras este en uso
                    DB_RF_STATUS.child( sesionDriving.getVehicle().getRegistrationNumber() ).child( "driving" ).setValue( 1 );//<-- Paso a ocupado
                    DB_RF_SESIONS_HISTORIC.child( sesionDriving.getUser().getIdUser() + "_" +
                            sesionDriving.getDate() + "_" + sesionDriving.getHours() + "_" +
                            sesionDriving.getTypeSesion() ).setValue( sesionDriving );//<-- Guardo sesion current
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        });
    }


    public void destroyValuesEvent(){
        if ( valueEventListenerSesions != null)
        DB_RF_STATUS.removeEventListener( valueEventListenerStatus );
    }

}
