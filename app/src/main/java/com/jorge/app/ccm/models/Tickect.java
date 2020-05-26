package com.jorge.app.ccm.models;


public class Tickect extends Provider {

    private String tickectNumber;
    private String tickectDate;
    private String tickectTotalExpense;



    public Tickect(String nameMethodOfPlayment,
                   String nameProvider,
                   String cifNifProvider,
                   String telephoneProvider,
                   String tickectNumber,
                   String date,
                   String totalExpense) {
        super( nameMethodOfPlayment, nameProvider, cifNifProvider, telephoneProvider );
        this.tickectNumber = tickectNumber;
        this.tickectDate = date;
        this.tickectTotalExpense = totalExpense;
    }

    public void setTickectNumber(String tickectNumber) {
        this.tickectNumber = tickectNumber;
    }

    public void setTickectDate(String tickectDate) {
        this.tickectDate = tickectDate;
    }

    public void setTickectTotalExpense(String tickectTotalExpense) {
        this.tickectTotalExpense = tickectTotalExpense;
    }

    public String getTickectNumber() {
        return tickectNumber;
    }

    public String getTickectDate() {
        return tickectDate;
    }

    public String getTickectTotalExpense() {
        return tickectTotalExpense;
    }

}
