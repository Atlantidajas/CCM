package com.jorge.app.ccm.ui.user;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {

    private String idUser;
    private String name;
    private Uri photoUri;
    private String email;
    private String telephone;

    public User(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        this.name = currentUser.getDisplayName();
        this.idUser = currentUser.getUid();
        this.photoUri = currentUser.getPhotoUrl();
        this.email = currentUser.getEmail();
        this.telephone = currentUser.getPhoneNumber();
    }


    public String getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }
}
