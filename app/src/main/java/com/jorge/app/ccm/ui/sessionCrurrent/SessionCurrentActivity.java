package com.jorge.app.ccm.ui.sessionCrurrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSessionsCurrents;
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.models.Session;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.ui.vehicleStatus.VehiclesStatusListActivity;

public class SessionCurrentActivity extends AppCompatActivity{

    private final String TAG = "SessionDrivingActivity";
    private ControllerDBSessionsHistoric controllerDBSessionsHistoric;
    private ImageView imageViewTypeSession;
    private TextView textViewTypeSession;
    private ImageView imageViewUserDate;
    private TextView textViewUserDate;
    private TextView textViewDate;
    private TextView textViewHours;
    private ImageView imageViewBrand;
    private TextView textViewBrand;
    private TextView textViewModel;
    private TextView textViewRegistrationNumber;
    private Button buttonCloseSesionCurrent;
    private Button buttonReturnSessionCrurrent;

    private Intent intentCloseSesion;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_session_current );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//<-- añado flecha retroseso
        controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext(), TAG );

        imageViewTypeSession = findViewById( R.id.imageViewTypeSession );
        textViewTypeSession = findViewById( R.id.textViewTypeSession );
        imageViewUserDate = findViewById( R.id.imageViewUserDate );
        textViewUserDate = findViewById( R.id.textViewUserDate );
        textViewDate = findViewById(R.id.textViewDate);
        textViewHours = findViewById(R.id.textViewHours);
        imageViewBrand = findViewById( R.id.imageViewBrand );
        textViewBrand = findViewById(R.id.textViewBrand);
        textViewModel = findViewById(R.id.textViewModel);
        textViewRegistrationNumber = findViewById(R.id.textViewRegistrationNumber);
        buttonReturnSessionCrurrent = findViewById( R.id.button_return_session_session_driving_crurrent );
        buttonCloseSesionCurrent = findViewById( R.id.button_close_session_session_driving_crurrent );

        user = new User( true );

        intentCloseSesion  = new Intent( SessionCurrentActivity.this, VehiclesStatusListActivity.class );

        // Cargo array adapte
        this.controllerDBSessionsHistoric.getDatabaseReferenceSessionsCurrents().child( user.getIdUser() ).addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    SessionDriving sessionDriving = new SessionDriving( dataSnapshot );

                    loadFieldUserSession( sessionDriving );
                    loadFieldStatuSession( sessionDriving );
                    loadFieldDateSession( sessionDriving );
                    loadFieldHoursSession( sessionDriving );
                    loadFieldBrandVehicle( sessionDriving );
                    loadFieldModelVehicle( sessionDriving );
                    loadFieldRegistrationNumberVehicle( sessionDriving );
                    loadButtonCloseSession( sessionDriving );

                }
                else {
                    Toast.makeText( getApplicationContext(), R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == android.R.id.home ){
            finish();
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }

    public void loadFieldUserSession( SessionDriving sessionDriving ){
        Resources resources = getResources();
        String userSession = resources.getString( R.string.fieldUserNameUser );
        Glide.with( getApplicationContext() ).load( sessionDriving.getUserPhotoUriString() ).into( imageViewUserDate );
        textViewUserDate.setText( userSession + " " + sessionDriving.getUserName() );
    }

    public void loadFieldStatuSession( SessionDriving sessionDriving ){

        Resources resources = getResources();
        String typeSessionStart = resources.getString( R.string.fieldSessionTypeSessionStart );
        String typeSessionEnd = resources.getString( R.string.fieldSessionTypeSessionEnd );

        switch ( sessionDriving.getSessionTypeSesion() ){
            case "Start":
                imageViewTypeSession.setImageResource( R.mipmap.ic_launcher_open_lock );
                textViewTypeSession.setTextColor( Color.BLUE );
                textViewTypeSession.setText( typeSessionStart );
                break;
            case "End":
                imageViewTypeSession.setImageResource( R.mipmap.ic_launcher_close_lock );
                textViewTypeSession.setText( typeSessionEnd );
                break;
            default:
                imageViewTypeSession.setImageResource( R.mipmap.ic_launcher_open_lock );
                textViewTypeSession.setText( sessionDriving.getSessionTypeSesion() );
                break;
        }
    }

    public void loadFieldDateSession( SessionDriving sessionDriving){
        Resources resources = getResources();
        String dateSession = resources.getString( R.string.fieldSessionDate );
        textViewDate.setText( dateSession + " " + sessionDriving.getSessionDate() );
    }

    public void loadFieldHoursSession( SessionDriving sessionDriving){
        Resources resources = getResources();
        String hoursSession = resources.getString( R.string.fieldSessionHours );
        textViewHours.setText( hoursSession  + " " + sessionDriving.getSessionHours() );
    }

    public void loadFieldBrandVehicle( SessionDriving sessionDriving ){
        Resources resources = getResources();
        String brandVehicleSession = resources.getString( R.string.fieldVehicleBrand );
        textViewBrand.setText( brandVehicleSession +  " " + sessionDriving.getVehicleBrand() );
        imageViewBrand.setImageResource( sessionDriving.getVehiclelogo() );
    }

    public void loadFieldModelVehicle( SessionDriving sessionDriving){
        Resources resources = getResources();
        String modelVehicleSession = resources.getString( R.string.fieldVehicleModel );
        textViewModel.setText( modelVehicleSession + " " + sessionDriving.getVehicleModel() );
    }

    public void loadFieldRegistrationNumberVehicle( SessionDriving sessionDriving){
        Resources resources = getResources();
        String registrationNumberVehicleSession = resources.getString( R.string.fieldVehicleRegistration );
        textViewRegistrationNumber.setText( registrationNumberVehicleSession + " " + sessionDriving.getVehicleRegistrationNumber() );
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    public void loadButtonCloseSession(final SessionDriving sessionDriving ){

        this.buttonCloseSesionCurrent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Una sesión que ya está cerrada no se puede volver a cerrar.
                if (sessionDriving.getSessionTypeSesion().equals( "End" )) {
                    Toast.makeText( getApplicationContext(), R.string.windowAttetionSessionClosenoClose, Toast.LENGTH_SHORT ).show();
                } else {

                    WindowDialogFragment windowCloseSesionVehicle = new WindowDialogFragment( R.string.windowAttentionSessionClose );

                    windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                        @Override
                        public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                            Session sessionEnd = new Session( "End" );

                            Vehicle vehicleSelect = sessionDriving.getVehicle();

                            final SessionDriving sessionDriving = new SessionDriving( sessionEnd, user, vehicleSelect );

                            Log.i( TAG, "SesionDriving seleccionado onclickItem (Valor): --> " + sessionDriving.getIdUser() );
                            Log.i( TAG, "id usuario en uso (Valor): --> " + user.getIdUser() );


                            //Controlo que sea el usuario en uso el que cierre su sesion abierta, no la de otro.
                            //Condicion 1
                            if (sessionDriving.getIdUser().equals( user.getIdUser() )) {
                                controllerDBSessionsHistoric.registrySessionHistoric( sessionDriving );
                                Toast.makeText( getApplicationContext(), "Cerrando sesión", Toast.LENGTH_SHORT ).show();
                                startActivity( intentCloseSesion );
                            } else {
                                Toast.makeText( getApplicationContext(), R.string.toast_message_logout_error, Toast.LENGTH_SHORT ).show();
                            }
                        }

                        @Override
                        public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                            return;
                        }
                    } );

                    windowCloseSesionVehicle.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );
                }
            }
        });

        buttonReturnSessionCrurrent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( intentCloseSesion );
            }
        } );

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
