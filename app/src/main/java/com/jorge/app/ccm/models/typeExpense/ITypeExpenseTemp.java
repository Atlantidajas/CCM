package com.jorge.app.ccm.models.typeExpense;

public interface ITypeExpenseTemp {

    /*
     * @Author: Jorge.HL
     */
    public void setTypeExpense( TypeExpense  typeExpense );
    public void setLogo( int logo );
    public void setTypeName(String typeName);
    public TypeExpense getTypeExpense();
    public String getTypeName();
    public int getLogo();
    public String getKEY_TYPE_NAME();
    public String getKEY_LOGO();
    public void removeTypeExpense();
    public void removeTypeName();
    public void removeLogo();
}
