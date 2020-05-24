package com.jorge.app.ccm.models;

import com.jorge.app.ccm.models.typeExpense.ITypeExpense;
import com.jorge.app.ccm.models.typeExpense.TypeExpense;

public class Expenses implements ITypeExpense {

    private TypeExpense typeExpense;
    private String tickectNumber;
    private String comercialEstablishment;
    private String date;
    private String methodOfPlayment;
    private float totalExpense;

    public Expenses(){}

    public Expenses( TypeExpense typeExpense, String tickectNumber, String comercialEstablishment, String date, String methodOfPlayment, float totalExpense) {
        this.typeExpense = typeExpense;
        this.tickectNumber = tickectNumber;
        this.comercialEstablishment = comercialEstablishment;
        this.date = date;
        this.methodOfPlayment = methodOfPlayment;
        this.totalExpense = totalExpense;
    }

    @Override
    public void setLogo( int logo ) {
        this.typeExpense.setLogo( logo );
    }

    @Override
    public void setTypeName( String typeName){
        this.typeExpense.setTypeName( typeName );
    }

    public void setTickectNumber(String tickectNumber) {
        this.tickectNumber = tickectNumber;
    }

    public void setComercialEstablishment(String comercialEstablishment) {
        this.comercialEstablishment = comercialEstablishment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMethodOfPlayment(String methodOfPlayment) {
        this.methodOfPlayment = methodOfPlayment;
    }


    @Override
    public int getLogo() {
        return typeExpense.getLogo();
    }

    @Override
    public String getTypeName() {
        return typeExpense.getTypeName();
    }

    public void setTotalExpense(float totalExpense) {
        this.totalExpense = totalExpense;
    }

    public String getTickectNumber() {
        return tickectNumber;
    }

    public String getComercialEstablishment() {
        return comercialEstablishment;
    }

    public String getDate() {
        return date;
    }

    public String getMethodOfPlayment() {
        return methodOfPlayment;
    }

    public float getTotalExpense() {
        return totalExpense;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "typeExpense='" + typeExpense + '\'' +
                ", tickectNumber='" + tickectNumber + '\'' +
                ", comercialEstablishment='" + comercialEstablishment + '\'' +
                ", date='" + date + '\'' +
                ", methodOfPlayment='" + methodOfPlayment + '\'' +
                ", totalExpense=" + totalExpense +
                '}';
    }

}
