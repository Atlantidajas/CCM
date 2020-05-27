package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;


public class ExpenseTemp implements iTicketTemp, iTypeExpenseTemp, iVehicleTemp  {

    private TicketTemp ticketTemp;
    private TypeExpenseTemp typeExpenseTemp;
    private VehicleTemp vehicleTemp;


    public ExpenseTemp( Context context, String TAG ){
        this.ticketTemp = new TicketTemp( context,TAG );
        this.typeExpenseTemp = new TypeExpenseTemp( context, TAG );
        this.vehicleTemp = new VehicleTemp( context, TAG );
    }

    public ExpenseTemp(Context context, String TAG, MethodOfPlayment methodOfPlayment,
                       Provider provider,
                       Tickect tickect,
                       TypeExpense typeExpense,
                       Vehicle vehicle ) {
        this.ticketTemp = new TicketTemp( context, TAG, methodOfPlayment, provider, tickect);
        this.typeExpenseTemp = new TypeExpenseTemp( context, TAG, typeExpense );
        this.vehicleTemp = new VehicleTemp( context, TAG, vehicle );
    }

    public ExpenseTemp(Context context, String TAG, int methodOfPlaymentLogo,
                       String methodOfPlaymentName,
                       String providerName,
                       String providerCifNif,
                       String providerTelephone,
                       String tickectNumber,
                       String tickectDate,
                       String tickectTotalExpense, //<-- Hasta aquí TickectTemp
                       int typeExpenseLogo,
                       String typeExpenseName, //<-- Hasta aquí TypeExpenseTemp
                       int logoVehicle,
                       String registrationNumber,
                       String brand,
                       String model,
                       String dateITV,
                       int driving //<--Hasta aquí VehicleTemp
    ) {
        this.ticketTemp = new TicketTemp( context, TAG, methodOfPlaymentLogo,
        methodOfPlaymentName,
        providerName,
        providerCifNif,
        providerTelephone,
        tickectNumber,
        tickectDate,
        tickectTotalExpense  );
        this.typeExpenseTemp = new TypeExpenseTemp( context, TAG, typeExpenseLogo, typeExpenseName );
        this.vehicleTemp = new VehicleTemp( context,
                TAG,
                logoVehicle,
                registrationNumber,
                brand,
                model,
                dateITV,
                driving );
    }


    public void setTicketTemp( Context context, String TAG, MethodOfPlayment methodOfPlayment,
                               Provider provider,
                               Tickect tickect ) {
        this.ticketTemp = new TicketTemp( context,
                TAG,
                methodOfPlayment,
                provider,
                tickect);
    }

    public void setTypeExpenseTemp( Context context, String TAG, TypeExpense typeExpense) {
        this.typeExpenseTemp = new TypeExpenseTemp( context, TAG, typeExpense );
    }

    public void setVehicleTemp( Context context, String TAG, Vehicle vehicle ) {
        this.vehicleTemp = new VehicleTemp( context,
                TAG,
                vehicle);
    }

    public Tickect getTicketTemp() {

        Tickect tickect = new Tickect( this.getMethodOfPlaymentValueLogo(),
        this.getMethodOfPlaymentValueName(),
        this.getProviderName(),
        this.getProviderCifNif(),
        this.getProviderTelephone(),
        this.getTickectNumber(),
        this.getTickectDate(),
        this.getTickectTotalExpense()  );

        return tickect;
    }

    public TypeExpense getTypeExpenseTemp() {

        TypeExpense typeExpense = new TypeExpense( this.getTypeExpenseLogo(), this.getTypeExpenseName() );

        return typeExpense;
    }

    public Vehicle getVehicleTemp() {

        Vehicle vehicle = new Vehicle( this.getVehiclelogo(),
                this.getVehicleRegistrationNumber(),
                this.getVehicleBrand(),
                this.getVehicleModel(),
                this.getVehicleDateITV(),
                this.getVehicleDriving());

        return vehicle;
    }

    public void removeExpenseTemp(){
        this.typeExpenseTemp.removeTypeExpense();
        this.vehicleTemp.removeVehicle();
        this.ticketTemp.removeTicket();

    }

    @Override
    public void setTickectNumber(String tickectNumber) {
        this.ticketTemp.setTickectNumber( tickectNumber );
    }

    @Override
    public void setTickectDate(String tickectDate) {
        this.ticketTemp.setTickectDate( tickectDate );
    }

    @Override
    public void setTickectTotalExpense(String tickectTotalExpense) {
        this.ticketTemp.setTickectTotalExpense( tickectTotalExpense );
    }

    @Override
    public String getTickectNumber() {
        return this.ticketTemp.getTickectNumber();
    }

    @Override
    public String getTickectDate() {
        return this.ticketTemp.getTickectDate();
    }

    @Override
    public String getTickectTotalExpense() {
        return this.ticketTemp.getTickectTotalExpense();
    }

    @Override
    public void removeTicket() {
        this.ticketTemp.removeTicket();
    }

    @Override
    public void removeTickectNumber() {
        this.ticketTemp.removeTicket();
    }

    @Override
    public void removeTickectDate() {
        this.ticketTemp.removeTickectDate();
    }

    @Override
    public void removeTickectTotalExpense() {
        this.ticketTemp.removeTickectTotalExpense();
    }

    @Override
    public void setProviderName(String providerName) {
        this.ticketTemp.setProviderName( providerName );
    }

    @Override
    public void setProviderCifNif(String providerCifNif) {
        this.ticketTemp.setProviderCifNif( providerCifNif );
    }

    @Override
    public void setProviderTelephone(String providerTelephone) {
        this.ticketTemp.setProviderTelephone( providerTelephone );
    }

    @Override
    public String getProviderName() {
        return this.ticketTemp.getProviderName();
    }

    @Override
    public String getProviderCifNif() {
        return this.ticketTemp.getProviderCifNif();
    }

    @Override
    public String getProviderTelephone() {
        return this.ticketTemp.getProviderTelephone();
    }

    @Override
    public void removeProvider() {
        this.ticketTemp.removeProvider();
    }

    @Override
    public void removeProviderName() {
        this.ticketTemp.removeProviderName();
    }

    @Override
    public void removeProviderCifNif() {
        this.ticketTemp.removeProviderCifNif();
    }

    @Override
    public void removeProviderTelephone() {
        this.ticketTemp.removeProviderTelephone();
    }

    @Override
    public void setMethodOfPlaymentValueLogo(int methodOfPlaymentValueLogo) {
        this.ticketTemp.setMethodOfPlaymentValueLogo( methodOfPlaymentValueLogo );
    }

    @Override
    public void setMethodOfPlaymentValueName(String methodOfPlaymentValueName) {
        this.ticketTemp.setMethodOfPlaymentValueName( methodOfPlaymentValueName );
    }

    @Override
    public int getMethodOfPlaymentValueLogo() {
        return this.ticketTemp.getMethodOfPlaymentValueLogo();
    }

    @Override
    public String getMethodOfPlaymentValueName() {
        return this.ticketTemp.getMethodOfPlaymentValueName();
    }

    @Override
    public void removeMethodOfPlayment() {
        this.ticketTemp.removeMethodOfPlayment();
    }

    @Override
    public void removeMethodOfPlaymentLogo() {
        this.ticketTemp.removeMethodOfPlaymentLogo();
    }

    @Override
    public void removeMethodOfPlaymentName() {
        this.ticketTemp.removeMethodOfPlaymentName();
    }


    @Override
    public void setTypeExpenseLogo(int typeExpenseLogo) {
        this.typeExpenseTemp.setTypeExpenseLogo( typeExpenseLogo );
    }

    @Override
    public void setTypeExpenseName(String typeExpenseName) {
        this.typeExpenseTemp.setTypeExpenseName( typeExpenseName );
    }

    @Override
    public int getTypeExpenseLogo() {
        return this.typeExpenseTemp.getTypeExpenseLogo();
    }

    @Override
    public String getTypeExpenseName() {
        return this.typeExpenseTemp.getTypeExpenseName();
    }

    @Override
    public void removeTypeExpense() {
        this.typeExpenseTemp.removeTypeExpense();
    }

    @Override
    public void removeTypeExpenseName() {
        this.typeExpenseTemp.removeTypeExpenseName();
    }

    @Override
    public void removeTypeExpenseLogo() {
        this.typeExpenseTemp.removeTypeExpenseLogo();
    }

    @Override
    public void setVehiclelogo(int vehiclelogo) {
        this.vehicleTemp.setVehiclelogo( vehiclelogo );
    }

    @Override
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleTemp.setVehicleRegistrationNumber( vehicleRegistrationNumber );
    }

    @Override
    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleTemp.setVehicleBrand( vehicleBrand );
    }

    @Override
    public void setVehicleModel(String vehicleModel) {
        this.vehicleTemp.setVehicleModel( vehicleModel );
    }

    @Override
    public void setVehicleDateITV(String vehicleDateITV) {
        this.vehicleTemp.setVehicleDateITV( vehicleDateITV );
    }

    @Override
    public void setVehicleDriving(int vehicleDriving) {
        this.vehicleTemp.setVehicleDriving( vehicleDriving );
    }

    @Override
    public void setVehicleDrivingCurrent(String vehicleDrivingCurrent) {
        this.vehicleTemp.setVehicleDrivingCurrent( vehicleDrivingCurrent );
    }

    @Override
    public int getVehiclelogo() {
        return this.vehicleTemp.getVehiclelogo();
    }

    @Override
    public String getVehicleRegistrationNumber() {
        return this.vehicleTemp.getVehicleRegistrationNumber();
    }

    @Override
    public String getVehicleBrand() {
        return this.vehicleTemp.getVehicleBrand();
    }

    @Override
    public String getVehicleModel() {
        return this.vehicleTemp.getVehicleModel();
    }

    @Override
    public String getVehicleDateITV() {
        return this.vehicleTemp.getVehicleDateITV();
    }

    @Override
    public int getVehicleDriving() {
        return this.vehicleTemp.getVehicleDriving();
    }

    @Override
    public String getVehicleDrivingCurrent() {
        return this.vehicleTemp.getVehicleDrivingCurrent();
    }

    @Override
    public void removeVehicle() {
        this.vehicleTemp.removeVehicle();
    }

    @Override
    public void removeVehicleLogo() {
        this.vehicleTemp.removeVehicleLogo();
    }

    @Override
    public void removeVehicleRegistrationNumber() {
        this.vehicleTemp.removeVehicleRegistrationNumber();
    }

    @Override
    public void removeVehicleBrand() {
        this.vehicleTemp.removeVehicleBrand();
    }

    @Override
    public void removeVehicleModel() {
        this.vehicleTemp.removeVehicleModel();
    }

    @Override
    public void removeVehicleDateItv() {
        this.vehicleTemp.removeVehicleDateItv();
    }

    @Override
    public void removeVehicleDriving() {
        this.vehicleTemp.removeVehicleDriving();
    }
}