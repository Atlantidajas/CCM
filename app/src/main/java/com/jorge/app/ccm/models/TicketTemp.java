package com.jorge.app.ccm.models;

import android.content.Context;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de tipo TicketTemp.
 */
public class TicketTemp extends ProviderTemp implements iTickect{

    private final String PRIMARY_KEY;
    private final String KEY_TICKET_NUMBER;
    private final String KEY_TICKET_DATE;
    private final String KEY_TICKET_METHOD_OF_PLAYMENT;
    private final String KEY_TICKET_TOTAL_IMPORT;

    public TicketTemp(Context context, String TAG) {
        super( context, TAG );
        PRIMARY_KEY = getFILE_NAME() + "ticket";
        this.KEY_TICKET_NUMBER = PRIMARY_KEY + "number";
        this.KEY_TICKET_DATE = PRIMARY_KEY + "date";
        this.KEY_TICKET_METHOD_OF_PLAYMENT = PRIMARY_KEY + "methodOfPlayment";
        this.KEY_TICKET_TOTAL_IMPORT = "totalImport";
    }



    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal objeto Ticket
     */
    public void setTicket( Tickect ticket ) {
        this.setDateString( KEY_TICKET_NUMBER, ticket.getTickectNumber() );
        this.setDateString( KEY_TICKET_DATE, ticket.getTickectDate() );
        this.setDateString( KEY_TICKET_METHOD_OF_PLAYMENT, ticket.getMethodOfPlaymentNameMethodOfPlayment() );
        this.setDateString( KEY_TICKET_TOTAL_IMPORT, ticket.getTickectTotalExpense() );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo ticketNumber de un un objeto Ticket
     */
    public void setTickectNumber(String tickectNumber) {
        this.setDateString( KEY_TICKET_NUMBER, tickectNumber );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo dateTicket de un un objeto Ticket
     */

    public void setTickectDate(String tickectDate) {
        this.setDateString( KEY_TICKET_DATE, tickectDate );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo totalImporTickect de un un objeto Ticket
     */

    public void setTickectTotalExpense(String tickectTotalExpense) {
        this.setDateString( KEY_TICKET_TOTAL_IMPORT, tickectTotalExpense );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo tickectNumber de un un objeto Ticket
     */

    public String getTickectNumber() {
        return this.getDateString( KEY_TICKET_NUMBER );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo dateTicket de un un objeto Ticket
     */

    public String getTickectDate() {
        return this.getDateString( KEY_TICKET_DATE );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo totalTicket -> en este caso un String de un un objeto Ticket
     */

    public String getTickectTotalExpense() {
        return this.getDateString( KEY_TICKET_TOTAL_IMPORT );
    }


    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo methodPlament de un un objeto Ticket
     */
    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.setDateString( KEY_TICKET_METHOD_OF_PLAYMENT, methodOfPlaymentNameMethodOfPlayment );
    }

    public int getMethodOfPlaymentLogo() {
        return 0;
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo methodOfPlayment de un un objeto Ticket
     */
    public String getMethodOfPlaymentNameMethodOfPlayment() {
        return this.getDateString( KEY_TICKET_METHOD_OF_PLAYMENT );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal los datos correspodiente al objeto Ticket
     */
    public void removeTicket(){
        this.removeDate( KEY_TICKET_NUMBER );
        this.removeDate( KEY_TICKET_DATE );
        this.removeDate( KEY_TICKET_METHOD_OF_PLAYMENT );
        this.removeDate( KEY_TICKET_TOTAL_IMPORT );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_TOTAL_IMPORT
     */
    public void removeKEY_TOTAL_IMPORT(){
        this.removeDate( KEY_TICKET_TOTAL_IMPORT );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_TICKET_NUMBER
     */
    public void removeKEY_TICKET_NUMBER(){
        this.removeDate( KEY_TICKET_NUMBER );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_DATE_TICKET
     */
    public void removeKEY_DATE_TICKET(){
        this.removeDate( KEY_TICKET_DATE );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_METHOD_OF_PLAYMENT
     */
    public void removeKEY_METHOD_OF_PLAYMENT(){
        this.removeDate( KEY_TICKET_METHOD_OF_PLAYMENT );
    }

}





