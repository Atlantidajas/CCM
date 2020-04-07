package com.jorge.app.ccm.ui.session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicle;
import com.jorge.app.ccm.ui.vehicles.Vehicle;
import com.jorge.app.ccm.ui.vehicles.VehiclesListActivity;

import static com.jorge.app.ccm.ui.vehicles.VehiclesListActivity.VEHICLE_SELECT_FOR_SESION;

public class SesionDrivingActivity extends AppCompatActivity{

    private ControllerVehicle controllerVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sesion_driving );
        controllerVehicle = new ControllerVehicle();

        Vehicle vehicleSelectForSesion = (Vehicle) getIntent().getExtras().getSerializable( VEHICLE_SELECT_FOR_SESION );
        SesionDriving userSesionVehicle = new SesionDriving( true , vehicleSelectForSesion);
        controllerVehicle.newSesionsVehicleResgistry( userSesionVehicle );
    }
}
