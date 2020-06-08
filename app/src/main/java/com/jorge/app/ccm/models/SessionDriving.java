package com.jorge.app.ccm.models;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;


import java.io.Serializable;

/**
 * @Author: Jorge.HL
 *
 */

public class SessionDriving implements Serializable, iSession, iUser, iVehicle {

    private Session session;
    private User user;
    private Vehicle vehicle;

    /**
     * Permite construir un objeto de tipo SessionDriving con los datos de usuario y session
     * @param session Objeto de tipo Session
     * @param user Objeto de tipo User
     */
    public SessionDriving(Session session, User user ) {
        this.user = user;
        this.session = session;
    }

    /**
     * Permite construir un objeto de tipo SessionDriving, con los datos de usuario, session, vehicle
     * @param session Objeto de tipo Session
     * @param user Objeto de tipo User
     * @param vehicle Objeto vehicle
     */
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

    /**
     * * Permite construir un objeto de tipo SessionDriving con los datos obtenidos de la db (Firebase)
     * @param dataSnapshotSesion Objeto DataSnapshotSesion (Firebase)
     */
    public SessionDriving(DataSnapshot dataSnapshotSesion ) {
        this.session = new Session( dataSnapshotSesion.child( "session" ) );
        this.user = new User( dataSnapshotSesion.child( "user" ) );
        if (dataSnapshotSesion.child( "vehicle" ).exists()) {
            this.vehicle = new Vehicle( dataSnapshotSesion.child( "vehicle" ) );
        }
    }

    /**
     * Permite aisgnar el valor session
     * @param session Objeto Session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Permite aisgnar el valor user
     * @param user objeto User
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Permite aisgnar el valor vehicle
     * @param vehicle objeto Vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Devuelve un objeto Vehicle
     * @return Vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Devuelve un objeto Session
     * @return Session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Devuelve un objeto User
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Permite almacenar los datos de la propiedad sessionDate
     * @param sessionDate objeto session
     */
    @Override
    public void setSessionDate(String sessionDate) {
        this.session.setSessionDate( sessionDate );
    }

    /**
     * Permite almacenar los datos de la propiedad sessionHours
     * @param sessionHours objeto session
     */
    @Override
    public void setSessionHours(String sessionHours) {
        this.session.setSessionHours( sessionHours );
    }

    /**
     * Permite aisgnar el valor de sessionTypeSesion
     * @param sessionTypeSesion
     */
    @Override
    public void setSessionTypeSesion(String sessionTypeSesion) {
        this.session.setSessionTypeSesion( sessionTypeSesion );
    }

    /**
     * Devuelve el valor almacenado en SessionDate
     * @return
     */
    @Override @Exclude
    public String getSessionDate() {
        return this.session.getSessionDate();
    }

    /**
     * Devuelve el valor almacenado en SessionHours
     * @return
     */
    @Override @Exclude
    public String getSessionHours() {
        return this.session.getSessionHours();
    }

    /**
     * Devuelve el valor almacenado en TypeSesion
     * @return
     */
    @Override @Exclude
    public String getSessionTypeSesion() {
        return this.session.getSessionTypeSesion();
    }

    /**
     *
     * @param email
     */
    @Override
    public void setUserEmail(String email) {
        this.user.setUserEmail( email );
    }

    /**
     *
     * @param telephone
     */
    @Override
    public void setUserTelephone(String telephone) {
        this.user.setUserTelephone( telephone );
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getIdUser() {
        return this.user.getIdUser();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getUserName() {
        return this.user.getUserName();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getUserPhotoUriString() {
        return this.user.getUserPhotoUriString();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getUserEmail() {
        return this.user.getUserEmail();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getUserTelephone() {
        return this.user.getUserTelephone();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public Uri getUserPhotoUri() {
        return this.user.getUserPhotoUri();
    }

    /**
     *
     * @param vehiclelogo
     */
    @Override
    public void setVehiclelogo(int vehiclelogo) {
        this.vehicle.setVehiclelogo( vehiclelogo );
    }

    /**
     *
     * @param vehicleRegistrationNumber
     */
    @Override
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicle.setVehicleRegistrationNumber( vehicleRegistrationNumber );
    }

    /**
     *
     * @param vehicleBrand
     */
    @Override
    public void setVehicleBrand(String vehicleBrand) {
        this.vehicle.setVehicleBrand( vehicleBrand );
    }

    /**
     *
     * @param vehicleModel
     */
    @Override
    public void setVehicleModel(String vehicleModel) {
        this.vehicle.setVehicleModel( vehicleModel );
    }

    /**
     *
     * @param vehicleDateITV
     */
    @Override
    public void setVehicleDateITV(String vehicleDateITV) {
        this.vehicle.setVehicleDateITV( vehicleDateITV );
    }

    /**
     *
     * @param vehicleDriving
     */
    @Override
    public void setVehicleDriving(int vehicleDriving) {
        this.vehicle.setVehicleDriving( vehicleDriving );
    }

    /**
     *
     * @param vehicleDrivingCurrent
     */
    @Override
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.vehicle.setVehicleDrivingCurrent( vehicleDrivingCurrent );
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public int getVehiclelogo() {
        return this.vehicle.getVehiclelogo();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getVehicleRegistrationNumber() {
        return this.vehicle.getVehicleRegistrationNumber();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getVehicleBrand() {
        return this.vehicle.getVehicleBrand();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getVehicleModel() {
        return this.vehicle.getVehicleModel();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getVehicleDateITV() {
        return this.vehicle.getVehicleDateITV();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public int getVehicleDriving() {
        return this.vehicle.getVehicleDriving();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getVehicleDrivingCurrent() {
        return this.vehicle.getVehicleDrivingCurrent();
    }

    /**
     *
     * @return
     */
    public boolean checkSesion() {

        if (this.getSessionTypeSesion().equals( "Start" )) {
            return false;
        }
        else {
            return true;
        }
    }
}
