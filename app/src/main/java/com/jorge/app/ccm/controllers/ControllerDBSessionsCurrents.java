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
import com.jorge.app.ccm.models.SessionDriving;

public class ControllerDBSessionsCurrents extends ControllerDBStatus{

    private String TAG;
    private Context context;
    private DatabaseReference databaseReferenceSessionsCurrents;

    protected ControllerDBSessionsCurrents(Context context, String TAG) {
        super( context, TAG );
        this.context = context;
        this.TAG = TAG;
        this.databaseReferenceSessionsCurrents = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "SessionsCurrents" );
    }

    public DatabaseReference getDatabaseReferenceSessionsCurrents() {
        return databaseReferenceSessionsCurrents;
    }

    protected void setValueSessionsCurrents( final SessionDriving sessionDriving){
        final DatabaseReference dbRF = databaseReferenceSessionsCurrents.child( sessionDriving.getIdUser() );

        final ValueEventListener valueEventListenerSetVehicle = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if( dataSnapshot.exists()){

                }
                else {
                    dbRF.setValue( sessionDriving );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        };
        dbRF.addValueEventListener( valueEventListenerSetVehicle );
    }

    protected void removeValueSessionsCurrents(final SessionDriving sessionDriving, String messageOnChildRemoved ){
        DatabaseReference dbRF = databaseReferenceSessionsCurrents.child( sessionDriving.getIdUser() );
        dbRF.addChildEventListener( setChildEventListenerSessionsCurrents(null, messageOnChildRemoved, null ) );
        dbRF.removeValue();
    }

    protected void updateValueSessionsCurrents(final SessionDriving sessionDriving, String messageOnChildChanged  ){
        DatabaseReference dbRF = databaseReferenceSessionsCurrents.child( sessionDriving.getIdUser() );
        dbRF.addChildEventListener( setChildEventListenerSessionsCurrents( messageOnChildChanged, null, null ) );
        dbRF.setValue( sessionDriving );
    }


    protected ChildEventListener setChildEventListenerSessionsCurrents(final String messageOnChildChanged,
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

    public DatabaseReference getDatabaseReferenceSessionsCurrentsSearch( SessionDriving sessionDriving){
        return databaseReferenceSessionsCurrents.child( sessionDriving.getIdUser() );
    }

}