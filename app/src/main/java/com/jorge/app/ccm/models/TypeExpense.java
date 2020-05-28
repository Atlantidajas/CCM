package com.jorge.app.ccm.models;

import java.io.Serializable;

public class TypeExpense implements Serializable {

    private int typeExpenseLogo;
    private String typeExpenseName;

    public TypeExpense(){}

    public TypeExpense(int typeExpenseLogo, String typeExpenseName) {
        this.typeExpenseLogo = typeExpenseLogo;
        this.typeExpenseName = typeExpenseName;
    }

    public TypeExpense( ExpenseTemp expenseTemp) {
        this.typeExpenseLogo = expenseTemp.getTypeExpenseLogo();
        this.typeExpenseName = expenseTemp.getTypeExpenseName();
    }

    public void setTypeExpenseLogo(int typeExpenseLogo) {
        this.typeExpenseLogo = typeExpenseLogo;
    }

    public void setTypeExpenseName(String typeExpenseName) {
        this.typeExpenseName = typeExpenseName;
    }

    public int getTypeExpenseLogo() {
        return typeExpenseLogo;
    }

    public String getTypeExpenseName() {
        return typeExpenseName;
    }
}
