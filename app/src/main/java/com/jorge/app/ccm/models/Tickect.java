package com.jorge.app.ccm.models;


import com.google.firebase.database.DataSnapshot;


import java.io.Serializable;

/**
 * @Author: Jorge.HL
 * Clase con funcionalidades heredades de Provider y MethodoPlament
 */
public class Tickect extends Provider implements Serializable{

    private String tickectNumber;
    private String tickectDate;
    private String tickectTotalExpense;

    public Tickect(){}

    /**
     * Permite Contruir un objeto de tipo Ticket
     * @param methodOfPlayment bjeto de tipo MethodOfPlayment
     * @param provider objeto de tipo Provider
     * @param tickectNumber String número de tickect
     * @param tickectDate String fecha del tickect
     * @param tickectTotalExpense String importe total del tickect
     */
    public Tickect(MethodOfPlayment methodOfPlayment,
                   Provider provider,
                   String tickectNumber,
                   String tickectDate,
                   String tickectTotalExpense) {
        super( methodOfPlayment.getMethodOfPlaymentLogo(),
                methodOfPlayment.getMethodOfPlaymentName(),
                provider.getProviderName(),
                provider.getProviderCifNif(),
                provider.getProviderTelephone() );
                this.tickectNumber = tickectNumber;
                this.tickectDate = tickectDate;
                this.tickectTotalExpense = tickectTotalExpense;
    }

    /**
     * Permite Contruir un objeto de tipo Ticket
     * @param methodOfPlaymentLogo int id del logo con el que se almacena en recursos
     * @param methodOfPlaymentName String nombre del método de pago
     * @param providerName String nombre del proveedor
     * @param providerCifNif String Cif o nif del proveedor
     * @param providerTelephone String número de teléfono del proveedor
     * @param tickectNumber String número de tickect
     * @param tickectDate String fecha del tickect
     * @param tickectTotalExpense String importe total del tickect
     */
    public Tickect(int methodOfPlaymentLogo,
                   String methodOfPlaymentName,
                   String providerName,
                   String providerCifNif,
                   String providerTelephone,
                   String tickectNumber,
                   String tickectDate,
                   String tickectTotalExpense) {
        super( methodOfPlaymentLogo, methodOfPlaymentName,
                providerName,
                providerCifNif,
                providerTelephone );
        this.tickectNumber = tickectNumber;
        this.tickectDate = tickectDate;
        this.tickectTotalExpense = tickectTotalExpense;
    }

    /**
     * Permite Contruir un objeto de tipo Ticket, obteniendo los datos de la DB de Firebase
     * @param dataSnapshotExpese Objeto DataSnapshotExpese
     */

    public Tickect( DataSnapshot dataSnapshotExpese ) {
        super( dataSnapshotExpese );
        this.tickectNumber = String.valueOf( dataSnapshotExpese.child("tickectNumber").getValue() );
        this.tickectDate = String.valueOf( dataSnapshotExpese.child("tickectDate").getValue() );
        this.tickectTotalExpense = String.valueOf( dataSnapshotExpese.child("tickectTotalExpense").getValue() );
    }

    /**
     * Permite Contruir un objeto de tipo Ticket, obteniendo los datos de un objeto de tipo ExpenseTemp obtenidos de un fichero
     * @param expenseTemp
     */
    public Tickect(ExpenseTemp expenseTemp) {
        super( expenseTemp );
        this.tickectNumber = expenseTemp.getTickectNumber();
        this.tickectDate = expenseTemp.getTickectDate();
        this.tickectTotalExpense = expenseTemp.getTickectTotalExpense();
    }

    /**
     * Permite asignar el valor número de tickect
     * @param tickectNumber String número de tickect
     */
    public void setTickectNumber(String tickectNumber) {
        this.tickectNumber = tickectNumber;
    }

    /**
     * Permite aisgnar el valor de fecha del tickect
     * @param tickectDate String fecha del tickect
     */
    public void setTickectDate(String tickectDate) {
        this.tickectDate = tickectDate;
    }

    /**
     * Permite asignar el valor de importe total del tickect
     * @param tickectTotalExpense String importe total del tickect
     */
    public void setTickectTotalExpense(String tickectTotalExpense) {
        this.tickectTotalExpense = tickectTotalExpense;
    }

    /**
     * Devuelve número de tickect
     * @return String número de tickect
     */
    public String getTickectNumber() {
        return tickectNumber;
    }

    /**
     * Devuelve fecha del tickect
     * @return String fecha del tickect
     */
    public String getTickectDate() {
        return tickectDate;
    }

    /**
     * Devuelve el importe total del tickect
     * @returnString String importe total del tickect
     */
    public String getTickectTotalExpense() {
        return tickectTotalExpense;
    }

}
