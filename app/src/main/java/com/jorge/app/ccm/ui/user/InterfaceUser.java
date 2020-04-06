package com.jorge.app.ccm.ui.user;

import android.net.Uri;

public interface InterfaceUser {

    public void setIdUser(String idUser);
    public void setName(String name);
    public void setPhotoUri(Uri photoUri);
    public void setEmail(String email);
    public void setTelephone(String telephone);
    public String getIdUser();
    public String getName();
    public Uri getPhotoUri();
    public String getEmail();
    public String getTelephone();
}
