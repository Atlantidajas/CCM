package com.jorge.app.ccm.ui.vehicles;

import com.google.firebase.database.DataSnapshot;

public class Vehicle {

    private int logoVehicle;
    private String registrationNumber;
    private String brand;
    private String model;

    public Vehicle(int logoVehicle, String registrationNumber, String brand, String model) {
        this.logoVehicle = logoVehicle;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
    }

    public Vehicle( DataSnapshot dataSnapshot ) {
        this.logoVehicle = Integer.parseInt( String.valueOf( dataSnapshot.child("logoVehicle").getValue() ) );
        this.registrationNumber =String.valueOf( dataSnapshot.child("registrationNumber").getValue() );
        this.brand = String.valueOf( dataSnapshot.child("brand").getValue() );
        this.model = String.valueOf( dataSnapshot.child("model").getValue() );
    }

    public void setLogoVehicle(int logoVehicle) {
        this.logoVehicle = logoVehicle;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getLogoVehicle() {
        return logoVehicle;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
