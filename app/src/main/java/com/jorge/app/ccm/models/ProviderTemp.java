package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;

public class ProviderTemp extends MethodOfPlaymentTemp implements iProvider {

    private final String PRIMARY_KEY;
    private final String KEY_PROVIDER_NAME;
    private final String KEY_PROVIDER_CIF_NIF;
    private final String KEY_PROVIDER_TELEPHONE;

    public ProviderTemp(Context context, String TAG) {
        super( context, TAG );
        PRIMARY_KEY = getFILE_NAME() + "provider";
        this.KEY_PROVIDER_NAME = PRIMARY_KEY + "name";
        this.KEY_PROVIDER_CIF_NIF = PRIMARY_KEY + "cifNif";
        this.KEY_PROVIDER_TELEPHONE = PRIMARY_KEY + "telephone";
    }

    public void setProvider( Provider provider ) {
        this.setDateString( KEY_PROVIDER_NAME, provider.getProviderName() );
        this.setDateString( KEY_PROVIDER_CIF_NIF, provider.getProviderCifNif() );
        this.setDateString( KEY_PROVIDER_TELEPHONE, provider.getProviderTelephone() );
    }

    public void setProviderName(String providerName) {
        this.setDateString( KEY_PROVIDER_NAME, providerName );
    }

    public void setProviderCifNif(String providerCifNif) {
        this.setDateString( KEY_PROVIDER_CIF_NIF, providerCifNif );
    }

    public void setProviderTelephone(String providerTelephone) {
        this.setDateString( KEY_PROVIDER_TELEPHONE, providerTelephone );
    }

    public String getProviderName() {
        return this.getDateString( KEY_PROVIDER_NAME );
    }

    public String getProviderCifNif() {
        return this.getDateString( KEY_PROVIDER_CIF_NIF );
    }

    public String getProviderTelephone() {
        return this.getDateString( KEY_PROVIDER_TELEPHONE );
    }

    public void removeProvider(){
        this.removeDate( KEY_PROVIDER_NAME );
        this.removeDate( KEY_PROVIDER_CIF_NIF );
        this.removeDate( KEY_PROVIDER_TELEPHONE );
    }
}
