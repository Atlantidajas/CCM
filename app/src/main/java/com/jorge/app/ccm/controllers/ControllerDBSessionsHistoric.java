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
import com.jorge.app.ccm.models.Session;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.models.User;

public class ControllerDBSessionsHistoric extends ControllerDBSessionsCurrents {

    private String TAG;
    private Context context;
    private DatabaseReference databaseReference;

    public ControllerDBSessionsHistoric(Context context, String TAG) {
        super( context, TAG );
        this.context = context;
        this.TAG = TAG;
        this.databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "SessionsHistorics" );
    }

    public DatabaseReference getDatabaseReferenceSessionsHistoric() {
        return databaseReference;
    }

    public void setValueSessionsHistoric( final SessionDriving sessionDriving){

        final DatabaseReference dbRF = databaseReference.push();

        final ValueEventListener valueEventListenerSetVehicle = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
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

    public ChildEventListener setChildEventListenerSessionsHistoric(final String messageOnChildChanged,
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

    public DatabaseReference getDatabaseReferenceSessionsHistoricSearch( SessionDriving sessionDriving){
        return databaseReference.child( sessionDriving.getIdUser() );
    }

    public void registrySessionHistoric( SessionDriving sessionDriving ){
        this.updateValueSessionsCurrents( sessionDriving, null );
        this.updateStatusVehicleForRegistrisHistoric( sessionDriving.getVehicle() );
        this.setValueSessionsHistoric( sessionDriving );
    }

    public void onSesionDrinvingCreate(){

        //Sesion de inicio por si es la primera ves que inicia sesión
        Session sessionCreate = new Session( "Create" );
        User user = new User( true );
        SessionDriving sessionDrivingCreate = new SessionDriving( sessionCreate, user );
        this.setValueSessionsCurrents( sessionDrivingCreate );
    }
}
