package com.jorge.app.ccm.models;

import android.net.Uri;

public interface IUser {

    public void setIdUser(String idUser);
    public void setName(String name);
    public void setPhotoUri(Uri photoUri);
    public void setEmail(String email);
    public void setTelephone(String telephone);
    public String getIdUser();
    public String getName();
    public String getPhotoUriString();
    public String getEmail();
    public String getTelephone();
}
