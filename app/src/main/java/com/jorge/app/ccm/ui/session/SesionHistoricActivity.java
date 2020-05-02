package com.jorge.app.ccm.ui.session;

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
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSesionsCurrents;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.WindowYesInitSesionVehicle;
import com.jorge.app.ccm.ui.user.User;
import com.jorge.app.ccm.ui.vehicles.VehiclesListActivity;

import java.util.ArrayList;

public class SesionHistoricActivity extends AppCompatActivity {
    private final String TAG = "SesionHistoricActivity";
    private ControllerDBSesionsCurrents controllerDBSesionsCurrents;
    private ControllerDBStatus controllerDBStatus;

    private AdapterSession arrayAdapterSesion;
    private TextView textView;
    private ListView listView;
    private ArrayList<SesionDriving> sesionsDrivings;
    private Intent intentCloseSesion;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sesion_driving );
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_sessions);
        controllerDBSesionsCurrents = new ControllerDBSesionsCurrents( getApplicationContext() );
        controllerDBStatus = new ControllerDBStatus( getApplication() );
        user = new User();

        //Eventos de cambios sobre el adaptador
        controllerDBSesionsCurrents.getDatabaseReference().addChildEventListener( new ChildEventListener() {
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

        intentCloseSesion  = new Intent( SesionHistoricActivity.this, VehiclesListActivity.class );

        //Inizializao Adapter para mostrar lista de sesiones
        arrayAdapterSesion = new AdapterSession( getApplication(), textView, listView);
        // Cargo array adapte
        controllerDBSesionsCurrents.setAdapter( arrayAdapterSesion );
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

              //  final SesionDriving sesionDrivingEnd = new SesionDriving( false, sesionsDrivings.get( position ).getVehicle() );

                WindowYesInitSesionVehicle windowCloseSesionVehicle = new WindowYesInitSesionVehicle( "Desea cerrar sesion" );

                windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                    @Override
                    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                        Log.i( TAG, "SesionDriving seleccionado onclickItem (Valor): --> " + sesionsDrivings.get( position ).getUser().getIdUser() );
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
