package com.jorge.app.ccm.ui.vehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicles;
import com.jorge.app.ccm.ui.alertsDialogos.NoticeDialogFragment;

public class RegistreVehicleActivity extends AppCompatActivity implements NoticeDialogFragment.NoticeDialogListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre_vehicle);
    }

    public void confirmAlertDialog( int title, int message, int textButtonPositive, int textButtonNegative, boolean cancelable ) {
        DialogFragment newFragment = new NoticeDialogFragment( title, message, textButtonPositive, textButtonNegative, cancelable);
        //newFragment.show(getSupportFragmentManager(), "Registry");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //Al construir NoticeDialogFragment con solo botón positivo no va hacer falta
        // este método pero es obigatorio su nombramiento por ser implementado mediante interface en esta clase.
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Intent intent= new Intent ( getApplicationContext(), VehiclesListActivity.class);
        startActivity(intent);
    }

}
