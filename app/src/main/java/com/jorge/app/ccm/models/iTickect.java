package com.jorge.app.ccm.models;

/**
 * @ Implemeta un objeto de tipo Tickect
 */
public interface iTickect {

    public void setTickectNumber(String tickectNumber);
    public void setTickectDate(String tickectDate);
    public void setTickectTotalExpense(String tickectTotalExpense);
    public String getTickectNumber();
    public String getTickectDate();
    public String getTickectTotalExpense();
    public void setProviderName(String providerName);
    public void setProviderCifNif(String providerCifNif);
    public void setProviderTelephone(String providerTelephone);
    public String getProviderName();
    public String getProviderCifNif();
    public String getProviderTelephone();
    public void setMethodOfPlaymentLogo(int methodOfPlaymentLogo);
    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment);
    public int getMethodOfPlaymentLogo();
    public String getMethodOfPlaymentNameMethodOfPlayment();
}
