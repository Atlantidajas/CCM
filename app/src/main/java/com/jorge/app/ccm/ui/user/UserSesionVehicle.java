package com.jorge.app.ccm.ui.user;

import com.jorge.app.ccm.ui.vehicles.Vehicle;
import com.jorge.app.ccm.utils.DateHoursUtil;


public class UserSesionVehicle {

    private String sesionInitDate;
    private String sesinoInitHours;
    private String sesionEndDate;
    private String sesinoEndHours;
    private User user;

    private Vehicle vehicle;

    public UserSesionVehicle( Vehicle vehicle, boolean session) {

        DateHoursUtil dateHoursUtil = new DateHoursUtil();
        this.user = new User();
        this.vehicle = vehicle;
        // Controlo que si el objeto es creado para registrar un inicio de session
        if ( session ) {
            this.sesionInitDate = dateHoursUtil.getDateFormatString();
            this.sesinoInitHours = dateHoursUtil.getHourFormatString();
        }
        // Controlo que si el objeto es creado para registrar un fin de session
        if ( session == false) {
            this.sesionEndDate = dateHoursUtil.getDateFormatString();
            this.sesinoEndHours = dateHoursUtil.getHourFormatString();
        }
    }

    public String getSesionInitDate() {
        return sesionInitDate;
    }

    public String getSesinoInitHours() {
        return sesinoInitHours;
    }

    public String getSesionEndDate() {
        return sesionEndDate;
    }

    public String getSesinoEndHours() {
        return sesinoEndHours;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public User getUser() {
        return user;
    }
}
