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


    /**
     * Permite Contruir un objeto de tipo TicketTemp, el mismo almacena otro de tipo Tickect en un fichero
     * @param context Context donse se va aplicar
     * @param TAG nombre de la actividad donde se aplicará
     */
    public TicketTemp(Context context,
                        String TAG ){
        super(context, TAG);
    }

    /**
     * Permite Contruir un objeto de tipo TicketTemp, el mismo almacena otro de tipo Tickect en un fichero
     * @param context Context donse se va aplicar
     * @param TAG String nombre de la actividad donde se aplicará
     * @param methodOfPlayment  objeto de tipo MethodOfPlayment
     * @param provider objeto de tipo Provider
     * @param tickect objeto de tipo Tickect
     */
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

    /**
     * Permite Contruir un objeto de tipo TicketTemp, el mismo almacena otro de tipo Tickect en un fichero
     * @param context Context donse se va aplicar
     * @param TAG String nombre de la actividad donde se aplicará
     * @param methodOfPlaymentLogo int id del logo con el que se almacena en recursos
     * @param methodOfPlaymentName String nombre del método de pago
     * @param providerName String nombre del proveedor
     * @param providerCifNif String Cif o nif del proveedor
     * @param providerTelephone String número de teléfono del proveedor
     * @param tickectNumber String número de tickect
     * @param tickectDate String fecha del tickect
     * @param tickectTotalExpense String importe total del tickect
     */
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

    /**
     * Permite asignar el valor número de tickect
     * @param tickectNumber String número de tickect
     */
    public void setTickectNumber(String tickectNumber) {
        this.setDateString( KEY_TICKET_NUMBER, tickectNumber );
    }

    /**
     * Permite aisgnar el valor de fecha del tickect
     * @param tickectDate String fecha del tickect
     */
    public void setTickectDate(String tickectDate) {
        this.setDateString( KEY_TICKET_DATE, tickectDate );
    }

    /**
     * Permite asignar el valor de importe total del tickect
     * @param tickectTotalExpense String importe total del tickect
     */
    public void setTickectTotalExpense(String tickectTotalExpense) {
        this.setDateString( KEY_TICKET_TOTAL_IMPORT, tickectTotalExpense );
    }

    /**
     * Devuelve número de tickect
     * @return String número de tickect
     */
    public String getTickectNumber() {
        return this.getDateString( KEY_TICKET_NUMBER );
    }

    /**
     * Devuelve fecha del tickect
     * @return String fecha del tickect
     */
    public String getTickectDate() {
        return this.getDateString( KEY_TICKET_DATE );
    }

    /**
     * Devuelve el importe total del tickect
     * @returnString String importe total del tickect
     */
    public String getTickectTotalExpense() {
        return this.getDateString( KEY_TICKET_TOTAL_IMPORT );
    }

    /**
     * Borrar todos los datos correspondientes al ticket del fichero temporal
     */
    public void removeTicket(){
        this.removeDate( KEY_TICKET_NUMBER );
        this.removeDate( KEY_TICKET_DATE );
        this.removeDate( KEY_TICKET_TOTAL_IMPORT );
        this.removeMethodOfPlayment();//<-- Heredada
        this.removeProvider();//<-- Heredada

    }

    /**
     * Borrar el datos correspondiente a número de ticket del fichero temporal
     */
    public void removeTickectNumber() {
        this.removeDate( KEY_TICKET_NUMBER );
    }

    /**
     * Borrar el datos correspondiente a fecha del ticket del fichero temporal
     */
    public void removeTickectDate() {
        this.removeDate( KEY_TICKET_DATE );
    }

    /**
     Borrar el datos correspondiente al importe total del ticket del fichero temporal
     */
    public void removeTickectTotalExpense() {
        this.removeDate( KEY_TICKET_TOTAL_IMPORT );
    }

}


