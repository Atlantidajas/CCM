package com.jorge.app.ccm.models;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.Exclude;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.models.iVehicle;
import com.jorge.app.ccm.utils.DatesTemp;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de tipo Vehicle.
 */

public class VehicleTemp extends DatesTemp implements iVehicle {

    private String PRIMARY_KEY = getFILE_NAME() + "vehicle";
    private String KEY_VEHICLE_LOGO = PRIMARY_KEY + "Logo";
    private String KEY_VEHICLE_REGISTRATION_NUMBER = PRIMARY_KEY + "RegistrationNumber";
    private String KEY_VEHICLE_BRAND = PRIMARY_KEY + "Brand";
    private String KEY_VEHICLE_MODEL = PRIMARY_KEY + "Model";
    private String KEY_VEHICLE_DATE_ITV = PRIMARY_KEY + "DateItv";
    private String KEY_VEHICLE_DRIVING = PRIMARY_KEY + "Driving";
    private String KEY_VEHICLE_DRIVING_CURRENT = PRIMARY_KEY + "DrivingCurrent";

    /**
     * Crea un objeto de tipo VehicleTemp que permite almacenar objetos de tipo Vehicle en un fichero
     * @param context Contexto en el se va ha ejecutar
     * @param TAG Nombre de la actividad donde se va a ejecutar
     */
    public VehicleTemp(Context context,
                       final String TAG){
        super( context, TAG );

    }

    /**
     * Crea un objeto de tipo VehicleTemp que permite almacenar objetos de tipo Vehicle en un fichero
     * @param context Contexto en el se va ha ejecutar
     * @param TAG Nombre de la actividad donde se va a ejecutar
     * @param vehicle objeto con el que se va a travajar el el fichero
     */
    public VehicleTemp(Context context,
                       final String TAG,
                       Vehicle vehicle ) {
        super( context, TAG );
        this.setDateInt( KEY_VEHICLE_LOGO, vehicle.getVehiclelogo() );
        this.setDateString( KEY_VEHICLE_REGISTRATION_NUMBER, vehicle.getVehicleRegistrationNumber() );
        this.setDateString( KEY_VEHICLE_BRAND, vehicle.getVehicleBrand() );
        this.setDateString( KEY_VEHICLE_MODEL, vehicle.getVehicleModel() );
        this.setDateString( KEY_VEHICLE_DATE_ITV, vehicle.getVehicleDateITV() );
        this.setDateInt( KEY_VEHICLE_DRIVING, vehicle.getVehicleDriving() );
    }

    /**
     * Crea un objeto de tipo VehicleTemp que permite almacenar objetos de tipo Vehicle en un fichero
     * @param context Contexto en el se va ha ejecutar
     * @param TAG Nombre de la actividad donde se va a ejecutar
     * @param logoVehicle atributo de un objeto Vehicle, este de tipo int
     * @param registrationNumber atributo de un objeto Vehicle, este de tipo String
     * @param brand atributo de un objeto Vehicle, este de tipo String
     * @param model atributo de un objeto Vehicle, este de tipo String
     * @param dateITV atributo de un objeto Vehicle, este de tipo String
     * @param driving atributo de un objeto Vehicle, este de tipo int
     */
    public VehicleTemp(Context context,
                       final String TAG,
                       int logoVehicle,
                       String registrationNumber,
                       String brand,
                       String model,
                       String dateITV,
                       int driving ) {
        super( context, TAG );
        this.setDateInt( KEY_VEHICLE_LOGO, logoVehicle );
        this.setDateString( KEY_VEHICLE_REGISTRATION_NUMBER, registrationNumber );
        this.setDateString( KEY_VEHICLE_BRAND, brand );
        this.setDateString( KEY_VEHICLE_MODEL, model );
        this.setDateString( KEY_VEHICLE_DATE_ITV, dateITV );
        this.setDateInt( KEY_VEHICLE_DRIVING, driving );
    }


    /**
     * @See DatesTemp setDateInt.  Guarda en fichero temporal el vehiclelogo de un un objeto Vehicle
     * @param vehiclelogo número del recurso con el que se almacena el logo de la marca de un vehículo
     */
    @Override
    public void setVehiclelogo(int vehiclelogo) {
        this.setDateInt( KEY_VEHICLE_LOGO, vehiclelogo );
    }

    /**
     * @See DatesTemp setDateString.  Guarda en fichero temporal el vehicleRegistrationNumber de un un objeto Vehicle
     * @param vehicleRegistrationNumber número de la matrícula del vehículos
     */
    @Override
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.setDateString( KEY_VEHICLE_REGISTRATION_NUMBER, vehicleRegistrationNumber );
    }

    /**
     * @See DatesTemp setDateString.  Guarda en fichero temporal el vehicleBrand de un un objeto Vehicle
     * @param vehicleBrand marca del vehículo que se pretende almacenar
     */
    @Override
    public void setVehicleBrand(String vehicleBrand) {
        this.setDateString( KEY_VEHICLE_BRAND, vehicleBrand );
    }

    /**
     * @See DatesTemp setDateString.  Guarda en fichero temporal el vehicleModel de un un objeto Vehicle
     * @param vehicleModel modelo del vehículo que se pretende almacenar
     */
    @Override
    public void setVehicleModel(String vehicleModel) {
        this.setDateString( KEY_VEHICLE_MODEL, vehicleModel );
    }

    /**
     * @See DatesTemp setDateString.  Guarda en fichero temporal el vehicleDateITV de un un objeto Vehicle
     * @param vehicleDateITV fecha de la próxima itv del vehículo que se pretende almacenar
     */
    @Override
    public void setVehicleDateITV(String vehicleDateITV) {
        this.setDateString( KEY_VEHICLE_DATE_ITV, vehicleDateITV );
    }

    /**
     * @See DatesTemp setDateInt.  Guarda en fichero temporal el vehicleDriving de un un objeto Vehicle
     * @param vehicleDriving valor que identifica si el vehículo está en uso o libre y que se pretende almacenar
     */
    @Override
    public void setVehicleDriving(int vehicleDriving) {
        this.setDateInt( KEY_VEHICLE_DRIVING, vehicleDriving );
    }

    /**
     * @See DatesTemp setDateString.  Guarda en fichero temporal el vehicleDrivingCurrent de un un objeto Vehicle
     * @param vehicleDrivingCurrent nombre del conductor que utilizó o utiliza en la actualidad el vehículo y se pretende almacenar
     */
    @Override
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.setDateString( KEY_VEHICLE_DRIVING, vehicleDrivingCurrent );
    }

    /**
     * Devuelve después de leer en fichero temporal objeto vehicle
     * @return objeto de tipo Vehicle
     */
    @Exclude
    public Vehicle getVehicleTemp(){

        Vehicle vehicle = new Vehicle( this.getDateInt( KEY_VEHICLE_LOGO),
                this.getDateString( KEY_VEHICLE_REGISTRATION_NUMBER),
                this.getDateString( KEY_VEHICLE_BRAND ),
                this.getDateString( KEY_VEHICLE_MODEL ),
                this.getDateString( KEY_VEHICLE_DATE_ITV ),
                this.getDateInt( KEY_VEHICLE_DRIVING ));
        return vehicle;
    }

    /**
     * Obtiene del fichero temporal el atributo logoVehicle de un un objeto Vehicle
     * @return valor int con el número del recurso del logo del vehículo almacenado en el fichero
     */
    @Override @Exclude
    public int getVehiclelogo() {
        return this.getDateInt( KEY_VEHICLE_LOGO );
    }

    /**
     * Obtiene del fichero temporal el atributo registration de un un objeto Vehicle
     * @return valor String con la matrícula del vehículo almacenado en el fichero
     */
    @Override @Exclude
    public String getVehicleRegistrationNumber() {
        return this.getDateString( KEY_VEHICLE_REGISTRATION_NUMBER );
    }

    /**
     * Obtiene del fichero temporal el atributo brand de un un objeto Vehicle
     * @return valor String con la marca del vehículo almacenado en el fichero
     */
    @Override @Exclude
    public String getVehicleBrand() {
        return  this.getDateString( KEY_VEHICLE_BRAND );
    }

    /**
     * Obtiene del fichero temporal el atributo modelo de un un objeto Vehicle
     * @return valor String con la modelo del vehículo almacenado en el fichero
     */
    @Override @Exclude
    public String getVehicleModel() {
        return  this.getDateString( KEY_VEHICLE_MODEL );
    }

    /**
     * Obtiene del fichero temporal el atributo dateITV de un un objeto Vehicle
     * @return valor String con la fecha de la próxima ITV del vehículo almacenado en el fichero
     */
    @Override @Exclude
    public String getVehicleDateITV() {
        return  this.getDateString( KEY_VEHICLE_DATE_ITV );
    }

    /**
     * Obtiene del fichero temporal el atributo driving de un un objeto Vehicle
     * @return valor int con el driving que permite saber si un vehículo está en uso o libre del vehículo almacenado en el fichero
     */
    @Override
    public int getVehicleDriving() {
        return  this.getDateInt( KEY_VEHICLE_DRIVING );
    }

    /**
     * Obtiene del fichero temporal el atributo drivingCurrent de un un objeto Vehicle
     * @return valor String con el nombre del usuario que utilizó o utiliza el vehículo almacenado en el fichero
     */
    @Override @Exclude
    public String getVehicleDrivingCurrent() {
        return this.getDateString( KEY_VEHICLE_DRIVING_CURRENT );
    }

    /**
     * Borrar del fichero temporal los datos correspodiente al objeto Vehicle
     */
    public void removeVehicle(){

        this.removeDate( KEY_VEHICLE_LOGO );
        this.removeDate( KEY_VEHICLE_REGISTRATION_NUMBER );
        this.removeDate( KEY_VEHICLE_BRAND );
        this.removeDate( KEY_VEHICLE_MODEL );
        this.removeDate( KEY_VEHICLE_DATE_ITV );
        this.removeDate( KEY_VEHICLE_DRIVING );

    }

    /**
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_LOGO_VHEICLE
     */
    public void removeVehicleLogo(){
        this.removeDate( KEY_VEHICLE_LOGO );
    }

    /**
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_REGISTRATION_NUMBER_VEHICLE
     */
    public void removeVehicleRegistrationNumber(){
        this.removeDate( KEY_VEHICLE_REGISTRATION_NUMBER );
    }

    /**
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_BRAND_VEHICLE
     */
    public void removeVehicleBrand(){
        this.removeDate( KEY_VEHICLE_BRAND );
    }

    /**
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_BRAND_VEHICLE
     */
    public void removeVehicleModel(){
        this.removeDate( KEY_VEHICLE_MODEL );
    }

    /**
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DATE_ITV
     */
    public void removeVehicleDateItv(){
        this.removeDate( KEY_VEHICLE_DATE_ITV );
    }

    /**
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DRIVING_VEHICLE
     */
    public void removeVehicleDriving(){
        this.removeDate( KEY_VEHICLE_DRIVING );
    }


}
