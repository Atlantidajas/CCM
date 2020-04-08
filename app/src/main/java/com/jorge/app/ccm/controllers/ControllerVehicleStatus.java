package com.jorge.app.ccm.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jorge.app.ccm.ui.session.SesionDriving;
import com.jorge.app.ccm.ui.vehicles.Vehicle;

public class ControllerVehicleStatus {

    private DatabaseReference databaseReference;
    private DatabaseReference childVehiclesStatus;
    private final String DB_NAME = "VehiclesDB";
    private final String FIELD_FIRST_STATUS = "Status";
    private final String FIELD_CHILD_LOGO_NAME = "logoVehicle";
    private final String FIELD_CHILD_BRAND = "brand";
    private final String FIELD_CHILD_MODEL = "logoVehicle";
    private final String FIELD_CHILD_DATE_ITV = "dateItv";
    private final String FIELD_CHILD_DRIVIND = "driving";

    public ControllerVehicleStatus() {
        databaseReference = FirebaseDatabase.getInstance().getReference( DB_NAME );
        childVehiclesStatus = databaseReference.child( FIELD_FIRST_STATUS );
    }

    public void setLogo( String registrationNumber, int logo) {
        this.childVehiclesStatus.child( registrationNumber ).child( FIELD_CHILD_LOGO_NAME ).setValue( logo );
    }

    public void setBrand(String registrationNumber, String brand) {
        this.childVehiclesStatus.child( registrationNumber ).child( FIELD_CHILD_BRAND ).setValue( brand );
    }

    public void setModel(String registrationNumber, String model) {
        this.childVehiclesStatus.child( registrationNumber ).child( FIELD_CHILD_MODEL ).setValue( model );
    }

    public void setDateITV( String registrationNumber, String dateITV) {
        this.childVehiclesStatus.child( registrationNumber ).child( FIELD_CHILD_DATE_ITV ).setValue( dateITV );
    }

    public void setDriving( String registrationNumber, int driving) {
        this.childVehiclesStatus.child( registrationNumber ).child( FIELD_CHILD_DRIVIND ).setValue( driving );
    }

    public void setVehicle( Vehicle vehicle ){
        this.childVehiclesStatus.setValue( vehicle );
    }

    public Query getLogoQuery(String registrationNumber ) {
        return this.childVehiclesStatus.child( FIELD_CHILD_LOGO_NAME ).equalTo( registrationNumber );
    }

    public DatabaseReference getLogoRef(String registrationNumber ) {
        return this.childVehiclesStatus.child( registrationNumber ).child( FIELD_CHILD_LOGO_NAME );
    }

    public Query getBrand( String registrationNumber ) {
        return this.childVehiclesStatus.child( FIELD_CHILD_BRAND ).equalTo( registrationNumber );
    }

    public Query getModel( String registrationNumber ) {
        return this.childVehiclesStatus.child( FIELD_CHILD_MODEL ).equalTo( registrationNumber );
    }

    public Query getDateITV( String registrationNumber ) {
        return this.childVehiclesStatus.child( FIELD_CHILD_DATE_ITV ).equalTo( registrationNumber );
    }

    public Query getDriving( String registrationNumber ) {
        return this.childVehiclesStatus.child( FIELD_CHILD_DRIVIND ).equalTo( registrationNumber );
    }

    public DatabaseReference getChildVehiclesStatus() {
        return childVehiclesStatus.child( FIELD_FIRST_STATUS );
    }

    public DatabaseReference getChildVehiclesStatusForRef( Vehicle vehicle ) {
        return childVehiclesStatus.child( vehicle.getRegistrationNumber() );
    }

}
