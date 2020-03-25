package com.jorge.app.ccm.controllers;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Controller {

    private DatabaseReference databaseReference;

    public Controller( String reference ) {
        databaseReference = FirebaseDatabase.getInstance().getReference( reference );
    }

    public void chekRegistration(final String referenceCheck ) {

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String resut = String.valueOf( dataSnapshot.child(referenceCheck).getValue() );
                if (dataSnapshot.exists()) {
                    System.out.println("dataSnapshot valor" + dataSnapshot.child(referenceCheck) +  "<-----------------------------------------------------------------------" );
                    System.out.println("Result valor" + resut +  "<-----------------------------------------------------------------------" );
                    if( resut.equals( dataSnapshot.child(referenceCheck)) ){
                        System.out.println("Ya existe un resultado con ese valor" + "<-----------------------------------------------------------------------" );
                    }
                    else if ( !resut.equals( dataSnapshot.child(referenceCheck))) {
                        System.out.println("Puede proceder al grabado de datos" + "<-----------------------------------------------------------------------" );
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( databaseError.toException() + "<-----------------------------------------------------------------------" );
            }
        });
    }

}
