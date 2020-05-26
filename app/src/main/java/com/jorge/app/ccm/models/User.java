package com.jorge.app.ccm.models;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class User implements Serializable {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String idUser;
    private String userName;
    private Uri UserPhotoUri;
    private String UserPhotoUriString;
    private String UserEmail;
    private String UserTelephone;

    public User(){
        this.mAuth = FirebaseAuth.getInstance();
        this.currentUser = mAuth.getCurrentUser();
        this.userName = currentUser.getDisplayName();
        this.idUser = currentUser.getUid();
        this.UserPhotoUriString = String.valueOf( currentUser.getPhotoUrl() );
        this.UserPhotoUri = currentUser.getPhotoUrl();
        this.UserEmail = currentUser.getEmail();
        this.UserTelephone = currentUser.getPhoneNumber();
    }

    public User(DataSnapshot dataSnapshotUser){
        this.userName = String.valueOf( dataSnapshotUser.child("name").getValue() );
        this.idUser = String.valueOf( dataSnapshotUser.child( "idUser" ).getValue() );
        this.UserPhotoUriString = String.valueOf( dataSnapshotUser.child( "photoUriString" ).getValue() );
        this.UserPhotoUri = Uri.parse( String.valueOf( dataSnapshotUser.child( "photoUriString" ).getValue() ) );
        this.UserEmail = String.valueOf( dataSnapshotUser.child("email").getValue() );
        this.UserTelephone = String.valueOf( dataSnapshotUser.child("telephone").getValue() );
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public void setUserPhotoUri(Uri photoUri) {
        this.UserPhotoUri = photoUri;
    }

    public void setUserPhotoUriString(Uri photoUri) {
        String photoString = String.valueOf( photoUri );
        this.UserPhotoUri = photoUri;
    }

    public void setUserEmail(String email) {
        this.UserEmail = email;
    }

    public void setUserTelephone(String telephone) {
        this.UserTelephone = telephone;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhotoUriString() {
        return UserPhotoUriString;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserTelephone() {
        return UserTelephone;
    }

    @Exclude
    public Uri getUserPhotoUri() {
        return UserPhotoUri;
    }
}
