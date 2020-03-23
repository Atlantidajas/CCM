package com.jorge.app.ccm.ui.vehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicles;
import com.jorge.app.ccm.ui.alertsDialogos.NoticeDialogFragment;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSelect;


import java.util.ArrayList;
import java.util.Iterator;

public class VehiclesListActivity extends AppCompatActivity implements NoticeDialogFragment.NoticeDialogListener {

    private ControllerVehicles controllerVehicles;
    private TextView textView;
    private ListView listView;
    private FormRegistryBrands formRegistryBrands;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);

        //Connect
        this.controllerVehicles = new ControllerVehicles();
        //readVehicleForReference("1234HFT");


        Vehicle vehiculoPrueba = new Vehicle(
                R.mipmap.ic_launcher_logo_brand_ford,
                "8",
                "Ford",
                "Fiesta" );

        writeNewVehicle( vehiculoPrueba);
        readVehicles();
        showFormRegistryBrands();//<-- Muestra el FialogFrgment Formulario de Marcas
        System.out.println("*******************************************************************************************");
        System.out.println( "Botón pulsado en formulario de marca registro" + this.formRegistryBrands.getItemResult() );
        System.out.println("*******************************************************************************************");

        /*confirmNoticeDialogFragment(R.string.title_alert_dialog,
                                    R.string.alert_message_a_license_plate_already_exists,
                                    R.string.button_positive_alert_dialog, false );*/


    }

    public void readVehicleForReference( String refence ) {
        //Lamada función buscar vehículo por matrícula
        DatabaseReference vehicleRF = controllerVehicles.getVehicleRf( refence );

        ProgressBar progressBar;

        vehicleRF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    setArrayAdapter(dataSnapshot);
                } else {
                    Toast.makeText(getApplicationContext(), "No se han obtenido resultado. Error en consulta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readVehicles() {
        //Lamada función buscar vehículos
        DatabaseReference vehicles = controllerVehicles.getVehicle();
        vehicles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    setArrayAdapter(dataSnapshot);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se han obtenido resultado. Error en consulta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setArrayAdapter( DataSnapshot dataSnapshot ){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
        ArrayList<Vehicle> listIntemVehicles = new ArrayList<Vehicle>();

        do{
            listIntemVehicles.add( new Vehicle( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        AdapterVehicle arrayAdapter = new AdapterVehicle(
                getApplicationContext(),
                R.id.imageView_image_item_vehicles,
                listIntemVehicles );
        this.textView = this.findViewById(R.id.textView_vehicles);
        this.listView = this.findViewById(R.id.listView_vehicles);

        if ( arrayAdapter.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje
            textView.setText( "No hay vehículos en la lista" );
        }else{
            listView.setAdapter(arrayAdapter);
        }

    }

    public void confirmNoticeDialogFragment( int title, int message, int textButtonPositive, boolean cancelable ) {
        DialogFragment newFragment = new NoticeDialogFragment( title, message, textButtonPositive, cancelable);
        newFragment.show(getSupportFragmentManager(), "NoticeDialogListener");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //Al construir NoticeDialogFragment con solo botón positivo no va hacer falta
        // este método pero es obigatorio su nombramiento por ser implementado mediante interface en esta clase.

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public void writeNewVehicle( Vehicle vehicle ){

         this.controllerVehicles.writeNewVehicle( vehicle );

       /*if( resultOpeWrite == 0 ){
            this.confirmAlertDialog( R.string.alert_title_notice,
                    R.string.registration_yes_carried_out,
                    R.string.alert_positive_button,
                    false );
        }
        if( resultOpeWrite == 1 ){
            this.confirmAlertDialog( R.string.alert_title_notice,
                    R.string.registration_not_carried_out,
                    R.string.alert_positive_button,
                    false );
        }

        if( resultOpeWrite == 2 ){
            this.confirmAlertDialog( R.string.alert_title_notice,
                    R.string.error_inesperado,
                    R.string.alert_positive_button,
                    false );
        }*/
    }

    public void showFormRegistryBrands(){
        this.formRegistryBrands = new FormRegistryBrands(getSupportFragmentManager());
    }

}
