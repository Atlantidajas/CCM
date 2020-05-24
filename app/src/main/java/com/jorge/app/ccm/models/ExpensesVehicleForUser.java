package com.jorge.app.ccm.models;

import java.io.Serializable;

public class ExpensesVehicleForUser {

    private Expenses expenses;
    private User user;
    private Vehicle vehicle;

    public ExpensesVehicleForUser(){}

    public ExpensesVehicleForUser( Expenses expense, User user, Vehicle vehicle ) {
        this.expenses = expense;
        this.user = user;
        this.vehicle = vehicle;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
