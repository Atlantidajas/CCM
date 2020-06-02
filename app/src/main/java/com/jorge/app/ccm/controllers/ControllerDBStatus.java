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
import com.jorge.app.ccm.models.Vehicle;

public class ControllerDBStatus {

    private String TAG;
    private Context context;
    private DatabaseReference databaseReferenceStatus;
    private int countChild;

    public ControllerDBStatus( final Context context, String TAG ) {
        this.context = context;
        this.TAG = TAG;
        this.databaseReferenceStatus = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "Status" );
    }

    public void setStatusVehicle( final Vehicle vehicle ){
        final DatabaseReference dbRF = databaseReferenceStatus.child( vehicle.getVehicleRegistrationNumber() );

        final ValueEventListener valueEventListenerSetVehicle = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    dbRF.setValue( vehicle );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        };
        dbRF.addValueEventListener( valueEventListenerSetVehicle );
    }

    public void removeStatusVehicle(final Vehicle vehicle, String messageOnChildRemoved ){
        DatabaseReference dbRF = databaseReferenceStatus.child( vehicle.getVehicleRegistrationNumber() );
        dbRF.addChildEventListener( setChildEventListenerStatus(null, messageOnChildRemoved, null ) );
        dbRF.removeValue();
    }

    public void updateStatusVehicle( final Vehicle vehicle, String messageOnChildChanged  ){
        DatabaseReference dbRF = databaseReferenceStatus.child( vehicle.getVehicleRegistrationNumber() );
        dbRF.addChildEventListener( setChildEventListenerStatus( messageOnChildChanged, null, null ) );
        dbRF.setValue( vehicle );
    }

    public int getCountChildStatus(){

        databaseReferenceStatus.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if ( dataSnapshot.exists() ){
                    countChild = (int) dataSnapshot.getChildrenCount();
                }
                else{
                    countChild = 0;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if ( dataSnapshot.exists() ){
                    countChild = (int) dataSnapshot.getChildrenCount();
                }
                else{
                    countChild = 0;
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists() ){
                    countChild = (int) dataSnapshot.getChildrenCount();
                }
                else{
                    countChild = 0;
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if ( dataSnapshot.exists() ){
                    countChild = (int) dataSnapshot.getChildrenCount();
                }
                else{
                    countChild = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
        return this.countChild;
    }

    public DatabaseReference getDatabaseReferenceStatusSearch( Vehicle vehicle){
        return databaseReferenceStatus.child( vehicle.getVehicleRegistrationNumber() );
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReferenceStatus;
    }


    public ChildEventListener setChildEventListenerStatus( final String messageOnChildChanged,
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
