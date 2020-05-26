package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;

public class SessionDriving {

    private Session session;
    private User user;
    private Vehicle vehicle;

    public SessionDriving(Session session, User user ) {
        this.user = user;
        this.session = session;
    }

    public SessionDriving(Session session, User user, Vehicle vehicle) {
        this.session = session;
        this.user = user;
        String nameRegistry;
        switch(session.getTypeSesion()) {
            case "Create":
                nameRegistry = "a";
                vehicle.setVehicleDriving( 0 );
                this.vehicle = vehicle;
                break;
            case "Start":
                nameRegistry = "b";
                vehicle.setVehicleDriving( 1 );
                this.vehicle = vehicle;
                break;
            case "End":
                nameRegistry = "c";
                vehicle.setVehicleDriving( 0 );
                this.vehicle = vehicle;
                break;
            default:
                // code block
        }

    }

    public SessionDriving(DataSnapshot dataSnapshotSesion ) {
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
