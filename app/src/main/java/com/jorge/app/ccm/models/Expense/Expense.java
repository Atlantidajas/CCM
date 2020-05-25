package com.jorge.app.ccm.models.Expense;

import com.jorge.app.ccm.models.ticket.Tickect;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.vehicle.Vehicle;

public class Expense {

    private Tickect tickect;
    private User user;
    private Vehicle vehicle;

    public Expense(){}

    public Expense(Tickect expense, User user, Vehicle vehicle ) {
        this.tickect = expense;
        this.user = user;
        this.vehicle = vehicle;
    }

    public void setTickect(Tickect tickect) {
        this.tickect = tickect;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Tickect getTickect() {
        return tickect;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
