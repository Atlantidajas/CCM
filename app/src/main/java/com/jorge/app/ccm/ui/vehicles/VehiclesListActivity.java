package com.jorge.app.ccm.ui.vehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.Controller;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentDatePincker;
import com.jorge.app.ccm.ui.form.SpinnerRegistryBrands;

/**
 * @author Jorge.HL
 */

public class VehiclesListActivity extends AppCompatActivity{

    private Controller controllerVehicles;
    private AdapterVehicle arrayAdapterVehicle;
    private SpinnerRegistryBrands spinnerRegistryBrands;
    private TextView textView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_vehicles);

        //Inizializao Adapter para mostrar lista de vehículos
        this.arrayAdapterVehicle = new AdapterVehicle( getApplication(), textView, listView);

        controllerVehicles = new Controller("Vehicles");

        Vehicle vehiculoPrueba = new Vehicle(
                R.mipmap.ic_launcher_logo_brand_fiat,
                "51",
                "Fiat",
                "Punto" );
        controllerVehicles.writeNewRegistry( vehiculoPrueba.getRegistrationNumber(), vehiculoPrueba );
        //result(controllerVehicles.isResultOperating());

        readVehicles();

        //FormRegistryModels rModel = new FormRegistryModels( getSupportFragmentManager() );
        DialogFragmentDatePincker dp = new DialogFragmentDatePincker();
        dp.show( getSupportFragmentManager(), "VehiclesListActivity" );
        System.out.println( "-------------------------------------------------------------------------------------->" + dp.getYear() );

    }

    public void readVehicles() {
        //Lamada función buscar vehículos
        DatabaseReference vehiclesDatabaseReference = controllerVehicles.getDatabaseReference();
        vehiclesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    arrayAdapterVehicle.setArrayAdapter(dataSnapshot);
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

    public void confirmNoticeDialogFragment( int title, int message, int textButtonPositive, boolean cancelable ) {
       // DialogFragment newFragment = new DialogFragmentNotice( title, message, textButtonPositive, cancelable);
        //newFragment.show(getSupportFragmentManager(), "NoticeDialogListener");
    }





    public SpinnerRegistryBrands getSpinnerRegistryBrands(){
        Resources res = getResources();
        String[] manufactures = res.getStringArray(R.array.manufactures);
        this.spinnerRegistryBrands = new SpinnerRegistryBrands( getSupportFragmentManager(), manufactures );
        return spinnerRegistryBrands;
    }


}
