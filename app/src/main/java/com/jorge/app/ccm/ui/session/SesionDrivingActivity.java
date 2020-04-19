package com.jorge.app.ccm.ui.session;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicle;
import com.jorge.app.ccm.ui.vehicles.Vehicle;
import java.util.ArrayList;

import static com.jorge.app.ccm.ui.vehicles.VehiclesListActivity.VEHICLE_SELECT_FOR_SESION;

public class SesionDrivingActivity extends AppCompatActivity{

    private ControllerVehicle controllerVehicle;
    private DatabaseReference dbRFSesions;
    private ValueEventListener valueEventListener;
    private AdapterSession arrayAdapterSesion;
    private Vehicle vehicleSelectForSesion;
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
        dbRFSesions = controllerVehicle.getDB_RF_SESIONS();
        valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshotSesion) {
                        if (dataSnapshotSesion.exists()) {
                            arrayAdapterSesion.setArrayAdapter(dataSnapshotSesion);
                        }
                        else {
                            Toast.makeText( getApplicationContext(), R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText( getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };


        //Inizializao Adapter para mostrar lista de sesiones
        this.arrayAdapterSesion = new AdapterSession( getApplication(), textView, listView);
        sesionsDrivings = arrayAdapterSesion.getListIntemSesions();
        vehicleSelectForSesion = (Vehicle) getIntent().getExtras().getSerializable( VEHICLE_SELECT_FOR_SESION );//<- El Inten
        dbRFSesions.addValueEventListener( valueEventListener );
    }

    @Override
    public void onStart() {
        super.onStart();
        onclickItemList();
        checkSesion();
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

    public void checkSesion(){

        SesionDriving sesion = new SesionDriving( true , vehicleSelectForSesion);//<-- Inicio session (Start)

        //Si no hay registro de sesión (No existe) no puede estar ocupado
        if ( sesionsDrivings.size() == 0 ){
            //Guardo sesion en db
            controllerVehicle.setSesion( sesion );
            controllerVehicle.setVehicle( sesion.getVehicle() );//<-- Lo machaco para pasar vehículo a ocupado en Status ->Driving
        }
        // Controlo que este vehiculo no esté en uso actualmente
        else {
            for( int i = 0; i < sesionsDrivings.size(); i++ ) {
                //Si existe registro de sesión del vehículo con el que se pretende iniciar la misma
                if (sesionsDrivings.get( i ).getUser().getEmail().equals( vehicleSelectForSesion.getDrivingCurrent() )) {
                        //No debería llegar aquí este vehículo está ocupado.
                        Toast.makeText( getApplicationContext(), "Ya tiene un sesion iniciada con matrícula: " +
                                vehicleSelectForSesion.getRegistrationNumber(), Toast.LENGTH_SHORT).show();
                    } else {
                        //Guardo sesion en db
                        controllerVehicle.setSesion( sesion );
                        controllerVehicle.setVehicle( sesion.getVehicle() );//<-- Lo machaco para pasar vehículo a ocupado en Status ->Driving
                    }
                }
            }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dbRFSesions.removeEventListener( valueEventListener );
    }
}
