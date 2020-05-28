package com.jorge.app.ccm.models;


public class Tickect extends Provider {

    private String tickectNumber;
    private String tickectDate;
    private String tickectTotalExpense;

    public Tickect(MethodOfPlayment methodOfPlayment,
                   Provider provider,
                   String tickectNumber,
                   String tickectDate,
                   String tickectTotalExpense) {
        super( methodOfPlayment.getMethodOfPlaymentLogo(),
                methodOfPlayment.getMethodOfPlaymentName(),
                provider.getProviderName(),
                provider.getProviderCifNif(),
                provider.getProviderTelephone() );
                this.tickectNumber = tickectNumber;
                this.tickectDate = tickectDate;
                this.tickectTotalExpense = tickectTotalExpense;
    }


    public Tickect(int methodOfPlaymentLogo,
                   String methodOfPlaymentName,
                   String providerName,
                   String providerCifNif,
                   String providerTelephone,
                   String tickectNumber,
                   String tickectDate,
                   String tickectTotalExpense) {
        super( methodOfPlaymentLogo, methodOfPlaymentName,
                providerName,
                providerCifNif,
                providerTelephone );
        this.tickectNumber = tickectNumber;
        this.tickectDate = tickectDate;
        this.tickectTotalExpense = tickectTotalExpense;
    }

    public Tickect(ExpenseTemp expenseTemp) {
        super( expenseTemp );
        this.tickectNumber = expenseTemp.getTickectNumber();
        this.tickectDate = expenseTemp.getTickectDate();
        this.tickectTotalExpense = expenseTemp.getTickectTotalExpense();
    }

    protected void setTickectNumber(String tickectNumber) {
        this.tickectNumber = tickectNumber;
    }

    protected void setTickectDate(String tickectDate) {
        this.tickectDate = tickectDate;
    }

    protected void setTickectTotalExpense(String tickectTotalExpense) {
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
