package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de tipo TypeExpense.
 */

public class TypeExpenseTemp extends DatesTemp implements iTypeExpense {

    private final String PRIMARY_KEY;
    private final String KEY_TYPE_NAME;
    private final String KEY_LOGO;
    private TypeExpense typeExpense;

    public TypeExpenseTemp( Context context, final String TAG ) {
        super( context, TAG );
        PRIMARY_KEY = getFILE_NAME() + "typeExpense";
        this.KEY_TYPE_NAME = PRIMARY_KEY + "typeName";
        this.KEY_LOGO = PRIMARY_KEY + "logo";
    }



    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal objeto TypeExpense
     */
    public void setTypeExpense( TypeExpense  typeExpense ) {
        this.setDateString( KEY_TYPE_NAME, typeExpense.getTypeExpenseName() );
        this.setDateInt( KEY_LOGO, typeExpense.getTypeExpenseLogo() );
    }

    /*
     * @Author: Jorge.HL
     * Devuelve después de leer en fichero temp objeto TypeExpense
     */
    public TypeExpense getTypeExpense(){
        return typeExpense = new TypeExpense( this.getDateInt( KEY_LOGO ), this.getDateString( KEY_TYPE_NAME ) );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo logo de un un objeto TypeExpense
     */
    @Override
    public void setTypeExpenseLogo(int typeExpenseLogo) {
        this.setDateInt( KEY_LOGO, typeExpenseLogo );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo typeName de un un objeto TypeExpense
     */
    @Override
    public void setTypeExpenseName(String typeExpenseName) {
        this.setDateString( KEY_TYPE_NAME, typeExpenseName );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo logo de un un objeto TypeExpense
     */
    @Override
    public int getTypeExpenseLogo() {
        return this.getDateInt( KEY_LOGO );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo typeName de un un objeto TypeExpense
     */
    @Override
    public String getTypeExpenseName() {
        return this.getDateString( KEY_TYPE_NAME );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave TYPE_NAME
     */
    public void removeTypeName(){
        this.removeDate( KEY_TYPE_NAME );
    }

    /*
     * * @Author: Jorge.HL
     * Borrar del fichero temporal el dato correspodiente a la clave lOGO
     */
    public void removeLogo(){
        this.removeDate( KEY_LOGO );
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal los datos correspodiente al objeto TypeExpense
     */
    public void removeTypeExpense(){
        this.removeDate( KEY_TYPE_NAME );
        this.removeDate( KEY_LOGO );
    }
}
