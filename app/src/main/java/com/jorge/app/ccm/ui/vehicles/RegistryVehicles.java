package com.jorge.app.ccm.ui.vehicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.Controller;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentDatePincker;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSpinner;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;
import com.jorge.app.ccm.ui.form.SpinnerRegistryBrands;

public class RegistryVehicles extends AppCompatActivity implements DialogFragmentSpinner.DialogFragmentListener,
        View.OnClickListener, DialogFragmentNotice.DialogNoticeListerner {

    private Controller controllerVehicles;
    private SpinnerRegistryBrands spinnerRegistryBrands;
    private EditText editTextBrand;
    private CheckBox checkBoxConfirmBrand;
    private EditText editTextModel;
    private CheckBox checkBoxConfirmModel;
    private EditText editTextRegistryNumber;
    private CheckBox checkBoxConfirmRegistryNumber;
    private EditText editTextDateITV;
    private CheckBox checkBoxConfirmDateITV;
    private DialogFragmentDatePincker dialogFragmentDatePincker;//<- Para ITV
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry_vehicles);

        controllerVehicles = new Controller("Vehicles");

        editTextBrand = findViewById( R.id.edit_text_brand_registry_vehicle);
        editTextBrand.setOnClickListener( this );
        checkBoxConfirmBrand = findViewById( R.id.checkBox_brand_registry_vehicle );

        editTextModel = findViewById( R.id.edit_text_model_registry_vehicle );
        checkBoxConfirmModel = findViewById( R.id.checkBox_model_registry_vehicle );
        showRegistryModel();

        editTextRegistryNumber = findViewById( R.id.edit_text_registry_number_registry_vehicle );
        checkBoxConfirmRegistryNumber = findViewById( R.id.checkBox_registry_number_registry_vehicle );
        showRegistryNumber();

        checkBoxConfirmDateITV = findViewById( R.id.check_box_registry_date_itv_vehicle );
        editTextDateITV = findViewById( R.id.edit_text_registry_date_itv_vehicle );
        dialogFragmentDatePincker = new DialogFragmentDatePincker();
        editTextDateITV.setOnClickListener( this );

        buttonSave = findViewById( R.id.button_registry_save_vehicle );
        buttonSave.setOnClickListener( this );





    }
    public void showRegistryBrands(){
        Resources res = getResources();
        String[] manufactures = res.getStringArray(R.array.manufactures);
        this.spinnerRegistryBrands = new SpinnerRegistryBrands( getSupportFragmentManager(), manufactures );
        //editTextModel.requestFocus();//<-- Mando el foco a la siguiente posición (Model)
    }

    public void showRegistryModel(){

        editTextModel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //checkBoxConfirmModel.setChecked( false );
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = getString(R.string.edit_text_model_registry_vehicle);
                if ( ( value.equals( editTextModel.getText().toString() ) == false ) ||
                        (value.equals( editTextModel.getText().toString() == "" ) ) ) {
                    checkBoxConfirmModel.setChecked(true);
                }
                else{
                    checkBoxConfirmModel.setChecked(false);
                }
            }
        });
    }

    public void showRegistryNumber(){
        editTextRegistryNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkBoxConfirmRegistryNumber.setChecked(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = getString(R.string.edit_text_registry_number_registry_vehicle);
                if ( value.equals( editTextRegistryNumber.getText().toString() ) == false ) {
                    checkBoxConfirmRegistryNumber.setChecked(true);
                }
                else{
                    checkBoxConfirmRegistryNumber.setChecked( false );
                }
            }
        });
    }

    public void showRegistryDateITV(){
        dialogFragmentDatePincker.show(getSupportFragmentManager(), "DatePinckerITV");
        Resources res = getResources();
        String value = res.getString(R.string.edit_text_registry_date_itv_vehicle);
        String day= String.valueOf( dialogFragmentDatePincker.getDay() );
        String month = String.valueOf( dialogFragmentDatePincker.getMonth()+1 );
        String year = String.valueOf( dialogFragmentDatePincker.getYear() );
        if ( value.equals( editTextDateITV.getText().toString() ) == false ) {
            checkBoxConfirmDateITV.setChecked( true );
        }
        else{
            checkBoxConfirmDateITV.setChecked( false );
        }
        editTextDateITV.setText( day + "/" + month + "/" + year );
    }

    public void showSaveRegistry(){

        if( checkBoxConfirmBrand.isChecked() &&
        checkBoxConfirmModel.isChecked() &&
        checkBoxConfirmRegistryNumber.isChecked() &&
        checkBoxConfirmDateITV.isChecked() ){


            int logo = R.mipmap.ic_launcher_logo_brand_seat;
            String registrationNumber = editTextRegistryNumber.toString();
            String brand = editTextBrand.toString();
            String model = editTextModel.toString();
            String dateITV = editTextDateITV.toString();


            Vehicle vehicle = new Vehicle( logo, registrationNumber, brand, model );


            controllerVehicles.writeNewRegistry( registrationNumber, vehicle );

            Toast.makeText(getApplicationContext(), "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.toast_message_, Toast.LENGTH_SHORT).show();
        }
    }

    //Onclik sobre item de FormRegistryBrands
    @Override
    public void onDialogItemClick(DialogFragment dialog) {
        String resultItemSelect = spinnerRegistryBrands.textItem(spinnerRegistryBrands.getItemResult() );
        editTextBrand.setText( resultItemSelect );
        checkBoxConfirmBrand.setChecked(true);
    }

    @Override
    public void onDialogFragmentSelectPositiveClick(DialogFragment dialog) {
    }

    @Override
    public void onDialogFragmentSelectNegativeClick(DialogFragment dialog) {
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edit_text_brand_registry_vehicle:
                showRegistryBrands();
                break;
            case R.id.edit_text_registry_date_itv_vehicle:
                showRegistryDateITV();
                break;
            case R.id.button_registry_save_vehicle:
                showSaveRegistry();
                break;

        }
    }

    @Override
    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {

    }



}
