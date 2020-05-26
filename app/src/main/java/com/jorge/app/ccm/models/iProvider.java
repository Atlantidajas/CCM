package com.jorge.app.ccm.models;

public interface iProvider {

    public void setProviderName(String providerName);
    public void setProviderCifNif(String providerCifNif);
    public void setProviderTelephone(String providerTelephone);
    public String getProviderName();
    public String getProviderCifNif();
    public String getProviderTelephone();
}
