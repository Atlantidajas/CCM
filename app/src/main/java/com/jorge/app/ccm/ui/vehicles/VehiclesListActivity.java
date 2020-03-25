package com.jorge.app.ccm.ui.vehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.res.Resources;
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
import com.jorge.app.ccm.controllers.Controller;
import com.jorge.app.ccm.controllers.ControllerVehicles;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSelect;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.FormRegistryBrands;
import com.jorge.app.ccm.ui.form.WindowsNoticeNoRegistryVehicle;
import com.jorge.app.ccm.ui.form.WindowsNoticeYesRegistryVehicle;

import java.util.ArrayList;
import java.util.Iterator;

public class VehiclesListActivity extends AppCompatActivity {

    private Controller controllerVehicles;
    private TextView textView;
    private ListView listView;
    private FormRegistryBrands formRegistryBrands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);

        //Connect
        controllerVehicles = new Controller("Vehicles");

        Vehicle vehiculoPrueba = new Vehicle(
                R.mipmap.ic_launcher_logo_brand_fiat,
                "46",
                "Fiat",
                "Punto" );

        controllerVehicles.chekRegistration( vehiculoPrueba.getRegistrationNumber() );


    }


}
