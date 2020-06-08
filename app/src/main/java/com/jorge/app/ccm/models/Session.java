package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.utils.DateHoursUtil;

import java.io.Serializable;


/**
 *  @Author: Jorge.HL
 */
public class Session implements Serializable {

    private String sessionDate;
    private String sessionHours;
    private String sessionTypeSesion;

    /**
     * Permite contruir un objeto Session
     * @param typeSesion String tipo de sesion de inicio (Create, Init, End )
     */
    public Session( String typeSesion) {
        DateHoursUtil dateHoursUtil = new DateHoursUtil();
        this.sessionDate = dateHoursUtil.getDateFormatString();
        this.sessionHours = dateHoursUtil.getHourFormatString();
        this.sessionTypeSesion = typeSesion;
    }

    /**
     * Permite contruir un objeto de tipo Session a partir de los datos obtenidos de la db Firebase
     * @param dataSnapshotSesion objeto DataSnapshotSesion
     */
    public Session( DataSnapshot dataSnapshotSesion ) {
        DateHoursUtil dateHoursUtil = new DateHoursUtil();
        this.sessionDate = dateHoursUtil.getDateFormatString();
        this.sessionHours = dateHoursUtil.getHourFormatString();
        this.sessionDate = String.valueOf( dataSnapshotSesion.child("sessionDate").getValue() );
        this.sessionHours = String.valueOf( dataSnapshotSesion.child("sessionHours").getValue() );
        this.sessionTypeSesion = String.valueOf( dataSnapshotSesion.child("sessionTypeSesion").getValue() );
    }

    /**
     * Fecha del hecho de la sesión
     * @param sessionDate
     */
    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    /**
     * Hora del hecho de la sesión
     * @param sessionHours
     */
    public void setSessionHours(String sessionHours) {
        this.sessionHours = sessionHours;
    }

    /**
     * Tipo de sesión
     * @param sessionTypeSesion String
     */
    public void setSessionTypeSesion(String sessionTypeSesion) {
        this.sessionTypeSesion = sessionTypeSesion;
    }

    /**
     * Fecha del hecho de la sesión
     * @return String fecha 
     */
    public String getSessionDate() {
        return sessionDate;
    }

    /**
     * Hora del hecho de la sesión
     * @return String hora
     */
    public String getSessionHours() {
        return sessionHours;
    }

    /**
     * Tipo de sesión
     * @return String tipo de sesión
     */
    public String getSessionTypeSesion() {
        return sessionTypeSesion;
    }
}
