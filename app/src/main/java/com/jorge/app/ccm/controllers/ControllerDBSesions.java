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
import com.jorge.app.ccm.ui.user.User;
import com.jorge.app.ccm.ui.vehicles.AdapterVehicle;

public class ControllerDBSesions {

    private Context context;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private ValueEventListener valueEventListenerSetAdapter;

    private int messageOnChildChangedChildEvent = R.string.toast_message_update_generic;
    private int messageOnChildRemovedChildEvent = R.string.toast_message_delete_generic;
    private int messageOnChildMovedChildEvent = R.string.toast_message_moved_generic;


    //Para usar Value Event
    public ControllerDBSesions( final Context context) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "Sesions" );
        this.childEventListener = new ChildEventListener() {
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
        //this.databaseReference.addChildEventListener( childEventListener );//<-- General para cualquier operación (Set, Update, delete)
    }

    public void setMessageOnChildChangedChildEvent(int messageOnChildChangedChildEvent) {
        this.messageOnChildChangedChildEvent = messageOnChildChangedChildEvent;
    }

    public void setMessageOnChildRemovedChildEvent(int messageOnChildRemovedChildEvent) {
        this.messageOnChildRemovedChildEvent = messageOnChildRemovedChildEvent;
    }

    public void setMessageOnChildMovedChildEvent(int messageOnChildMovedChildEvent) {
        this.messageOnChildMovedChildEvent = messageOnChildMovedChildEvent;
    }

    public void setAdapter( final AdapterSession ADAPTER_SESION ){

        databaseReference.child( "SesionsHistorics" ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    ADAPTER_SESION.setArrayAdapter( dataSnapshot );
                }
                else {
                    Toast.makeText( context, R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setSesion( final SesionDriving sesionDriving ) {

        final  DatabaseReference dbSesionsCurrent = databaseReference.child( "SesionsCurrents" ).child( sesionDriving.getUser().getIdUser() );
        final  DatabaseReference dbSesionsHistoric = databaseReference.child( "SesionsHistorics" );

        dbSesionsCurrent.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ControllerDBStatus controllerDBStatus = new ControllerDBStatus( context, sesionDriving.getVehicle().getRegistrationNumber() );
                DatabaseReference dbRfStatus = controllerDBStatus.getDatabaseReference();

                if (dataSnapshot.exists()) {

                    SesionDriving resultSesionCurrent = new SesionDriving( dataSnapshot );


                    // Si la acción que se pretende es iniciar pero ya hay una sesión de este usuario iniciada.
                    if ((resultSesionCurrent.getTypeSesion().equals( "Start" )) && (sesionDriving.getTypeSesion().equals( "Start" ))) {
                        Toast.makeText( context, "Debe cerrar sesión abierta antes de iniciar otra", Toast.LENGTH_SHORT ).show();
                        return;
                    }
                    // Si está iniciada pero se pretende cerrar
                    if (( resultSesionCurrent.getTypeSesion().equals( "Start" )) && (sesionDriving.getTypeSesion().equals( "End" ))) {
                        dbSesionsHistoric.child( sesionDriving.getUser().getIdUser() + "_" +
                                sesionDriving.getDate() + "_" + sesionDriving.getHours() + "_" +
                                sesionDriving.getTypeSesion() ).setValue( sesionDriving );//<-- Cambio a cerrada sesión current
                        dbRfStatus.child( "driving" ).setValue( 0 );//<-- Paso a liberado
                        dbRfStatus.child( "typeSesion" ).setValue( "End" );//<-- Paso a liberado
                    }
                } else {
                    // Si no existe es que no está iniciada
                    dbSesionsCurrent.child( sesionDriving.getUser().getIdUser() ).setValue( sesionDriving );//<-- Guardo sesion current por si alguien pretende iniciar mientras este en uso
                    dbRfStatus.child( "driving" ).setValue( 1 );//<-- Paso a ocupado
                    dbSesionsHistoric.child( sesionDriving.getUser().getIdUser() + "_" +
                            sesionDriving.getDate() + "_" + sesionDriving.getHours() + "_" +
                            sesionDriving.getTypeSesion() ).setValue( sesionDriving );//<-- Guardo sesion current
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
