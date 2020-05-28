package com.jorge.app.ccm.models;

public class MethodOfPlayment {

    private int methodOfPlaymentLogo;
    private String methodOfPlaymentName;

    public MethodOfPlayment( int methodOfPlaymentLogo, String methodOfPlaymentName ) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
        this.methodOfPlaymentName = methodOfPlaymentName;
    }

    public MethodOfPlayment( ExpenseTemp expenseTemp ) {
        this.methodOfPlaymentLogo = expenseTemp.getMethodOfPlaymentValueLogo();
        this.methodOfPlaymentName = expenseTemp.getMethodOfPlaymentValueName();
    }

    protected void setMethodOfPlaymentLogo(int methodOfPlaymentLogo) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
    }

    protected void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.methodOfPlaymentName = methodOfPlaymentNameMethodOfPlayment;
    }

    public int getMethodOfPlaymentLogo() {
        return methodOfPlaymentLogo;
    }

    public String getMethodOfPlaymentName() {
        return methodOfPlaymentName;
    }
}
