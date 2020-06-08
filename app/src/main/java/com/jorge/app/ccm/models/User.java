package com.jorge.app.ccm.models;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

import java.io.Serializable;


/**
 * Clase que permite trabajar, crear con objetos de tipo User
 * @Author: Jorge.HL
 */
public class User implements Serializable {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String idUser;
    private String userName;
    private Uri UserPhotoUri;
    private String UserPhotoUriString;
    private String UserEmail;
    private String UserTelephone;

    public User(){}

    /**
     *Permite contruir un objeto de tipo User
     * @param
     */

    public User( Boolean on ){

        if( on == true ){
            this.mAuth = FirebaseAuth.getInstance();
            this.currentUser = mAuth.getCurrentUser();
            this.userName = currentUser.getDisplayName();
            this.idUser = currentUser.getUid();
            this.UserPhotoUriString = String.valueOf( currentUser.getPhotoUrl() );
            this.UserPhotoUri = currentUser.getPhotoUrl();
            this.UserEmail = currentUser.getEmail();
            this.UserTelephone = currentUser.getPhoneNumber();
        }

    }

    /**
     * Permite contruir un objeto de tipo User a partir de la obtención de datos de la lectura de la DB
     * @param dataSnapshotUser objeto de tipo DataSnapshotUser (Firebase)
     */

    public User(DataSnapshot dataSnapshotUser){
        this.userName = String.valueOf( dataSnapshotUser.child("userName").getValue() );
        this.idUser = String.valueOf( dataSnapshotUser.child( "idUser" ).getValue() );
        this.UserPhotoUriString = String.valueOf( dataSnapshotUser.child( "userPhotoUriString" ).getValue() );
        this.UserPhotoUri = Uri.parse( String.valueOf( dataSnapshotUser.child( "userPhotoUriString" ).getValue() ) );
        this.UserEmail = String.valueOf( dataSnapshotUser.child("userEmail").getValue() );
        this.UserTelephone = String.valueOf( dataSnapshotUser.child("userTelephone").getValue() );
    }

    /**
     * Almacena la propiedad idUser
     * @param idUser String valor del id del usuario
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Almacena la propiedad userName
     * @param name String valor del nombre del usuario
     */
    public void setUserName(String name) {
        this.userName = name;
    }

    /**
     * Almacena la propiedad photoUri
     * @param photoUri Uri valor del photoUri donde se almacena la fotografía de perfíl del usuario
     */
    public void setUserPhotoUri(Uri photoUri) {
        this.UserPhotoUri = photoUri;
    }

    /**
     * Almacena la propiedad photoUri
     * @param photoUri Uri valor del photoUri donde se almacena la fotografía de perfíl del usuario
     */
    public void setUserPhotoUriString(Uri photoUri) {
        String photoString = String.valueOf( photoUri );
        this.UserPhotoUri = photoUri;
    }

    /**
     * Almacena la propiedad email
     * @param email valor del email del usuario
     */
    public void setUserEmail(String email) {
        this.UserEmail = email;
    }

    /**
     * Almacena la propiedad telephone
     * @param telephone valor del telephone del usuario
     */
    public void setUserTelephone(String telephone) {
        this.UserTelephone = telephone;
    }

    /**
     * Devuelve
     * @return String identificador del usuario
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Devuelve
     * @return String userName nombre del usuario
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Devuelve
     * @return String UserPhotoUriString valor de la url donde se almacena fotografía de perfil del usuario
     */
    public String getUserPhotoUriString() {
        return UserPhotoUriString;
    }

    /**
     * Devuelve
     * @return String UserEmail email del usuario
     */
    public String getUserEmail() {
        return UserEmail;
    }

    /**
     * Devuelve
     * @return String UserTelephone teléfono del usuario
     */
    public String getUserTelephone() {
        return UserTelephone;
    }

    /**
     * Devuelve
     * @return Uri UserPhotoUri url donde se almacena la fotografía de perfíl del usuario
     */
    @Exclude
    public Uri getUserPhotoUri() {
        return UserPhotoUri;
    }
}
