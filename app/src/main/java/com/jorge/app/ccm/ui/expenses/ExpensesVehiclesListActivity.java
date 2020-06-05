package com.jorge.app.ccm.ui.expenses;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.ui.vehicleStatus.RegistryVehiclesActivity;

import java.io.Serializable;

/**
 * @author Jorge.HL
 */
public class ExpensesVehiclesListActivity extends AppCompatActivity implements Serializable{

    private String TAG = "VehiclesSelectListActivity";
    private AdapterExpensesVehicles adapterVehicleSelect;
    private Intent intentRegistryVehicles;
    private TextView textView;
    private ListView listView;
    public static final String VEHICLE_SELECT = "com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity.VEHICLE_SELECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_select_list );
        textView = findViewById(R.id.textView_vehicles_vehicle_select_list);
        listView = findViewById(R.id.listView_vehicles_vehicle_select_list);

        //Inizializao Adapter para mostrar lista de vehículos
        this.adapterVehicleSelect = new AdapterExpensesVehicles( getApplication(), listView, TAG);

        intentRegistryVehicles = new Intent( ExpensesVehiclesListActivity.this, RegistryVehiclesActivity.class );

        fieldLoadTextViewTitle();
    }

    /**
     * Muestra dos mensajes e iconos dependiendo de si hay elementos en la lista, en ambos caso misma funcionalidad en su evento onclick
     */
    public void fieldLoadTextViewTitle(){

        final ControllerDBStatus controllerDBStatus = new ControllerDBStatus( getApplicationContext(), TAG );
        //Cargo con los datos de la db el adapter
        controllerDBStatus.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Drawable img;

                if (dataSnapshot.exists()) {

                    Log.i( TAG, "fieldLoadTextViewTitle() -> StatusDB -> getCountChild: " + String.valueOf( controllerDBStatus.getCountChildStatus() ) );
                    textView.setText( R.string.textView_vehicles_select_list_activity_false );
                    img = getApplicationContext().getResources().getDrawable( R.mipmap.ic_launcher_select_multiple );
                }
                else {
                    textView.setText( R.string.textView_vehicles_select_list_activity_true );
                    img = getApplicationContext().getResources().getDrawable( R.mipmap.ic_launcher_arrow_top );
                }

                textView.setCompoundDrawablesWithIntrinsicBounds( null, null, img, null);
                textView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();//<-- Regresa a la actividad anterior
                    }
                } );
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

                Log.i( TAG, "Vehículo seleccionado Matrícula (Valor) -> " + vehicleSelect.getVehicleRegistrationNumber() );

                Bundle bundle= new Bundle();
                bundle.putSerializable( VEHICLE_SELECT, vehicleSelect );
                intentRegistryVehicles.putExtras(bundle);
                setResult( RESULT_OK, intentRegistryVehicles );
                finish();
            }
        });
    }
}






