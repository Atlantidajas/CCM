package com.jorge.app.ccm.models;

import android.content.Context;

import com.jorge.app.ccm.utils.DatesTemp;

import java.io.Serializable;

public class TypeExpense implements Serializable {

    private int logo;
    private String typeName;

    public TypeExpense(){}

    public TypeExpense(int logo, String typeName) {
        this.logo = logo;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "TypeExpense{" +
                "logo=" + logo +
                ", typeName='" + typeName + '\'' +
                '}';
    }

}
