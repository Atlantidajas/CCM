package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

public class MethodOfPlayment {

    private int methodOfPlaymentLogo;
    private String methodOfPlaymentName;

    public MethodOfPlayment(){}

    public MethodOfPlayment( int methodOfPlaymentLogo, String methodOfPlaymentName ) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
        this.methodOfPlaymentName = methodOfPlaymentName;
    }

    public MethodOfPlayment( ExpenseTemp expenseTemp ) {
        this.methodOfPlaymentLogo = expenseTemp.getMethodOfPlaymentValueLogo();
        this.methodOfPlaymentName = expenseTemp.getMethodOfPlaymentValueName();
    }

    public MethodOfPlayment( DataSnapshot dataSnapshotExpense ) {
//        this.methodOfPlaymentLogo = Integer.parseInt( String.valueOf( dataSnapshotExpense.child("methodOfPlaymentLogo").getValue() ) );
        this.methodOfPlaymentName = String.valueOf( dataSnapshotExpense.child("methodOfPlaymentName").getValue() );
    }

    protected void setMethodOfPlaymentLogo(int methodOfPlaymentLogo) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
    }

    protected void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.methodOfPlaymentName = methodOfPlaymentNameMethodOfPlayment;
    }

    @Exclude
    public int getMethodOfPlaymentLogo() {
        return methodOfPlaymentLogo;
    }

    public String getMethodOfPlaymentName() {
        return methodOfPlaymentName;
    }
}
