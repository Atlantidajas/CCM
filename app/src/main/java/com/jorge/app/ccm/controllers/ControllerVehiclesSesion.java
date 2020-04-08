package com.jorge.app.ccm.controllers;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jorge.app.ccm.ui.session.SesionDriving;
import com.jorge.app.ccm.ui.user.User;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

public class ControllerVehiclesSesion {

    private DatabaseReference databaseReference;
    private DatabaseReference childVehicleSesions;

    public ControllerVehiclesSesion() {
        databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" );
        childVehicleSesions = databaseReference.child( "Sesions" );
    }

    public void setPhotoURL( String registrationNumber, Uri photoUri ){
        String photoString = String.valueOf( photoUri );
        childVehicleSesions.child( registrationNumber ).child( "photoUri" ).setValue( photoString );
    }

    public void setDate( String registrationNumber, String date ){
        childVehicleSesions.child( registrationNumber ).child( "date" ).setValue( date );
    }
    public void setHours( String registrationNumber, String hours ){
        childVehicleSesions.child( registrationNumber ).child( "hours" ).setValue( hours );
    }
    public void setTypeSesion( String registrationNumber, String typeSesion ){
        childVehicleSesions.child( registrationNumber ).child( "typeSesion" ).setValue( typeSesion );
    }
    public void SetVehicle( String registrationNumber, Vehicle vehicle ){
        childVehicleSesions.child( registrationNumber ).child( "vehicle" ).setValue( vehicle );
    }

    public void setUser( String registrationNumber, User user ){
        childVehicleSesions.child( registrationNumber ).child( "user" ).setValue( user );
    }

    public void newSesionsVehicleResgistry( SesionDriving sesionVehicle ){
        childVehicleSesions.child( sesionVehicle.getVehicle().getRegistrationNumber() ).setValue( sesionVehicle );
    }

}
