package com.jorge.app.ccm.models.temp;

import android.content.Context;

import com.jorge.app.ccm.models.MethodOfPlayment;
import com.jorge.app.ccm.utils.DatesTemp;

public class MethodOfPlaymentTemp extends DatesTemp {

    private final String PRIMARY_KEY = getFILE_NAME() + "methodOfPlayment";;
    private final String KEY_METHOD_OF_PLAYMENT_LOGO = PRIMARY_KEY + "Logo";;
    private final String KEY_METHOD_OF_PLAYMENT_NAME = PRIMARY_KEY + "Name";


    protected MethodOfPlaymentTemp(Context context,
                                String TAG ){
        super(context, TAG);
    }

    protected MethodOfPlaymentTemp(Context context,
                                String TAG,
                                MethodOfPlayment methodOfPlaymentValueLogo) {
        super( context, TAG );
        this.setDateInt( KEY_METHOD_OF_PLAYMENT_LOGO, methodOfPlaymentValueLogo.getMethodOfPlaymentLogo() );
        this.setDateString( KEY_METHOD_OF_PLAYMENT_NAME, methodOfPlaymentValueLogo.getMethodOfPlaymentName() );
    }

    protected MethodOfPlaymentTemp(Context context,
                                String TAG,
                                int methodOfPlaymentValueLogo,
                                String methodOfPlaymentValueName) {
        super( context, TAG );
        this.setDateInt( KEY_METHOD_OF_PLAYMENT_LOGO, methodOfPlaymentValueLogo );
        this.setDateString( KEY_METHOD_OF_PLAYMENT_NAME, methodOfPlaymentValueName );
    }

    protected void setMethodOfPlaymentValueLogo(int methodOfPlaymentValueLogo) {
        this.setDateInt( KEY_METHOD_OF_PLAYMENT_LOGO, methodOfPlaymentValueLogo );
    }

    protected void setMethodOfPlaymentValueName(String methodOfPlaymentValueName) {
        this.setDateString( KEY_METHOD_OF_PLAYMENT_NAME, methodOfPlaymentValueName );
    }

    protected int getMethodOfPlaymentValueLogo() {
        return this.getDateInt( KEY_METHOD_OF_PLAYMENT_LOGO );
    }

    protected String getMethodOfPlaymentValueName() {
        return this.getDateString( KEY_METHOD_OF_PLAYMENT_NAME );
    }

    protected void removeMethodOfPlayment(){
        this.removeDate( KEY_METHOD_OF_PLAYMENT_LOGO );
        this.removeDate( KEY_METHOD_OF_PLAYMENT_NAME );
    }

    protected void removeMethodOfPlaymentLogo() {
        this.removeDate( KEY_METHOD_OF_PLAYMENT_LOGO );
    }

    protected void removeMethodOfPlaymentName() {
        this.removeDate( KEY_METHOD_OF_PLAYMENT_NAME );
    }
}
