package com.jorge.app.ccm.ui.vehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.jorge.app.ccm.controllers.ControllerVehicle;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.WindowInitSesionVehicle;
import com.jorge.app.ccm.ui.session.SesionDriving;
import com.jorge.app.ccm.ui.session.SesionDrivingActivity;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Jorge.HL
 */
public class VehiclesListActivity extends AppCompatActivity implements Serializable, DialogFragmentNotice.DialogNoticeListerner{

    Intent intent;
    public static final String VEHICLE_SELECT_FOR_SESION = "com.jorge.app.ccm.vehicles.VEHICLE_SELECT_FOR_SESION";
    private ControllerVehicle controllerVehicle;
    private AdapterVehicle arrayAdapterVehicle;
    private TextView textView;
    private ListView listView;
    private WindowInitSesionVehicle windowInitSV;
    private ArrayList<Vehicle> vehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_vehicles);
        controllerVehicle = new ControllerVehicle();
        //Inizializao Adapter para mostrar lista de vehículos
        this.arrayAdapterVehicle = new AdapterVehicle( getApplication(), textView, listView);
        vehicles = arrayAdapterVehicle.listIntemVehicles;
        readVehicles();
        registerForContextMenu( listView);

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
            Intent intent= new Intent ( VehiclesListActivity.this, RegistryVehicles.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }


    public void readVehicles() {

        //Lamada función buscar vehículos

        DatabaseReference vehiclesStatus = controllerVehicle.getChildVehiclesStatus();
        vehiclesStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    arrayAdapterVehicle.setArrayAdapter(dataSnapshot);

                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
            menu.setHeaderTitle( getString( R.string.menu_contextual_list_view_vehicles_title ) + " " + arrayAdapterVehicle.getVehicle().getRegistrationNumber());

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

                break;

            case R.id.menu_contextual_list_view_vehicles_item_delete:
               // messageToast(getString(R.string.eliminar) + this.users.getUsers().get( position).getName());
                //this.users.deteteUser( position );
                //this.adapter.notifyDataSetChanged();

                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    // Muestra una tostada.
    private void messageToast(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }


    public void onclickItemList(){
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    int position, long id) {

                Resources resources = getResources();
                Vehicle vehicle = (Vehicle) arrayAdapterVehicle.getItem( position );
                String message = resources.getString( R.string.windows_init_session_vehicle_message ) + " " +
                        vehicle.getRegistrationNumber();

                intent = new Intent(VehiclesListActivity.this, SesionDrivingActivity.class);
                intent.putExtra(VEHICLE_SELECT_FOR_SESION, (Serializable) arrayAdapterVehicle.getItem( position ) );

                windowInitSV = new WindowInitSesionVehicle( message );//<-- Show desde onclickItemList
                windowInitSV.show( getSupportFragmentManager(), "DialogFragmentNoticeSesionVehicle" );

            }
        });
    }


    @Override
    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {
        startActivity(intent);
    }

    @Override
    public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
        return;
    }
}
