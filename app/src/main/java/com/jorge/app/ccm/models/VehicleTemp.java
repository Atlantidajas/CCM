package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.models.iVehicle;
import com.jorge.app.ccm.utils.DatesTemp;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de tipo Vehicle.
 */

public class VehicleTemp extends DatesTemp implements iVehicle {

    private final String PRIMARY_KEY;
    private final String KEY_VEHICLE_BRAND;
    private final String KEY_VEHICLE_MODEL;
    private final String KEY_VEHICLE_DATE_ITV;
    private final String KEY_VEHICLE_DRIVING;
    private final String KEY_VEHICLE_LOGO;
    private final String KEY_VEHICLE_REGISTRATION_NUMBER;
    private final String KEY_VEHICLE_DRIVING_CURRENT;
    private Vehicle vehicleTemp;

    public VehicleTemp(Context context, final String TAG ) {
        super( context, TAG );
        PRIMARY_KEY = getFILE_NAME() + "vehicle";
        this.KEY_VEHICLE_BRAND = PRIMARY_KEY + "brand";
        this.KEY_VEHICLE_MODEL = PRIMARY_KEY + "model";
        this.KEY_VEHICLE_DATE_ITV = PRIMARY_KEY + "dateItv";
        this.KEY_VEHICLE_DRIVING = PRIMARY_KEY + "driving";
        this.KEY_VEHICLE_LOGO = PRIMARY_KEY + "logo";
        this.KEY_VEHICLE_REGISTRATION_NUMBER = PRIMARY_KEY + "registrationNumber";
        this.KEY_VEHICLE_DRIVING_CURRENT = PRIMARY_KEY + "drivingCurrent";
        this.vehicleTemp = new Vehicle();

    }


    public void setVehicleTemp(Vehicle vehicleTemp) {
        this.vehicleTemp = vehicleTemp;
    }



    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal objeto Vehicle
     */
    public void setvehicle( Vehicle vehicle ) {

        this.setDateString( KEY_VEHICLE_BRAND, vehicle.getVehicleBrand());
        this.setDateString( KEY_VEHICLE_MODEL, vehicle.getVehicleModel() );
        this.setDateString( KEY_VEHICLE_DATE_ITV, vehicle.getVehicleDateITV() );
        this.setDateInt( KEY_VEHICLE_DRIVING, vehicle.getVehicleDriving() );
        this.setDateInt( KEY_VEHICLE_LOGO, vehicle.getVehiclelogo() );
        this.setDateString( KEY_VEHICLE_REGISTRATION_NUMBER, vehicle.getVehicleRegistrationNumber() );

    }

    /*
     * @Author: Jorge.HL
     * Devuelve después de leer en fichero temporal objeto vehicle
     */
    public Vehicle getVehicleTemp(){

        vehicleTemp = new Vehicle( this.getDateInt( KEY_VEHICLE_LOGO),
                this.getDateString( KEY_VEHICLE_REGISTRATION_NUMBER),
                this.getDateString( KEY_VEHICLE_BRAND ),
                this.getDateString( KEY_VEHICLE_MODEL ),
                this.getDateString( KEY_VEHICLE_DATE_ITV ),
                this.getDateInt( KEY_VEHICLE_DRIVING ));
        return vehicleTemp;
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal los datos correspodiente al objeto Vehicle
     */
    public void removeVehicle(){
        this.removeDate( KEY_VEHICLE_BRAND );
        this.removeDate( KEY_VEHICLE_MODEL );
        this.removeDate( KEY_VEHICLE_DATE_ITV );
        this.removeDate( KEY_VEHICLE_DRIVING );
        this.removeDate( KEY_VEHICLE_LOGO );
        this.removeDate( KEY_VEHICLE_REGISTRATION_NUMBER );
    }

    @Override
    public void setVehiclelogo(int vehiclelogo) {
        this.setDateInt( KEY_VEHICLE_LOGO, vehiclelogo );
    }

    @Override
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.setDateString( KEY_VEHICLE_REGISTRATION_NUMBER, vehicleRegistrationNumber );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo brand de un un objeto Vehicle
     */
    @Override
    public void setVehicleBrand(String vehicleBrand) {
        this.setDateString( KEY_VEHICLE_BRAND, vehicleBrand );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo model de un un objeto Vehicle
     */
    @Override
    public void setVehicleModel(String vehicleModel) {
        this.setDateString( KEY_VEHICLE_MODEL, vehicleModel );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo driving de un un objeto Vehicle
     */
    @Override
    public void setVehicleDateITV(String vehicleDateITV) {
        this.setDateString( KEY_VEHICLE_DATE_ITV, vehicleDateITV );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo driving de un un objeto Vehicle
     */
    @Override
    public void setVehicleDriving(int vehicleDriving) {
        this.setDateInt( KEY_VEHICLE_DRIVING, vehicleDriving );
    }

    @Override
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.setDateString( KEY_VEHICLE_DRIVING, vehicleDrivingCurrent );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo logoVehicle de un un objeto Vehicle
     */
    @Override
    public int getVehiclelogo() {
        return this.getDateInt( KEY_VEHICLE_LOGO );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo registrationNumber de un un objeto Vehicle
     */
    @Override
    public String getVehicleRegistrationNumber() {
        return this.getDateString( KEY_VEHICLE_REGISTRATION_NUMBER );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo brand de un un objeto Vehicle
     */
    @Override
    public String getVehicleBrand() {
        return  this.getDateString( KEY_VEHICLE_BRAND );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo modelVehicle de un un objeto Vehicle
     */
    @Override
    public String getVehicleModel() {
        return  this.getDateString( KEY_VEHICLE_MODEL );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo dateItv de un un objeto Vehicle
     */
    @Override
    public String getVehicleDateITV() {
        return  this.getDateString( KEY_VEHICLE_DATE_ITV );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo driving de un un objeto Vehicle
     */
    @Override
    public int getVehicleDriving() {
        return  this.getDateInt( KEY_VEHICLE_DRIVING );
    }

    @Override
    public String getVehicleDrivingCurrent() {
        return this.getDateString( KEY_VEHICLE_DRIVING_CURRENT );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_BRAND_VEHICLE
     */
    public void removeKEY_VEHICLE_BRAND(){
        this.removeDate( KEY_VEHICLE_BRAND );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_BRAND_VEHICLE
     */
    public void removeKEY_VEHICLE_MODEL(){
        this.removeDate( KEY_VEHICLE_MODEL );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DATE_ITV
     */
    public void removeKEY_VEHICLE_DATE_ITV(){
        this.removeDate( KEY_VEHICLE_DATE_ITV );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DRIVING_VEHICLE
     */
    public void removeKEY_VEHICLE_DRIVING_VEHICLE(){
        this.removeDate( KEY_VEHICLE_DRIVING );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_LOGO_VHEICLE
     */
    public void removeKEY_VEHICLE_LOGO(){
        this.removeDate( KEY_VEHICLE_LOGO );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_REGISTRATION_NUMBER_VEHICLE
     */
    public void removeKEY_VEHICLE_REGISTRATION_NUMBER(){
        this.removeDate( KEY_VEHICLE_REGISTRATION_NUMBER );
    }
}
