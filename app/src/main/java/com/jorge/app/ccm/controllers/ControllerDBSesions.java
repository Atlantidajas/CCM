package com.jorge.app.ccm.controllers;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.session.AdapterSession;
import com.jorge.app.ccm.ui.session.SesionDriving;


public class ControllerDBSesions {

    private Context context;
    private DatabaseReference databaseReference;

    public ControllerDBSesions( final Context context) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "Sesions" );
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public void setAdapter(final AdapterSession ADAPTER_SESION ){

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

    public void updateCurrent( SesionDriving sesionDriving ){
        databaseReference.child( "SesionsCurrents" ).child( sesionDriving.getUser().getIdUser() ).setValue( sesionDriving );
    }

    public void startSesion( final SesionDriving sesionDriving ) {

        final DatabaseReference dbSesionsCurrent = databaseReference.child( "SesionsCurrents" ).child( sesionDriving.getUser().getIdUser() );
        final DatabaseReference dbSesionsHistoric = databaseReference.child( "SesionsHistorics" );



                // Si la acción que se pretende es iniciar pero ya hay una sesión de este usuario iniciada.

                    dbSesionsHistoric.child( sesionDriving.getUser().getIdUser() + "_" +
                            sesionDriving.getDate() + "_" + sesionDriving.getHours() + "_" +
                            sesionDriving.getTypeSesion() ).setValue( sesionDriving );//<-- Cambio a cerrada sesión current
                    Toast.makeText( context, R.string.toast_message_init_sesion, Toast.LENGTH_SHORT ).show();


    }

    public void endSesion( final SesionDriving sesionDriving ) {

        //DatabaseReference dbSesionsCurrent = databaseReference.child( "SesionsCurrents" ).child( sesionDriving.getUser().getIdUser() );
        final DatabaseReference dbSesionsHistoric = databaseReference.child( "SesionsHistorics" );

        dbSesionsHistoric.child( sesionDriving.getUser().getIdUser() + "_" +
                sesionDriving.getDate() + "_" + sesionDriving.getHours() + "_" +
                sesionDriving.getTypeSesion() ).setValue( sesionDriving );//<-- Cambio a cerrada sesión current
        Toast.makeText( context, R.string.toast_message_close_sesion, Toast.LENGTH_SHORT ).show();

    }
}