package com.jorge.app.ccm.ui.vehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
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
import com.jorge.app.ccm.controllers.ControllerVehicle;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentDatePincker;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSpinner;
import com.jorge.app.ccm.ui.form.SpinnerRegistryBrands;
import com.jorge.app.ccm.utils.BrandsUtil;

import static com.jorge.app.ccm.ui.vehicles.VehiclesListActivity.VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE;

public class UpdateVehicle extends AppCompatActivity implements DialogFragmentSpinner.DialogFragmentListener, View.OnClickListener{

    private ControllerVehicle controllerVS;
    private Vehicle vehicleForUpdate;
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
        setContentView( R.layout.activity_registry_vehicles);

        controllerVS = new ControllerVehicle( getApplicationContext() );
        vehicleForUpdate = (Vehicle) getIntent().getExtras().getSerializable( VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE );//<- El Inten
        editTextBrand = findViewById( R.id.edit_text_brand_registry_vehicle);
        editTextBrand.setText( vehicleForUpdate.getBrand() );
        editTextBrand.setOnClickListener( this );
        checkBoxConfirmBrand = findViewById( R.id.checkBox_brand_registry_vehicle );

        editTextModel = findViewById( R.id.edit_text_model_registry_vehicle );
        editTextModel.setText( vehicleForUpdate.getModel() );
        checkBoxConfirmModel = findViewById( R.id.checkBox_model_registry_vehicle );
        showRegistryModel();

        editTextRegistryNumber = findViewById( R.id.edit_text_registry_number_registry_vehicle );
        editTextRegistryNumber.setText( vehicleForUpdate.getRegistrationNumber() );
        checkBoxConfirmRegistryNumber = findViewById( R.id.checkBox_registry_number_registry_vehicle );
        showRegistryNumber();

        checkBoxConfirmDateITV = findViewById( R.id.check_box_registry_date_itv_vehicle );
        editTextDateITV = findViewById( R.id.edit_text_registry_date_itv_vehicle );
        editTextDateITV.setText( vehicleForUpdate.getDateITV() );
        dialogFragmentDatePincker = new DialogFragmentDatePincker();
        editTextDateITV.setOnClickListener( this );

        buttonSave = findViewById( R.id.button_registry_save_vehicle );
        buttonSave.setOnClickListener( this );

    }

    @Override
    public void onResume(){
        super.onResume();
        controllerVS.getDB_RF_STATUS().removeEventListener( controllerVS.getChildEventListener() );
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

    public void showRegistryBrands(){
        Resources resource = getResources();
        BrandsUtil brandsUtil = new BrandsUtil( resource );
        String[] manufactures = brandsUtil.getBrands();
        this.spinnerRegistryBrands = new SpinnerRegistryBrands( getSupportFragmentManager(), manufactures );
        editTextModel.requestFocus();//<-- Mando el foco a la siguiente posición
    }

    public void showRegistryModel(){

        editTextModel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

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
        editTextDateITV.setText( day + "-" + month + "-" + year );
    }

    public void showSaveRegistry(){

        if( checkBoxConfirmBrand.isChecked() &&
                checkBoxConfirmModel.isChecked() &&
                checkBoxConfirmRegistryNumber.isChecked() &&
                checkBoxConfirmDateITV.isChecked() ){

            Resources resource = getResources();
            BrandsUtil brandsUtil = new BrandsUtil( resource );

            String registrationNumber = editTextRegistryNumber.getText().toString();
            String brand = editTextBrand.getText().toString();
            String model = editTextModel.getText().toString();
            String dateITV =  editTextDateITV.getText().toString();

            int logo = brandsUtil.getIdResource( brand );
            int driving = 0;//<-- Siempre que se crea vehículo a 0 para cuando lo coja para conducir de un 1 en String

            Vehicle vehicle = new Vehicle( logo, registrationNumber, brand, model, dateITV, driving );

            // Eventos en controlador
            controllerVS.updateVehicle( vehicle );

            Intent intent= new Intent ( UpdateVehicle.this, VehiclesListActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.toast_message_empty_fields, Toast.LENGTH_SHORT).show();
        }
    }

}