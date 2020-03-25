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

                if (dataSnapshot.exists()) {
                    String resutReferenceCheck = String.valueOf( dataSnapshot.child(referenceCheck).getValue() );
                    if (dataSnapshot.child(referenceCheck).exists() ) {
                        String dataSnapshotChildrenValue = dataSnapshot.child(referenceCheck).getValue().toString();
                        System.out.println("dataSnapshot valor" + dataSnapshot.child(referenceCheck) +  "<------------------------------------" );
                        System.out.println("ResultReferenceCheck valor" + resutReferenceCheck + "<-----------------------------------------------------------------------");

                        if (resutReferenceCheck.equals( dataSnapshotChildrenValue )) {
                            System.out.println("Ya existe un resultado con ese valor" + "<----------------------------------------------------");
                        }
                    }
                    else{
                        System.out.println("Puede grabar" + "<------------------------------------------------------------------------------------");
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
