package com.jorge.app.ccm.models;

public interface iVehicleTemp {

    public void setVehiclelogo(int vehiclelogo);
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber);
    public void setVehicleBrand(String vehicleBrand);
    public void setVehicleModel(String vehicleModel);
    public void setVehicleDateITV(String vehicleDateITV);
    public void setVehicleDriving(int vehicleDriving);
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent);
    public int getVehiclelogo();
    public String getVehicleRegistrationNumber();
    public String getVehicleBrand();
    public String getVehicleModel();
    public String getVehicleDateITV();
    public int getVehicleDriving();
    public String getVehicleDrivingCurrent();
    public void removeVehicle();
    public void removeVehicleLogo();
    public void removeVehicleRegistrationNumber();
    public void removeVehicleBrand();
    public void removeVehicleModel();
    public void removeVehicleDateItv();
    public void removeVehicleDriving();
}
