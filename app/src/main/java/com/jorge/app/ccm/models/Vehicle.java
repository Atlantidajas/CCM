package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Vehicle implements Serializable {

    private int vehiclelogo;
    private String vehicleRegistrationNumber;
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleDateITV;
    private int vehicleDriving;
    private String vehicleDrivingCurrent;

    public Vehicle() {
    }

    public Vehicle( int logoVehicle,
                   String registrationNumber,
                   String brand,
                   String model,
                   String dateITV,
                   int driving ) {
        this.vehiclelogo = logoVehicle;
        this.vehicleRegistrationNumber = registrationNumber;
        this.vehicleBrand = brand;
        this.vehicleModel = model;
        this.vehicleDateITV = dateITV;
        this.vehicleDriving = driving;
    }

    public Vehicle( ExpenseTemp expenseTemp ) {
        this.vehiclelogo = expenseTemp.getVehiclelogo();
        this.vehicleRegistrationNumber = expenseTemp.getVehicleRegistrationNumber();
        this.vehicleBrand = expenseTemp.getVehicleBrand();
        this.vehicleModel = expenseTemp.getVehicleModel();
        this.vehicleDateITV = expenseTemp.getVehicleDateITV();
        this.vehicleDriving = expenseTemp.getVehicleDriving();
    }

    public Vehicle(DataSnapshot dataSnapshot ) {
        this.vehiclelogo = Integer.parseInt( String.valueOf( dataSnapshot.child("vehiclelogo").getValue() ) );
        this.vehicleRegistrationNumber =String.valueOf( dataSnapshot.child("vehicleRegistrationNumber").getValue() );
        this.vehicleBrand = String.valueOf( dataSnapshot.child("vehicleBrand").getValue() );
        this.vehicleModel = String.valueOf( dataSnapshot.child("vehicleModel").getValue() );
        this.vehicleDateITV = String.valueOf( dataSnapshot.child("vehicleDateITV").getValue() );
        this.vehicleDriving = Integer.parseInt( String.valueOf( dataSnapshot.child("vehicleDriving").getValue() ) );
        this.vehicleDrivingCurrent = String.valueOf( dataSnapshot.child("vehicleDrivingCurrent").getValue() );

    }

    public void setVehiclelogo(int vehiclelogo) {
        this.vehiclelogo = vehiclelogo;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setVehicleDateITV(String vehicleDateITV) {
        this.vehicleDateITV = vehicleDateITV;
    }

    public void setVehicleDriving(int vehicleDriving) {
        this.vehicleDriving = vehicleDriving;
    }

    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.vehicleDrivingCurrent = vehicleDrivingCurrent;
    }

    public int getVehiclelogo() {
        return vehiclelogo;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleDateITV() {
        return vehicleDateITV;
    }

    public int getVehicleDriving() {
        return vehicleDriving;
    }

    public String getVehicleDrivingCurrent() {
        return vehicleDrivingCurrent;
    }

}
