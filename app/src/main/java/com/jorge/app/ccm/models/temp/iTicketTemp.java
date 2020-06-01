package com.jorge.app.ccm.models.temp;

public interface iTicketTemp {

    public void setTickectNumber(String tickectNumber);
    public void setTickectDate(String tickectDate);
    public void setTickectTotalExpense(String tickectTotalExpense);
    public String getTickectNumber();
    public String getTickectDate();
    public String getTickectTotalExpense();
    public void removeTicket();
    public void removeTickectNumber();
    public void removeTickectDate();
    public void removeTickectTotalExpense();
    public void setProviderName(String providerName);
    public void setProviderCifNif(String providerCifNif);
    public void setProviderTelephone(String providerTelephone);
    public String getProviderName();
    public String getProviderCifNif();
    public String getProviderTelephone();
    public void removeProvider();
    public void removeProviderName();
    public void removeProviderCifNif();
    public void removeProviderTelephone();
    public void setMethodOfPlaymentValueLogo(int methodOfPlaymentValueLogo);
    public void setMethodOfPlaymentValueName(String methodOfPlaymentValueName);
    public int getMethodOfPlaymentValueLogo();
    public String getMethodOfPlaymentValueName();
    public void removeMethodOfPlayment();
    public void removeMethodOfPlaymentLogo();
    public void removeMethodOfPlaymentName();



}
