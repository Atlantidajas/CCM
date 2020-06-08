package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.models.TypeExpense;
import com.jorge.app.ccm.models.iTypeExpense;
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

    /**
     * Permite construir un objeto de tipo TypeExpenseTemp para almacenar en un fichero uno de tipo TypeExpense
     * @param context Context Contexto donde se va a crear
     * @param TAG String Nombre de la actividad donde se va a crear
     * @param typeExpense String tipo de gasto
     */
    public TypeExpenseTemp( Context context,
                            final String TAG,
                            TypeExpense typeExpense ) {
        super( context, TAG );
        this.setDateInt( KEY_TYPE_EXPENSE_LOGO, typeExpense.getTypeExpenseLogo() );
        this.setDateString( KEY_TYPE_EXPENSE_NAME, typeExpense.getTypeExpenseName() );

    }

    /**
     * Permite construir un objeto de tipo TypeExpenseTemp para almacenar en un fichero uno de tipo TypeExpense
     * @param context Context Contexto donde se va a crear
     * @param TAG String Nombre de la actividad donde se va a crear
     * @param typeExpenseLogo int identificador del recurso con el que se almacena el logo del tipo de gasto
     * @param typeExpenseName nombre que se le asigna al tipo de gasto
     */
    public TypeExpenseTemp( Context context,
                            final String TAG,
                            int typeExpenseLogo,
                            String typeExpenseName ) {
        super( context, TAG );
        this.setDateInt( KEY_TYPE_EXPENSE_LOGO, typeExpenseLogo );
        this.setDateString( KEY_TYPE_EXPENSE_NAME, typeExpenseName );
    }

    /**
     * Guarda en fichero temporal el atributo logo de un un objeto TypeExpense
     */
    @Override
    public void setTypeExpenseLogo(int typeExpenseLogo) {
        this.setDateInt( KEY_TYPE_EXPENSE_LOGO, typeExpenseLogo );
    }

    /**
     * Guarda en fichero temporal el atributo typeName de un un objeto TypeExpense
     */
    @Override
    public void setTypeExpenseName(String typeExpenseName) {
        this.setDateString( KEY_TYPE_EXPENSE_NAME, typeExpenseName );
    }

    /**
     * Obtiene del fichero temporal el atributo logo de un un objeto TypeExpense
     */
    @Override
    public int getTypeExpenseLogo() {
        return this.getDateInt( KEY_TYPE_EXPENSE_LOGO );
    }

    /**
     * Obtiene del fichero temporal el atributo typeName de un un objeto TypeExpense
     */
    @Override
    public String getTypeExpenseName() {
        return this.getDateString( KEY_TYPE_EXPENSE_NAME );
    }

    /**
     * Borrar del fichero temporal los datos correspodiente al objeto TypeExpense
     */
    public void removeTypeExpense(){
        this.removeDate( KEY_TYPE_EXPENSE_NAME );
        this.removeDate( KEY_TYPE_EXPENSE_LOGO );
    }

    /**
     * Borrar del fichero temporal el dato correspodiente a la clave TYPE_NAME
     */
    public void removeTypeExpenseName(){
        this.removeDate( KEY_TYPE_EXPENSE_NAME );
    }

    /**
     * Borrar del fichero temporal el dato correspodiente a la clave lOGO
     */
    public void removeTypeExpenseLogo(){
        this.removeDate( KEY_TYPE_EXPENSE_LOGO );
    }


}
