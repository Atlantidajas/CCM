package com.jorge.app.ccm.models;

public class Provider extends MethodOfPlayment  {

    private String providerName;
    private String providerCifNif;
    private String providerTelephone;

    public Provider(String nameMethodOfPlayment, String providerName, String providerCifNif, String providerTelephone) {
        super( nameMethodOfPlayment );
        this.providerName = providerName;
        this.providerCifNif = providerCifNif;
        this.providerTelephone = providerTelephone;
    }

    public Provider(int logoMethodOfPlayment, String nameMethodOfPlayment, String providerName, String providerCifNif, String providerTelephone) {
        super( logoMethodOfPlayment, nameMethodOfPlayment );
        this.providerName = providerName;
        this.providerCifNif = providerCifNif;
        this.providerTelephone = providerTelephone;
    }

    protected void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    protected void setProviderCifNif(String providerCifNif) {
        this.providerCifNif = providerCifNif;
    }

    protected void setProviderTelephone(String providerTelephone) {
        this.providerTelephone = providerTelephone;
    }

    protected String getProviderName() {
        return providerName;
    }

    protected String getProviderCifNif() {
        return providerCifNif;
    }

    protected String getProviderTelephone() {
        return providerTelephone;
    }
}
