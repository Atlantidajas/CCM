package com.jorge.app.ccm.ui.vehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSelect;
import com.jorge.app.ccm.ui.form.FormRegistryBrands;

public class RegistryVehicles extends AppCompatActivity implements DialogFragmentSelect.DialogFragmentListener {

    private FormRegistryBrands formRegistryBrands;
    private EditText buttonBrand;
    private CheckBox checkBoxConfirmBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry_vehicles);
        buttonBrand = findViewById( R.id.edit_text_brand_registry_vehicle);
        checkBoxConfirmBrand = findViewById( R.id.checkBox_brand_registry_vehicle );
        checkBoxConfirmBrand.isChecked();

        buttonBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFormRegistryBrands();

            }
        });

    }

    public void getFormRegistryBrands(){
        Resources res = getResources();
        String[] manufactures = res.getStringArray(R.array.manufactures);
        this.formRegistryBrands = new FormRegistryBrands( getSupportFragmentManager(), manufactures );
    }

    //Onclik sobre item de FormRegistryBrands
    @Override
    public void onDialogItemClick(DialogFragment dialog) {
        String resultItemSelect = formRegistryBrands.textItem(formRegistryBrands.getItemResult() );
        buttonBrand.setText( resultItemSelect );
        checkBoxConfirmBrand.setChecked(true);
    }

    @Override
    public void onDialogFragmentSelectPositiveClick(DialogFragment dialog) {
    }

    @Override
    public void onDialogFragmentSelectNegativeClick(DialogFragment dialog) {
        //System.out.println("Pulsado atras <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
