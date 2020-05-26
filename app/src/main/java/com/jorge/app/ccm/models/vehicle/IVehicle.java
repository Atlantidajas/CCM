package com.jorge.app.ccm.models.vehicle;

public interface IVehicle {

    public void setRegistrationNumber(String registrationNumber);
    public void setBrand(String brand);
    public void setModel(String model);
    public void setDateITV(String dateITV);
    public void setDriving(int driving);
    public int getLogoVehicle();
    public String getRegistrationNumber();
    public String getBrand();
    public String getModel();
    public String getDateITV();
    public int getDriving();
}
