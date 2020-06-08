package com.jorge.app.ccm.models;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

/**
 * @Author: Jorge.HL
 * Clase que almacena los datos de un proveedor y hereda atributos de la clase MethodOfPlayment
 */
public class Provider extends MethodOfPlayment implements Serializable {

    protected String providerName;
    protected String providerCifNif;
    protected String providerTelephone;

    protected Provider(){}

    /**
     * Permite crear un objeto de tipo a traves de su padre
     * @param methodOfPlayment Objeto de tipo MethodOfPlayment
     * @param providerName String nombre del proveedor
     * @param providerCifNif String Cif - Nif del proveedor
     * @param providerTelephone String teléfono del proveedor
     */
    protected Provider( MethodOfPlayment methodOfPlayment,
                     String providerName, String providerCifNif,
                     String providerTelephone) {
        super( methodOfPlayment.getMethodOfPlaymentLogo(), methodOfPlayment.getMethodOfPlaymentName() );
        this.providerName = providerName;
        this.providerCifNif = providerCifNif;
        this.providerTelephone = providerTelephone;
    }

    /**
     * Permite crear un objeto de tipo a traves de su padre
     * @param methodOfPlaymentLogo identificador con el que se almacena el logo del método de pago
     * @param methodOfPlaymentNameMethodOfPlayment nombre del método de pago
     * @param providerName String nombre del proveedor
     * @param providerCifNif String Cif - Nif del proveedor
     * @param providerTelephone String teléfono del proveedor
     */
    protected Provider(int methodOfPlaymentLogo,
                    String methodOfPlaymentNameMethodOfPlayment,
                    String providerName, String providerCifNif,
                    String providerTelephone) {
        super( methodOfPlaymentLogo, methodOfPlaymentNameMethodOfPlayment );
        this.providerName = providerName;
        this.providerCifNif = providerCifNif;
        this.providerTelephone = providerTelephone;
    }

    /**
     * Permite crear un objeto de tipo a traves de su padre Tichect
     * @param expenseTemp objeto ExpenseTemp
     */
    protected Provider( ExpenseTemp expenseTemp ) {
        super( expenseTemp );
        this.providerName = expenseTemp.getProviderName();
        this.providerCifNif = expenseTemp.getProviderCifNif();
        this.providerTelephone = expenseTemp.getProviderTelephone();
    }

    /**
     * Permite crear un objeto de tipo Provider a traves de la obtención de datos de la db (Fiebase)
     * @param dataSnapshotExpese DataSnapshotExpese (Firebase)
     */
    protected Provider( DataSnapshot dataSnapshotExpese ) {
        super( dataSnapshotExpese );
        this.providerName = String.valueOf( dataSnapshotExpese.child("providerName").getValue() );
        this.providerCifNif = String.valueOf( dataSnapshotExpese.child("providerCifNif").getValue() );
        this.providerTelephone = String.valueOf( dataSnapshotExpese.child("providerTelephone").getValue() );
    }

    /**
     * Permite aisgnar el valor de providerName
     * @param providerName
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * Permite aisgnar el valor de providerCifNif
     * @param providerCifNif
     */
    public void setProviderCifNif(String providerCifNif) {
        this.providerCifNif = providerCifNif;
    }

    /**
     * Permite aisgnar el valor de providerTelephone
     * @param providerTelephone
     */
    public void setProviderTelephone(String providerTelephone) {
        this.providerTelephone = providerTelephone;
    }

    /**
     * Devuelve la propiedad providerName con el nombre del proveedor
     * @return
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * Devuelve la propiedad providerName con el cif-nif del proveedor
     * @return
     */
    public String getProviderCifNif() {
        return providerCifNif;
    }

    /**
     * * Devuelve la propiedad providerTelephone con el teléfono del proveedor
     * @return
     */
    public String getProviderTelephone() {
        return providerTelephone;
    }
}
