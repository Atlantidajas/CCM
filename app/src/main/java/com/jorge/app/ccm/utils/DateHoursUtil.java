package com.jorge.app.ccm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public DateFormat getHourFormat() {
        return HOURT_FORMAT;
    }

    public DateFormat getDateFormat() {
        return DATE_FORMAT;
    }

    public DateFormat getHourdateFormat() {
        return HOUR_DATE_FORMAT;
    }

    public String getHourFormatString() {
        return hourFormatString = this.HOURT_FORMAT.format(this.DATE);
    }

    public String getDateFormatString() {
        return dateFormarString = this.DATE_FORMAT.format(this.DATE);
    }

    public String getHourdateFormatString() {
        return hourDateFormatString = HOUR_DATE_FORMAT.format(DATE);
    }
}
