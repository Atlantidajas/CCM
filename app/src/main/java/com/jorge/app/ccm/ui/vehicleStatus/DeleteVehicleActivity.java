package com.jorge.app.ccm.ui.vehicleStatus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.models.Vehicle;

import static com.jorge.app.ccm.ui.vehicleStatus.VehiclesStatusListActivity.REQUEST_INTENT_VEHICLE_FOR_DELETE_VEHICLE;
import static com.jorge.app.ccm.ui.vehicleStatus.VehiclesStatusListActivity.VEHICLE_FOR_DELETE_VEHICLE;
import static java.lang.Thread.sleep;

public class DeleteVehicleActivity extends AppCompatActivity implements Runnable{

    private final String TAG = "DeleteVehicleActivity";
    private ImageView imageViewAlert;
    private Intent intentVehicleList;
    private Button buttonDeleteCancelVehicle;
    private Button buttonDeleteAcceptVehicle;
    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_delete_vehicle );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//<-- añado flecha retroseso

        imageViewAlert = findViewById( R.id.image_alert_delete_vehicle );
        buttonDeleteCancelVehicle = findViewById( R.id.buttonDeleteCancelVehicle );
        buttonDeleteAcceptVehicle = findViewById( R.id.buttonDeleteAcceptVehicle );
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.progress_dialog_delete_vehicle));
        mProgressDialog.setIndeterminate(true);

        onActivityResult( REQUEST_INTENT_VEHICLE_FOR_DELETE_VEHICLE, RESULT_OK, getIntent() );
        loadFieldButtonCancelExpenses();

        intentVehicleList = new Intent( DeleteVehicleActivity.this, VehiclesStatusListActivity.class );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == android.R.id.home ){
            finish();
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }


    /*
     * Guardo comprueba la correcta recepción de los datos recibidos por medio de Items, cargo los campos
     */
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if ( requestCode == REQUEST_INTENT_VEHICLE_FOR_DELETE_VEHICLE  && resultCode  == RESULT_OK ) {

                Bundle objetIn = data.getExtras();
                Vehicle vehicleSelect = null;


                vehicleSelect = (Vehicle) objetIn.getSerializable(VEHICLE_FOR_DELETE_VEHICLE);

                loadButtonDeleteVehicle( vehicleSelect );

            }

        } catch (Exception ex) {
            Toast.makeText( DeleteVehicleActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void loadButtonDeleteVehicle( final Vehicle vehicle ){


        final ControllerDBStatus controllerDBStatus = new ControllerDBStatus( getApplicationContext(), TAG );
        final int count = controllerDBStatus.getCountChildStatus();
        final Thread thread = new Thread( this );
        buttonDeleteAcceptVehicle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( controllerDBStatus.removeStatusVehicle( vehicle ) ){
                    mProgressDialog.show();
                    buttonDeleteAcceptVehicle.setVisibility( View.INVISIBLE );
                    buttonDeleteCancelVehicle.setVisibility( View.INVISIBLE );
                    imageViewAlert.setImageResource( R.drawable.ic_ballot_confirm );
                    thread.start();


                }
            }
        } );

    }

    public void loadFieldButtonCancelExpenses(){

        buttonDeleteCancelVehicle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity( intentVehicleList );
            }
        } );
    }

    @Override
    public void run( ) {

        try {
            sleep(6000);

            startActivity( intentVehicleList );
            finish();
        }
        catch (InterruptedException e) {
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}