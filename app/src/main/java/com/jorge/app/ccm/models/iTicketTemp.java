package com.jorge.app.ccm.models;

public interface iTicketTemp {

    /*
     * @Author: Jorge.HL
     */
    public void setTicket( Tickect ticket );
    public void setTickectNumber(String tickectNumber);
    public void setTickectDate(String tickectDate);
    public void setTickectTotalExpense(String tickectTotalExpense);
    public String getTickectNumber();
    public String getTickectDate();
    public String getTickectTotalExpense();
    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment);
    public int getMethodOfPlaymentLogo();
    public String getMethodOfPlaymentNameMethodOfPlayment();
    public void removeTicket();
    public void removeKEY_TOTAL_IMPORT();
    public void removeKEY_TICKET_NUMBER();
    public void removeKEY_DATE_TICKET();
    public void removeKEY_METHOD_OF_PLAYMENT();


}
