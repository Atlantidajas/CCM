package com.jorge.app.ccm.models.temp;

import android.content.Context;

import com.jorge.app.ccm.models.MethodOfPlayment;
import com.jorge.app.ccm.models.Provider;

public class ProviderTemp extends MethodOfPlaymentTemp {

    private final String PRIMARY_KEY = getFILE_NAME() + "provider";
    private final String KEY_PROVIDER_NAME = PRIMARY_KEY + "Name";
    private final String KEY_PROVIDER_CIF_NIF = PRIMARY_KEY + "CifNif";
    private final String KEY_PROVIDER_TELEPHONE = PRIMARY_KEY + "Telephone";

    protected ProviderTemp(Context context,
                                String TAG ){
        super(context, TAG);
    }

    protected ProviderTemp(Context context,
                        String TAG,
                        MethodOfPlayment methodOfPlayment,
                        Provider provider ) {
        super( context, TAG, methodOfPlayment );
        this.setDateString( KEY_PROVIDER_NAME, provider.getProviderName() );
        this.setDateString( KEY_PROVIDER_CIF_NIF, provider.getProviderName());
        this.setDateString( KEY_PROVIDER_TELEPHONE, provider.getProviderName() );
    }

    protected ProviderTemp(Context context,
                        String TAG,
                        int methodOfPlaymentLogo,
                        String methodOfPlaymentName,
                        String providerName,
                        String providerCifNif,
                        String providerTelephone ) {
        super( context, TAG, methodOfPlaymentLogo, methodOfPlaymentName );
        this.setDateString( KEY_PROVIDER_NAME, providerName );
        this.setDateString( KEY_PROVIDER_CIF_NIF, providerCifNif );
        this.setDateString( KEY_PROVIDER_TELEPHONE, providerTelephone );
    }

    protected void setProviderName(String providerName) {
        this.setDateString( KEY_PROVIDER_NAME, providerName );
    }

    protected void setProviderCifNif(String providerCifNif) {
        this.setDateString( KEY_PROVIDER_CIF_NIF, providerCifNif );
    }

    protected void setProviderTelephone(String providerTelephone) {
        this.setDateString( KEY_PROVIDER_TELEPHONE, providerTelephone );
    }

    protected String getProviderName() {
        return this.getDateString( KEY_PROVIDER_NAME );
    }

    protected String getProviderCifNif() {
        return this.getDateString( KEY_PROVIDER_CIF_NIF );
    }

    protected String getProviderTelephone() {
        return this.getDateString( KEY_PROVIDER_TELEPHONE );
    }

    protected void removeProvider(){
        this.removeDate( KEY_PROVIDER_NAME );
        this.removeDate( KEY_PROVIDER_CIF_NIF );
        this.removeDate( KEY_PROVIDER_TELEPHONE );
    }

    protected void removeProviderName() {
        this.removeDate( KEY_PROVIDER_NAME );
    }

    protected void removeProviderCifNif() {
        this.removeDate( KEY_PROVIDER_CIF_NIF );
    }

    protected void removeProviderTelephone() {
        this.removeDate( KEY_PROVIDER_TELEPHONE );
    }
}
