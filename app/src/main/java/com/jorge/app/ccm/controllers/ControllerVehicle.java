package com.jorge.app.ccm.controllers;

public class ControllerVehicle {

    private ControllerVehicleStatus controllerVehicleStatus;
    private ControllerVehiclesSesion controllerVehiclesSesion;

    public ControllerVehicle() {
        this.controllerVehicleStatus = new ControllerVehicleStatus();
        this.controllerVehiclesSesion = new ControllerVehiclesSesion();
    }

    public ControllerVehicleStatus getControllerVehicleStatus() {
        return controllerVehicleStatus;
    }

    public ControllerVehiclesSesion getControllerVehiclesSesion() {
        return controllerVehiclesSesion;
    }
}
