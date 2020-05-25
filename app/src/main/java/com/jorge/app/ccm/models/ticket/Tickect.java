package com.jorge.app.ccm.models.ticket;

import com.jorge.app.ccm.models.typeExpense.TypeExpense;
import com.jorge.app.ccm.models.vehicle.Vehicle;

public class Tickect {


    private String tickectNumber;
    private String date;
    private String methodOfPlayment;
    private float totalExpense;

    public Tickect(){}

    public Tickect(Vehicle vehicle,
                   TypeExpense typeExpense,
                   String tickectNumber,
                   String date,
                   String methodOfPlayment,
                   float totalExpense) {

        this.tickectNumber = tickectNumber;
        this.date = date;
        this.methodOfPlayment = methodOfPlayment;
        this.totalExpense = totalExpense;
    }



    public void setTickectNumber(String tickectNumber) {
        this.tickectNumber = tickectNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMethodOfPlayment(String methodOfPlayment) {
        this.methodOfPlayment = methodOfPlayment;
    }

    public void setTotalExpense(float totalExpense) {
        this.totalExpense = totalExpense;
    }

    public String getTickectNumber() {
        return tickectNumber;
    }

    public String getDate() {
        return date;
    }

    public String getMethodOfPlayment() {
        return methodOfPlayment;
    }

    public float getTotalExpense() {
        return totalExpense;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", tickectNumber='" + tickectNumber + '\'' +
                ", date='" + date + '\'' +
                ", methodOfPlayment='" + methodOfPlayment + '\'' +
                ", totalExpense=" + totalExpense +
                '}';
    }
}
