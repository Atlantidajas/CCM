package com.jorge.app.ccm.controllers;

import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Controller {

    private DatabaseReference databaseReference;


    public Controller( String reference ) {
        databaseReference = FirebaseDatabase.getInstance().getReference( reference );
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    /*
     * @Autor Jorge H
     * Comprueba si ya exixte un resultado por medio de la referenncia
     * */
    public void writeNewRegistry(final String referenceCheck, final Object object ) {

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    String resutReferenceCheck = String.valueOf( dataSnapshot.child(referenceCheck).getValue() );
                    if (dataSnapshot.child(referenceCheck).exists() ) {
                        String dataSnapshotChildrenValue = dataSnapshot.child(referenceCheck).getValue().toString();

                        if ( resutReferenceCheck.equals( dataSnapshotChildrenValue )) {
                            System.out.println("Ya existe ---------------------------------");
                            return;
                        }
                    }
                    else{
                        //Grabado
                        System.out.println("Datos guardados ----------------------------------");
                        databaseReference.child(referenceCheck).setValue( object );
                        return;
                    }
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( databaseError.toException() + "<-----------------------------------------------------------------------" );
            }
        });
        return;
    }


}
