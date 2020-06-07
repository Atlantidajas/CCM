package com.jorge.app.ccm.ui.vehicleStatus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.models.Session;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.ui.home.HomeActivity;
import com.jorge.app.ccm.ui.sessionCrurrent.SessionCurrentActivity;
import com.jorge.app.ccm.models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Jorge.HL
 */
public class VehiclesStatusListActivity extends AppCompatActivity {


    private final String TAG = "VehiclesListActivity";
    public Intent intentHome;
    public Intent intentSesionDriving;
    public Intent intentForUpdate;
    public Intent intentForDelete;
    public Intent intentForRegistryVehicles;
    public static final String VEHICLE_FOR_UPDATE_VEHICLE = "com.jorge.app.ccm.ui.vehiclesStatus.VehiclesStatusListActivity.VEHICLE_FOR_UPDATE_VEHICLE";
    public static final String VEHICLE_FOR_DELETE_VEHICLE = "com.jorge.app.ccm.ui.vehiclesStatus.VehiclesStatusListActivity.VEHICLE_FOR_DELETE_VEHICLE";
    public static final int REQUEST_INTENT_VEHICLE_FOR_UPDATE_VEHICLE = 0;
    public static final int REQUEST_INTENT_VEHICLE_FOR_DELETE_VEHICLE = 1;

    private AdapterVehicleStatus arrayAdapterVehicleStatus;
    private TextView textView;
    private ListView listView;
    private SessionDriving sessionDrivingCurrent;//<-- Inicializada desde función readSesionCurrent()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_status_list );
        textView = findViewById(R.id.testView_title_registrarion_numbre);
        listView = findViewById(R.id.listView_vehicles_status_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//<-- añado flecha retroseso

        //Si es la primera vez que usa la app, creo una primera sesión de tipo Create (Esta no será mostrada al usuario)
        onSesionDrinvingCreate();

        //Inicializo sessionDrivingCurrent
        readSesionCurrent();

        registerForContextMenu( listView );
        onclickItemList();

        //Intens
        intentHome = new Intent( VehiclesStatusListActivity.this, HomeActivity.class);
        intentForRegistryVehicles = new Intent ( VehiclesStatusListActivity.this, RegistryVehiclesActivity.class);
        intentForUpdate = new Intent ( VehiclesStatusListActivity.this, UpdateVehicleActivity.class);
        intentForDelete = new Intent ( VehiclesStatusListActivity.this, DeleteVehicleActivity.class);
        intentSesionDriving = new Intent( VehiclesStatusListActivity.this, SessionCurrentActivity.class );
        arrayAdapterVehicleStatus = new AdapterVehicleStatus( getApplicationContext() );
        ControllerDBStatus controllerDBStatus = new ControllerDBStatus( getApplicationContext(), TAG );


       DatabaseReference databaseReference = controllerDBStatus.getDatabaseReference();
       databaseReference.addValueEventListener(  new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                    ArrayList<Vehicle>vehicles = new ArrayList<>(  );
                    do {
                        vehicles.add( new Vehicle( dataSnapshots.next() ) );
                    } while (dataSnapshots.hasNext());

                    arrayAdapterVehicleStatus.setListIntemVehicles( vehicles );
                    listView.setAdapter( arrayAdapterVehicleStatus );
                }
                else {
                    Toast.makeText( getApplicationContext(), R.string.toast_message_no_data, Toast.LENGTH_SHORT ).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Inicializa sessionDrivingCurrent
     */
    public void readSesionCurrent(){

        User user = new User( true );
        ControllerDBSessionsHistoric controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext(), TAG );
        controllerDBSessionsHistoric.getDatabaseReferenceSessionsCurrents().child( user.getIdUser() ).addValueEventListener( new ValueEventListener() {
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
        if ( id == android.R.id.home ){
            finish();
            startActivity( intentHome );
            return true;
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo ) {

        // Si se ha hecho LongClick sobre la lista.
        if ( v.getId() == R.id.listView_vehicles_status_list ) {
            // Obtengo la posición de la lista que se ha pulsado
            int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            // Inflo el menú.
            this.getMenuInflater().inflate( R.menu.menu_contextual_list_view_vehicles, menu );

            MenuItem itemMenu1 = menu.findItem( R.id.menu_contextual_list_view_vehicles_item_edit );

            Vehicle vehicleSelect = arrayAdapterVehicleStatus.getItem( position );

            // Establezco el título que se muestra en el encabezado del menú. + número de matrúcula para avisar al usuario del cambio
            menu.setHeaderTitle( getString( R.string.menu_contextual_list_view_vehicles_title ) + " " +
                    vehicleSelect.getVehicleRegistrationNumber());

        }
        // Llamo al OnCreateContextMenu del padre por si quiere añadir algún elemento
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Posición lista pulsado
        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        Vehicle vehicleSelect = arrayAdapterVehicleStatus.getListIntemVehicles().get( position );

        // Información al usuario sobre menú pulsado.
        switch (item.getItemId()) {

            //Editar
            case R.id.menu_contextual_list_view_vehicles_item_edit:

                //Editar un vehículo de la db
                editVehicle( vehicleSelect );

                break;

            case R.id.menu_contextual_list_view_vehicles_item_delete:
                //Eliminar un vehículo de la db
                deleteVehicle( vehicleSelect );


            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public void onSesionDrinvingCreate(){

        //Sesion de inicio por si es la primera ves que inicia sesión
        ControllerDBSessionsHistoric controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext(), TAG );
        controllerDBSessionsHistoric.onSesionDrinvingCreate();
    }

    public void onclickItemList(){

        final User user = new User( true );
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    final int position, long id) {

                Resources resources = getResources();
                final Vehicle vehicleSelect = arrayAdapterVehicleStatus.getListIntemVehicles().get( position );

                String messageYes = resources.getString( R.string.windows_yes_init_session_vehicle_message ) + " " +
                        vehicleSelect.getVehicleRegistrationNumber();
                String messageNo = resources.getString( R.string.windows_no_init_session_vehicle_message ) + " " +
                        vehicleSelect.getVehicleRegistrationNumber();

                    if ( vehicleSelect.getVehicleDriving() == 1 ) {

                        Log.i( TAG, "onclickItemList() -> sesionDrivingsCurrents -> typeSesion (Valor) : " + sessionDrivingCurrent.getSessionTypeSesion() );

                        if (sessionDrivingCurrent.getSessionTypeSesion().equals( "Create" ) ) {
                            Toast.makeText( getApplicationContext(), R.string.windowSesionNo, Toast.LENGTH_SHORT ).show();
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
                if (vehicleSelect.getVehicleDriving() == 0) {

                    Session session = new Session("Start" );

                    SessionDriving sessionDrivingStart = new SessionDriving( session, user, vehicleSelect );
                    checkSesion( sessionDrivingStart );//Chequea si se puede realizar la operación y si es así la realiza
                    Log.i( TAG, "vehicleSelect (Valor)" + vehicleSelect.getVehicleDriving());
                }
            }
        });
    }

    public void checkSesion( final SessionDriving sessionDrivingStart) {

        final ControllerDBStatus controllerDBStatus = new ControllerDBStatus( getApplicationContext(), TAG );
        final ControllerDBSessionsHistoric controllerDBSessionsHistoric = new ControllerDBSessionsHistoric( getApplicationContext(), TAG );

        //Si puede iniciar
        if ( sessionDrivingCurrent.checkSesion() ){
            WindowDialogFragment windowInitSesion = new WindowDialogFragment( R.string.windowInitSession );
            windowInitSesion.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                @Override
                public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                    Log.i( TAG, "checkSesion() Condición 3 -> sesionDrivings -> typeSesion (Valor) " + sessionDrivingCurrent.getSessionTypeSesion() );
                    Log.i( TAG, "checkSesion() Condición 3 -> sesionDrivingStart -> vehigle -> driving (Valor) " + sessionDrivingStart.getVehicle().getVehicleDriving() );

                    controllerDBSessionsHistoric.registrySessionHistoric(sessionDrivingStart);
                    startActivity( intentSesionDriving );
                }

                @Override
                public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                    return;
                }
            } );
            windowInitSesion.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );
        }
        //No puede iniciar
        else {
            Log.i( TAG, "checkSesion() -> Condición 1 -> sesionDrivings -> typeSesion (Valor) " + sessionDrivingCurrent.getSessionTypeSesion() );

            WindowDialogFragment windowForActivictiSesionDriving = new WindowDialogFragment( R.string.windowInitOnUser );
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

    }

    public void editVehicle( Vehicle vehicleForEdit){

        //Si se puede editar.
        if ( vehicleForEdit.getVehicleDriving() == 0 ){

            Bundle bundle= new Bundle();
            bundle.putSerializable( VEHICLE_FOR_UPDATE_VEHICLE, vehicleForEdit );
            intentForUpdate.putExtras(bundle);
            setResult( RESULT_OK, intentForUpdate );

            startActivityForResult(intentForUpdate, REQUEST_INTENT_VEHICLE_FOR_UPDATE_VEHICLE);

        }
        // No se puede editar ya que hay una sesión abierta y esta quedaría así hasta la perpetuidad, causando incoherencia en históricos.
        else if ( vehicleForEdit.getVehicleDriving() == 1 ){

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
    }

    public void deleteVehicle(Vehicle vehicleForDelete){

        //Si se puede editar.
        if ( vehicleForDelete.getVehicleDriving() == 0 ){

            Bundle bundle= new Bundle();
            bundle.putSerializable( VEHICLE_FOR_DELETE_VEHICLE, vehicleForDelete );
            intentForDelete.putExtras(bundle);
            setResult( RESULT_OK, intentForDelete );
            startActivityForResult(intentForDelete, REQUEST_INTENT_VEHICLE_FOR_DELETE_VEHICLE);
            finish();

        }
        // No se puede eliminar ya que hay una sesión abierta y esta quedaría así hasta la perpetuidad, causando incoherencia en históricos.
        else {
            WindowDialogFragment windowCloseRedirecSesionDriving = new WindowDialogFragment( R.string.windowCloseRedirecSesionDriving_remove_message );//<-- Show desde onclickItemList
            windowCloseRedirecSesionDriving.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                @Override
                public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {
                    //Lo remito a la actividad para que pueda cerrar sessión si está decidodo a borrarlo
                    startActivity( intentSesionDriving );
                }

                @Override
                public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                    return;
                }
            } );
            windowCloseRedirecSesionDriving.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );
        }
    }


}
