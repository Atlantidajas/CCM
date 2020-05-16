package com.jorge.app.ccm.ui.sessionCrurrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.jorge.app.ccm.ui.vehicleStatus.VehiclesListActivity;

public class SessionCurrentActivity extends AppCompatActivity{

    private final String TAG = "SessionDrivingActivity";
    private ControllerDBSessionsCurrents controllerDBSessionsCurrents;
    private ControllerDBStatus controllerDBStatus;
    private ControllerDBSessionsHistoric controllerDBSessionsHistoric;
    private SessionDriving sessionDriving;

    private TextView textViewDate;
    private TextView textViewHours;
    private TextView textViewBrand;
    private TextView textViewModel;
    private TextView textViewRegistrationNumber;
    private Button buttonCloseSesionCurrent;

    private Intent intentCloseSesion;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sesion_current );

        textViewDate = findViewById(R.id.textViewDate);
        textViewHours = findViewById(R.id.textViewHours);
        textViewBrand = findViewById(R.id.textViewBrand);
        textViewModel = findViewById(R.id.textViewModel);
        textViewRegistrationNumber = findViewById(R.id.textViewRegistrationNumber);
        buttonCloseSesionCurrent = findViewById( R.id.buttonCloseSesionCurrent );


        controllerDBSessionsCurrents = new ControllerDBSessionsCurrents( getApplicationContext() );
        controllerDBStatus = new ControllerDBStatus( getApplication() );
        controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext() );
        user = new User();

        intentCloseSesion  = new Intent( SessionCurrentActivity.this, VehiclesListActivity.class );

        // Cargo array adapte
        this.controllerDBSessionsCurrents.getDatabaseReference().child( user.getIdUser() ).addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    sessionDriving = new SessionDriving( dataSnapshot );

                    textViewDate.setText( sessionDriving.getSession().getDate() );
                    textViewHours.setText( sessionDriving.getSession().getHours() );
                    textViewBrand.setText( sessionDriving.getVehicle().getBrand() );
                    textViewModel.setText( sessionDriving.getVehicle().getModel() );
                    textViewRegistrationNumber.setText( sessionDriving.getVehicle().getRegistrationNumber() );

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
    public void onStart() {
        super.onStart();
        onclick();
    }

    public void onclick(){
        // Creo el listener para cuando se hace click en un item de la lista.
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

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
