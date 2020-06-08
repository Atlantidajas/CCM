package com.jorge.app.ccm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Jorge H
 * Permite trabajar con los con la clase Date, dando informaciones de fechas y horas del sistema
 */

public class DateHoursUtil {

    private final Date DATE;
    private final DateFormat HOURT_FORMAT;
    private final DateFormat DATE_FORMAT;
    private final DateFormat HOUR_DATE_FORMAT;
    private String hourFormatString;
    private String dateFormarString;
    private String hourDateFormatString;

    public DateHoursUtil() {
        this.DATE = new Date();
        this.HOURT_FORMAT = new SimpleDateFormat("HH:mm:ss");
        this.DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        this.HOUR_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    }

    /**
     * @return Retorna un objeto de tipo DateFormat con la hora minitos y segundos del dispositivo con formato :
     */
    public DateFormat getHourFormat() {
        return HOURT_FORMAT;
    }

    /**
     * @return Retorna un objeto de tipo DateFormat con el día, mes y año del dispositivo con formato -
     */
    public DateFormat getDateFormat() {
        return DATE_FORMAT;
    }

    /**
     * @return Retorna Retorna un objeto de tipo DateFormat con la hora minitos y segundos del dispositivo
     * con formato: así como con el día, mes y año del dispositivo con formato -
     */
    public DateFormat getHourdateFormat() {
        return HOUR_DATE_FORMAT;
    }

    /**
     *
     * @return Retorna un objeto de tipo String con la hora minitos y segundos del dispositivo con formato :
     */
    public String getHourFormatString() {
        return hourFormatString = this.HOURT_FORMAT.format(this.DATE);
    }

    /**
     *
     * @return Retorna un objeto de tipo String con el día, mes y año del dispositivo con formato -
     */
    public String getDateFormatString() {
        return dateFormarString = this.DATE_FORMAT.format(this.DATE);
    }

    /**
     *
     * @return Retorna un objeto de tipo String con la hora minitos y segundos del dispositivo con formato :
     * y el día, mes y año del dispositivo con formato -
     */
    public String getHourdateFormatString() {
        return hourDateFormatString = HOUR_DATE_FORMAT.format(DATE);
    }
}
