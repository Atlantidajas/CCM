package com.jorge.app.ccm.models.ticket;


/*
* @Author: Jorge.HL
*/

public interface ITicket {

    public void setTickectNumber(String tickectNumber);

    public void setDate(String date);

    public void setMethodOfPlayment(String methodOfPlayment);

    public void setTotalExpense(float totalExpense);

    public String getTickectNumber();

    public String getDate();

    public String getMethodOfPlayment();

    public float getTotalExpense();
}
