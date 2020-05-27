package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de tipo TypeExpense.
 */

public class TypeExpenseTemp extends DatesTemp implements iTypeExpense {

    private String PRIMARY_KEY = getFILE_NAME() + "typeExpense";
    private String KEY_TYPE_EXPENSE_LOGO = PRIMARY_KEY + "Logo";
    private String KEY_TYPE_EXPENSE_NAME = PRIMARY_KEY + "TypeName";

    public TypeExpenseTemp(Context context,
                       final String TAG){
        super( context, TAG );

    }

    public TypeExpenseTemp( Context context,
                            final String TAG,
                            TypeExpense typeExpense ) {
        super( context, TAG );
        this.setDateInt( KEY_TYPE_EXPENSE_LOGO, typeExpense.getTypeExpenseLogo() );
        this.setDateString( KEY_TYPE_EXPENSE_NAME, typeExpense.getTypeExpenseName() );

    }

    public TypeExpenseTemp( Context context,
                            final String TAG,
                            int typeExpenseLogo,
                            String typeExpenseName ) {
        super( context, TAG );
        this.setDateInt( KEY_TYPE_EXPENSE_LOGO, typeExpenseLogo );
        this.setDateString( KEY_TYPE_EXPENSE_NAME, typeExpenseName );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo logo de un un objeto TypeExpense
     */
    @Override
    public void setTypeExpenseLogo(int typeExpenseLogo) {
        this.setDateInt( KEY_TYPE_EXPENSE_LOGO, typeExpenseLogo );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo typeName de un un objeto TypeExpense
     */
    @Override
    public void setTypeExpenseName(String typeExpenseName) {
        this.setDateString( KEY_TYPE_EXPENSE_NAME, typeExpenseName );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo logo de un un objeto TypeExpense
     */
    @Override
    public int getTypeExpenseLogo() {
        return this.getDateInt( KEY_TYPE_EXPENSE_LOGO );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo typeName de un un objeto TypeExpense
     */
    @Override
    public String getTypeExpenseName() {
        return this.getDateString( KEY_TYPE_EXPENSE_NAME );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal los datos correspodiente al objeto TypeExpense
     */
    public void removeTypeExpense(){
        this.removeDate( KEY_TYPE_EXPENSE_NAME );
        this.removeDate( KEY_TYPE_EXPENSE_LOGO );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave TYPE_NAME
     */
    public void removeTypeExpenseName(){
        this.removeDate( KEY_TYPE_EXPENSE_NAME );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave lOGO
     */
    public void removeTypeExpenseLogo(){
        this.removeDate( KEY_TYPE_EXPENSE_LOGO );
    }


}
