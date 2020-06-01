package com.jorge.app.ccm.utils;

import android.content.res.Resources;
import com.jorge.app.ccm.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @Author Jorge H
 */

public class BrandsUtil {

    private Resources resources;
    private String brands[];
    private String nameResource;
    private String packegeNameResource;
    private String typeNameResource;
    private int idResource;

    public BrandsUtil( Resources resources ) {
        this.resources = resources;
        this.brands = this.resources.getStringArray( R.array.manufactures );
        this.packegeNameResource = this.resources.getResourcePackageName( R.mipmap.ic_launcher_logo_brand_alfa_romeo );
        this.typeNameResource = this.resources.getResourceTypeName( R.mipmap.ic_launcher_logo_brand_alfa_romeo );

    }

    public String[] getBrands() {
        return brands;
    }

    public String getNameResource( int idResource ) {
        return nameResource = this.resources.getResourceName( idResource );
    }

    public String getPackegeNameResource( ) {
        return packegeNameResource;
    }

    public String getTypeNameResource() {
        return typeNameResource;
    }

    public int getIdResource( String nameResource ) {
        return idResource = this.resources.getIdentifier( "ic_launcher_logo_brand_" + nameResource.toLowerCase(),
                typeNameResource, packegeNameResource);
    }

    public int getIdResourceTypeExpense( String nameResource ) {
        return idResource = this.resources.getIdentifier( nameResource,
                typeNameResource, packegeNameResource);
    }

    public ArrayList<String> namesLogosMipMap(){

        Class<R.mipmap> resourceMimap = R.mipmap.class;

        Field[] mimaps = resourceMimap.getFields();
        ArrayList<String> namesLogos = new ArrayList<>(  );
        for ( int i = 0; i < mimaps.length; i++ ){
            namesLogos.add( mimaps[i].getName() );
        }
        return namesLogos;
    }

    public ArrayList<Integer> idsLogosMipMap(){

        ArrayList<Integer>idsLogosMipMap = new ArrayList<>(  );

        for ( int i = 0; i < namesLogosMipMap().size(); i++ ){
            idsLogosMipMap.add( this.getIdResource( this.namesLogosMipMap().get( i ) ) );
        }
        return idsLogosMipMap;
    }


}
