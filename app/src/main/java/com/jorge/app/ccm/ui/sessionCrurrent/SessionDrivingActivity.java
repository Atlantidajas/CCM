package com.jorge.app.ccm.ui.sessionCrurrent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSessionsCurrents;
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.models.Session;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.ui.vehicleStatus.VehiclesListActivity;

import java.util.ArrayList;

public class SessionDrivingActivity extends AppCompatActivity{

    private final String TAG = "SesionDrivingActivity";
    private ControllerDBSessionsCurrents controllerDBSessionsCurrents;
    private ControllerDBStatus controllerDBStatus;
    private ControllerDBSessionsHistoric controllerDBSessionsHistoric;

    private AdapterSessionCurrent arrayAdapterSesion;
    private TextView textView;
    private ListView listView;
    private ArrayList<SessionDriving> sesionsDrivings;
    private Intent intentCloseSesion;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sesion_driving );
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_sessions);
        controllerDBSessionsCurrents = new ControllerDBSessionsCurrents( getApplicationContext() );
        controllerDBStatus = new ControllerDBStatus( getApplication() );
        controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext() );
        user = new User();

        //Eventos de cambios sobre el adaptador
        controllerDBSessionsCurrents.getDatabaseReference().addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapterSesion.getListIntemSesions().clear();
                arrayAdapterSesion.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapterSesion.getListIntemSesions().clear();
                arrayAdapterSesion.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                arrayAdapterSesion.getListIntemSesions().clear();
                arrayAdapterSesion.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapterSesion.getListIntemSesions().clear();
                arrayAdapterSesion.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        intentCloseSesion  = new Intent( SessionDrivingActivity.this, VehiclesListActivity.class );

        //Inizializao Adapter para mostrar lista de sesiones
        arrayAdapterSesion = new AdapterSessionCurrent( getApplication(), textView, listView);
        // Cargo array adapte
        setAdapter( arrayAdapterSesion );
        sesionsDrivings = arrayAdapterSesion.getListIntemSesions();

    }

    @Override
    public void onStart() {
        super.onStart();
        onclickItemList();
    }


    public void onclickItemList(){
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    final int position, long id) {

                //final SesionDriving sesionDrivingEnd = new SesionDriving( false, sesionsDrivings.get( position ).getVehicle() );

                WindowDialogFragment windowCloseSesionVehicle = new WindowDialogFragment( "Desea cerrar sesion" );

                windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                    @Override
                    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                        Session sessionEnd = new Session( "End" );

                        Vehicle vehicleSelect = sesionsDrivings.get( position ).getVehicle();

                        final SessionDriving sessionDriving = new SessionDriving( sessionEnd, user,  vehicleSelect );

                        Log.i( TAG, "SesionDriving seleccionado onclickItem (Valor): --> " + sesionsDrivings.get( position ).getUser().getIdUser() );
                        Log.i( TAG, "id usuario en uso (Valor): --> " + user.getIdUser() );

                        //Controlo que sea el usuario en uso el que cierre su sesion abierta, no la de otro.
                        //Condicion 1
                        if (sesionsDrivings.get( position ).getUser().getIdUser().equals( user.getIdUser() ) ) {
                            controllerDBStatus.updateValue( sessionDriving.getVehicle(), null );
                            controllerDBSessionsCurrents.updateValue( sessionDriving, "Ha cerrado sesión" );
                            controllerDBSessionsHistoric.setValue( sessionDriving );
                            startActivity( intentCloseSesion );
                        }
                        else {
                            Toast.makeText( getApplicationContext(), R.string.toast_message_logout_error, Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                        return;
                    }
                } );

                windowCloseSesionVehicle.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );
            }
        });
    }

    public void setAdapter(final AdapterSessionCurrent ADAPTER_SESION ){

        this.controllerDBSessionsCurrents.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    ADAPTER_SESION.setArrayAdapterSessionCurrent( dataSnapshot );
                }
                else {
                    Toast.makeText( getApplicationContext(), R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
