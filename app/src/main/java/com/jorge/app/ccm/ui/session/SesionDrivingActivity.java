package com.jorge.app.ccm.ui.session;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSesions;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.WindowYesInitSesionVehicle;
import com.jorge.app.ccm.ui.vehicles.RegistryVehicles;
import com.jorge.app.ccm.ui.vehicles.Vehicle;
import com.jorge.app.ccm.ui.vehicles.VehiclesListActivity;

import java.util.ArrayList;

public class SesionDrivingActivity extends AppCompatActivity{

    private final String TAG = "SesionDrivingActivity";
    private ControllerDBSesions controllerDBSesions;
    private ControllerDBStatus controllerDBStatus;

    private AdapterSession arrayAdapterSesion;
    private TextView textView;
    private ListView listView;
    private ArrayList<SesionDriving> sesionsDrivings;
    private Intent intentCloseSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sesion_driving );
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_sessions);
        controllerDBSesions = new ControllerDBSesions( getApplicationContext() );
        controllerDBStatus = new ControllerDBStatus( getApplication() );
        intentCloseSesion  = new Intent( SesionDrivingActivity.this, VehiclesListActivity.class );


        //Inizializao Adapter para mostrar lista de sesiones
        arrayAdapterSesion = new AdapterSession( getApplication(), textView, listView);
        // Cargo array adapte
        controllerDBSesions.setAdapter( arrayAdapterSesion );
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

                final SesionDriving sesionDrivingEnd = new SesionDriving( false, sesionsDrivings.get( position ).getVehicle() );
                WindowYesInitSesionVehicle windowCloseSesionVehicle = new WindowYesInitSesionVehicle( "Desea cerrar sesion" );

                windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                    @Override
                    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                        Log.i( TAG, "OnclickItem -> sesionDrivingEND -> typeSesion (Valor) : " + sesionDrivingEnd.getTypeSesion() );
                        Log.i( TAG, "OnclickItem -> vehicleSesionDriving -> driving (Valor) : " + sesionDrivingEnd.getVehicle().getDriving() );

                        controllerDBStatus.updateValue( sesionDrivingEnd.getVehicle(), null );
                        controllerDBSesions.updateCurrent( sesionDrivingEnd );
                        controllerDBSesions.endSesion( sesionDrivingEnd );
                        arrayAdapterSesion.getListIntemSesions().clear();//<-- Limpio por si retrocede
                        arrayAdapterSesion.notifyDataSetChanged();//<-- Notifico cambios
                        startActivity( intentCloseSesion );
                        finish();
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
