package com.jorge.app.ccm.models;

import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.models.VehicleTemp;

public interface iVehicleTemp {


    /*
     * @Author: Jorge.HL
     */
    public void setvehicle( Vehicle vehicle );
    public void setRegistrationNunber(String registrationNunber);
    public void setBrand(String brand);
    public void setModel(String model);
    public void setDateITV(String dateITV);
    public void setDriving(int driving);
    public Vehicle getVehicleTemp();
    public int getLogoVehicle();
    public String getRegistrationNumber();
    public String getBrand();
    public String getModel();
    public String getDateITV();
    public int getDriving();
    public void removeKEY_BRAND_VEHICLE();
    public void removeKEY_MODEL_VEHICLE();
    public void removeKEY_DATE_ITV();
    public void removeKEY_DRIVING_VEHICLE();
    public void removeKEY_LOGO_VHEICLE();
    public void removeKEY_REGISTRATION_NUMBER_VEHICLE();
    public void removeVehicle();
}
