package com.jorge.app.ccm.ui.user;

import com.jorge.app.ccm.ui.vehicles.Vehicle;
import com.jorge.app.ccm.utils.DateHoursUtil;


public class UserSesionVehicle extends DateHoursUtil  {

   private final String DATE_SESION;
   private Vehicle vehicle;
   private User user;

    public UserSesionVehicle( Vehicle vehicle, User user) {

        this.DATE_SESION = this.getHourdateFormatString();
        this.vehicle = vehicle;
        this.user = user;
    }

    public String getDATE_SESION() {
        return DATE_SESION;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public User getUser() {
        return user;
    }
}
