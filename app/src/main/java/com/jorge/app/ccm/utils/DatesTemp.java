package com.jorge.app.ccm.utils;

import android.content.Context;
import android.content.SharedPreferences;


/*
 * @Author: Jorge H.L
 * Permite guardar datos en un fichero almacenado en el sistema así como modificarlos y borrarlos
 */

public class DatesTemp {

    private Context context;
    private final String FILE_NAME;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DatesTemp( Context context, String TAG ) {
        this.context = context;
        this.FILE_NAME = "temp" + TAG;//<-- Nombre específico temp + el nombre de la actividad
        this.sharedPreferences = context.getSharedPreferences( FILE_NAME, context.MODE_PRIVATE );
        this.editor = sharedPreferences.edit();
    }




    /*
     * @Author: Jorge.HL
     * Guarda los datos en fichero con una clave
     */
    public void setDateString( String key, String value){
        editor.putString( key, value);
        editor.commit();
    }

    /*
     * @Author: Jorge.HL
     * Guarda los datos en fichero con una clave
     */
    public void setDateInt( String key, int value){
        editor.putInt( key, value);
        editor.commit();
    }


    /*
     * @Author: Jorge.HL
     * Guarda los datos en fichero con una clave
     */
    public void setDateFloat( String key, float value){
        editor.putFloat( key, value);
        editor.commit();
    }


    /*
     * @Author: Jorge.HL
     * Guarda los datos en fichero con una clave
     */
    public void setDateBoolean( String key, boolean value){
        editor.putBoolean( key, value);
        editor.commit();
    }

    /*
     * @Author: Jorge.HL
     * Devuelve un dato buscado en fichero
     */
    public String getDateString( String key ){
        return sharedPreferences.getString( key, "" );
    }


    /*
     * @Author: Jorge.HL
     * Devuelve un dato buscado en fichero
     */
    public float getDateFloat( String key ){
        return sharedPreferences.getFloat( key, 0 );
    }

    /*
     * @Author: Jorge.HL
     * Devuelve un dato buscado en fichero
     */
    public int getDateInt( String key ){
        return sharedPreferences.getInt( key, 0 );
    }


    /*
     * @Author: Jorge.HL
     * Devuelve un dato buscado en fichero
     */
    public boolean getDateBoolean( String key ){
        return sharedPreferences.getBoolean( key, false );
    }

    /*
     * @Author: Jorge.HL
     * Devuelve el nombre del fichero donde se guardan los datos.
     */
    public String getFILE_NAME() {
        return FILE_NAME;
    }

    /*
     * @Author: Jorge.HL
     * Borra el dato bucado por la clave del fichero
     */
    public void removeDate( String key ){
        editor.remove( key );
        editor.commit();
    }
}
