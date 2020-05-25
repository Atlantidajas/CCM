package com.jorge.app.ccm.models.ticket;

public interface ITicketTemp {

    public void setTicket( Tickect ticket );
    public void setTickectNumber(String tickectNumber);
    public void setDate(String date);
    public void setMethodOfPlayment(String methodOfPlayment);
    public void setTotalExpense(float totalExpense);
    public String getTickectNumber();
    public String getDate();
    public String getMethodOfPlayment();
    public float getTotalExpense();
    public void removeTicket();
    public void removeKEY_TICKET_NUMBER();
    public void removeKEY_DATE_TICKET();
    public void removeKEY_METHOD_OF_PLAYMENT();
    public void removeKEY_TOTAL_IMPORT();
    public String getPRIMARY_KEY();
    public String getKEY_TICKET_NUMBER();
    public String getKEY_DATE_TICKET();
    public String getKEY_METHOD_OF_PLAYMENT();
    public String getKEY_TOTAL_IMPORT();
}
