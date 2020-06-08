package com.jorge.app.ccm.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *@Author Jorge HL
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


    /**
     * Guarda los datos de tipo string en fichero con una clave
     * @param key clave con la que se guardrá
     * @param value valor que guardará
     */
    public void setDateString( String key, String value){
        editor.putString( key, value);
        editor.commit();
    }

    /**
     * Guarda los datos de tipo int en fichero con una clave
     * @param key clave con la que se guardará
     * @param value valor que guardará
     */
    public void setDateInt( String key, int value){
        editor.putInt( key, value);
        editor.commit();
    }


    /**
     * Guarda los datos de tipo float en fichero con una clave
     * @param key clave con la que se guardará
     * @param value valor que guardará
     */
    public void setDateFloat( String key, float value){
        editor.putFloat( key, value);
        editor.commit();
    }


    /**
     * Guarda los datos de tipo bollean en fichero con una clave
     * @param key clave con la que se guardará
     * @param value valor que guardará
     */
    public void setDateBoolean( String key, boolean value){
        editor.putBoolean( key, value);
        editor.commit();
    }

    /**
     * Busca un datos en el fichero
     * @param key Clave del dato buscado
     * @return devuelve un String con el dato buscado, por defecto devuelve ""
     */
    public String getDateString( String key ){
        return sharedPreferences.getString( key, "" );
    }


    /**
     * Busca un datos en el fichero
     * @param key Clave del dato buscado
     * @return devuelve un float con el dato buscado, por defecto devuelve 0
     */
    public float getDateFloat( String key ){
        return sharedPreferences.getFloat( key, 0 );
    }

    /**
     * Busca un datos en el fichero
     * @param key Clave del dato buscado
     * @return devuelve un int con el dato buscado, por defecto devuelve 0
     */
    public int getDateInt( String key ){
        return sharedPreferences.getInt( key, 0 );
    }


    /**
     * Busca un datos en el fichero
     * @param key Clave del dato buscado
     * @return devuelve un boolean con el dato buscado, por defecto devuelve false
     */
    public boolean getDateBoolean( String key ){
        return sharedPreferences.getBoolean( key, false );
    }

    /**
     * @Author: Jorge.HL
     * Devuelve el nombre del fichero donde se guardan los datos.
     */

    /**
     * Nombre del fichero donde se almacenan los datos
     * @return devuelve un String con el nombre del fichero
     */
    public String getFILE_NAME() {
        return FILE_NAME;
    }

    /**
     * Borra un dato del fichero
     * @param key clave del datos que se pretende borrar
     */
    public void removeDate( String key ){
        editor.remove( key );
        editor.commit();
    }
}
