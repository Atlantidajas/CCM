package com.jorge.app.ccm.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jorge.app.ccm.ui.session.SesionDriving;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

public class ControllerVehicleStatus {

    private DatabaseReference databaseReference;
    private DatabaseReference childVehiclesStatus;

    public ControllerVehicleStatus() {
        databaseReference = FirebaseDatabase.getInstance().getReference( "VehiclesDB" );
        childVehiclesStatus = databaseReference.child( "Status" );
    }

    public void setLogo( String registrationNumber, int logo) {
        this.childVehiclesStatus.child( registrationNumber ).child( "logoVehicle" ).setValue( logo );
    }

    public void setBrand(String registrationNumber, String brand) {
        this.childVehiclesStatus.child( registrationNumber ).child( "brand" ).setValue( brand );
    }

    public void setModel(String registrationNumber, String model) {
        this.childVehiclesStatus.child( registrationNumber ).child( "model" ).setValue( model );
    }

    public void setDateITV( String registrationNumber, String dateITV) {
        this.childVehiclesStatus.child( registrationNumber ).child( "dateITV" ).setValue( dateITV );
    }

    public void setDriving( String registrationNumber, int driving) {
        this.childVehiclesStatus.child( registrationNumber ).child( "driving" ).setValue( driving );
    }

    public void newResgistryVehicle( Vehicle vehicle ){
        this.childVehiclesStatus.child( vehicle.getRegistrationNumber() ).setValue( vehicle );//<- De id uso la matrícula
    }

    public DatabaseReference getChildVehiclesStatus() {
        return childVehiclesStatus;
    }
}
