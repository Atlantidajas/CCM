package com.jorge.app.ccm.models.Expense;

import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.vehicle.Vehicle;

public interface IExpenses {

    public void setUser(User user);
    public void setVehicle(Vehicle vehicle);
    public User getUser();
    public Vehicle getVehicle();
}
