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


/**
 * @author Jorge.HL
 */
public class SessionStatusActivity extends AppCompatActivity {

    private final String TAG = "SessionStatusActivity";
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
        buttonCloseSesionCurrent = findViewById( R.id.button_close_session_session_driving_crurrent );

        user = new User( true );

        intentCloseSesion  = new Intent( SessionStatusActivity.this, SessionHistoricActivity.class );

        final Session sessionIntent = (Session) getIntent().getExtras().getSerializable( SESSION_HISTORIC );//<- El Inten
        final ArrayList<SessionDriving>listSessionDriving = new ArrayList<>(  );

        // Cargo array adapte
        this.controllerDBSessionsHistoric.getDatabaseReferenceSessionsHistoric().addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {

                    Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();

                    do{
                        SessionDriving sessionDriving = new SessionDriving( dataSnapshots.next() );

                        if( sessionDriving.getIdUser().equals( user.getIdUser() ) ) {

                            //Listado de todas las sessiones históricas de la db
                            listSessionDriving.add( sessionDriving );

                        }


                    }while (dataSnapshots.hasNext());

                    //Recorro todas las sesiones históricas de la db
                    for( int i = 0; i < listSessionDriving.size(); i++ ){

                        //Solo me quedo con las sesiones del usuario y con la que tenga el mismo registro de tiempo que la recibida por el intent
                        if( listSessionDriving.get( i ).getIdUser().equals( user.getIdUser() )  &&
                                ( listSessionDriving.get( i ).getSessionHours().equals( sessionIntent.getSessionHours() ) )  ){

                            sessionDriving = listSessionDriving.get( i );

                            /*Compruebo el verdadero estado de la sesión, aunque el registro recibido por sessionIntent desde la actividad correspondiente,
                            examino la lista para mostrar estado real de la sesión y si está una posición: (Par ó impar siempre que esta no sea la última posición)
                             */
                            boolean REAL_STATUS_SESSION = realEstateSessionHistoric( i, listSessionDriving.size() );

                            Log.i( TAG, sessionDriving.getIdUser() );

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

    /**
     * Carga campo UserSession
     */
    public void loadFieldUserSession( SessionDriving sessionDriving ){
        Resources resources = getResources();
        String userSession = resources.getString( R.string.fieldUserNameUser );
        Glide.with( getApplicationContext() ).load( sessionDriving.getUserPhotoUriString() ).into( imageViewUserDate );
        textViewUserDate.setText( userSession + " " + sessionDriving.getUserName() );
    }

    /**
     * Carga campo StatuSession
     */
    public void loadFieldStatuSession( boolean realStatusSesion ){
        Resources resources = getResources();
        String typeSessionStart = resources.getString( R.string.fieldSessionTypeSessionStart );
        String typeSessionEnd = resources.getString( R.string.fieldSessionTypeSessionEnd );

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

    /**
     * Carga campo DateSession
     */
    public void loadFieldDateSession( SessionDriving sessionDriving){
        Resources resources = getResources();
        String dateSession = resources.getString( R.string.fieldSessionDate );
        textViewDate.setText( dateSession + " " + sessionDriving.getSessionDate() );
    }

    /**
     * Carga campo HoursSession
     */
    public void loadFieldHoursSession( SessionDriving sessionDriving){
        Resources resources = getResources();
        String hoursSession = resources.getString( R.string.fieldSessionHours );
        textViewHours.setText( hoursSession  + " " + sessionDriving.getSessionHours() );
    }

    /**
     * Carga campo BrandVehicle
     */
    public void loadFieldBrandVehicle( SessionDriving sessionDriving ){
        Resources resources = getResources();
        String brandVehicleSession = resources.getString( R.string.fieldVehicleBrand );
        textViewBrand.setText( brandVehicleSession +  " " + sessionDriving.getVehicleBrand() );
        imageViewBrand.setImageResource( sessionDriving.getVehiclelogo() );
    }

    /**
     * Carga campo ModelVehicle
     */
    public void loadFieldModelVehicle( SessionDriving sessionDriving){
        Resources resources = getResources();
        String modelVehicleSession = resources.getString( R.string.fieldVehicleModel );
        textViewModel.setText( modelVehicleSession + " " + sessionDriving.getVehicleModel() );
    }

    /**
     * Carga campo RegistrationNumberVehicle
     */
    public void loadFieldRegistrationNumberVehicle(){
        Resources resources = getResources();
        String registrationNumberVehicleSession = resources.getString( R.string.fieldVehicleRegistration );
        textViewRegistrationNumber.setText( registrationNumberVehicleSession + " " + sessionDriving.getVehicleRegistrationNumber() );
    }

    /**
     * Carga campo ButtonCloseSession, permite cerrar session de conducción de un vehiculo
     * @param sessionDriving Session actual
     * @param REAL_STATUS_SESSION
     */
    public void loadButtonCloseSession(final SessionDriving sessionDriving, final boolean REAL_STATUS_SESSION ){

        buttonCloseSesionCurrent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Una sesión que ya está cerrada no se puede volver a cerrar.
                if (REAL_STATUS_SESSION == false) {
                    Toast.makeText( getApplicationContext(), R.string.windowAttetionSessionClosenoClose, Toast.LENGTH_SHORT ).show();
                }
                else {

                    WindowDialogFragment windowCloseSesionVehicle = new WindowDialogFragment( R.string.windowAttentionSessionClose );

                    windowCloseSesionVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                        @Override
                        public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                            Session sessionEnd = new Session( "End" );

                            Vehicle vehicleSelect = sessionDriving.getVehicle();

                            final SessionDriving sessionDriving = new SessionDriving( sessionEnd, user, vehicleSelect );

                            Log.i( TAG, "SesionDriving idUser(Valor): --> " + sessionDriving.getIdUser() );
                            Log.i( TAG, "id usuario en uso (Valor): --> " + user.getIdUser() );


                            //Controlo que sea el usuario en uso el que cierre su sesion abierta, no la de otro.
                            //Condicion 1
                            if (sessionDriving.getIdUser().equals( user.getIdUser() )) {

                                controllerDBSessionsHistoric.registrySessionHistoric( sessionDriving );

                                Toast.makeText( getApplicationContext(), R.string.windowCloseSession, Toast.LENGTH_SHORT ).show();
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


    /**
     * Devuelve true si el resultado es par, si es impar false
     * @param index indice que ocupa en el list
     * @param listSize tamaño des list
     * @return
     */
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

}
