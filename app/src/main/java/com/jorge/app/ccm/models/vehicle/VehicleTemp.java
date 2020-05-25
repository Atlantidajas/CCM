package com.jorge.app.ccm.models.vehicle;

import android.content.Context;

import com.jorge.app.ccm.models.typeExpense.ITypeExpense;
import com.jorge.app.ccm.models.typeExpense.TypeExpense;
import com.jorge.app.ccm.utils.DatesTemp;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de tipo Vehicle.
 */

public class VehicleTemp extends DatesTemp implements IVehicle {

    private final String PRIMARY_KEY;
    private final String KEY_BRAND_VEHICLE;
    private final String KEY_MODEL_VEHICLE;
    private final String KEY_DATE_ITV;
    private final String KEY_DRIVING_VEHICLE;
    private final String KEY_LOGO_VHEICLE;
    private final String KEY_REGISTRATION_NUMBER_VEHICLE;
    private Vehicle vehicle;

    public VehicleTemp(Context context, final String TAG ) {
        super( context, TAG );
        PRIMARY_KEY = getFILE_NAME() + "vehicle";
        this.KEY_BRAND_VEHICLE = PRIMARY_KEY + "brandVehicle";
        this.KEY_MODEL_VEHICLE = PRIMARY_KEY + "modelVehicle";
        this.KEY_DATE_ITV = PRIMARY_KEY + "dateItvVehicle";
        this.KEY_DRIVING_VEHICLE = PRIMARY_KEY + "drivingVehicle";
        this.KEY_LOGO_VHEICLE = PRIMARY_KEY + "logoVehicle";
        this.KEY_REGISTRATION_NUMBER_VEHICLE = PRIMARY_KEY + "registrationNumberVehicle";
        this.vehicle = new Vehicle(  );

    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal objeto Vehicle
     */
    public void setvehicle( Vehicle vehicle ) {

        this.setDateString( KEY_BRAND_VEHICLE, vehicle.getBrand());
        this.setDateString( KEY_MODEL_VEHICLE, vehicle.getModel() );
        this.setDateString( KEY_DATE_ITV, vehicle.getDateITV());
        this.setDateInt( KEY_DRIVING_VEHICLE, vehicle.getDriving());
        this.setDateInt( KEY_LOGO_VHEICLE, vehicle.getLogoVehicle() );
        this.setDateString( KEY_REGISTRATION_NUMBER_VEHICLE, vehicle.getRegistrationNumber() );

    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo brand de un un objeto Vehicle
     */
    @Override
    public void setBrand(String brand) {
        this.setDateString( KEY_BRAND_VEHICLE, vehicle.getBrand() );
    }


    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo model de un un objeto Vehicle
     */
    @Override
    public void setModel(String model) {
        this.setDateString( KEY_MODEL_VEHICLE, vehicle.getModel() );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo driving de un un objeto Vehicle
     */
    @Override
    public void setDateITV(String dateITV) {
        this.setDateString( KEY_DATE_ITV, vehicle.getDateITV() );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo driving de un un objeto Vehicle
     */
    @Override
    public void setDriving(int driving) {
        this.setDateInt( KEY_DRIVING_VEHICLE, vehicle.getDriving() );
    }

    /*
     * @Author: Jorge.HL
     * Devuelve después de leer en fichero temporal objeto vehicle
     */
    public Vehicle getVehicle(){

        Vehicle vehicleTemp = new Vehicle( this.getDateInt( KEY_LOGO_VHEICLE),
                this.getDateString( KEY_REGISTRATION_NUMBER_VEHICLE),
                this.getDateString( KEY_BRAND_VEHICLE ),
                this.getDateString( KEY_MODEL_VEHICLE ),
                this.getDateString( KEY_DATE_ITV ),
                this.getDateInt( KEY_DRIVING_VEHICLE ));
        return vehicleTemp;
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo logoVehicle de un un objeto Vehicle
     */
    @Override
    public int getLogoVehicle() {
        return this.getDateInt( KEY_LOGO_VHEICLE );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo registrationNumber de un un objeto Vehicle
     */
    @Override
    public String getRegistrationNumber() {
        return this.getDateString( KEY_REGISTRATION_NUMBER_VEHICLE );

    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo brand de un un objeto Vehicle
     */
    @Override
    public String getBrand() {
        return  this.getDateString( KEY_BRAND_VEHICLE );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo modelVehicle de un un objeto Vehicle
     */
    @Override
    public String getModel() {
        return  this.getDateString( KEY_MODEL_VEHICLE );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo dateItv de un un objeto Vehicle
     */
    @Override
    public String getDateITV() {
        return  this.getDateString( KEY_DATE_ITV );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo driving de un un objeto Vehicle
     */
    @Override
    public int getDriving() {
        return  this.getDateInt( KEY_DRIVING_VEHICLE );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_BRAND_VEHICLE
     */
    public void removeKEY_BRAND_VEHICLE(){
        this.removeDate( KEY_BRAND_VEHICLE );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_BRAND_VEHICLE
     */
    public void removeKEY_MODEL_VEHICLE(){
        this.removeDate( KEY_MODEL_VEHICLE );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DATE_ITV
     */
    public void removeKEY_DATE_ITV(){
        this.removeDate( KEY_DATE_ITV );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DRIVING_VEHICLE
     */
    public void removeKEY_DRIVING_VEHICLE(){
        this.removeDate( KEY_DRIVING_VEHICLE );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_LOGO_VHEICLE
     */
    public void removeKEY_LOGO_VHEICLE(){
        this.removeDate( KEY_LOGO_VHEICLE );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_REGISTRATION_NUMBER_VEHICLE
     */
    public void removeKEY_REGISTRATION_NUMBER_VEHICLE(){
        this.removeDate( KEY_REGISTRATION_NUMBER_VEHICLE );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal los datos correspodiente al objeto Vehicle
     */
    public void removeVehicle(){
        this.removeDate( KEY_BRAND_VEHICLE );
        this.removeDate( KEY_MODEL_VEHICLE );
        this.removeDate( KEY_DATE_ITV );
        this.removeDate( KEY_DRIVING_VEHICLE );
        this.removeDate( KEY_LOGO_VHEICLE );
        this.removeDate( KEY_REGISTRATION_NUMBER_VEHICLE );
    }
}
