package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.utils.DateHoursUtil;

import java.io.Serializable;

public class Session implements Serializable {

    private String sessionDate;
    private String sessionHours;
    private String sessionTypeSesion;

    public Session( String typeSesion) {
        DateHoursUtil dateHoursUtil = new DateHoursUtil();
        this.sessionDate = dateHoursUtil.getDateFormatString();
        this.sessionHours = dateHoursUtil.getHourFormatString();
        this.sessionTypeSesion = typeSesion;
    }

    public Session( DataSnapshot dataSnapshotSesion ) {
        DateHoursUtil dateHoursUtil = new DateHoursUtil();
        this.sessionDate = dateHoursUtil.getDateFormatString();
        this.sessionHours = dateHoursUtil.getHourFormatString();
        this.sessionDate = String.valueOf( dataSnapshotSesion.child("sessionDate").getValue() );
        this.sessionHours = String.valueOf( dataSnapshotSesion.child("sessionHours").getValue() );
        this.sessionTypeSesion = String.valueOf( dataSnapshotSesion.child("sessionTypeSesion").getValue() );
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setSessionHours(String sessionHours) {
        this.sessionHours = sessionHours;
    }

    public void setSessionTypeSesion(String sessionTypeSesion) {
        this.sessionTypeSesion = sessionTypeSesion;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public String getSessionHours() {
        return sessionHours;
    }

    public String getSessionTypeSesion() {
        return sessionTypeSesion;
    }
}
