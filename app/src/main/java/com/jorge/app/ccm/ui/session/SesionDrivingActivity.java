package com.jorge.app.ccm.ui.session;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicle;
import com.jorge.app.ccm.ui.vehicles.Vehicle;
import java.util.ArrayList;

import static com.jorge.app.ccm.ui.vehicles.VehiclesListActivity.VEHICLE_SELECT_FOR_SESION;

public class SesionDrivingActivity extends AppCompatActivity{

    private ControllerVehicle controllerVehicle;
    private AdapterSession arrayAdapterSesion;
    private TextView textView;
    private ListView listView;
    private ArrayList<SesionDriving> sesionsDrivings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sesion_driving );
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_sessions);
        controllerVehicle = new ControllerVehicle( getApplicationContext() );
        //Inizializao Adapter para mostrar lista de sesiones
        this.arrayAdapterSesion = new AdapterSession( getApplication(), textView, listView);
        sesionsDrivings = arrayAdapterSesion.getListIntemSesions();


        Vehicle vehicleSelectForSesion = (Vehicle) getIntent().getExtras().getSerializable( VEHICLE_SELECT_FOR_SESION );//<- El Inten
        SesionDriving sesionDriving = new SesionDriving( true , vehicleSelectForSesion);
        controllerVehicle.setSesion( sesionDriving );

        //controllerVehicle.getControllerVehiclesSesion().newSesionsVehicleResgistry( userSesionVehicle );
        //Glide.with(this).load(user.photoUri()).into(imageViewLogoUser);
        //  controllerVehicle.getControllerVehicleStatus().setDriving( vehicleSelectForSesion.getRegistrationNumber(), 1 );
    }

    @Override
    public void onStart() {
        super.onStart();
        onclickItemList();
        arrayAdapterSesion.readSesions();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //if ( id == R.id.xxxxxxxxx ) {
       // }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }

    public void onclickItemList(){
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    int position, long id) {}
        });
    }
}
