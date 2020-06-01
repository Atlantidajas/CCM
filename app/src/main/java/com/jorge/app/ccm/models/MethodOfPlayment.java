package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;
import com.jorge.app.ccm.models.temp.ExpenseTemp;

public class MethodOfPlayment {

    protected int methodOfPlaymentLogo;
    protected String methodOfPlaymentName;

    protected MethodOfPlayment(){}

    protected MethodOfPlayment( int methodOfPlaymentLogo, String methodOfPlaymentName ) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
        this.methodOfPlaymentName = methodOfPlaymentName;
    }

    protected MethodOfPlayment( ExpenseTemp expenseTemp ) {
        this.methodOfPlaymentLogo = expenseTemp.getMethodOfPlaymentValueLogo();
        this.methodOfPlaymentName = expenseTemp.getMethodOfPlaymentValueName();
    }

    protected MethodOfPlayment( DataSnapshot dataSnapshotExpese ) {
        this.methodOfPlaymentLogo = Integer.parseInt( String.valueOf( dataSnapshotExpese.child("methodOfPlaymentLogo").getValue() ) );
        this.methodOfPlaymentName = String.valueOf( dataSnapshotExpese.child("methodOfPlaymentName").getValue() );
    }

    public void setMethodOfPlaymentLogo(int methodOfPlaymentLogo) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
    }

    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.methodOfPlaymentName = methodOfPlaymentNameMethodOfPlayment;
    }

    public int getMethodOfPlaymentLogo() {
        return methodOfPlaymentLogo;
    }

    public String getMethodOfPlaymentName() {
        return methodOfPlaymentName;
    }
}
