package com.jorge.app.ccm.models;

import android.net.Uri;

import com.google.firebase.database.Exclude;

public class Expense implements iTickect, iTypeExpense, iUser, iVehicle {

    private Tickect tickect;
    private TypeExpense typeExpense;
    private User user;
    private Vehicle vehicle;

    public Expense(){ }

    public Expense(Tickect tickect, TypeExpense typeExpense, User user, Vehicle vehicle) {
        this.tickect = tickect;
        this.typeExpense = typeExpense;
        this.user = user;
        this.vehicle = vehicle;
    }

    public Expense( ExpenseTemp expenseTemp) {
        this.tickect = new Tickect( expenseTemp );
        this.typeExpense = new TypeExpense( expenseTemp );
        this.user = new User();
        this.vehicle = new Vehicle( expenseTemp );
    }

    public Expense( Vehicle vehicle ) {
        this.vehicle = vehicle;
    }

    public void setTickect(Tickect tickect) {
        this.tickect = tickect;
    }

    public void setTypeExpense(TypeExpense typeExpense) {
        this.typeExpense = typeExpense;
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

    public TypeExpense getTypeExpense() {
        return typeExpense;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setTickectNumber(String tickectNumber) {
        this.tickect.setTickectNumber( tickectNumber );
    }

    @Override
    public void setTickectDate(String tickectDate) {
        this.tickect.setTickectDate( tickectDate );
    }

    @Override
    public void setTickectTotalExpense(String tickectTotalExpense) {
        this.tickect.setTickectTotalExpense( tickectTotalExpense );
    }

    @Override @Exclude
    public String getTickectNumber() {
        return this.tickect.getTickectNumber();
    }

    @Override @Exclude
    public String getTickectDate() {
        return this.tickect.getTickectTotalExpense();
    }

    @Override @Exclude
    public String getTickectTotalExpense() {
        return this.tickect.getTickectTotalExpense();
    }

    @Override
    public void setProviderName(String providerName) {
        this.tickect.setProviderName( providerName );
    }

    @Override
    public void setProviderCifNif(String providerCifNif) {
        this.tickect.setProviderCifNif( providerCifNif );
    }

    @Override
    public void setProviderTelephone(String providerTelephone) {
        this.tickect.setProviderTelephone( providerTelephone );
    }

    @Override @Exclude
    public String getProviderName() {
        return this.tickect.getProviderName();
    }

    @Override @Exclude
    public String getProviderCifNif() {
        return this.tickect.getProviderCifNif();
    }

    @Override @Exclude
    public String getProviderTelephone() {
        return this.tickect.getProviderTelephone();
    }

    @Override
    public void setMethodOfPlaymentLogo(int methodOfPlaymentLogo) {
        this.tickect.setMethodOfPlaymentLogo( methodOfPlaymentLogo );
    }

    @Override
    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.tickect.setMethodOfPlaymentNameMethodOfPlayment( methodOfPlaymentNameMethodOfPlayment );
    }

    @Override @Exclude
    public int getMethodOfPlaymentLogo() {
        return this.tickect.getMethodOfPlaymentLogo();
    }

    @Override @Exclude
    public String getMethodOfPlaymentName() {
        return this.tickect.getMethodOfPlaymentName();
    }

    @Override
    public void setTypeExpenseLogo(int typeExpenseLogo) {
        this.typeExpense.setTypeExpenseLogo( typeExpenseLogo );
    }

    @Override
    public void setTypeExpenseName(String typeExpenseName) {
        this.typeExpense.setTypeExpenseName( typeExpenseName );
    }

    @Override @Exclude
    public int getTypeExpenseLogo() {
        return this.typeExpense.getTypeExpenseLogo();
    }

    @Override @Exclude
    public String getTypeExpenseName() {
        return this.typeExpense.getTypeExpenseName();
    }

    @Override
    public void setUserEmail(String email) {
        this.user.setUserEmail( email );
    }

    @Override
    public void setUserTelephone(String telephone) {
        this.user.setUserTelephone( telephone );
    }

    @Override @Exclude
    public String getIdUser() {
        return this.user.getIdUser();
    }

    @Override @Exclude
    public String getUserName() {
        return this.user.getUserName();
    }

    @Override @Exclude
    public String getUserPhotoUriString() {
        return this.user.getUserPhotoUriString();
    }

    @Override @Exclude
    public String getUserEmail() {
        return this.user.getUserEmail();
    }

    @Override @Exclude
    public String getUserTelephone() {
        return this.user.getUserTelephone();
    }

    @Override @Exclude
    public Uri getUserPhotoUri() {
        return this.user.getUserPhotoUri();
    }

    @Override
    public void setVehiclelogo(int vehiclelogo) {
        this.vehicle.setVehiclelogo( vehiclelogo );
    }

    @Override
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicle.setVehicleRegistrationNumber( vehicleRegistrationNumber );
    }

    @Override
    public void setVehicleBrand(String vehicleBrand) {
        this.vehicle.setVehicleBrand( vehicleBrand );
    }

    @Override
    public void setVehicleModel(String vehicleModel) {
        this.vehicle.setVehicleModel( vehicleModel );
    }

    @Override
    public void setVehicleDateITV(String vehicleDateITV) {
        this.vehicle.setVehicleDateITV( vehicleDateITV );
    }

    @Override
    public void setVehicleDriving(int vehicleDriving) {
        this.vehicle.setVehicleDriving( vehicleDriving );
    }

    @Override
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.vehicle.setVehicleDrivingCurrent( vehicleDrivingCurrent );
    }

    @Override @Exclude
    public int getVehiclelogo() {
        return this.vehicle.getVehiclelogo();
    }

    @Override @Exclude
    public String getVehicleRegistrationNumber() {
        return this.vehicle.getVehicleRegistrationNumber();
    }

    @Override @Exclude
    public String getVehicleBrand() {
        return this.vehicle.getVehicleBrand();
    }

    @Override @Exclude
    public String getVehicleModel() {
        return this.vehicle.getVehicleModel();
    }

    @Override @Exclude
    public String getVehicleDateITV() {
        return this.vehicle.getVehicleDateITV();
    }

    @Override @Exclude
    public int getVehicleDriving() {
        return this.vehicle.getVehicleDriving();
    }

    @Override @Exclude
    public String getVehicleDrivingCurrent() {
        return this.vehicle.getVehicleDrivingCurrent();
    }

}
