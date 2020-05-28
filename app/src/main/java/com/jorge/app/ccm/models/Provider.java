package com.jorge.app.ccm.models;

public class Provider extends MethodOfPlayment {

    private String providerName;
    private String providerCifNif;
    private String providerTelephone;

    public Provider( MethodOfPlayment methodOfPlayment,
                     String providerName, String providerCifNif,
                     String providerTelephone) {
        super( methodOfPlayment.getMethodOfPlaymentLogo(), methodOfPlayment.getMethodOfPlaymentName() );
        this.providerName = providerName;
        this.providerCifNif = providerCifNif;
        this.providerTelephone = providerTelephone;
    }

    public Provider(int methodOfPlaymentLogo,
                    String methodOfPlaymentNameMethodOfPlayment,
                    String providerName, String providerCifNif,
                    String providerTelephone) {
        super( methodOfPlaymentLogo, methodOfPlaymentNameMethodOfPlayment );
        this.providerName = providerName;
        this.providerCifNif = providerCifNif;
        this.providerTelephone = providerTelephone;
    }

    public Provider( ExpenseTemp expenseTemp ) {
        super( expenseTemp );
        this.providerName = expenseTemp.getProviderName();
        this.providerCifNif = expenseTemp.getProviderCifNif();
        this.providerTelephone = expenseTemp.getProviderTelephone();
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

    public String getProviderName() {
        return providerName;
    }

    public String getProviderCifNif() {
        return providerCifNif;
    }

    public String getProviderTelephone() {
        return providerTelephone;
    }
}
