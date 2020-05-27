package com.jorge.app.ccm.models;

public class MethodOfPlayment {

    private int methodOfPlaymentLogo;
    private String methodOfPlaymentName;

    public MethodOfPlayment( int methodOfPlaymentLogo, String methodOfPlaymentName ) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
        this.methodOfPlaymentName = methodOfPlaymentName;
    }

    protected void setMethodOfPlaymentLogo(int methodOfPlaymentLogo) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
    }

    protected void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.methodOfPlaymentName = methodOfPlaymentNameMethodOfPlayment;
    }

    protected int getMethodOfPlaymentLogo() {
        return methodOfPlaymentLogo;
    }

    protected String getMethodOfPlaymentName() {
        return methodOfPlaymentName;
    }
}
