package com.jorge.app.ccm.models;

import android.net.Uri;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

/**
 * Clase que permite trabajar, crear con objetos de tipo Expense
 * @Author: Jorge.HL
 */
public class Expense implements iTickect, iTypeExpense, iUser, iVehicle{

    public Tickect tickect;
    public TypeExpense typeExpense;
    public User user;
    public Vehicle vehicle;

    public Expense(){ }

    /**
     *
     * @param tickect
     * @param typeExpense
     * @param user
     * @param vehicle
     */
    public Expense(Tickect tickect, TypeExpense typeExpense, User user, Vehicle vehicle) {
        this.tickect = tickect;
        this.typeExpense = typeExpense;
        this.user = user;
        this.vehicle = vehicle;
    }

    /**
     *
     * @param expenseTemp
     */
    public Expense( ExpenseTemp expenseTemp) {
        this.tickect = new Tickect( expenseTemp );
        this.typeExpense = new TypeExpense( expenseTemp );
        this.user = new User( true );
        this.vehicle = new Vehicle( expenseTemp );
    }

    /**
     *
     * @param dataSnapshotExpense
     */
    public Expense( DataSnapshot dataSnapshotExpense ) {
        this.tickect = new Tickect( dataSnapshotExpense );
        this.typeExpense = new TypeExpense( dataSnapshotExpense );
        this.user = new User( dataSnapshotExpense );
        this.vehicle = new Vehicle( dataSnapshotExpense );
    }

    /**
     *
     * @param vehicle
     */
    public Expense( Vehicle vehicle ) {
        this.vehicle = vehicle;
    }

    /**
     *
     * @param tickect
     */
    public void setTickect(Tickect tickect) {
        this.tickect = tickect;
    }

    /**
     *
     * @param typeExpense
     */
    public void setTypeExpense(TypeExpense typeExpense) {
        this.typeExpense = typeExpense;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @param vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     *
     * @return
     */
    @Exclude
    public Tickect getTickect() {
        return tickect;
    }

    /**
     *
     * @return
     */
    @Exclude
    public TypeExpense getTypeExpense() {
        return typeExpense;
    }

    /**
     *
     * @return
     */
    @Exclude
    public User getUser() {
        return user;
    }

    /**
     *
     * @return
     */
    @Exclude
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     *
     * @param tickectNumber
     */
    @Override
    public void setTickectNumber(String tickectNumber) {
        this.tickect.setTickectNumber( tickectNumber );
    }

    /**
     *
     * @param tickectDate
     */
    @Override
    public void setTickectDate(String tickectDate) {
        this.tickect.setTickectDate( tickectDate );
    }

    /**
     *
     * @param tickectTotalExpense
     */
    @Override
    public void setTickectTotalExpense(String tickectTotalExpense) {
        this.tickect.setTickectTotalExpense( tickectTotalExpense );
    }

    /**
     *
     * @return
     */
    @Override
    public String getTickectNumber() {
        return this.tickect.getTickectNumber();
    }

    /**
     *
     * @return
     */
    @Override
    public String getTickectDate() {
        return this.tickect.getTickectDate();
    }

    /**
     *
     * @return
     */
    @Override
    public String getTickectTotalExpense() {
        return this.tickect.getTickectTotalExpense();
    }

    /**
     *
     * @param providerName
     */
    @Override
    public void setProviderName(String providerName) {
        this.tickect.setProviderName( providerName );
    }

    /**
     *
     * @param providerCifNif
     */
    @Override
    public void setProviderCifNif(String providerCifNif) {
        this.tickect.setProviderCifNif( providerCifNif );
    }

    /**
     *
     * @param providerTelephone
     */
    @Override
    public void setProviderTelephone(String providerTelephone) {
        this.tickect.setProviderTelephone( providerTelephone );
    }

    /**
     *
     * @return
     */
    @Override
    public String getProviderName() {
        return this.tickect.getProviderName();
    }

    /**
     *
     * @return
     */
    @Override
    public String getProviderCifNif() {
        return this.tickect.getProviderCifNif();
    }

    /**
     *
     * @return
     */
    @Override
    public String getProviderTelephone() {
        return this.tickect.getProviderTelephone();
    }

    /**
     *
     * @param methodOfPlaymentLogo
     */
    @Override
    public void setMethodOfPlaymentLogo(int methodOfPlaymentLogo) {
        this.tickect.setMethodOfPlaymentLogo( methodOfPlaymentLogo );
    }

    /**
     *
     * @param methodOfPlaymentNameMethodOfPlayment
     */
    @Override
    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.tickect.setMethodOfPlaymentNameMethodOfPlayment( methodOfPlaymentNameMethodOfPlayment );
    }

    /**
     *
     * @return
     */
    @Override
    public int getMethodOfPlaymentLogo() {
        return this.tickect.getMethodOfPlaymentLogo();
    }

    /**
     *
     * @return
     */
    @Override
    public String getMethodOfPlaymentName() {
        return this.tickect.getMethodOfPlaymentName();
    }

    /**
     *
     * @param typeExpenseLogo
     */
    @Override
    public void setTypeExpenseLogo(int typeExpenseLogo) {
        this.typeExpense.setTypeExpenseLogo( typeExpenseLogo );
    }

    /**
     *
     * @param typeExpenseName
     */
    @Override
    public void setTypeExpenseName(String typeExpenseName) {
        this.typeExpense.setTypeExpenseName( typeExpenseName );
    }

    /**
     *
     * @return
     */
    @Override
    public int getTypeExpenseLogo() {
        return this.typeExpense.getTypeExpenseLogo();
    }

    /**
     *
     * @return
     */
    @Override
    public String getTypeExpenseName() {
        return this.typeExpense.getTypeExpenseName();
    }

    /**
     *
     * @param email
     */
    @Override
    public void setUserEmail(String email) {
        this.user.setUserEmail( email );
    }

    /**
     *
     * @param telephone
     */
    @Override
    public void setUserTelephone(String telephone) {
        this.user.setUserTelephone( telephone );
    }

    /**
     *
     * @return
     */
    @Override
    public String getIdUser() {
        return this.user.getIdUser();
    }

    /**
     *
     * @return
     */
    @Override
    public String getUserName() {
        return this.user.getUserName();
    }

    /**
     *
     * @return
     */
    @Override
    public String getUserPhotoUriString() {
        return this.user.getUserPhotoUriString();
    }

    /**
     *
     * @return
     */
    @Override
    public String getUserEmail() {
        return this.user.getUserEmail();
    }

    /**
     *
     * @return
     */
    @Override
    public String getUserTelephone() {
        return this.user.getUserTelephone();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public Uri getUserPhotoUri() {
        return this.user.getUserPhotoUri();
    }

    /**
     *
     * @param vehiclelogo
     */
    @Override
    public void setVehiclelogo(int vehiclelogo) {
        this.vehicle.setVehiclelogo( vehiclelogo );
    }

    /**
     *
     * @param vehicleRegistrationNumber
     */
    @Override
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicle.setVehicleRegistrationNumber( vehicleRegistrationNumber );
    }

    /**
     *
     * @param vehicleBrand
     */
    @Override
    public void setVehicleBrand(String vehicleBrand) {
        this.vehicle.setVehicleBrand( vehicleBrand );
    }

    /**
     *
     * @param vehicleModel
     */
    @Override
    public void setVehicleModel(String vehicleModel) {
        this.vehicle.setVehicleModel( vehicleModel );
    }

    /**
     *
     * @param vehicleDateITV
     */
    @Override
    public void setVehicleDateITV(String vehicleDateITV) {
        this.vehicle.setVehicleDateITV( vehicleDateITV );
    }

    /**
     *
     * @param vehicleDriving
     */
    @Override
    public void setVehicleDriving(int vehicleDriving) {
        this.vehicle.setVehicleDriving( vehicleDriving );
    }

    /**
     *
     * @param vehicleDrivingCurrent
     */
    @Override
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.vehicle.setVehicleDrivingCurrent( vehicleDrivingCurrent );
    }

    /**
     *
     * @return
     */
    @Override
    public int getVehiclelogo() {
        return this.vehicle.getVehiclelogo();
    }

    /**
     *
     * @return
     */
    @Override
    public String getVehicleRegistrationNumber() {
        return this.vehicle.getVehicleRegistrationNumber();
    }

    /**
     *
     * @return
     */
    @Override
    public String getVehicleBrand() {
        return this.vehicle.getVehicleBrand();
    }

    /**
     *
     * @return
     */
    @Override
    public String getVehicleModel() {
        return this.vehicle.getVehicleModel();
    }

    /**
     *
     * @return
     */
    @Override
    public String getVehicleDateITV() {
        return this.vehicle.getVehicleDateITV();
    }

    /**
     *
     * @return
     */
    @Override
    public int getVehicleDriving() {
        return this.vehicle.getVehicleDriving();
    }

    /**
     *
     * @return
     */
    @Override @Exclude
    public String getVehicleDrivingCurrent() {
        return this.vehicle.getVehicleDrivingCurrent();
    }

}
