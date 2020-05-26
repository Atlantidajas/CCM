package com.jorge.app.ccm.models;

import android.net.Uri;

public interface iUser {

    public void setUserEmail(String email);
    public void setUserTelephone(String telephone);
    public String getIdUser();
    public String getUserName();
    public String getUserPhotoUriString();
    public String getUserEmail();
    public String getUserTelephone();
    public Uri getUserPhotoUri();
}
