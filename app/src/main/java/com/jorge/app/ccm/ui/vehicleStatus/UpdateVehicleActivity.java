package com.jorge.app.ccm.ui.vehicleStatus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.gadget.notices.DatePickerFragment;
import com.jorge.app.ccm.models.ExpenseTemp;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.utils.BrandsUtil;

import static com.jorge.app.ccm.ui.vehicleStatus.VehiclesStatusListActivity.REQUEST_INTENT_VEHICLE_FOR_UPDATE_VEHICLE;
import static com.jorge.app.ccm.ui.vehicleStatus.VehiclesStatusListActivity.VEHICLE_FOR_UPDATE_VEHICLE;

public class UpdateVehicleActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "UpdateVehicleActivity";
    private ImageView imageViewBrand;
    private Spinner spinnerBrand;
    private EditText editTextModel;
    private EditText editTextRegistryNumber;
    private EditText editTextDateITV;
    private Button buttonSave;
    private Button buttonCancel;
    private ExpenseTemp vehicleTemp;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_registry_vehicles);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//<-- añado flecha retroseso

        imageViewBrand = findViewById( R.id.imageView_image_item_vehicles );
        spinnerBrand = findViewById( R.id.spinner_brand_registry_vehicle);
        editTextModel = findViewById( R.id.edit_text_model_registry_vehicle );
        editTextRegistryNumber = findViewById( R.id.edit_text_registry_number_registry_vehicle );
        editTextDateITV = findViewById( R.id.edit_text_registry_date_itv_vehicle );
        findViewById( R.id.button_accept_vehicleRegistry ).setOnClickListener( this );
        findViewById( R.id.button_cancel_vehicleRegistry ).setOnClickListener( this );


        //Fichero para guardar datos de forma temporal objetos tipo expensesVehicleForUserTemp
        vehicleTemp = new ExpenseTemp( getApplicationContext(), TAG );
        vehicleTemp.setVehicleDriving( 0 );//<-- Nadie lo conduce ya que se acaba de crear.

       onActivityResult( REQUEST_INTENT_VEHICLE_FOR_UPDATE_VEHICLE, RESULT_OK, getIntent() );

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == android.R.id.home ){
            finish();
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }


    /*
     * Guardo comprueba la correcta recepción de los datos recibidos por medio de Items, cargo los campos
     */
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if ( requestCode == REQUEST_INTENT_VEHICLE_FOR_UPDATE_VEHICLE  && resultCode  == RESULT_OK ) {

                Bundle objetoIn = data.getExtras();
                Vehicle vehicleSelect = null;


                vehicleSelect = (Vehicle) objetoIn.getSerializable(VEHICLE_FOR_UPDATE_VEHICLE);

                // Guardo los datos en fichero de forma temporar por si el usuario regresa o sale de la actividad para seleccionar typeExpense
                vehicleTemp.setVehicleTemp( this, TAG, vehicleSelect );

                Log.i( TAG, VEHICLE_FOR_UPDATE_VEHICLE + " expenseVehicleLogo " +
                        "Valor que se guardará: " + vehicleTemp.getVehiclelogo() );
                Log.i( TAG, VEHICLE_FOR_UPDATE_VEHICLE + " expenseVehicleRegistrationNumber " +
                        "Valor que se guardará: " + vehicleTemp.getVehicleRegistrationNumber() );
                Log.i( TAG, VEHICLE_FOR_UPDATE_VEHICLE + " expenseVehicleBrand " +
                        "Valor que se guardará: " + vehicleTemp.getVehicleBrand() );
                Log.i( TAG, VEHICLE_FOR_UPDATE_VEHICLE + " getModel " +
                        "Valor que se guardará: " + vehicleTemp.getVehicleModel() );
                Log.i( TAG, VEHICLE_FOR_UPDATE_VEHICLE + " expenseVehicleDateITV " +
                        "Valor que se guardará: " + vehicleTemp.getVehicleDateITV() );
                Log.i( TAG, VEHICLE_FOR_UPDATE_VEHICLE + " expenseVehicleDriving " +
                        "Valor que se guardará: " + vehicleTemp.getVehicleDriving() );

                this.loadFieldEditTextSpinnerBrand( vehicleTemp.getVehicleBrand() );
                this.loadFieldEditTextModel( vehicleTemp.getVehicleModel() );
                this.loadFieldEditTextRegistryNumber( vehicleTemp.getVehicleRegistrationNumber() );
                this.loadFieldEditTextDateITV( vehicleTemp.getVehicleDateITV() );
            }

        } catch (Exception ex) {
            Toast.makeText( UpdateVehicleActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFieldEditTextSpinnerBrand( String brandTemp ){

        Resources res = getResources();
        final String[] brands = res.getStringArray( R.array.manufactures );
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, brands );
        spinnerBrand.setAdapter( adapter );

        final BrandsUtil brandsUtil = new BrandsUtil( res );


        //Funciones ejecutadas al seleccionar un elemento del desplegable
        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if( brands[i] != null ) {
                    vehicleTemp.setVehicleBrand( brands[i] );
                    vehicleTemp.setVehiclelogo( brandsUtil.getIdResource( brands[i] ) );
                    imageViewBrand.setImageResource( vehicleTemp.getVehiclelogo() );
                    Log.i( TAG, "spinnerBrand -> Item pulsado -> (Valor)" + brands[i] );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerBrand.setSelection( 0 );
            }
        });

        //Cargo los datos del fichero temporal.

        //La primera vez que se carga la actividad será nula,
        if ( ( brandTemp == null) || ( brandTemp.equals( "" ) ) ) {
            brandTemp = "";
            imageViewBrand.setImageResource( vehicleTemp.getVehiclelogo() );
        }
        else {
            for( int i = 0; i < brands.length; i++ ){

                if ( brands[i].equals( brandTemp ) ){
                    spinnerBrand.setSelection( i );
                }
            }
        }
    }

    private void loadFieldEditTextModel( String modelTemp ){

        //Cargo los datos del fichero temporal, que se guardarón por el intent
        editTextModel.setText( modelTemp );

        editTextModel.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String text = editTextModel.getText().toString();
                vehicleTemp.setVehicleModel( text );
                return false;
            }
        } );

    }

    private void loadFieldEditTextRegistryNumber( String registrationNumbreTemp ){

        editTextRegistryNumber.setEnabled( false );//<-- No se puede cambiar matrícula (Se trata tambien del id del vehículo)
        //Cargo los datos del fichero temporal.
        editTextRegistryNumber.setText( registrationNumbreTemp );

    }

    private void loadFieldEditTextDateITV( String dateITVTemp ){

        editTextDateITV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        } );

        editTextDateITV.setText( dateITVTemp );
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance( new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 Enero es 0.
                final String selectedDate = day + "-" + ( month+1 ) + "-" + year;
                vehicleTemp.setVehicleDateITV( selectedDate );
                editTextDateITV.setText( vehicleTemp.getVehicleDateITV() );
            }
        });

        newFragment.show(getSupportFragmentManager(), TAG + "datePickerDateItvVehicle");

    }

    private void restartActivity(){
        Intent intent = getIntent();
        finish();
    }

    private boolean validateFieldTypeEditText(EditText editText ){

        String textForValidate = editText.getText().toString();

        if (TextUtils.isEmpty( textForValidate ) || textForValidate.equals( "0.0" ) ){
            //Cambio color
            editText.setBackgroundColor( Color.parseColor("#A1F9A825"));

            //Posiciono el foco sobre el editText
            editText.requestFocus();

            Toast.makeText(this, R.string.toast_message_empty_fields, Toast.LENGTH_LONG).show();

            return false;
        }
        else{
            return true;
        }
    }

    private boolean validateFieldTypeSpinner( Spinner spinner ){

        String textForValidate = spinner.getSelectedItem().toString();

        Resources res = getResources();
        final String[] brands = res.getStringArray( R.array.manufactures );

        // Compruebo que no sea la primera posición. Ya que esta, es el mensaje de campo.
        if ( TextUtils.isEmpty( textForValidate ) || textForValidate.equals( brands[0] ) ){
            //Cambio color
            spinner.setBackgroundColor( Color.parseColor("#A1F9A825"));

            //Posiciono el foco sobre el editText
            spinner.requestFocus();

            Toast.makeText(this, R.string.toast_message_empty_fields, Toast.LENGTH_LONG).show();

            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Guarda objeto de tipo Vehicle en la DB
     */
    private void saveDatesFormForDB( Vehicle vehicle ){
        ControllerDBStatus controllerDBStatus = new ControllerDBStatus( getApplicationContext(), TAG );
        controllerDBStatus.updateStatusVehicle( vehicle );
        finish();
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();

        if (i == R.id.button_cancel_vehicleRegistry) {
            vehicleTemp.removeExpenseTemp();
            restartActivity();
        }

        if (i == R.id.button_accept_vehicleRegistry) {

            if ( validateFieldTypeSpinner( spinnerBrand ) &&
                    validateFieldTypeEditText( editTextModel ) &&
                    validateFieldTypeEditText( editTextRegistryNumber ) &&
                    validateFieldTypeEditText( editTextDateITV ) ){

                Vehicle vehicle = new Vehicle( vehicleTemp );
                saveDatesFormForDB( vehicle );//<-- Guarda db
                vehicleTemp.removeExpenseTemp();//<-- Borro datos del fichero temporal correspondientes al objeto expenseTemp
                finish();
            }
        }
        // Los llamo desde toolbar signOut(), revokeAccess().
    }
}

