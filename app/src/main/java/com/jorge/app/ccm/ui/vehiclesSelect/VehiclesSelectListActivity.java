package com.jorge.app.ccm.ui.vehiclesSelect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;
import com.jorge.app.ccm.ui.expenses.ExpensesActivity;

import java.io.Serializable;


/**
 * @author Jorge.HL
 */
public class VehiclesSelectListActivity extends AppCompatActivity implements Serializable{

    private final String TAG = "VehiclesSelectListActivity";
    private AdapterVehicleSelect adapterVehicleSelect;
    private Intent intentRegistryVehicles;
    private Intent intentExpensesActivity;
    private TextView textView;
    private ListView listView;
    public static final String REGISTRATION_NUMBER_VEHICLE_SELECT = "com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity.REGISTRATION_NUMBER_VEHICLE_SELECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_select_list );
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_vehicles);

        intentRegistryVehicles = new Intent( VehiclesSelectListActivity.this, RegistryVehiclesActivity.class );
        intentExpensesActivity = new Intent( VehiclesSelectListActivity.this, ExpensesActivity.class );

        //Inizializao Adapter para mostrar lista de vehículos
        this.adapterVehicleSelect = new AdapterVehicleSelect( getApplication(), textView, listView);

        fieldLoadTextViewTitle();

    }

    public void fieldLoadTextViewTitle(){

        textView.setText( R.string.textView_vehicles_select_list_activity );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//<-- Regresa a la actividad anterior
            }
        } );
    }

    @Override
    public void onStart() {
        super.onStart();
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
            startActivity( intentRegistryVehicles );
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }


    public void onclickItemList(){
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    final int position, long id) {

                Vehicle vehicleSelect = (Vehicle) adapterVehicleSelect.getItem( position );
                intentExpensesActivity.putExtra(REGISTRATION_NUMBER_VEHICLE_SELECT, vehicleSelect.getRegistrationNumber()  );
                startActivity( intentExpensesActivity );
            }
        });
    }
}


