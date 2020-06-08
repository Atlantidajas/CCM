package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

/**
 * Clase que permite trabajar, crear con objetos de tipo vehículo
 * @Author: Jorge.HL
 */

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

    /**
     * Permite contruir un objeto de tipo vehículo inicializando sus atributos
     * @param logoVehicle int número del logo con el que se almacena el recurso
     * @param registrationNumber String matrícula del vehículo
     * @param brand String marca del vehículo
     * @param model String modelo del vehículo
     * @param dateITV String fecha de la próxima ITV del vehículo
     * @param driving int valor que permite saber si un vehículo está en uso o libre
     */
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


    /**
     * Permite contruir un objeto de tipo vehículo a partir de un objeto de ExpenseTemp almacenado
     * @param expenseTemp objeto de tipo ExpenseTemp
     */
    public Vehicle( ExpenseTemp expenseTemp ) {
        this.vehiclelogo = expenseTemp.getVehiclelogo();
        this.vehicleRegistrationNumber = expenseTemp.getVehicleRegistrationNumber();
        this.vehicleBrand = expenseTemp.getVehicleBrand();
        this.vehicleModel = expenseTemp.getVehicleModel();
        this.vehicleDateITV = expenseTemp.getVehicleDateITV();
        this.vehicleDriving = expenseTemp.getVehicleDriving();
    }

    /**
     * Permite construir un objeto de tipo vehícle a partir de la lectura de la db Firebase
     * @param dataSnapshot objeto de tipo DataSnapshot (FireBase)
     */
    public Vehicle(DataSnapshot dataSnapshot ) {
        this.vehiclelogo = Integer.parseInt( String.valueOf( dataSnapshot.child("vehiclelogo").getValue() ) );
        this.vehicleRegistrationNumber =String.valueOf( dataSnapshot.child("vehicleRegistrationNumber").getValue() );
        this.vehicleBrand = String.valueOf( dataSnapshot.child("vehicleBrand").getValue() );
        this.vehicleModel = String.valueOf( dataSnapshot.child("vehicleModel").getValue() );
        this.vehicleDateITV = String.valueOf( dataSnapshot.child("vehicleDateITV").getValue() );
        this.vehicleDriving = Integer.parseInt( String.valueOf( dataSnapshot.child("vehicleDriving").getValue() ) );
        this.vehicleDrivingCurrent = String.valueOf( dataSnapshot.child("vehicleDrivingCurrent").getValue() );

    }

    /**
     * Almacena la propiedad vehicleLogo
     * @param vehiclelogo int valor del identificador del recurso correspondiente al logo del vehículo
     */
    public void setVehiclelogo(int vehiclelogo) {
        this.vehiclelogo = vehiclelogo;
    }

    /**
     * Almacena la propiedad vehicleRegistrationNumber
     * @param vehicleRegistrationNumber String valor de la matrícula del vehículo
     */
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    /**
     * Almacena la propiedad vehicleBrand
     * @param vehicleBrand String valor de la marca del vehículo
     */
    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    /**
     * Almacena la propiedad vehicleModel
     * @param vehicleModel String valor de la modelo del vehículo
     */
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * Almacena la propiedad vehicleDateITV
     * @param vehicleDateITV String valor de la fecha de la próxima ITV del vehículo
     */
    public void setVehicleDateITV(String vehicleDateITV) {
        this.vehicleDateITV = vehicleDateITV;
    }

    /**
     * Almacena la propiedad vehicleDriving
     * @param vehicleDriving int valor de la propiedad driving del vehículo
     */
    public void setVehicleDriving(int vehicleDriving) {
        this.vehicleDriving = vehicleDriving;
    }

    /**
     * Almacena la propiedad vehicleDrivingCurrent
     * @param vehicleDrivingCurrent String valor de la propiedad vehicleDrivingCurrent con el nombre del último conductor del vehículo
     */
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.vehicleDrivingCurrent = vehicleDrivingCurrent;
    }

    /**
     * Obtiene el valos del identificador del recurso para la propiedad logo de vehículo
     * @return int con el valor del id con el que se almacena el logo del vehículo
     */
    public int getVehiclelogo() {
        return vehiclelogo;
    }

    /**
     * Obtiene el valor de la matrícula del vehículo
     * @return String con el valor de la matrícula del vehículo
     */
    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    /**
     * Obtiene el valor de la vehicleBrand del vehículo
     * @return String con el valor de la vehicleBrand del vehículo
     */
    public String getVehicleBrand() {
        return vehicleBrand;
    }

    /**
     * Obtiene el valor de la vehicleModel del vehículo
     * @return String con el valor de la vehicleModel del vehículo
     */
    public String getVehicleModel() {
        return vehicleModel;
    }

    /**
     * Obtiene el valor de la vehicleDateITV del vehículo
     * @return String con el valor de la vehicleDateITV (Próxima ITV) del vehículo
     */
    public String getVehicleDateITV() {
        return vehicleDateITV;
    }

    /**
     * Obtiene el valor de la vehicleDriving del vehículo
     * @return String con el valor de la vehicleDriving (Permite saber si un vehículo está en uso o libre) del vehículo
     */
    public int getVehicleDriving() {
        return vehicleDriving;
    }

    /**
     * Obtiene el valor de la vehicleDrivingCurrent del vehículo
     * @return String con el valor de la vehicleDrivingCurrent último conductor del vehículo
     */
    public String getVehicleDrivingCurrent() {
        return vehicleDrivingCurrent;
    }

}
