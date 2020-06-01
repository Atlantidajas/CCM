package com.jorge.app.ccm.models.temp;

import android.content.Context;

import com.google.firebase.database.Exclude;
import com.jorge.app.ccm.models.TypeExpense;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.models.MethodOfPlayment;
import com.jorge.app.ccm.models.Provider;
import com.jorge.app.ccm.models.Tickect;


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

    @Override @Exclude
    public String getTickectNumber() {
        return this.ticketTemp.getTickectNumber();
    }

    @Override @Exclude
    public String getTickectDate() {
        return this.ticketTemp.getTickectDate();
    }

    @Override @Exclude
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

    @Override @Exclude
    public String getProviderName() {
        return this.ticketTemp.getProviderName();
    }

    @Override @Exclude
    public String getProviderCifNif() {
        return this.ticketTemp.getProviderCifNif();
    }

    @Override @Exclude
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

    @Override @Exclude
    public int getMethodOfPlaymentValueLogo() {
        return this.ticketTemp.getMethodOfPlaymentValueLogo();
    }

    @Override @Exclude
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

    @Override @Exclude
    public int getTypeExpenseLogo() {
        return this.typeExpenseTemp.getTypeExpenseLogo();
    }

    @Override @Exclude
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

    @Override @Exclude
    public int getVehiclelogo() {
        return this.vehicleTemp.getVehiclelogo();
    }

    @Override @Exclude
    public String getVehicleRegistrationNumber() {
        return this.vehicleTemp.getVehicleRegistrationNumber();
    }

    @Override @Exclude
    public String getVehicleBrand() {
        return this.vehicleTemp.getVehicleBrand();
    }

    @Override @Exclude
    public String getVehicleModel() {
        return this.vehicleTemp.getVehicleModel();
    }

    @Override @Exclude
    public String getVehicleDateITV() {
        return this.vehicleTemp.getVehicleDateITV();
    }

    @Override @Exclude
    public int getVehicleDriving() {
        return this.vehicleTemp.getVehicleDriving();
    }

    @Override @Exclude
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