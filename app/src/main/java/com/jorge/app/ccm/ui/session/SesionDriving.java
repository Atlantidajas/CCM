package com.jorge.app.ccm.ui.session;

import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.ui.user.User;
import com.jorge.app.ccm.ui.vehicles.Vehicle;
import com.jorge.app.ccm.utils.DateHoursUtil;


public class SesionDriving {

    private String date;
    private String hours;
    private String typeSesion;
    private User user;
    private Vehicle vehicle;

    public SesionDriving() {}

    public SesionDriving(boolean typeSesion, Vehicle vehicle) {
        this.vehicle = vehicle;
        this.user = new User();
        DateHoursUtil dateHoursUtil = new DateHoursUtil();

        // Controlo que si el objeto es creado para registrar un inicio de session
        if ( typeSesion == true) {
            this.typeSesion = "Start";
            this.date = dateHoursUtil.getDateFormatString();
            this.hours = dateHoursUtil.getHourFormatString();
            this.vehicle.setDriving( 1 );//<-- Paso a ocupado vehículo
            this.vehicle.setDrivingCurrent( user.getEmail() );
        }
        // Controlo que si el objeto es creado para registrar un fin de session
        if ( typeSesion == false) {
            this.typeSesion = "End";
            this.date = dateHoursUtil.getDateFormatString();
            this.hours = dateHoursUtil.getHourFormatString();
            this.vehicle.setDriving( 0 );//<-- Paso a libre vehículo
        }
        this.user = new User();
        this.vehicle = vehicle;
    }

    public SesionDriving( DataSnapshot dataSnapshotSesion ) {

        this.typeSesion = String.valueOf( dataSnapshotSesion.child("typeSesion").getValue() );
        this.date = String.valueOf( dataSnapshotSesion.child("date").getValue() );
        this.hours = String.valueOf( dataSnapshotSesion.child("hours").getValue() );
        this.user = new User( dataSnapshotSesion.child( "user" ) );
        this.vehicle = new Vehicle( dataSnapshotSesion.child( "vehicle" ) );
    }

    public String getTypeSesion() {
        return typeSesion;
    }

    public void setTypeSesion(String typeSesion) {
        this.typeSesion = typeSesion;
    }

    public String getDate() {
        return date;
    }

    public String getHours() {
        return hours;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}
