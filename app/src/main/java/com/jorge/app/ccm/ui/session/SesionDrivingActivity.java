package com.jorge.app.ccm.ui.session;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSesions;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.WindowYesInitSesionVehicle;
import com.jorge.app.ccm.ui.vehicles.VehiclesListActivity;

import java.util.ArrayList;

public class SesionDrivingActivity extends AppCompatActivity{

    private ControllerDBSesions controllerDBSesions;

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
        intentCloseSesion  = new Intent( SesionDrivingActivity.this, VehiclesListActivity.class );
        controllerDBSesions = new ControllerDBSesions( getApplicationContext() );


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
            public void onItemClick( AdapterView<?> lst, View viewRow,
                                    int position, long id) {

                final SesionDriving sesionDrivingEnd = new SesionDriving( false, sesionsDrivings.get( position ).getVehicle() );
                WindowYesInitSesionVehicle windowCloseSesionVehicle = new WindowYesInitSesionVehicle( "Desea cerrar sesion" );
                windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                    @Override
                    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                        controllerDBSesions.endSesion( sesionDrivingEnd );

                        arrayAdapterSesion.getListIntemSesions().clear();//<-- Limpio por si retrosede
                        arrayAdapterSesion.notifyDataSetChanged();//<-- Notifico cambios
                        startActivity( intentCloseSesion );
                        finish();
                    }

                    @Override
                    public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                        return;
                    }
                } );
                windowCloseSesionVehicle.getDialogFragmentNotice().show( getSupportFragmentManager(), "windowCloseSesionVehicle" );


            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        controllerDBSesions = null;
    }

}
