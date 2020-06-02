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

    public VehicleTemp(Context context,
                       final String TAG){
        super( context, TAG );

    }

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
     * Devuelve después de leer en fichero temporal objeto vehicle
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

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo logoVehicle de un un objeto Vehicle
     */
    @Override @Exclude
    public int getVehiclelogo() {
        return this.getDateInt( KEY_VEHICLE_LOGO );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo registrationNumber de un un objeto Vehicle
     */
    @Override @Exclude
    public String getVehicleRegistrationNumber() {
        return this.getDateString( KEY_VEHICLE_REGISTRATION_NUMBER );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo brand de un un objeto Vehicle
     */
    @Override @Exclude
    public String getVehicleBrand() {
        return  this.getDateString( KEY_VEHICLE_BRAND );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo modelVehicle de un un objeto Vehicle
     */
    @Override @Exclude
    public String getVehicleModel() {
        return  this.getDateString( KEY_VEHICLE_MODEL );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo dateItv de un un objeto Vehicle
     */
    @Override @Exclude
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

    @Override @Exclude
    public String getVehicleDrivingCurrent() {
        return this.getDateString( KEY_VEHICLE_DRIVING_CURRENT );
    }

    /*
     * @Author: Jorge.HL
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

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_LOGO_VHEICLE
     */
    public void removeVehicleLogo(){
        this.removeDate( KEY_VEHICLE_LOGO );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_REGISTRATION_NUMBER_VEHICLE
     */
    public void removeVehicleRegistrationNumber(){
        this.removeDate( KEY_VEHICLE_REGISTRATION_NUMBER );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_BRAND_VEHICLE
     */
    public void removeVehicleBrand(){
        this.removeDate( KEY_VEHICLE_BRAND );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_BRAND_VEHICLE
     */
    public void removeVehicleModel(){
        this.removeDate( KEY_VEHICLE_MODEL );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DATE_ITV
     */
    public void removeVehicleDateItv(){
        this.removeDate( KEY_VEHICLE_DATE_ITV );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DRIVING_VEHICLE
     */
    public void removeVehicleDriving(){
        this.removeDate( KEY_VEHICLE_DRIVING );
    }


}
