package com.jorge.app.ccm.models;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

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

    public User(DataSnapshot dataSnapshotUser){
        this.name = String.valueOf( dataSnapshotUser.child("name").getValue() );
        this.idUser = String.valueOf( dataSnapshotUser.child( "idUser" ).getValue() );
        this.photoUriString = String.valueOf( dataSnapshotUser.child( "photoUriString" ).getValue() );
        this.photoUri = Uri.parse( String.valueOf( dataSnapshotUser.child( "photoUriString" ).getValue() ) );
        this.email = String.valueOf( dataSnapshotUser.child("email").getValue() );
        this.telephone = String.valueOf( dataSnapshotUser.child("telephone").getValue() );
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

    public String getPhotoUriString() {
        return photoUriString;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Uri PhotoUri() {
        return photoUri;
    }
}
