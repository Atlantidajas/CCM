package com.jorge.app.ccm.controllers;

import android.content.Context;
import android.util.Log;
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
import com.jorge.app.ccm.models.Vehicle;

public class ControllerDBStatus  {

    private String TAG;
    private Context context;
    private DatabaseReference databaseReferenceStatus;
    private int countChild;

    public ControllerDBStatus( final Context context, String TAG ) {
        this.context = context;
        this.TAG = TAG;
        this.databaseReferenceStatus = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "Status" );

        databaseReferenceStatus.keepSynced(true);
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

    public boolean removeStatusVehicle(final Vehicle vehicle ){

        if( vehicle.getVehicleDriving() == 0 ){
            Log.i( TAG, "OncontextItemSeled() -> Delete -> VegicleSelect -> Driving -> (Valor)" + vehicle.getVehicleDriving() );
            DatabaseReference dbRF = databaseReferenceStatus.child( vehicle.getVehicleRegistrationNumber() );
            //dbRF.addChildEventListener( setChildEventListenerStatus(0, R.string.toast_message_removed_vehicle_generic, 0 ) );
            dbRF.removeValue();
            return true;

        }
        else{
            Log.i( TAG, "OncontextItemSeled() -> Delete -> VegicleSelect -> Driving -> (Valor)" + vehicle.getVehicleDriving() );
            return false;
        }
    }



    public boolean updateStatusVehicle( final Vehicle vehicle  ){

        if( vehicle.getVehicleDriving() == 0 ){
            Log.i( TAG, "OncontextItemSeled() -> Delete -> VegicleSelect -> Driving -> (Valor)" + vehicle.getVehicleDriving() );
            DatabaseReference dbRF = databaseReferenceStatus.child( vehicle.getVehicleRegistrationNumber() );
            dbRF.addChildEventListener( setChildEventListenerStatus( R.string.toast_message_changed_vehicle_generic, 0, 0 ) );
            dbRF.setValue( vehicle );


            return true;
        }
        else {
            Log.i( TAG, "OncontextItemSeled() -> Delete -> VegicleSelect -> Driving -> (Valor)" + vehicle.getVehicleDriving() );
            return false;
        }
    }

    public boolean updateStatusVehicle( final Vehicle vehicle, String messageOnChildChanged  ){

        if( vehicle.getVehicleDriving() == 0 ) {
            Log.i( TAG, "OncontextItemSeled() -> Delete -> VegicleSelect -> Driving -> (Valor)" + vehicle.getVehicleDriving() );
            DatabaseReference dbRF = databaseReferenceStatus.child( vehicle.getVehicleRegistrationNumber() );
            dbRF.addChildEventListener( setChildEventListenerStatus( messageOnChildChanged, null, null ) );
            dbRF.setValue( vehicle );
            return true;
        }
        else {
            Log.i( TAG, "OncontextItemSeled() -> Delete -> VegicleSelect -> Driving -> (Valor)" + vehicle.getVehicleDriving() );
            return false;
        }
    }

    //Solo para registro de historicos, no tiene en cuenta si el vehículo está en uso.
    protected void updateStatusVehicleForRegistrisHistoric( final Vehicle vehicle ){
        DatabaseReference dbRF = databaseReferenceStatus.child( vehicle.getVehicleRegistrationNumber() );
        dbRF.addChildEventListener( setChildEventListenerStatus( null, null, null ) );
        dbRF.setValue( vehicle );
        Log.i( TAG, "OncontextItemSeled() -> Delete -> VegicleSelect -> Driving -> (Valor)" + vehicle.getVehicleDriving() );
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

    public ChildEventListener setChildEventListenerStatus( final int messageOnChildChanged,
                                                           final int messageOnChildRemoved,
                                                           final int messageOnChildMoved ) {
        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if( messageOnChildChanged != 0 ){
                    Toast.makeText( context, messageOnChildChanged, Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                if( messageOnChildRemoved != 0 ) {
                    Toast.makeText( context, messageOnChildRemoved, Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if( messageOnChildMoved != 0 ) {
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
