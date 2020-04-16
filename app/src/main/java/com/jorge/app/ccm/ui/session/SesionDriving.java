package com.jorge.app.ccm.ui.session;

import com.jorge.app.ccm.ui.user.User;
import com.jorge.app.ccm.ui.vehicles.Vehicle;
import com.jorge.app.ccm.utils.DateHoursUtil;


public class SesionDriving {

    private User user;
    private String date;
    private String hours;
    private String typeSesion;
    private Vehicle vehicle;

    public SesionDriving(boolean typeSesion, Vehicle vehicle) {

        this.user = new User();
        this.vehicle = vehicle;
        DateHoursUtil dateHoursUtil = new DateHoursUtil();

        // Controlo que si el objeto es creado para registrar un inicio de session
        if ( typeSesion ) {
            this.typeSesion = "Start";
            this.date = dateHoursUtil.getDateFormatString();
            this.hours = dateHoursUtil.getHourFormatString();
        }
        // Controlo que si el objeto es creado para registrar un fin de session
        if ( typeSesion == false) {
            this.typeSesion = "End";
            this.date = dateHoursUtil.getDateFormatString();
            this.hours = dateHoursUtil.getHourFormatString();
        }
        this.user = new User();
        this.vehicle = vehicle;

    }
    public String getTypeSesion() {
        return typeSesion;
    }

    public String getDate() {
        return date;
    }

    public String getHours() {
        return hours;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}
