package com.jorge.app.ccm.utils;

import android.content.res.Resources;
import com.jorge.app.ccm.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *@Author Jorge HL
 * Permite trabajar con los logotipos de marcas de vehículos almacenados como recursos del sistema
 */

public class BrandsUtil {

    private Resources resources;
    private String brands[];
    private String nameResource;
    private String packegeNameResource;
    private String typeNameResource;
    private int idResource;


    /**
     *
     * @param resources recurso del sistema
     */
    public BrandsUtil( Resources resources ) {
        this.resources = resources;
        this.brands = this.resources.getStringArray( R.array.manufactures );
        this.packegeNameResource = this.resources.getResourcePackageName( R.mipmap.ic_launcher_logo_brand_alfa_romeo );
        this.typeNameResource = this.resources.getResourceTypeName( R.mipmap.ic_launcher_logo_brand_alfa_romeo );

    }


    /**
     *
     * @return array contenido en los recursos con todas las marcas de vehículos
     */
    public String[] getBrands() {
        return brands;
    }

    /**
     *
     * @param idResource identificador del recurso almacenado en el sistema
     * @return nombre del recurso almacenado.
     */
    public String getNameResource( int idResource ) {
        return nameResource = this.resources.getResourceName( idResource );
    }

    /**
     *
     * @return devuelve el nombre de paquete que contine el recurso
     */
    public String getPackegeNameResource( ) {
        return packegeNameResource;
    }

    /**
     *
     * @return devuelve el nombre del tipo de recurso
     */
    public String getTypeNameResource() {
        return typeNameResource;
    }

    /**
     *
     * @param nameResource nombre completo en minúsculas del recurso sobre el que se quiere obtener el id
     * @return devuelve el identificador de recurso buscado
     */

    public int getIdResource( String nameResource ) {
        return idResource = this.resources.getIdentifier( "ic_launcher_logo_brand_" + nameResource.toLowerCase(),
                typeNameResource, packegeNameResource);
    }


    public int getIdResourceTypeExpense( String nameResource ) {
        return idResource = this.resources.getIdentifier( nameResource,
                typeNameResource, packegeNameResource);
    }

    /**
     *
     * @return  Devuelve un arrayList con todas las marcas de vehículos almacenados
     */

    public ArrayList<String> namesLogosMipMap(){

        Class<R.mipmap> resourceMimap = R.mipmap.class;

        Field[] mimaps = resourceMimap.getFields();
        ArrayList<String> namesLogos = new ArrayList<>(  );
        for ( int i = 0; i < mimaps.length; i++ ){
            namesLogos.add( mimaps[i].getName() );
        }
        return namesLogos;
    }


    /**
     *
     * @return devuelve un arrayList cin todos los identificadores de los recursos buscados
     */
    public ArrayList<Integer> idsLogosMipMap(){

        ArrayList<Integer>idsLogosMipMap = new ArrayList<>(  );

        for ( int i = 0; i < namesLogosMipMap().size(); i++ ){
            idsLogosMipMap.add( this.getIdResource( this.namesLogosMipMap().get( i ) ) );
        }
        return idsLogosMipMap;
    }


}
