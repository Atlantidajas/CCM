package com.jorge.app.ccm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHoursUtil {

    private Date date;
    private DateFormat hourFormat;
    private DateFormat dateFormat;
    private DateFormat hourdateFormat;
    private String hourFormatString;
    private String dateFormatString;
    private String hourdateFormatString;

    public DateHoursUtil() {
        this.date = new Date();
        this.hourFormat = new SimpleDateFormat("HH:mm:ss");
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.hourdateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    }

    public DateFormat getHourFormat() {
        return hourFormat;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public DateFormat getHourdateFormat() {
        return hourdateFormat;
    }

    public String getHourFormatString() {
        return hourFormatString = this.hourFormat.format(this.date);
    }

    public String getDateFormatString() {
        return dateFormatString = this.dateFormat.format(this.date);
    }

    public String getHourdateFormatString() {
        return hourdateFormatString = hourdateFormat.format(date);
    }
}
