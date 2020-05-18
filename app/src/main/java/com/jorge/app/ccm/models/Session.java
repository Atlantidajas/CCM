package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.utils.DateHoursUtil;

import java.io.Serializable;

public class Session implements Serializable {

    private String date;
    private String hours;
    private String typeSesion;

    public Session( String typeSesion) {
        DateHoursUtil dateHoursUtil = new DateHoursUtil();
        this.date = dateHoursUtil.getDateFormatString();
        this.hours = dateHoursUtil.getHourFormatString();
        this.typeSesion = typeSesion;
    }

    public Session( DataSnapshot dataSnapshotSesion ) {
        DateHoursUtil dateHoursUtil = new DateHoursUtil();
        this.date = dateHoursUtil.getDateFormatString();
        this.hours = dateHoursUtil.getHourFormatString();
        this.typeSesion = String.valueOf( dataSnapshotSesion.child("typeSesion").getValue() );
        this.date = String.valueOf( dataSnapshotSesion.child("date").getValue() );
        this.hours = String.valueOf( dataSnapshotSesion.child("hours").getValue() );
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setTypeSesion(String typeSesion) {
        this.typeSesion = typeSesion;
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

}
