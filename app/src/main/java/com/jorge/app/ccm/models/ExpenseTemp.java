package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;

public class ExpenseTemp extends DatesTemp implements iTicketTemp, iTypeExpenseTemp, iVehicleTemp  {

    private TypeExpenseTemp typeExpenseTemp;
    private TicketTemp ticketTemp;
    private VehicleTemp vehicleTemp;

    public ExpenseTemp( Context context, String TAG ) {
        super( context, TAG );
        this.ticketTemp = new TicketTemp( context, TAG );
        this.vehicleTemp = new VehicleTemp( context, TAG );
        this.typeExpenseTemp = new TypeExpenseTemp( context, TAG );
    }

    public Tickect getTickect(){
        Tickect tickect = new Tickect(
                ticketTemp.getMethodOfPlaymentNameMethodOfPlayment(),
                ticketTemp.getProviderName(),
                ticketTemp.getProviderCifNif(),
                ticketTemp.getProviderTelephone(),
                ticketTemp.getTickectNumber(),
                ticketTemp.getTickectDate(),
                ticketTemp.getTickectTotalExpense());
        return tickect;
    }

    public TypeExpenseTemp getTypeExpenseTemp() {
        return typeExpenseTemp;
    }

    public TicketTemp getTicketTemp() {
        return ticketTemp;
    }

    @Override
    public void setTicket(Tickect ticket) {
        this.ticketTemp.setTickectNumber( ticket.getTickectTotalExpense() );
        this.ticketTemp.setTickectDate( ticket.getTickectDate() );
        this.ticketTemp.setTickectTotalExpense( ticket.getTickectTotalExpense() );
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
    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.typeExpenseTemp.setTypeExpenseName( methodOfPlaymentNameMethodOfPlayment );
    }

    @Override
    public int getMethodOfPlaymentLogo() {
        return this.typeExpenseTemp.getTypeExpenseLogo();
    }

    @Override
    public String getMethodOfPlaymentNameMethodOfPlayment() {
        return this.typeExpenseTemp.getTypeExpenseName();
    }

    @Override
    public void removeTicket() {
        this.ticketTemp.removeTicket();
    }

    @Override
    public void removeKEY_TOTAL_IMPORT() {
        this.ticketTemp.removeKEY_TOTAL_IMPORT();
    }

    @Override
    public void removeKEY_TICKET_NUMBER() {
        this.ticketTemp.removeKEY_TICKET_NUMBER();
    }

    @Override
    public void removeKEY_DATE_TICKET() {
        this.ticketTemp.removeKEY_DATE_TICKET();
    }

    @Override
    public void removeKEY_METHOD_OF_PLAYMENT() {
        this.ticketTemp.removeKEY_METHOD_OF_PLAYMENT();
    }


    @Override
    public void setTypeExpense(TypeExpense typeExpense) {
        typeExpenseTemp.setTypeExpenseName( typeExpense.getTypeExpenseName() );
        typeExpenseTemp.setTypeExpenseLogo( typeExpense.getTypeExpenseLogo() );
    }

    @Override
    public TypeExpense getTypeExpense() {
        return this.typeExpenseTemp.getTypeExpense();
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
    public void removeTypeName() {
        this.typeExpenseTemp.removeTypeName();
    }

    @Override
    public void removeLogo() {
        this.typeExpenseTemp.removeLogo();
    }

    @Override
    public void removeTypeExpense() {
        this.typeExpenseTemp.removeTypeExpense();
    }


    @Override
    public void setvehicle(Vehicle vehicle) {
        this.vehicleTemp.setvehicle( vehicle );
    }

    @Override
    public void setRegistrationNunber(String registrationNunber) {
        this.vehicleTemp.setVehicleRegistrationNumber( registrationNunber );
    }

    @Override
    public void setBrand(String brand) {
        this.vehicleTemp.setVehicleBrand( brand );
    }

    @Override
    public void setModel(String model) {
        this.vehicleTemp.setVehicleModel( model );
    }

    @Override
    public void setDateITV(String dateITV) {
        this.vehicleTemp.setVehicleDateITV( dateITV );
    }

    @Override
    public void setDriving(int driving) {
        this.vehicleTemp.setVehicleDriving( driving );
    }

    @Override
    public Vehicle getVehicleTemp() {
        return vehicleTemp.getVehicleTemp();
    }

    @Override
    public int getLogoVehicle() {
        return this.vehicleTemp.getVehiclelogo();
    }

    @Override
    public String getRegistrationNumber() {
        return this.vehicleTemp.getVehicleRegistrationNumber();
    }

    @Override
    public String getBrand() {
        return this.vehicleTemp.getVehicleBrand();
    }

    @Override
    public String getModel() {
        return this.vehicleTemp.getVehicleModel();
    }

    @Override
    public String getDateITV() {
        return this.vehicleTemp.getVehicleDateITV();
    }

    @Override
    public int getDriving() {
        return this.vehicleTemp.getVehicleDriving();
    }

    @Override
    public void removeKEY_BRAND_VEHICLE() {
        this.vehicleTemp.removeKEY_VEHICLE_BRAND();
    }

    @Override
    public void removeKEY_MODEL_VEHICLE() {
        this.vehicleTemp.removeKEY_VEHICLE_MODEL();
    }

    @Override
    public void removeKEY_DATE_ITV() {
        this.vehicleTemp.removeKEY_VEHICLE_DATE_ITV();
    }

    @Override
    public void removeKEY_DRIVING_VEHICLE() {
        this.vehicleTemp.removeKEY_VEHICLE_DRIVING_VEHICLE();
    }

    @Override
    public void removeKEY_LOGO_VHEICLE() {
        this.vehicleTemp.removeKEY_VEHICLE_LOGO();
    }

    @Override
    public void removeKEY_REGISTRATION_NUMBER_VEHICLE() {
        this.vehicleTemp.removeKEY_VEHICLE_REGISTRATION_NUMBER();
    }

    @Override
    public void removeVehicle() {
        this.vehicleTemp.removeVehicle();
    }
}
