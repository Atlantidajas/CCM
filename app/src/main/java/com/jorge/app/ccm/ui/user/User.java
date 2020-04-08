package com.jorge.app.ccm.ui.user;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {

    private String idUser;
    private String name;
    private Uri photoUri;
    private String photoUriString;
    private String email;
    private String telephone;

    public User(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        this.name = currentUser.getDisplayName();
        this.idUser = currentUser.getUid();
        this.photoUriString = String.valueOf( currentUser.getPhotoUrl() );
        this.photoUri = currentUser.getPhotoUrl();
        this.email = currentUser.getEmail();
        this.telephone = currentUser.getPhoneNumber();
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }

    public void setPhotoUriString(Uri photoUri) {
        String photoString = String.valueOf( photoUri );
        this.photoUri = photoUri;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public Uri photoUri() {
        return photoUri;
    }

    public String getPhotoUriString() {
        return photoUriString;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }
}
