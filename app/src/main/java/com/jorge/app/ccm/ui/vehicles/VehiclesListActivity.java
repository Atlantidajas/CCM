package com.jorge.app.ccm.ui.vehicles;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSesions;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.WindowNoInitSesionVehicle;
import com.jorge.app.ccm.ui.form.WindowYesInitSesionVehicle;
import com.jorge.app.ccm.ui.session.SesionDriving;
import com.jorge.app.ccm.ui.session.SesionDrivingActivity;
import com.jorge.app.ccm.ui.user.User;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Jorge.HL
 */
public class VehiclesListActivity extends AppCompatActivity implements Serializable{

    private final String TAG = "VehiclesListActivity";
    public Intent intentSesionDriving;
    public Intent intentSesionParking;
    public Intent intentForUpdate;
    public static final String VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE = "com.jorge.app.ccm.vehicles.VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE";
    private ControllerDBStatus controllerDBStatus;
    private ControllerDBSesions controllerDBSesions;

    private AdapterVehicle arrayAdapterVehicle;
    private TextView textView;
    private ListView listView;
    private WindowYesInitSesionVehicle windowYesInitSV;
    private WindowNoInitSesionVehicle windowNoInitSV;
    private ArrayList<Vehicle> vehicles;
    private User user;
    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_vehicles);
        controllerDBStatus = new ControllerDBStatus( getApplicationContext() );
        controllerDBSesions = new ControllerDBSesions( getApplicationContext() );
        user = new User(  );

        //Inizializao Adapter para mostrar lista de vehículos
        this.arrayAdapterVehicle = new AdapterVehicle( getApplication(), textView, listView);
        controllerDBStatus.setAdapter( arrayAdapterVehicle );
        vehicles = arrayAdapterVehicle.getListIntemVehicles();

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
            Intent intent = new Intent ( VehiclesListActivity.this, RegistryVehicles.class);
            startActivity(intent);
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
        // Llamo al OnCreateContextMenu del padre por si quiere
        // añadir algún elemento.
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Posición lista pulsado
        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;

        // Información al usuario sobre menú pulsado.
        switch (item.getItemId()) {

            case R.id.menu_contextual_list_view_vehicles_item_edit:

                intentForUpdate= new Intent ( VehiclesListActivity.this, UpdateVehicle.class);
                intentForUpdate.putExtra(VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE, (Serializable) arrayAdapterVehicle.getItem( position ) );
                startActivity(intentForUpdate);
                break;

            case R.id.menu_contextual_list_view_vehicles_item_delete:

                //Evento childEventListernet Incorporado en Cotrolador Status
                String messageRemove = getString( R.string.toast_message_removed_vehicle_generic );
                controllerDBStatus.removeValue( vehicles.get( position ), messageRemove + " " +
                        vehicles.get( position ).getRegistrationNumber() );

                //Cambo de mensaje genérico a espesívico para este evento
                this.arrayAdapterVehicle.getListIntemVehicles().clear();
                this.arrayAdapterVehicle.notifyDataSetChanged();
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
                intentSesionDriving = new Intent( VehiclesListActivity.this, SesionDrivingActivity.class );
                intentSesionParking = new Intent( VehiclesListActivity.this, VehicleParkingActivity.class );

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

                    // Si no se ha iniciado sesión para la conducción de este vehículo (Ventana dos botones)

                    if (vehicle.getDriving() == 0) {

                        windowYesInitSV = new WindowYesInitSesionVehicle( messageYes );//<-- Show desde onclickItemList
                        windowYesInitSV.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                            @Override
                            public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                                final Vehicle vehicleResult = vehicles.get( position );
                                final SesionDriving sesionDriving = new SesionDriving( true, vehicleResult );

                                dbRef = controllerDBSesions.getDatabaseReference().child( "SesionsCurrents" ).child( sesionDriving.getUser().getIdUser() );
                                valueEventListener = new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        //Condición 1
                                        if( dataSnapshot.exists() ) {

                                            SesionDriving resultSesionCurrent = new SesionDriving( dataSnapshot );

                                            Log.i( TAG, "Clave primaria para la consulta en db: --> " + dataSnapshot.getKey() );
                                            Log.i( TAG, "SesionDring -> typeSesion: --> " + sesionDriving.getTypeSesion() );
                                            Log.i( TAG, "Resultado typeSesion en db : -->" + resultSesionCurrent.getTypeSesion() );

                                            //Condición 1.2
                                            if ( resultSesionCurrent.getTypeSesion().equals( "Create" ) && sesionDriving.getTypeSesion().equals( "Create" ) ) {

                                                Log.i( TAG, "Condición 1.2 -> resultSesionCurrente (Valor) : " + resultSesionCurrent.getTypeSesion() );
                                                Log.i( TAG, "Condición 1.2 -> sesionDriving -> typeSesion (Valor) : " + sesionDriving.getTypeSesion() );

                                                sesionDriving.setTypeSesion( "Start" );
                                                controllerDBSesions.updateCurrent( sesionDriving );
                                                controllerDBSesions.startSesion( sesionDriving );
                                                arrayAdapterVehicle.getListIntemVehicles().clear();//<-- Limpio por si retrocede
                                                arrayAdapterVehicle.notifyDataSetChanged();//<-- Notifico cambios
                                                finish();
                                            }

                                            //Condición 1.3
                                            if ( resultSesionCurrent.getTypeSesion().equals( "End" ) && sesionDriving.getTypeSesion().equals( "Start" )  ) {

                                                Log.i( TAG, "Condición 1.3 -> resultSesionCurrente (Valor) : " + resultSesionCurrent.getTypeSesion() );
                                                Log.i( TAG, "Condición 1.3 -> sesionDriving -> typeSesion (Valor) : " + sesionDriving.getTypeSesion() );
                                                Log.i( TAG, "Condición 1.3 -> sesionDriving -> typeSesion (Valor) : " + sesionDriving.getVehicle().getRegistrationNumber() );
                                                Log.i( TAG, "Condición 1.3 -> sesionDriving -> typeSesion (Valor) : " + sesionDriving.getUser().getIdUser() );

                                                controllerDBSesions.updateCurrent( sesionDriving );
                                                controllerDBStatus.updateValue( sesionDriving.getVehicle(), null );
                                                controllerDBSesions.startSesion( sesionDriving );
                                                arrayAdapterVehicle.getListIntemVehicles().clear();//<-- Limpio por si retrosede
                                                arrayAdapterVehicle.notifyDataSetChanged();//<-- Notifico cambios
                                                startActivity( intentSesionParking );
                                                finish();
                                            }

                                            //Condición 1.4
                                            if ( resultSesionCurrent.getTypeSesion().equals( "Start" ) && sesionDriving.getTypeSesion().equals( "Start" )) {

                                                Log.i( TAG, "Condición 1.4 -> resultSesionCurrente (Valor) : " + resultSesionCurrent.getTypeSesion() );
                                                Log.i( TAG, "Condición 1.4 -> sesionDriving -> typeSesion (Valor) : " + sesionDriving.getTypeSesion() );

                                                arrayAdapterVehicle.getListIntemVehicles().clear();//<-- Limpio por si retrosede
                                                arrayAdapterVehicle.notifyDataSetChanged();//<-- Notifico cambios
                                                startActivity( intentSesionDriving );
                                                finish();
                                            }

                                        }

                                        //Condición  2
                                        else {

                                            sesionDriving.setTypeSesion( "Create" );
                                            Log.i( TAG, "Condición 2 sesionDriving (Valor): --> " + sesionDriving.getTypeSesion() );
                                            controllerDBSesions.updateCurrent( sesionDriving );
                                            Log.i( TAG, "Valor de driving en en vehículo : --> " + vehicleResult.getDriving() );
                                            controllerDBStatus.updateValue( vehicleResult, null );
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                };
                                dbRef.addValueEventListener( valueEventListener );
                            }

                            @Override
                            public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                                return;
                            }
                        } );
                        windowYesInitSV.getDialogFragmentNotice().show( getSupportFragmentManager(), "WindowYesInitSesionVehicle" );
                    }
            }
        });

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if( valueEventListener != null ){
            dbRef.removeEventListener( valueEventListener );
        }
    }

}
