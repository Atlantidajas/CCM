package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

/**
 * @Author: Jorge.HL
 * Permite construir un objeto de tipo TypeExpense
 */
public class TypeExpense implements Serializable {

    private int typeExpenseLogo;
    private String typeExpenseName;

    public TypeExpense(){}

    /**
     * Permite contruir un objeto de tipo TypeExpense
     * @param typeExpenseLogo int número del identificador con el que se almacena la propiedad
     * @param typeExpenseName String nombre del tipo de gasto
     */
    public TypeExpense(int typeExpenseLogo, String typeExpenseName) {
        this.typeExpenseLogo = typeExpenseLogo;
        this.typeExpenseName = typeExpenseName;
    }

    /**
     * Permite contruir un objeto de tipo TypeExpense a partir de otro TypeExpenseTemp
     * @param expenseTemp objeto TypeExpense con las propiedades obtenidas desde le fichero
     */
    public TypeExpense( ExpenseTemp expenseTemp) {
        this.typeExpenseLogo = expenseTemp.getTypeExpenseLogo();
        this.typeExpenseName = expenseTemp.getTypeExpenseName();
    }

    /**
     * Permite contruir un objeto de tipo TypeExpense a partir de otro DataSnapshot, permitiendo construir este a partir de la lectura de la db
     * @param dataSnapshotExpense DataSnapshotExpense de (Firebase)
     */
    public TypeExpense( DataSnapshot dataSnapshotExpense ) {
        this.typeExpenseLogo = Integer.parseInt( String.valueOf( dataSnapshotExpense.child("typeExpenseLogo").getValue() ) );
        this.typeExpenseName = String.valueOf( dataSnapshotExpense.child("typeExpenseName").getValue() );
    }

    /**
     * Asigna el valor a la propiedad typeExpenseLogo
     * @param typeExpenseLogo int número del identificador con el que se almacena la propiedad
     */
    public void setTypeExpenseLogo(int typeExpenseLogo) {
        this.typeExpenseLogo = typeExpenseLogo;
    }

    /**
     * Asigna el valor a la propiedad typeExpenseName
     * @param typeExpenseName String nombre del tipo de gasto
     */
    public void setTypeExpenseName(String typeExpenseName) {
        this.typeExpenseName = typeExpenseName;
    }


    /**
     * Devuelve el valor de la propiedad typeExpenseLogo
     * @return int número del identificador con el que se almacena la propiedad
     */
    public int getTypeExpenseLogo() {
        return typeExpenseLogo;
    }

    /**
     * Devuelve el valor de la propiedad typeExpenseName
     * @return String nombre del tipo de gasto
     */
    public String getTypeExpenseName() {
        return typeExpenseName;
    }
}
