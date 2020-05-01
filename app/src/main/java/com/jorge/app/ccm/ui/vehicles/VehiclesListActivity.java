package com.jorge.app.ccm.ui.vehicles;

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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSesionsCurrents;
import com.jorge.app.ccm.controllers.ControllerDBSesionsHistoric;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.WindowNoInitSesionVehicle;
import com.jorge.app.ccm.ui.form.WindowYesInitSesionVehicle;
import com.jorge.app.ccm.ui.session.SesionDriving;
import com.jorge.app.ccm.ui.session.SesionDrivingActivity;
import com.jorge.app.ccm.ui.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


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
    private ControllerDBSesionsCurrents controllerDBSesionsCurrents;
    private ControllerDBSesionsHistoric controllerDBSesionsHistoric;

    private AdapterVehicle arrayAdapterVehicle;
    private TextView textView;
    private ListView listView;
    private WindowNoInitSesionVehicle windowNoInitSV;
    private WindowYesInitSesionVehicle windowCloseRedirecSesionDriving;
    private ArrayList<Vehicle> vehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_vehicles);

        //Inicialización de controladores
        controllerDBStatus = new ControllerDBStatus( getApplicationContext() );
        controllerDBSesionsCurrents = new ControllerDBSesionsCurrents( getApplicationContext() );
        controllerDBSesionsHistoric = new ControllerDBSesionsHistoric( getApplicationContext() );


        //Eventos de cambios sobre el adaptador
        controllerDBStatus.getDatabaseReference().addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapterVehicle.getListIntemVehicles().clear();
                arrayAdapterVehicle.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapterVehicle.getListIntemVehicles().clear();
                arrayAdapterVehicle.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                arrayAdapterVehicle.getListIntemVehicles().clear();
                arrayAdapterVehicle.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapterVehicle.getListIntemVehicles().clear();
                arrayAdapterVehicle.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        //Inizializao Adapter para mostrar lista de vehículos
        this.arrayAdapterVehicle = new AdapterVehicle( getApplication(), textView, listView);
        controllerDBStatus.setAdapter( arrayAdapterVehicle );
        vehicles = arrayAdapterVehicle.getListIntemVehicles();

        //Intens
        intentForRegistryVehicles = new Intent ( VehiclesListActivity.this, RegistryVehicles.class);
        intentForUpdate= new Intent ( VehiclesListActivity.this, UpdateVehicle.class);
        intentSesionDriving = new Intent( VehiclesListActivity.this, SesionDrivingActivity.class );



    }

    @Override
    public void onStart() {
        super.onStart();
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

                    windowCloseRedirecSesionDriving = new WindowYesInitSesionVehicle( R.string.windowCloseRedirecSesionDriving_edit_message );//<-- Show desde onclickItemList
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

                    windowCloseRedirecSesionDriving = new WindowYesInitSesionVehicle( R.string.windowCloseRedirecSesionDriving_remove_message );//<-- Show desde onclickItemList
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

    public void onclickItemList(){
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    final int position, long id) {
                Resources resources = getResources();
                final Vehicle vehicle = (Vehicle) arrayAdapterVehicle.getItem( position );
                String messageYes = resources.getString( R.string.windows_yes_init_session_vehicle_message ) + " " +
                        vehicle.getRegistrationNumber();
                String messageNo = resources.getString( R.string.windows_no_init_session_vehicle_message ) + " " +
                        vehicle.getRegistrationNumber();

                SesionDriving sesionDriving = new SesionDriving( true, vehicle );

                    // Si ya hay iniciado sesión para la conducción de este vehículo (Ventana de un solo botón)
                    if (vehicle.getDriving() == 1) {
                        windowNoInitSV = new WindowNoInitSesionVehicle( messageNo );//<-- Show desde onclickItemList
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
                if (vehicle.getDriving() == 0) {

                    //Log.i( TAG, "sesionDrivings -> (Valor) : " + sesionDrivings.get( 0 ).getTypeSesion() );
                    Log.i( TAG, "sesionDriving -> (Valor) : " + sesionDriving.getVehicle().getDriving() );

                }

            }
        });

    }
/*
    public void checkSesion(Vehicle vehicleStatusSelect, SesionDriving sesionDriving) {

        switch ( vehicleStatusSelect.getDriving() ) {

            case 0:
                //Condición 1
                if (sesionDrivings.get( 0 ).getTypeSesion().equals( "Start" )) {
                    Log.i( TAG, "Case 0 -> checkSesion() -> Condición 1 -> sesionDrivings -> typeSesion (Valor) " + sesionDrivings.get( 0 ).getTypeSesion() );
                    windowForIntenSesionDriving( "Desea iniciar sesion" );
                }

                //Condición 2
                else if (sesionDrivings.get( 0 ).getTypeSesion() != "Start") {

                    Log.i( TAG, "Case 0 -> checkSesion() -> Condición 2 -> sesionDrivings -> typeSesion (Valor) " + sesionDrivings.get( 0 ).getTypeSesion() );
                    controllerDBSesionsCurrents.updateValue( sesionDriving, null );
                    controllerDBStatus.updateValue( sesionDriving.getVehicle(), null );
                    controllerDBSesionsHistoric.setValue( sesionDriving );
                    startActivity( intentSesionDriving );
                    finish();
                }
                break;

            case 1:
                Log.i( TAG, "checkSesion() -> Case 1 -> vehicleSelectStatus -> driving (Valor) " + vehicleStatusSelect.getDriving() );
                windowForIntenSesionDriving( "No puede iniciar una sesion si tiene una previamente abierta. Cierrela" );
                break;

        }
    }*/

    public void windowForIntenSesionDriving( String message ) {
        Resources resources = getResources();
        //String messageNo = resources.getString( R.string.windows_no_init_session_vehicle_message ) + " " +
        //      vehicleStatus.getRegistrationNumber();


        windowNoInitSV = new WindowNoInitSesionVehicle( message );//<-- Show desde onclickItemList
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


    @Override
    public void onDestroy(){
        super.onDestroy();
        //Destruyo el evento para evitar recursividad

    }

}
