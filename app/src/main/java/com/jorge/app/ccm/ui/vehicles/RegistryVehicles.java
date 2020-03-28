package com.jorge.app.ccm.ui.vehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSelect;
import com.jorge.app.ccm.ui.form.FormRegistryBrands;

public class RegistryVehicles extends AppCompatActivity implements DialogFragmentSelect.DialogFragmentListener {

    private FormRegistryBrands formRegistryBrands;
    private Button buttonBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry_vehicles);
        buttonBrand = findViewById( R.id.button_brand_registry_vehicle );

        buttonBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFormRegistryBrands();
            }
        });

    }

    public FormRegistryBrands getFormRegistryBrands(){
        Resources res = getResources();
        String[] manufactures = res.getStringArray(R.array.manufactures);
        this.formRegistryBrands = new FormRegistryBrands( getSupportFragmentManager(), manufactures );
        return formRegistryBrands;
    }

    //Onclik sobre item de FormRegistryBrands
    @Override
    public void onDialogItemClick(DialogFragment dialog) {

        //System.out.println( "Item pulsado: " + this.formRegistryBrands.getItemResult() );
        System.out.println("************************************************************************************************");
        System.out.println( "Texto pulsado: " + this.formRegistryBrands.textItem(this.formRegistryBrands.getItemResult() ) );
        System.out.println("************************************************************************************************");

    }

    @Override
    public void onDialogFragmentSelectPositiveClick(DialogFragment dialog) {
        //System.out.println("Pulsado siguiente >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void onDialogFragmentSelectNegativeClick(DialogFragment dialog) {
        //System.out.println("Pulsado atras <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
