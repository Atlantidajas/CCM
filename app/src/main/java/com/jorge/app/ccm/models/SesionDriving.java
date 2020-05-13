package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;

public class SesionDriving {

    private Session session;
    private User user;
    private Vehicle vehicle;

    public SesionDriving( Session session, User user ) {
        this.user = user;
        this.session = session;
    }

    public SesionDriving( Session session, User user,  Vehicle vehicle) {
        this.session = session;
        this.user = user;
        switch(session.getTypeSesion()) {
            case "Create":
                vehicle.setDriving( 0 );
                this.vehicle = vehicle;
                break;
            case "Start":
                vehicle.setDriving( 1 );
                this.vehicle = vehicle;
                break;
            case "End":
                vehicle.setDriving( 0 );
                this.vehicle = vehicle;
                break;
            default:
                // code block
        }

    }

    public SesionDriving( DataSnapshot dataSnapshotSesion ) {
        this.session = new Session( dataSnapshotSesion.child( "session" ) );
        this.user = new User( dataSnapshotSesion.child( "user" ) );
        if (dataSnapshotSesion.child( "vehicle" ).exists()) {
            this.vehicle = new Vehicle( dataSnapshotSesion.child( "vehicle" ) );
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Session getSession() {
        return session;
    }

    public User getUser() {
        return user;
    }
}
