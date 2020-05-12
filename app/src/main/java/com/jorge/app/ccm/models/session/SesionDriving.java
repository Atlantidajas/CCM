package com.jorge.app.ccm.models.session;

import android.net.Uri;

import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.models.user.IUser;
import com.jorge.app.ccm.models.user.User;
import com.jorge.app.ccm.models.vehicle.IVehicle;
import com.jorge.app.ccm.models.vehicle.Vehicle;
import com.jorge.app.ccm.utils.DateHoursUtil;


public class SesionDriving implements IUser, IVehicle {

    private String date;
    private String hours;
    private String typeSesion;
    private User user;
    private Vehicle vehicle;


    public SesionDriving(boolean typeSesion, Vehicle vehicle) {
        this.user = new User();

        DateHoursUtil dateHoursUtil = new DateHoursUtil();

        // Controlo que si el objeto es creado para registrar un inicio de session
        if (typeSesion == true) {
            this.typeSesion = "Start";
            this.date = dateHoursUtil.getDateFormatString();
            this.hours = dateHoursUtil.getHourFormatString();
            vehicle.setDriving( 1 );
            vehicle.setDrivingCurrent( user.getEmail() );
            this.vehicle = vehicle;
        }
        // Controlo que si el objeto es creado para registrar un fin de session
        if (typeSesion == false) {
            this.typeSesion = "End";
            this.date = dateHoursUtil.getDateFormatString();
            this.hours = dateHoursUtil.getHourFormatString();
            vehicle.setDriving( 0 );
            this.vehicle = vehicle;
        }
    }
    public SesionDriving() {
        this.user = new User();
        DateHoursUtil dateHoursUtil = new DateHoursUtil();
        this.date = dateHoursUtil.getDateFormatString();
        this.hours = dateHoursUtil.getHourFormatString();
        this.vehicle = new Vehicle();
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


    @Override
    public void setIdUser(String idUser) {
        this.user.setIdUser( idUser );
    }

    @Override
    public void setName(String name) {
        this.user.setName( name );
    }

    @Override
    public void setPhotoUri(Uri photoUri) {
        this.user.setPhotoUri( photoUri );
    }

    @Override
    public void setEmail(String email) {
        this.user.setEmail( email );
    }

    @Override
    public void setTelephone(String telephone) {
        this.setTelephone( telephone );
    }

    @Override
    public String getIdUser() {
        return this.user.getIdUser();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public String getEmail() {
        return this.user.getEmail();
    }

    @Override
    public String getTelephone() {
        return this.user.getTelephone();
    }


    @Override
    public void setBrand(String brand) {
        this.vehicle.setBrand( brand );
    }

    @Override
    public void setModel(String model) {
        this.vehicle.setModel( model );
    }

    @Override
    public void setDateITV(String dateITV) {
        this.setDateITV( dateITV );
    }

    @Override
    public void setDriving(int driving) {
        this.setDriving( driving );
    }

    @Override
    public int getLogoVehicle() {
        return this.vehicle.getLogoVehicle();
    }

    @Override
    public String getRegistrationNumber() {
        return this.vehicle.getRegistrationNumber();
    }

    @Override
    public String getBrand() {
        return this.vehicle.getBrand();
    }

    @Override
    public String getModel() {
        return this.vehicle.getModel();
    }

    @Override
    public String getDateITV() {
        return this.vehicle.getDateITV();
    }

    @Override
    public int getDriving() {
        return this.vehicle.getDriving();
    }

}
