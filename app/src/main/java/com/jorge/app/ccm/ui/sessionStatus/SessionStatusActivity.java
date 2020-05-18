package com.jorge.app.ccm.ui.sessionStatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.ui.sessionHistoric.SessionHistoricActivity;


import java.util.ArrayList;
import java.util.Iterator;

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
        buttonCloseSesionCurrent = findViewById( R.id.button_close_session_session_driving_crurrent );


        controllerDBSessionsCurrents = new ControllerDBSessionsCurrents( getApplicationContext() );
        controllerDBStatus = new ControllerDBStatus( getApplication() );
        controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext() );
        user = new User();

        intentCloseSesion  = new Intent( SessionStatusActivity.this, SessionHistoricActivity.class );

        final Session sessionIntent = (Session) getIntent().getExtras().getSerializable( SESSION_HISTORIC );//<- El Inten
        final ArrayList<SessionDriving>listSessionDriving = new ArrayList<>(  );

        // Cargo array adapte
        this.controllerDBSessionsHistoric.getDatabaseReference().addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {

                    Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();

                    do{
                        SessionDriving sessionDriving = new SessionDriving( dataSnapshots.next() );

                        if( sessionDriving.getUser().getIdUser().equals( user.getIdUser() ) ) {

                            //Listado de todas las sessiones históricas de la db
                            listSessionDriving.add( sessionDriving );

                        }


                    }while (dataSnapshots.hasNext());

                    //Recorro todas las sesiones históricas de la db
                    for( int i = 0; i < listSessionDriving.size(); i++ ){

                        //Solo me quedo con las sesiones del usuario y con la que tenga el mismo registro de tiempo que la recibida por el intent
                        if( listSessionDriving.get( i ).getUser().getIdUser().equals( user.getIdUser() )  &&
                                ( listSessionDriving.get( i ).getSession().getHours().equals( sessionIntent.getHours() ) )  ){

                            sessionDriving = listSessionDriving.get( i );

                            /*Compruebo el verdadero estado de la sesión, aunque el registro recibido por sessionIntent desde la actividad correspondiente,
                            examino la lista para mostrar estado real de la sesión y si está una posición: (Par ó impar siempre que esta no sea la última posición)
                             */
                            boolean REAL_STATUS_SESSION = realEstateSessionHistoric( i, listSessionDriving.size() );

                            Log.i( TAG, sessionDriving.getUser().getIdUser() );

                            //Cargo todos los campos
                            loadFieldUserSession( sessionDriving );
                            loadFieldStatuSession( REAL_STATUS_SESSION );
                            loadFieldDateSession( sessionDriving );
                            loadFieldHoursSession( sessionDriving );
                            loadFieldBrandVehicle( sessionDriving );
                            loadFieldModelVehicle( sessionDriving );
                            loadFieldRegistrationNumberVehicle();
                            loadButtonCloseSession( sessionDriving, REAL_STATUS_SESSION );

                        }
                    }
                }
                else {
                    //En caso no tener ningún registro, informo al usuario
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
        Glide.with( getApplicationContext() ).load( sessionDriving.getUser().getPhotoUriString() ).into( imageViewUserDate );
        textViewUserDate.setText( userSession + " " + sessionDriving.getUser().getName() );
    }

    public void loadFieldStatuSession( boolean realStatusSesion ){
        Resources resources = getResources();
        String typeSessionStart = resources.getString( R.string.textViewTypeSessionStart );
        String typeSessionEnd = resources.getString( R.string.textViewTypeSessionEnd );

        if ( realStatusSesion ){
            imageViewTypeSession.setImageResource( R.mipmap.ic_launcher_open_lock );
            textViewTypeSession.setTextColor( Color.BLUE );
            textViewTypeSession.setTypeface( Typeface.defaultFromStyle( Typeface.BOLD ) );
            textViewTypeSession.setText( typeSessionStart );
        }
        else{
            imageViewTypeSession.setImageResource( R.mipmap.ic_launcher_close_lock );
            textViewTypeSession.setText( typeSessionEnd );
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
        textViewBrand.setText( brandVehicleSession +  " " + sessionDriving.getVehicle().getBrand() );
        imageViewBrand.setImageResource( sessionDriving.getVehicle().getLogoVehicle() );
    }

    public void loadFieldModelVehicle( SessionDriving sessionDriving){
        Resources resources = getResources();
        String modelVehicleSession = resources.getString( R.string.textViewModel );
        textViewModel.setText( modelVehicleSession + " " + sessionDriving.getVehicle().getModel() );
    }

    public void loadFieldRegistrationNumberVehicle(){
        Resources resources = getResources();
        String registrationNumberVehicleSession = resources.getString( R.string.textViewRegistrationNumber );
        textViewRegistrationNumber.setText( registrationNumberVehicleSession + " " + sessionDriving.getVehicle().getRegistrationNumber() );
    }

    public void loadButtonCloseSession(final SessionDriving sessionDriving, final boolean REAL_STATUS_SESSION ){

        buttonCloseSesionCurrent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Una sesión que ya está cerrada no se puede volver a cerrar.
                if (REAL_STATUS_SESSION == false) {
                    Toast.makeText( getApplicationContext(), "No puede cerrar una sesión que ya lo está", Toast.LENGTH_SHORT ).show();
                }
                else {

                    WindowDialogFragment windowCloseSesionVehicle = new WindowDialogFragment( "Desea cerrar sesion" );

                    windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                        @Override
                        public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                            Session sessionEnd = new Session( "End" );

                            Vehicle vehicleSelect = sessionDriving.getVehicle();

                            final SessionDriving sessionDriving = new SessionDriving( sessionEnd, user, vehicleSelect );

                            Log.i( TAG, "SesionDriving idUser(Valor): --> " + sessionDriving.getUser().getIdUser() );
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

        } );
    }


    // Devuelve true si el resultado es par, si es impar false
    public boolean realEstateSessionHistoric( int index, int listSize ){

        index++;//<-- Incremento en 1 ya que enpieza en 0 por ser un indice
        if ( ( (index % 2) == 0 ) && ( listSize != index ) ) {
            return false; //cerrar -> par
        }
        if ( ( (index % 2) != 0) && ( listSize != index ) ) {
            return false; //Abrir -> impar
        }
        if ( ( (index % 2) != 0) && ( listSize == index ) ) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
