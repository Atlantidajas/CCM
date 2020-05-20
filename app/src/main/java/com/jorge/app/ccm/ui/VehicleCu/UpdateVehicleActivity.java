package com.jorge.app.ccm.ui.VehicleCu;

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
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.gadget.notices.DialogFragmentDatePincker;
import com.jorge.app.ccm.gadget.notices.DialogFragmentSpinner;
import com.jorge.app.ccm.gadget.GadgetSpinner;
import com.jorge.app.ccm.ui.vehicleStatus.VehiclesListActivity;
import com.jorge.app.ccm.utils.BrandsUtil;

import static com.jorge.app.ccm.ui.vehicleStatus.VehiclesListActivity.VEHICLE_REGISTRY_NUMBER_FOR_UPDATE_VEHICLE;

public class UpdateVehicleActivity extends AppCompatActivity implements DialogFragmentSpinner.DialogFragmentListener, View.OnClickListener{

    private final String TAG = "UpdateVehicleActivity";
    private ControllerDBStatus controllerDBStatus;
    private Vehicle vehicleForUpdate;
    private GadgetSpinner spinnerBrands;
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
        editTextRegistryNumber.setEnabled( false );//<-- No se puede cambiar matrícula (Se trata tambien del id del vehículo)
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
    public void onStart() {
        super.onStart();
    }

    //Onclik sobre item de FormRegistryBrands
    @Override
    public void onDialogItemClick(DialogFragment dialog) {
        String resultItemSelect = spinnerBrands.textItem( spinnerBrands.getItemResult() );
        editTextBrand.setText( resultItemSelect );
        checkBoxConfirmBrand.setChecked(true);
    }

    @Override
    public void onDialogFragmentSelectPositiveClick(DialogFragment dialog) {
    }

    @Override
    public void onDialogFragmentSelectNeutralClick(DialogFragment dialog) {

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
                showUpdateRegistry();
                break;

        }
    }

    public void showRegistryBrands(){
        Resources resource = getResources();
        BrandsUtil brandsUtil = new BrandsUtil( resource );
        String[] manufactures = brandsUtil.getBrands();
        this.spinnerBrands = new GadgetSpinner( getSupportFragmentManager(), R.string.spiner_title_records_vehicles, manufactures, TAG );
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

    public void showUpdateRegistry(){

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

            Vehicle vehicle = new Vehicle( logo, registrationNumber, brand, model, dateITV,0);
            controllerDBStatus = new ControllerDBStatus( getApplicationContext() );
            String messageUpdateVehicle = getString( R.string.toast_message_changed_vehicle_generic );
            controllerDBStatus.updateValue( vehicle, messageUpdateVehicle + " " + vehicle.getRegistrationNumber());
            controllerDBStatus = null;

            Intent intent= new Intent ( UpdateVehicleActivity.this, VehiclesListActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.toast_message_empty_fields, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        controllerDBStatus = null;
    }

}