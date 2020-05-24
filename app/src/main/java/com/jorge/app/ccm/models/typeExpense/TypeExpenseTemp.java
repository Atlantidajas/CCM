package com.jorge.app.ccm.models.typeExpense;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de DatesTemp y atribuciones específicas para un objeto de typo TypeExpense.
 */

public class TypeExpenseTemp extends DatesTemp implements ITypeExpense {

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
        this.setDateString( KEY_TYPE_NAME, typeExpense.getTypeName() );
        this.setDateInt( KEY_LOGO, typeExpense.getLogo() );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo logo de un un objeto TypeExpense
     */
    @Override
    public void setLogo( int logo ) {
        this.setDateInt( KEY_LOGO, logo );
    }

    /*
     * @Author: Jorge.HL
     * Guarda en fichero temporal el atributo typeName de un un objeto TypeExpense
     */
    @Override
    public void setTypeName(String typeName) {
        this.setDateString( KEY_TYPE_NAME, typeName );
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
     * Obtiene del fichero temporal el atributo typeName de un un objeto TypeExpense
     */
    public String getTypeName() {
        return this.getDateString( KEY_TYPE_NAME );
    }

    /*
     * @Author: Jorge.HL
     * Obtiene del fichero temporal el atributo logo de un un objeto TypeExpense
     */
    @Override
    public int getLogo() {
        return this.getDateInt( KEY_LOGO );
    }

    /*
     * * @Author: Jorge.HL
     * Devuelve la key con la que se guarda en el fichero temporal el atributo typeName
     */
    public String getKEY_TYPE_NAME() {
        return KEY_TYPE_NAME;
    }

    /*
     * @Author: Jorge.HL
     * Devuelve la key con la que se guarda en el fichero temporal el atributo logo
     */
    public String getKEY_LOGO() {
        return KEY_LOGO;
    }

    /*
     * @Author: Jorge.HL
     * Borrar del fichero temporal los datos correspodiente al objeto TypeExpense
     */
    public void removeTypeExpense(){
        this.removeDate( KEY_TYPE_NAME );
        this.removeDate( KEY_LOGO );
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
}
