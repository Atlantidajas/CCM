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
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.vehicles.AdapterVehicle;

public class ControllerDBStatus {

    private Context context;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private ValueEventListener valueEventListenerSetAdapter;

    private int messageOnChildChangedChildEvent = R.string.toast_message_update_generic;
    private int messageOnChildRemovedChildEvent = R.string.toast_message_delete_generic;
    private int messageOnChildMovedChildEvent = R.string.toast_message_moved_generic;

    public ControllerDBStatus( final Context context, String nameDataBaseReerence) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "Status" ).child( nameDataBaseReerence );
        this.childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText( context, messageOnChildChangedChildEvent, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText( context, messageOnChildRemovedChildEvent, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText( context, messageOnChildMovedChildEvent, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        };
        this.databaseReference.addChildEventListener( childEventListener );//<-- General para cualquier operación (Set, Update, delete)
    }

    //Para usar Value Event
    public ControllerDBStatus( final Context context) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" ).child( "Status" );
    }

    public void setMessageOnChildChangedChildEvent(int messageOnChildChangedChildEvent) {
        this.messageOnChildChangedChildEvent = messageOnChildChangedChildEvent;
    }

    public void setMessageOnChildRemovedChildEvent(int messageOnChildRemovedChildEvent) {
        this.messageOnChildRemovedChildEvent = messageOnChildRemovedChildEvent;
    }

    public void setMessageOnChildMovedChildEvent(int messageOnChildMovedChildEvent) {
        this.messageOnChildMovedChildEvent = messageOnChildMovedChildEvent;
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

    public void setValue(Object object ){
        databaseReference.setValue( object );
    }

    public void removeValue(){
        databaseReference.removeValue();
    }

    public void updateValue( Object object  ){
        databaseReference.setValue( object );
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

}
