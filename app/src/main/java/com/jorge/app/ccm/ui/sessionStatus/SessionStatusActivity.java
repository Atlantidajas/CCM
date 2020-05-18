package com.jorge.app.ccm.ui.sessionStatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Resources;
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
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.models.Session;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.ui.sessionHistoric.SessionHistoricActivity;


import static com.jorge.app.ccm.ui.sessionHistoric.SessionHistoricActivity.SESSION_HISTORIC;

public class SessionStatusActivity extends AppCompatActivity {

    private final String TAG = "SessionStatusActivity";
    private ControllerDBSessionsCurrents controllerDBSessionsCurrents;
    private ControllerDBStatus controllerDBStatus;
    private ControllerDBSessionsHistoric controllerDBSessionsHistoric;
    private SessionDriving sessionDriving;


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
        setContentView( R.layout.activity_session_status );


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

        intentCloseSesion  = new Intent( SessionStatusActivity.this, SessionHistoricActivity.class );

        Session sessionIntent = (Session) getIntent().getExtras().getSerializable( SESSION_HISTORIC );//<- El Inten
        System.out.println( sessionIntent.getHours() + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" );

        // Cargo array adapte
        this.controllerDBSessionsHistoric.getDatabaseReference().child( "session" ).addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {

                    Session session = new Session( dataSnapshot );
                    sessionDriving = new SessionDriving( dataSnapshot );

                    Resources resources = getResources();
                    String typeSessionStart = resources.getString( R.string.textViewTypeSessionStart );
                    String typeSessionEnd = resources.getString( R.string.textViewTypeSessionEnd );
                    String userSession = resources.getString( R.string.textViewUserDate );
                    String dateSession = resources.getString( R.string.textViewDate );
                    String hoursSession = resources.getString( R.string.textViewHours );
                    String brandVehicleSession = resources.getString( R.string.textViewBrand );
                    String modelVehicleSession = resources.getString( R.string.textViewModel );
                    String registrationNumberVehicleSession = resources.getString( R.string.textViewRegistrationNumber );

                    Glide.with( getApplicationContext() ).load( sessionDriving.getUser().getPhotoUriString() ).into( imageViewUserDate );
                    switch ( sessionDriving.getSession().getTypeSesion() ){
                        case "Start":
                            imageViewTypeSession.setImageResource( R.mipmap.ic_launcher_open_lock );
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
                    textViewUserDate.setText( userSession + " " + sessionDriving.getUser().getName() );
                    textViewDate.setText( dateSession + " " + sessionDriving.getSession().getDate() );
                    textViewHours.setText( hoursSession  + " " + sessionDriving.getSession().getHours() );
                    textViewBrand.setText( brandVehicleSession +  " " + sessionDriving.getVehicle().getBrand() );
                    imageViewBrand.setImageResource( sessionDriving.getVehicle().getLogoVehicle() );
                    textViewModel.setText( modelVehicleSession + " " + sessionDriving.getVehicle().getModel() );
                    textViewRegistrationNumber.setText( registrationNumberVehicleSession + " " + sessionDriving.getVehicle().getRegistrationNumber() );

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
