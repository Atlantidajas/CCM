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
import com.jorge.app.ccm.ui.sessionCrurrent.AdapterSessionCurrent;
import com.jorge.app.ccm.models.SesionDriving;

public class ControllerDBSesionsCurrents {

    private Context context;
    private DatabaseReference databaseReference;

    public ControllerDBSesionsCurrents(final Context context) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "Sesions" ).child( "Currents" );
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public void setValue( final SesionDriving sesionDriving ){
        final DatabaseReference dbRF = databaseReference.child( sesionDriving.getUser().getIdUser() );

        final ValueEventListener valueEventListenerSetVehicle = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if( dataSnapshot.exists()){

                }
                else {
                    dbRF.setValue( sesionDriving );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        };
        dbRF.addValueEventListener( valueEventListenerSetVehicle );
    }

    public void removeValue(final SesionDriving sesionDriving, String messageOnChildRemoved ){
        DatabaseReference dbRF = databaseReference.child( sesionDriving.getUser().getIdUser() );
        dbRF.addChildEventListener( setChildEventListener(null, messageOnChildRemoved, null ) );
        dbRF.removeValue();
    }

    public void updateValue( final SesionDriving sesionDriving, String messageOnChildChanged  ){
        DatabaseReference dbRF = databaseReference.child( sesionDriving.getUser().getIdUser() );
        dbRF.addChildEventListener( setChildEventListener( messageOnChildChanged, null, null ) );
        dbRF.setValue( sesionDriving );
    }

    public void setAdapter(final AdapterSessionCurrent ADAPTER_SESION ){

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    ADAPTER_SESION.setArrayAdapterSessionCurrent( dataSnapshot );
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

    public ChildEventListener setChildEventListener(final String messageOnChildChanged,
                                                    final String messageOnChildRemoved,
                                                    final String messageOnChildMoved ) {
        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if( messageOnChildChanged != null ){
                    Toast.makeText( context, messageOnChildChanged, Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                if( messageOnChildRemoved != null ) {
                    Toast.makeText( context, messageOnChildRemoved, Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if( messageOnChildMoved != null ) {
                    Toast.makeText( context, messageOnChildMoved, Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        };
        return childEventListener;
    }

    public DatabaseReference getDatabaseReferenceSearch( SesionDriving sesionDriving){
        return databaseReference.child( sesionDriving.getUser().getIdUser() );
    }

}