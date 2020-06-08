package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

/**
 * @Author: Jorge.HL
 */

public class MethodOfPlayment implements Serializable {

    protected int methodOfPlaymentLogo;
    protected String methodOfPlaymentName;

    protected MethodOfPlayment(){}

    /**
     * Permite construir un objeto MethodOfPlayment
     * @param methodOfPlaymentLogo int identificador con el que se almacena el logo del método de pago
     * @param methodOfPlaymentName String nombre del método de pago
     */
    protected MethodOfPlayment( int methodOfPlaymentLogo, String methodOfPlaymentName ) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
        this.methodOfPlaymentName = methodOfPlaymentName;
    }

    /**
     * * Permite crear un objeto de tipo MethodOfPlayment obteniendo los datos de un fichero
     * @param expenseTemp Objeto de tipo ExpenseTemp
     */
    protected MethodOfPlayment( ExpenseTemp expenseTemp ) {
        this.methodOfPlaymentLogo = expenseTemp.getMethodOfPlaymentValueLogo();
        this.methodOfPlaymentName = expenseTemp.getMethodOfPlaymentValueName();
    }

    /**
     * Permite crear un objeto de tipo MethodOfPlayment obteniendo los datos de DB (Firebase)
     * @param dataSnapshotExpese
     */
    protected MethodOfPlayment( DataSnapshot dataSnapshotExpese ) {
        this.methodOfPlaymentLogo = Integer.parseInt( String.valueOf( dataSnapshotExpese.child("methodOfPlaymentLogo").getValue() ) );
        this.methodOfPlaymentName = String.valueOf( dataSnapshotExpese.child("methodOfPlaymentName").getValue() );
    }

    /**
     * int identificador con el que se almacena el logo del método de pago
     * @param methodOfPlaymentLogo
     */
    public void setMethodOfPlaymentLogo(int methodOfPlaymentLogo) {
        this.methodOfPlaymentLogo = methodOfPlaymentLogo;
    }

    /**
     * String nombre del método de pagp
     * @param methodOfPlaymentNameMethodOfPlayment
     */
    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.methodOfPlaymentName = methodOfPlaymentNameMethodOfPlayment;
    }

    /**
     * devuelve nombre del método de pagp
     * @return int methodOfPlaymentLogo
     */
    public int getMethodOfPlaymentLogo() {
        return methodOfPlaymentLogo;
    }

    /**
     * Devuelve String nombre del método de pago
     * @return String methodOfPlaymentName
     */
    public String getMethodOfPlaymentName() {
        return methodOfPlaymentName;
    }
}
