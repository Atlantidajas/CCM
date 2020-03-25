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

                        if ( !resutReferenceCheck.equals( dataSnapshotChildrenValue )) {
                            //Grabado
                            databaseReference.child(referenceCheck).setValue( object );
                            return;
                        }
                        else{
                            System.out.println("Ya existe un registro con esa matrícula <<<< -----------------------------------------------------------------");
                        }
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( databaseError.toException() + "<-----------------------------------------------------------------------" );
            }
        });
    }

    public void readVehicles(   ) {
        //Lamada función buscar vehículos
        DatabaseReference vehicles = controllerVehicles.getVehicle();
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    setArrayAdapter(dataSnapshot);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se han obtenido resultado. Error en consulta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
