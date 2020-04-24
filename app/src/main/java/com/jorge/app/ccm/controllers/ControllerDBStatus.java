package com.jorge.app.ccm.controllers;

import android.content.Context;
import android.content.res.Resources;
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
import com.jorge.app.ccm.ui.vehicles.AdapterVehicle;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

import static android.provider.Settings.System.getString;

public class ControllerDBStatus {

    private Context context;
    private DatabaseReference databaseReference;

    public ControllerDBStatus( final Context context ) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "Status" );
    }

    public void setAdapter( final AdapterVehicle ADAPTER_VEHICLE ){

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ADAPTER_VEHICLE.setArrayAdapter(dataSnapshot);
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

    public void setValue( final Vehicle vehicle ){
        DatabaseReference dbRF = databaseReference.child( vehicle.getRegistrationNumber() );
        dbRF.setValue( vehicle );
    }

    public void removeValue(final Vehicle vehicle, String messageOnChildRemoved ){
        DatabaseReference dbRF = databaseReference.child( vehicle.getRegistrationNumber() );
        dbRF.addChildEventListener( setChildEventListener(null, messageOnChildRemoved, null ) );
        dbRF.removeValue();
    }

    public void updateValue( final Vehicle vehicle, String messageOnChildChanged  ){
        DatabaseReference dbRF = databaseReference.child( vehicle.getRegistrationNumber() );
        dbRF.addChildEventListener( setChildEventListener( messageOnChildChanged, null, null ) );
        dbRF.setValue( vehicle );
    }

    public DatabaseReference getDatabaseReferenceSearch( Vehicle vehicle){
        return databaseReference.child( vehicle.getRegistrationNumber() );
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }


    public ChildEventListener setChildEventListener( final String messageOnChildChanged,
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
}
