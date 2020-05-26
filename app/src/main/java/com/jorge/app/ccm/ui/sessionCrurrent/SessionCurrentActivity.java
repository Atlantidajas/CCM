package com.jorge.app.ccm.ui.sessionCrurrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
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
    private ControllerDBSessionsCurrents controllerDBSessionsCurrents;
    private ControllerDBStatus controllerDBStatus;
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


        controllerDBSessionsCurrents = new ControllerDBSessionsCurrents( getApplicationContext() );
        controllerDBStatus = new ControllerDBStatus( getApplication() );
        controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext() );
        user = new User();

        intentCloseSesion  = new Intent( SessionCurrentActivity.this, VehiclesStatusListActivity.class );

        // Cargo array adapte
        this.controllerDBSessionsCurrents.getDatabaseReference().child( user.getIdUser() ).addValueEventListener( new ValueEventListener() {

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

    public void loadFieldUserSession( SessionDriving sessionDriving ){
        Resources resources = getResources();
        String userSession = resources.getString( R.string.textViewUserDate );
        Glide.with( getApplicationContext() ).load( sessionDriving.getUser().getUserPhotoUriString() ).into( imageViewUserDate );
        textViewUserDate.setText( userSession + " " + sessionDriving.getUser().getUserName() );
    }

    public void loadFieldStatuSession( SessionDriving sessionDriving ){

        Resources resources = getResources();
        String typeSessionStart = resources.getString( R.string.textViewTypeSessionStart );
        String typeSessionEnd = resources.getString( R.string.textViewTypeSessionEnd );

        switch ( sessionDriving.getSession().getTypeSesion() ){
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
                textViewTypeSession.setText( sessionDriving.getSession().getTypeSesion() );
                break;
        }
    }

    public void loadFieldDateSession( SessionDriving sessionDriving){
        Resources resources = getResources();
        String dateSession = resources.getString( R.string.textViewDate );
        textViewDate.setText( dateSession + " " + sessionDriving.getSession().getDate() );
    }

    public void loadFieldHoursSession( SessionDriving sessionDriving){
        Resources resources = getResources();
        String hoursSession = resources.getString( R.string.textViewHours );
        textViewHours.setText( hoursSession  + " " + sessionDriving.getSession().getHours() );
    }

    public void loadFieldBrandVehicle( SessionDriving sessionDriving ){
        Resources resources = getResources();
        String brandVehicleSession = resources.getString( R.string.textViewBrand );
        textViewBrand.setText( brandVehicleSession +  " " + sessionDriving.getVehicle().getVehicleBrand() );
        imageViewBrand.setImageResource( sessionDriving.getVehicle().getVehiclelogo() );
    }

    public void loadFieldModelVehicle( SessionDriving sessionDriving){
        Resources resources = getResources();
        String modelVehicleSession = resources.getString( R.string.textViewModel );
        textViewModel.setText( modelVehicleSession + " " + sessionDriving.getVehicle().getVehicleModel() );
    }

    public void loadFieldRegistrationNumberVehicle( SessionDriving sessionDriving){
        Resources resources = getResources();
        String registrationNumberVehicleSession = resources.getString( R.string.textViewRegistrationNumber );
        textViewRegistrationNumber.setText( registrationNumberVehicleSession + " " + sessionDriving.getVehicle().getVehicleRegistrationNumber() );
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
                if (sessionDriving.getSession().getTypeSesion().equals( "End" )) {
                    Toast.makeText( getApplicationContext(), "No puede cerrar una sesión que ya lo está", Toast.LENGTH_SHORT ).show();
                } else {

                    WindowDialogFragment windowCloseSesionVehicle = new WindowDialogFragment( "Desea cerrar sesion" );

                    windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                        @Override
                        public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                            Session sessionEnd = new Session( "End" );

                            Vehicle vehicleSelect = sessionDriving.getVehicle();

                            final SessionDriving sessionDriving = new SessionDriving( sessionEnd, user, vehicleSelect );

                            Log.i( TAG, "SesionDriving seleccionado onclickItem (Valor): --> " + sessionDriving.getUser().getIdUser() );
                            Log.i( TAG, "id usuario en uso (Valor): --> " + user.getIdUser() );


                            //Controlo que sea el usuario en uso el que cierre su sesion abierta, no la de otro.
                            //Condicion 1
                            if (sessionDriving.getUser().getIdUser().equals( user.getIdUser() )) {
                                controllerDBStatus.updateValue( sessionDriving.getVehicle(), null );
                                controllerDBSessionsCurrents.updateValue( sessionDriving, null );
                                controllerDBSessionsHistoric.setValue( sessionDriving );
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
