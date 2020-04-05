package com.jorge.app.ccm.controllers;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Controller {

    private DatabaseReference databaseReference;
    private DatabaseReference childReference;


    public Controller(String referenceChild) {
        databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" );
        childReference = databaseReference.child( referenceChild );
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public DatabaseReference getChildReference() {
        return childReference;
    }

    /*
     * @Autor Jorge H
     * Comprueba si ya exixte un resultado por medio de la referenncia
     * */
    public void writeNewRegistry(final String referenceCheck, final Object object ) {

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if (dataSnapshot.exists()) {
                    String resutReferenceCheck = String.valueOf( dataSnapshot.child(referenceCheck).getValue() );
                    if (dataSnapshot.child(referenceCheck).exists() ) {
                        String dataSnapshotChildrenValue = dataSnapshot.child(referenceCheck).getValue().toString();

                        if ( resutReferenceCheck.equals( dataSnapshotChildrenValue )) {
                            return;
                        }
                    }
                    else{
                        //Grabado
                        childReference.child(referenceCheck).setValue( object );
                        return;
                    }
               // }



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( databaseError.toException() + "<-----------------------------------------------------------------------" );
            }
        });
        return;
    }


}
