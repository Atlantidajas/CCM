package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;

public class Provider extends MethodOfPlayment {

    protected String providerName;
    protected String providerCifNif;
    protected String providerTelephone;

    protected Provider(){}

    protected Provider( MethodOfPlayment methodOfPlayment,
                     String providerName, String providerCifNif,
                     String providerTelephone) {
        super( methodOfPlayment.getMethodOfPlaymentLogo(), methodOfPlayment.getMethodOfPlaymentName() );
        this.providerName = providerName;
        this.providerCifNif = providerCifNif;
        this.providerTelephone = providerTelephone;
    }

    protected Provider(int methodOfPlaymentLogo,
                    String methodOfPlaymentNameMethodOfPlayment,
                    String providerName, String providerCifNif,
                    String providerTelephone) {
        super( methodOfPlaymentLogo, methodOfPlaymentNameMethodOfPlayment );
        this.providerName = providerName;
        this.providerCifNif = providerCifNif;
        this.providerTelephone = providerTelephone;
    }

    protected Provider( ExpenseTemp expenseTemp ) {
        super( expenseTemp );
        this.providerName = expenseTemp.getProviderName();
        this.providerCifNif = expenseTemp.getProviderCifNif();
        this.providerTelephone = expenseTemp.getProviderTelephone();
    }

    protected Provider( DataSnapshot dataSnapshotExpense ) {
        super( dataSnapshotExpense );
        this.providerName = String.valueOf( dataSnapshotExpense.child("providerName").getValue() );
        this.providerCifNif = String.valueOf( dataSnapshotExpense.child("providerCifNif").getValue() );
        this.providerTelephone = String.valueOf( dataSnapshotExpense.child("providerTelephone").getValue() );
    }


    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setProviderCifNif(String providerCifNif) {
        this.providerCifNif = providerCifNif;
    }

    public void setProviderTelephone(String providerTelephone) {
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
