package com.jorge.app.ccm.models;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;

import java.io.Serializable;

public class SessionDriving implements Serializable, iSession, iUser, iVehicle {

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
        switch(session.getSessionTypeSesion() ) {
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

    @Override
    public void setSessionDate(String sessionDate) {
        this.session.setSessionDate( sessionDate );
    }

    @Override
    public void setSessionHours(String sessionHours) {
        this.session.setSessionHours( sessionHours );
    }

    @Override
    public void setSessionTypeSesion(String sessionTypeSesion) {
        this.session.setSessionTypeSesion( sessionTypeSesion );
    }

    @Override @Exclude
    public String getSessionDate() {
        return this.session.getSessionDate();
    }

    @Override @Exclude
    public String getSessionHours() {
        return this.session.getSessionHours();
    }

    @Override @Exclude
    public String getSessionTypeSesion() {
        return this.session.getSessionTypeSesion();
    }

    @Override
    public void setUserEmail(String email) {
        this.user.setUserEmail( email );
    }

    @Override
    public void setUserTelephone(String telephone) {
        this.user.setUserTelephone( telephone );
    }

    @Override @Exclude
    public String getIdUser() {
        return this.user.getIdUser();
    }

    @Override @Exclude
    public String getUserName() {
        return this.user.getUserName();
    }

    @Override @Exclude
    public String getUserPhotoUriString() {
        return this.user.getUserPhotoUriString();
    }

    @Override @Exclude
    public String getUserEmail() {
        return this.user.getUserEmail();
    }

    @Override @Exclude
    public String getUserTelephone() {
        return this.user.getUserTelephone();
    }

    @Override @Exclude
    public Uri getUserPhotoUri() {
        return this.user.getUserPhotoUri();
    }

    @Override
    public void setVehiclelogo(int vehiclelogo) {
        this.vehicle.setVehiclelogo( vehiclelogo );
    }

    @Override
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicle.setVehicleRegistrationNumber( vehicleRegistrationNumber );
    }

    @Override
    public void setVehicleBrand(String vehicleBrand) {
        this.vehicle.setVehicleBrand( vehicleBrand );
    }

    @Override
    public void setVehicleModel(String vehicleModel) {
        this.vehicle.setVehicleModel( vehicleModel );
    }

    @Override
    public void setVehicleDateITV(String vehicleDateITV) {
        this.vehicle.setVehicleDateITV( vehicleDateITV );
    }

    @Override
    public void setVehicleDriving(int vehicleDriving) {
        this.vehicle.setVehicleDriving( vehicleDriving );
    }

    @Override
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.vehicle.setVehicleDrivingCurrent( vehicleDrivingCurrent );
    }

    @Override @Exclude
    public int getVehiclelogo() {
        return this.vehicle.getVehiclelogo();
    }

    @Override @Exclude
    public String getVehicleRegistrationNumber() {
        return this.vehicle.getVehicleRegistrationNumber();
    }

    @Override @Exclude
    public String getVehicleBrand() {
        return this.vehicle.getVehicleBrand();
    }

    @Override @Exclude
    public String getVehicleModel() {
        return this.vehicle.getVehicleModel();
    }

    @Override @Exclude
    public String getVehicleDateITV() {
        return this.vehicle.getVehicleDateITV();
    }

    @Override @Exclude
    public int getVehicleDriving() {
        return this.vehicle.getVehicleDriving();
    }

    @Override @Exclude
    public String getVehicleDrivingCurrent() {
        return this.vehicle.getVehicleDrivingCurrent();
    }

    public boolean checkSesion() {

        if (this.getSessionTypeSesion().equals( "Start" )) {
            return false;
        }
        else {
            return true;
        }
    }
}
