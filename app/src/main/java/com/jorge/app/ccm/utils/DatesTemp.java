package com.jorge.app.ccm.utils;

import android.content.Context;
import android.content.SharedPreferences;


/***
 * @Author: Jorge H.L
 */

public class DatesTemp {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DatesTemp( Context context,
                      String fileName ) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences( fileName, context.getApplicationContext().MODE_PRIVATE );
        this.editor = sharedPreferences.edit();

    }


    /*
     * Guarda los datos en fichero con una clave
     */
    public void setDateString( String key, String value){
        editor.putString( key, value);
        editor.apply();
    }


    /**
     * Devuelve un dato buscado en fichero
     */
    public String getDateString( String key ){
        return sharedPreferences.getString( key, "" );
    }

    /*
     * Guarda los datos en fichero con una clave
     */
    public void setDateFloat( String key, Float value){
        editor.putFloat( key, value);
        editor.apply();
    }


    /**
     * Devuelve un dato buscado en fichero
     */
    public Float getDateFloat( String key ){
        return sharedPreferences.getFloat( key, 0 );
    }

    /*
     * Guarda los datos en fichero con una clave
     */
    public void setDateInt( String key, int value){
        editor.putInt( key, value);
        editor.apply();
    }


    /**
     * Devuelve un dato buscado en fichero
     */
    public int getDateInt( String key ){
        return sharedPreferences.getInt( key, 0 );
    }

    /*
     * Guarda los datos en fichero con una clave
     */
    public void setDateBoolean( String key, boolean value){
        editor.putBoolean( key, value);
        editor.apply();
    }


    /**
     * Devuelve un dato buscado en fichero
     */
    public boolean getDateBoolean( String key ){
        return sharedPreferences.getBoolean( key, false );
    }


    /**
     * Borra el dato bucado por la clave del fichero
     */
    public void removeDate( String key ){
        editor.remove( key );
        editor.commit();
    }
}
