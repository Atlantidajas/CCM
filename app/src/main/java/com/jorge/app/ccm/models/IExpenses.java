package com.jorge.app.ccm.models;

public interface IExpenses {

    public void setTypeExpense();

    public void setTickectNumber();

    public void setComercialEstablishment();

    public void setDate();

    public void setMethodOfPlayment();

    public void setTotalExpense();

    public String getTypeExpense();

    public String getTickectNumber();

    public String getComercialEstablishment();

    public String getDate();

    public String getMethodOfPlayment();

    public float getTotalExpense();
}
