package com.jorge.app.ccm.ui.vehicleStatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSessionsCurrents;
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.models.Session;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;
import com.jorge.app.ccm.ui.VehicleCu.UpdateVehicleActivity;
import com.jorge.app.ccm.ui.sessionCrurrent.SessionCurrentActivity;
import com.jorge.app.ccm.models.User;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Jorge.HL
 */
public class VehiclesListActivity extends AppCompatActivity implements Serializable{

    private final String TAG = "VehiclesListActivity";
    public Intent intentSesionDriving;
    public Intent intentForUpdate;
    public Intent intentForRegistryVehicles;
    public static final String VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE = "com.jorge.app.ccm.vehicles.VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE";
    private ControllerDBStatus controllerDBStatus;
    private ControllerDBSessionsCurrents controllerDBSessionsCurrents;
    private ControllerDBSessionsHistoric controllerDBSessionsHistoric;

    private AdapterVehicle arrayAdapterVehicle;
    private TextView textView;
    private ListView listView;
    private ArrayList<Vehicle> vehicles;
    private SessionDriving sessionDrivingCurrent;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_vehicles);

        //Inicialización de controladores
        controllerDBStatus = new ControllerDBStatus( getApplicationContext() );
        controllerDBSessionsCurrents = new ControllerDBSessionsCurrents( getApplicationContext() );
        controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext() );
        user = new User();

        controllerDBSessionsCurrents.getDatabaseReference().child( user.getIdUser() ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if( dataSnapshot.exists() ) {
                    sessionDrivingCurrent = new SessionDriving( dataSnapshot );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        //Inizializao Adapter para mostrar lista de vehículos
        this.arrayAdapterVehicle = new AdapterVehicle( getApplication(), textView, listView);
        vehicles = arrayAdapterVehicle.getListIntemVehicles();

        //Intens
        intentForRegistryVehicles = new Intent ( VehiclesListActivity.this, RegistryVehiclesActivity.class);
        intentForUpdate= new Intent ( VehiclesListActivity.this, UpdateVehicleActivity.class);
        intentSesionDriving = new Intent( VehiclesListActivity.this, SessionCurrentActivity.class );

    }

    @Override
    public void onStart() {
        super.onStart();
        //Si es la primera vez que usa la app, creo una primera sesión de tipo Create (Esta no será mostrada al usuario)
        onSesionDrinvingCreate();
        registerForContextMenu( listView );
        onclickItemList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.vehicles_toolbar, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.resgistreVehicle ) {
            startActivity(intentForRegistryVehicles);
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo ) {

        // Si se ha hecho LongClick sobre la lista.
        if ( v.getId() == R.id.listView_vehicles ) {
            // Obtengo la posición de la lista que se ha pulsado
            int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            // Inflo el menú.
            this.getMenuInflater().inflate(R.menu.menu_contextual_list_view_vehicles, menu);

            MenuItem itemMenu1 = menu.findItem( R.id.menu_contextual_list_view_vehicles_item_edit );
            // Establezco el título que se muestra en el encabezado del menú. + número de matrúcula para avisar al usuario del cambio
            menu.setHeaderTitle( getString( R.string.menu_contextual_list_view_vehicles_title ) + " " +
                    vehicles.get( position ).getRegistrationNumber());

        }
        // Llamo al OnCreateContextMenu del padre por si quiere añadir algún elemento
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Posición lista pulsado
        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        final Vehicle vehicleSelect = vehicles.get( position );

        // Información al usuario sobre menú pulsado.
        switch (item.getItemId()) {

            case R.id.menu_contextual_list_view_vehicles_item_edit:

                //Si se puede editar.
                if ( vehicleSelect.getDriving() == 0 ){
                    intentForUpdate.putExtra(VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE, (Serializable) arrayAdapterVehicle.getItem( position ) );
                    startActivity(intentForUpdate);
                }
                // No se puede editar ya que hay una sesión abierta y esta quedaría así hasta la perpetuidad, causando incoherencia en históricos.
                else if ( vehicleSelect.getDriving() == 1 ){

                    WindowDialogFragment windowCloseRedirecSesionDriving = new WindowDialogFragment( R.string.windowCloseRedirecSesionDriving_edit_message );//<-- Show desde onclickItemList
                    windowCloseRedirecSesionDriving.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                        @Override
                        public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {
                            startActivity( intentSesionDriving );
                        }

                        @Override
                        public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                            return;
                        }
                    } );
                    windowCloseRedirecSesionDriving.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );

                }

                break;

            case R.id.menu_contextual_list_view_vehicles_item_delete:

                //Evento childEventListernet Incorporado en Cotrolador Status
                final String messageRemove = getString( R.string.toast_message_removed_vehicle_generic );

                Log.i( TAG, "OncontextItemSeled() -> Delete -> VegicleSelect -> Driving -> (Valor)" + vehicleSelect.getDriving() );

                // Se puede eliminar
                if ( vehicleSelect.getDriving() == 0 ){
                    controllerDBStatus.removeValue( vehicleSelect, messageRemove + " " +
                            vehicleSelect.getRegistrationNumber() );
                }
                // No se puede eliminar ya que hay una sesión abierta y esta quedaría así hasta la perpetuidad, causando incoherencia en históricos.
                else if ( vehicleSelect.getDriving() == 1 ){

                    WindowDialogFragment windowCloseRedirecSesionDriving = new WindowDialogFragment( R.string.windowCloseRedirecSesionDriving_remove_message );//<-- Show desde onclickItemList
                    windowCloseRedirecSesionDriving.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                        @Override
                        public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {
                            startActivity( intentSesionDriving );
                        }

                        @Override
                        public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                            return;
                        }
                    } );
                    windowCloseRedirecSesionDriving.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );

                }
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public void onSesionDrinvingCreate(){

        //Sesion de inicio por si es la primera ves que inicia sesión
        Session sessionCreate = new Session( "Create" );
        SessionDriving sessionDrivingCreate = new SessionDriving( sessionCreate, user );
        controllerDBSessionsCurrents.setValue( sessionDrivingCreate );
    }

    public void onclickItemList(){
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    final int position, long id) {
                Resources resources = getResources();
                final Vehicle vehicleSelect = vehicles.get( position );
                String messageYes = resources.getString( R.string.windows_yes_init_session_vehicle_message ) + " " +
                        vehicleSelect.getRegistrationNumber();
                String messageNo = resources.getString( R.string.windows_no_init_session_vehicle_message ) + " " +
                        vehicleSelect.getRegistrationNumber();


                    if (vehicleSelect.getDriving() == 1) {

                        Log.i( TAG, "onclickItemList() -> sesionDrivingsCurrents -> typeSesion (Valor) : " + sessionDrivingCurrent.getSession().getTypeSesion() );

                        if (sessionDrivingCurrent.getSession().getTypeSesion().equals( "Create" ) ) {
                            Toast.makeText( getApplicationContext(), "Este vehículo está siendo usado por otro usuario, no puede iniciar sesión", Toast.LENGTH_SHORT ).show();
                        }
                        else {

                            WindowDialogFragment windowNoInitSV = new WindowDialogFragment( messageNo );//<-- Show desde onclickItemList
                            windowNoInitSV.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                                @Override
                                public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {
                                    startActivity( intentSesionDriving );
                                }

                                @Override
                                public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                                    return;
                                }
                            } );
                            windowNoInitSV.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );
                        }
                    }
                if (vehicleSelect.getDriving() == 0) {

                    Session session = new Session("Start" );

                    SessionDriving sessionDrivingStart = new SessionDriving( session, user, vehicleSelect );
                    checkSesion( sessionDrivingStart );
                    Log.i( TAG, "vehicleSelect (Valor)" + vehicleSelect.getDriving());
                }

            }
        });
    }

    public void checkSesion( final SessionDriving sessionDrivingStart) {

        //Condición 1
        if (sessionDrivingCurrent.getSession().getTypeSesion().equals( "Start" )) {

            Log.i( TAG, "checkSesion() -> Condición 1 -> sesionDrivings -> typeSesion (Valor) " + sessionDrivingCurrent.getSession().getTypeSesion() );

            WindowDialogFragment windowForActivictiSesionDriving = new WindowDialogFragment( "Ya tiene una sesión abierta con otro vehículo, cierrela primero." );
            windowForActivictiSesionDriving.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                @Override
                public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {
                    startActivity( intentSesionDriving );
                }

                @Override
                public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                    return;
                }
            } );
            windowForActivictiSesionDriving.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );
        }

        //Condición 3
        else if (sessionDrivingCurrent.getSession().getTypeSesion() != "Start") {

            WindowDialogFragment windowInitSesion = new WindowDialogFragment( "Deses iniciar sesión" );
            windowInitSesion.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                @Override
                public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                    Log.i( TAG, "checkSesion() Condición 3 -> sesionDrivings -> typeSesion (Valor) " + sessionDrivingCurrent.getSession().getTypeSesion() );
                    Log.i( TAG, "checkSesion() Condición 3 -> sesionDrivingStart -> vehigle -> driving (Valor) " + sessionDrivingStart.getVehicle().getDriving() );

                    controllerDBSessionsCurrents.updateValue( sessionDrivingStart, null );
                    controllerDBStatus.updateValue( sessionDrivingStart.getVehicle(), null);
                    controllerDBSessionsHistoric.setValue( sessionDrivingStart );
                    startActivity( intentSesionDriving );
                }

                @Override
                public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                    return;
                }
            } );
            windowInitSesion.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );

        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //Destruyo el evento para evitar recursividad

    }

}
