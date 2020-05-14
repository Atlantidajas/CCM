package com.jorge.app.ccm.ui.sessionHistoric;

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
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.ui.vehicleStatus.VehiclesListActivity;

import java.util.ArrayList;

public class SessionHistoricActivity extends AppCompatActivity {

    private final String TAG = "SesionHistoricActivity";
    private ControllerDBSessionsHistoric controllerDBSessionsHistoric;
    private AdapterSessionHistoric adapterSessionHistoric;
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
        controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext() );

        user = new User();

        //Eventos de cambios sobre el adaptador
        controllerDBSessionsHistoric.getDatabaseReference().addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapterSessionHistoric.getListIntemSesions().clear();
                adapterSessionHistoric.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapterSessionHistoric.getListIntemSesions().clear();
                adapterSessionHistoric.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapterSessionHistoric.getListIntemSesions().clear();
                adapterSessionHistoric.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapterSessionHistoric.getListIntemSesions().clear();
                adapterSessionHistoric.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        intentCloseSesion  = new Intent( SessionHistoricActivity.this, VehiclesListActivity.class );

        //Inizializao Adapter para mostrar lista de sesiones
        adapterSessionHistoric = new AdapterSessionHistoric( getApplication(), textView, listView);
        // Cargo array adapte
        setAdapter( adapterSessionHistoric  );
        sesionsDrivings = adapterSessionHistoric.getListIntemSesions();

    }

    public void setAdapter(final AdapterSessionHistoric ADAPTER_SESION ){

        controllerDBSessionsHistoric.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    ADAPTER_SESION.setArrayAdapterHistoric( dataSnapshot );
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

              //  final SesionDriving sesionDrivingEnd = new SesionDriving( false, sesionsDrivings.get( position ).getVehicle() );

                WindowDialogFragment windowCloseSesionVehicle = new WindowDialogFragment( "Desea cerrar sesion" );

                windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                    @Override
                    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                        User userSessionDriving = sesionsDrivings.get( position ).getUser();

                        Log.i( TAG, "SesionDriving seleccionado onclickItem (Valor): --> " + userSessionDriving.getIdUser() );
                        Log.i( TAG, "id usuario en uso (Valor): --> " + user.getIdUser() );

                        //Controlo que sea el usuario en uso el que cierre su sesion abierta, no la de otro.
                        //Condicion 1
                        if (sesionsDrivings.get( position ).getUser().getIdUser().equals( user.getIdUser() ) ) {

                    //        Log.i( TAG, "Condicion 1: OnclickItem -> sesionDrivingEND -> typeSesion (Valor) -->: " + sesionDrivingEnd.getTypeSesion() );
                    //        Log.i( TAG, "Condicion 1: OnclickItem -> vehicleSesionDriving -> driving (Valor) -->: " + sesionDrivingEnd.getVehicle().getDriving() );

                         //   controllerDBStatus.updateValue( sesionDrivingEnd.getVehicle(), null );
                           // controllerDBSesionsCurrents.updateCurrent( sesionDrivingEnd );
                            //controllerDBSesionsCurrents.endSesion( sesionDrivingEnd );
                            startActivity( intentCloseSesion );
                            finish();
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

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
