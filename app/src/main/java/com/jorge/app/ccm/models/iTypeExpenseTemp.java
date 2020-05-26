package com.jorge.app.ccm.models;

public interface iTypeExpenseTemp {

    /*
     * @Author: Jorge.HL
     */
    public void setTypeExpense( TypeExpense  typeExpense );
    public TypeExpense getTypeExpense();
    public void setTypeExpenseLogo(int typeExpenseLogo);
    public void setTypeExpenseName(String typeExpenseName);
    public int getTypeExpenseLogo();
    public String getTypeExpenseName();
    public void removeTypeName();
    public void removeLogo();
    public void removeTypeExpense();
}
