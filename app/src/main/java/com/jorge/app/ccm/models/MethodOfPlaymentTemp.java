package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;

public class MethodOfPlaymentTemp extends DatesTemp implements iMethodOfPlayment{

    private final String PRIMARY_KEY;
    private final String KEY_METHOD_OF_PLAYMENT_LOGO;
    private final String KEY_METHOD_OF_PLAYMENT_NAME;

    public MethodOfPlaymentTemp(Context context, String TAG) {
        super( context, TAG );
        PRIMARY_KEY = getFILE_NAME() + "ticket";
        this.KEY_METHOD_OF_PLAYMENT_LOGO = PRIMARY_KEY + "ticketDate";
        this.KEY_METHOD_OF_PLAYMENT_NAME = PRIMARY_KEY + "methodOfPlayment";
    }

    public void setMethodOfPlayment( MethodOfPlaymentTemp methodOfPlayment ) {
        this.setDateInt( KEY_METHOD_OF_PLAYMENT_LOGO, methodOfPlayment.getMethodOfPlaymentLogo() );
        this.setDateString( KEY_METHOD_OF_PLAYMENT_NAME, methodOfPlayment.getMethodOfPlaymentNameMethodOfPlayment() );
    }

    @Override
    public void setMethodOfPlaymentLogo(int methodOfPlaymentLogo) {
        this.setDateInt( KEY_METHOD_OF_PLAYMENT_LOGO, methodOfPlaymentLogo );
    }

    @Override
    public void setMethodOfPlaymentNameMethodOfPlayment(String methodOfPlaymentNameMethodOfPlayment) {
        this.setDateString( KEY_METHOD_OF_PLAYMENT_NAME, methodOfPlaymentNameMethodOfPlayment );
    }

    @Override
    public int getMethodOfPlaymentLogo() {
        return this.getDateInt( KEY_METHOD_OF_PLAYMENT_LOGO );
    }

    @Override
    public String getMethodOfPlaymentNameMethodOfPlayment() {
        return this.getDateString( KEY_METHOD_OF_PLAYMENT_NAME );
    }

    public void removeTicket(){
        this.removeDate( KEY_METHOD_OF_PLAYMENT_LOGO );
        this.removeDate( KEY_METHOD_OF_PLAYMENT_NAME );
    }

    public void removeKEY_METHOD_OF_PLAYMENT_LOGO(){
        this.removeDate( KEY_METHOD_OF_PLAYMENT_LOGO );
    }

    public void removeKEY_METHOD_OF_PLAYMENT_NAME(){
        this.removeDate( KEY_METHOD_OF_PLAYMENT_NAME );
    }

}
