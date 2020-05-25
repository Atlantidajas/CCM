package com.jorge.app.ccm.models.Expense;

import android.content.Context;

import com.jorge.app.ccm.models.ticket.ITicketTemp;
import com.jorge.app.ccm.models.ticket.Tickect;
import com.jorge.app.ccm.models.ticket.TicketTemp;
import com.jorge.app.ccm.models.typeExpense.ITypeExpenseTemp;
import com.jorge.app.ccm.models.typeExpense.TypeExpense;
import com.jorge.app.ccm.models.typeExpense.TypeExpenseTemp;
import com.jorge.app.ccm.models.vehicle.IVehicleTemp;
import com.jorge.app.ccm.models.vehicle.Vehicle;
import com.jorge.app.ccm.models.vehicle.VehicleTemp;
import com.jorge.app.ccm.utils.DatesTemp;

public class ExpenseTemp implements ITicketTemp, IVehicleTemp, ITypeExpenseTemp {

    private TicketTemp ticketTemp;
    private VehicleTemp vehicleTemp;
    private TypeExpenseTemp typeExpenseTemp;

    public ExpenseTemp(Context context, String TAG) {
        this.ticketTemp = new TicketTemp( context, TAG );
        this.vehicleTemp = new VehicleTemp( context, TAG );
        this.typeExpenseTemp = new TypeExpenseTemp( context, TAG );

    }

    @Override
    public void setTicket(Tickect ticket) {
        this.ticketTemp.setTicket( ticket );
    }

    @Override
    public void setTickectNumber(String tickectNumber) {
        this.ticketTemp.setTickectNumber( tickectNumber );
    }

    @Override
    public void setDate(String date) {
        this.ticketTemp.setDate( date );
    }

    @Override
    public void setMethodOfPlayment(String methodOfPlayment) {
        this.ticketTemp.setMethodOfPlayment( methodOfPlayment );
    }

    @Override
    public void setTotalExpense(float totalExpense) {
        this.ticketTemp.setTotalExpense( totalExpense );
    }

    @Override
    public void setvehicle(Vehicle vehicle) {
        this.vehicleTemp.setvehicle( vehicle );
    }

    @Override
    public void setBrand(String brand) {
        this.vehicleTemp.setBrand( brand );
    }

    @Override
    public void setModel(String model) {
        this.vehicleTemp.setModel( model );
    }

    @Override
    public void setDateITV(String dateITV) {
        this.vehicleTemp.setDateITV( dateITV );
    }

    @Override
    public void setDriving(int driving) {
        this.vehicleTemp.setDriving( driving );
    }

    @Override
    public Vehicle getVehicle() {
        return vehicleTemp.getVehicle();
    }

    @Override
    public int getLogoVehicle() {
        return vehicleTemp.getLogoVehicle();
    }

    @Override
    public String getRegistrationNumber() {
        return vehicleTemp.getRegistrationNumber();
    }

    @Override
    public String getBrand() {
        return vehicleTemp.getBrand();
    }

    @Override
    public String getModel() {
        return vehicleTemp.getModel();
    }

    @Override
    public String getDateITV() {
        return vehicleTemp.getDateITV();
    }

    @Override
    public int getDriving() {
        return vehicleTemp.getDriving();
    }

    @Override
    public void removeKEY_BRAND_VEHICLE() {
        vehicleTemp.removeKEY_BRAND_VEHICLE();
    }

    @Override
    public void removeKEY_MODEL_VEHICLE() {
        vehicleTemp.removeKEY_MODEL_VEHICLE();
    }

    @Override
    public void removeKEY_DATE_ITV() {
        vehicleTemp.removeKEY_DATE_ITV();
    }

    @Override
    public void removeKEY_DRIVING_VEHICLE() {
        vehicleTemp.removeKEY_DRIVING_VEHICLE();
    }

    @Override
    public void removeKEY_LOGO_VHEICLE() {
        vehicleTemp.removeKEY_LOGO_VHEICLE();
    }

    @Override
    public void removeKEY_REGISTRATION_NUMBER_VEHICLE() {
        vehicleTemp.removeKEY_REGISTRATION_NUMBER_VEHICLE();
    }

    @Override
    public void removeVehicle() {
        vehicleTemp.removeVehicle();
    }

    @Override
    public void setTypeExpense(TypeExpense typeExpense) {
        typeExpenseTemp.setTypeExpense( typeExpense );
    }

    @Override
    public void setLogo(int logo) {
        typeExpenseTemp.setLogo( logo );
    }

    @Override
    public void setTypeName(String typeName) {
        typeExpenseTemp.setTypeName( typeName );
    }

    @Override
    public TypeExpense getTypeExpense() {
        return typeExpenseTemp.getTypeExpense();
    }

    @Override
    public String getTypeName() {
        return typeExpenseTemp.getTypeName();
    }

    @Override
    public int getLogo() {
        return typeExpenseTemp.getLogo();
    }

    @Override
    public String getKEY_TYPE_NAME() {
        return typeExpenseTemp.getKEY_TYPE_NAME();
    }

    @Override
    public String getKEY_LOGO() {
        return typeExpenseTemp.getKEY_LOGO();
    }

    @Override
    public void removeTypeExpense() {
        typeExpenseTemp.removeTypeExpense();
    }

    @Override
    public void removeTypeName() {
        typeExpenseTemp.removeTypeName();
    }

    @Override
    public void removeLogo() {
        typeExpenseTemp.removeLogo();
    }



    @Override
    public String getTickectNumber() {
        return this.ticketTemp.getTickectNumber();
    }

    @Override
    public String getDate() {
        return this.ticketTemp.getDate();
    }

    @Override
    public String getMethodOfPlayment() {
        return this.ticketTemp.getMethodOfPlayment();
    }

    @Override
    public float getTotalExpense() {
        return this.ticketTemp.getTotalExpense();
    }

    @Override
    public void removeTicket() {
        this.ticketTemp.removeTicket();
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
    public void removeKEY_TOTAL_IMPORT() {
        this.ticketTemp.removeKEY_TOTAL_IMPORT();
    }

    @Override
    public String getPRIMARY_KEY() {
        return this.ticketTemp.getPRIMARY_KEY();
    }

    @Override
    public String getKEY_TICKET_NUMBER() {
        return this.ticketTemp.getTickectNumber();
    }

    @Override
    public String getKEY_DATE_TICKET() {
        return this.ticketTemp.getKEY_DATE_TICKET();
    }

    @Override
    public String getKEY_METHOD_OF_PLAYMENT() {
        return this.ticketTemp.getKEY_METHOD_OF_PLAYMENT();
    }

    @Override
    public String getKEY_TOTAL_IMPORT() {
        return this.ticketTemp.getKEY_TOTAL_IMPORT();
    }
}
