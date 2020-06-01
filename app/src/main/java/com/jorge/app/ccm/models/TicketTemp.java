package com.jorge.app.ccm.models;

import android.content.Context;

import java.io.Serializable;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de tipo TicketTemp.
 */
public class TicketTemp extends ProviderTemp implements Serializable {

    private String PRIMARY_KEY = getFILE_NAME() + "ticket";
    private String KEY_TICKET_NUMBER = PRIMARY_KEY + "Number";
    private String KEY_TICKET_DATE = PRIMARY_KEY + "Date";
    private String KEY_TICKET_TOTAL_IMPORT = "TotalImport";

    public TicketTemp(Context context,
                        String TAG ){
        super(context, TAG);
    }


    public TicketTemp(Context context,
                      String TAG,
                      MethodOfPlayment methodOfPlayment,
                      Provider provider,
                      Tickect tickect ) {
        super( context, TAG, methodOfPlayment, provider );
        this.setDateString( KEY_TICKET_NUMBER, tickect.getTickectNumber() );
        this.setDateString( KEY_TICKET_DATE, tickect.getTickectDate());
        this.setDateString( KEY_TICKET_TOTAL_IMPORT, tickect.getTickectTotalExpense() );
    }

    public TicketTemp(Context context,
                        String TAG,
                        int methodOfPlaymentLogo,
                        String methodOfPlaymentName,
                        String providerName,
                        String providerCifNif,
                        String providerTelephone,
                        String tickectNumber,
                        String tickectDate,
                        String tickectTotalExpense ) {
        super( context, TAG, methodOfPlaymentLogo, methodOfPlaymentName, providerName, providerCifNif, providerTelephone );
        this.setDateString( KEY_TICKET_NUMBER, tickectNumber );
        this.setDateString( KEY_TICKET_DATE, tickectDate );
        this.setDateString( KEY_TICKET_TOTAL_IMPORT, tickectTotalExpense );
    }

    public void setTickectNumber(String tickectNumber) {
        this.setDateString( KEY_TICKET_NUMBER, tickectNumber );
    }

    public void setTickectDate(String tickectDate) {
        this.setDateString( KEY_TICKET_DATE, tickectDate );
    }

    public void setTickectTotalExpense(String tickectTotalExpense) {
        this.setDateString( KEY_TICKET_TOTAL_IMPORT, tickectTotalExpense );
    }

    public String getTickectNumber() {
        return this.getDateString( KEY_TICKET_NUMBER );
    }

    public String getTickectDate() {
        return this.getDateString( KEY_TICKET_DATE );
    }

    public String getTickectTotalExpense() {
        return this.getDateString( KEY_TICKET_TOTAL_IMPORT );
    }

    public void removeTicket(){
        this.removeDate( KEY_TICKET_NUMBER );
        this.removeDate( KEY_TICKET_DATE );
        this.removeDate( KEY_TICKET_TOTAL_IMPORT );
        this.removeMethodOfPlayment();//<-- Heredada
        this.removeProvider();//<-- Heredada

    }

    public void removeTickectNumber() {
        this.removeDate( KEY_TICKET_NUMBER );
    }

    public void removeTickectDate() {
        this.removeDate( KEY_TICKET_DATE );
    }

    public void removeTickectTotalExpense() {
        this.removeDate( KEY_TICKET_TOTAL_IMPORT );
    }

}


