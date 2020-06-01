package com.jorge.app.ccm.models;

import android.net.Uri;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.jorge.app.ccm.models.temp.ExpenseTemp;

public class Expense implements iTickect, iTypeExpense, iUser, iVehicle{

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
        this.user = new User( true );
        this.vehicle = new Vehicle( expenseTemp );
    }

    public Expense( DataSnapshot dataSnapshotExpense ) {
        this.tickect = new Tickect( dataSnapshotExpense );
        this.typeExpense = new TypeExpense( dataSnapshotExpense );
        this.user = new User( dataSnapshotExpense );
        this.vehicle = new Vehicle( dataSnapshotExpense );
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

    @Exclude
    public Tickect getTickect() {
        return tickect;
    }

    @Exclude
    public TypeExpense getTypeExpense() {
        return typeExpense;
    }

    @Exclude
    public User getUser() {
        return user;
    }

    @Exclude
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

    @Override
    public String getTickectNumber() {
        return this.tickect.getTickectNumber();
    }

    @Override
    public String getTickectDate() {
        return this.tickect.getTickectDate();
    }

    @Override
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

    @Override
    public String getProviderName() {
        return this.tickect.getProviderName();
    }

    @Override
    public String getProviderCifNif() {
        return this.tickect.getProviderCifNif();
    }

    @Override
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

    @Override
    public int getMethodOfPlaymentLogo() {
        return this.tickect.getMethodOfPlaymentLogo();
    }

    @Override
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

    @Override
    public int getTypeExpenseLogo() {
        return this.typeExpense.getTypeExpenseLogo();
    }

    @Override
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

    @Override
    public String getIdUser() {
        return this.user.getIdUser();
    }

    @Override
    public String getUserName() {
        return this.user.getUserName();
    }

    @Override
    public String getUserPhotoUriString() {
        return this.user.getUserPhotoUriString();
    }

    @Override
    public String getUserEmail() {
        return this.user.getUserEmail();
    }

    @Override
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

    @Override
    public int getVehiclelogo() {
        return this.vehicle.getVehiclelogo();
    }

    @Override
    public String getVehicleRegistrationNumber() {
        return this.vehicle.getVehicleRegistrationNumber();
    }

    @Override
    public String getVehicleBrand() {
        return this.vehicle.getVehicleBrand();
    }

    @Override
    public String getVehicleModel() {
        return this.vehicle.getVehicleModel();
    }

    @Override
    public String getVehicleDateITV() {
        return this.vehicle.getVehicleDateITV();
    }

    @Override
    public int getVehicleDriving() {
        return this.vehicle.getVehicleDriving();
    }

    @Override @Exclude
    public String getVehicleDrivingCurrent() {
        return this.vehicle.getVehicleDrivingCurrent();
    }

}
