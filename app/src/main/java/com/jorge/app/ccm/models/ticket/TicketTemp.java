package com.jorge.app.ccm.models.ticket;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de tipo TicketTemp.
 */
public class TicketTemp extends DatesTemp implements ITicket{

    private final String PRIMARY_KEY;
    private final String KEY_TICKET_NUMBER;
    private final String KEY_DATE_TICKET;
    private final String KEY_METHOD_OF_PLAYMENT;
    private final String KEY_TOTAL_IMPORT;

    public TicketTemp(Context context, String TAG) {
        super( context, TAG );
        PRIMARY_KEY = getFILE_NAME() + "ticket";
        this.KEY_TICKET_NUMBER = PRIMARY_KEY + "ticketNumber";
        this.KEY_DATE_TICKET = PRIMARY_KEY + "ticketDate";
        this.KEY_METHOD_OF_PLAYMENT = PRIMARY_KEY + "methodOfPlayment";
        this.KEY_TOTAL_IMPORT = "totalImport";

    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal objeto Ticket
     */
    public void setTicket( Tickect ticket ) {
        this.setDateString( KEY_TICKET_NUMBER, ticket.getTickectNumber() );
        this.setDateString( KEY_DATE_TICKET, ticket.getDate() );
        this.setDateString( KEY_METHOD_OF_PLAYMENT, ticket.getMethodOfPlayment() );
        this.setDateFloat( KEY_TOTAL_IMPORT, ticket.getTotalExpense() );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo ticketNumber de un un objeto Ticket
     */
    @Override
    public void setTickectNumber(String tickectNumber) {
        this.setDateString( KEY_TICKET_NUMBER, tickectNumber );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo date de un un objeto Ticket
     */
    @Override
    public void setDate(String date) {
        this.setDateString( KEY_DATE_TICKET, date );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo methodPlament de un un objeto Ticket
     */
    @Override
    public void setMethodOfPlayment(String methodOfPlayment) {
        this.setDateString( KEY_METHOD_OF_PLAYMENT, methodOfPlayment );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo totalExpense de un un objeto Ticket
     */
    @Override
    public void setTotalExpense(float totalExpense) {
        this.setDateFloat( KEY_TOTAL_IMPORT, totalExpense );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo tickectNumber de un un objeto Ticket
     */
    @Override
    public String getTickectNumber() {
        return this.getDateString( KEY_TICKET_NUMBER );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo date de un un objeto Ticket
     */
    @Override
    public String getDate() {
        return this.getDateString( KEY_DATE_TICKET );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo methodOfPlayment de un un objeto Ticket
     */
    @Override
    public String getMethodOfPlayment() {
        return this.getDateString( KEY_METHOD_OF_PLAYMENT );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo totalExpense de un un objeto Ticket
     */
    @Override
    public float getTotalExpense() {
        return this.getDateFloat( KEY_TOTAL_IMPORT );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal los datos correspodiente al objeto Ticket
     */
    public void removeTicket(){
        this.removeDate( KEY_TICKET_NUMBER );
        this.removeDate( KEY_DATE_TICKET );
        this.removeDate( KEY_METHOD_OF_PLAYMENT );
        this.removeDate( KEY_TOTAL_IMPORT );
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
        this.removeDate( KEY_DATE_TICKET );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_METHOD_OF_PLAYMENT
     */
    public void removeKEY_METHOD_OF_PLAYMENT(){
        this.removeDate( KEY_METHOD_OF_PLAYMENT );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave KEY_TOTAL_IMPORT
     */
    public void removeKEY_TOTAL_IMPORT(){
        this.removeDate( KEY_TOTAL_IMPORT );
    }

    /*
     * @Author: Jorge.HL
     * Devuelve la key principla con la que se guarda en el fichero temporal el objeto de typo Ticket
     */
    public String getPRIMARY_KEY() {
        return PRIMARY_KEY;
    }

    /*
     * @Author: Jorge.HL
     * Devuelve la key con la que se guarda en el fichero temporal el atributo logo
     */
    public String getKEY_TICKET_NUMBER() {
        return KEY_TICKET_NUMBER;
    }

    /*
     * @Author: Jorge.HL
     * Devuelve la KEY_DATE_TICKET con la que se guarda en el fichero temporal el atributo dateTicket
     */
    public String getKEY_DATE_TICKET() {
        return KEY_DATE_TICKET;
    }

    /*
     * @Author: Jorge.HL
     * Devuelve la KEY_METHOD_OF_PLAYMENT con la que se guarda en el fichero temporal el atributo methodPlayment
     */
    public String getKEY_METHOD_OF_PLAYMENT() {
        return KEY_METHOD_OF_PLAYMENT;
    }

    /*
     * @Author: Jorge.HL
     * Devuelve la KEY_TOTAL_IMPORT con la que se guarda en el fichero temporal el atributo totalImport
     */
    public String getKEY_TOTAL_IMPORT() {
        return KEY_TOTAL_IMPORT;
    }
}
