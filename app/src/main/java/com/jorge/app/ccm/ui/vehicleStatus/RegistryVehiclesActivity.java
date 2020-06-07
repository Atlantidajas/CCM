package com.jorge.app.ccm.ui.vehicleStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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

public class RegistryVehiclesActivity extends AppCompatActivity{

    private String TAG = "RegistryVehiclesActivity";
    private ImageView imageViewBrand;
    private Spinner spinnerBrand;
    private EditText editTextModel;
    private EditText editTextRegistryNumber;
    private EditText editTextDateITV;
    private Button buttonSave;
    private Button buttonCancel;
    private ExpenseTemp vehicleTemp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_registry_vehicles);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//<-- añado flecha retroseso

        imageViewBrand = findViewById( R.id.imageView_image_item_vehicles );
        spinnerBrand = findViewById( R.id.spinner_brand_registry_vehicle);
        editTextModel = findViewById( R.id.edit_text_model_registry_vehicle );
        editTextRegistryNumber = findViewById( R.id.edit_text_registry_number_registry_vehicle );
        editTextDateITV = findViewById( R.id.edit_text_registry_date_itv_vehicle );
        buttonSave = findViewById( R.id.button_accept_vehicleRegistry );
        buttonCancel = findViewById( R.id.button_cancel_vehicleRegistry );

        //Fichero para guardar datos de forma temporal objetos tipo expensesVehicleForUserTemp
        vehicleTemp = new ExpenseTemp( getApplicationContext(), TAG );
        vehicleTemp.setVehicleDriving( 0 );//<-- Nadie lo conduce ya que se acaba de crear.


        loadFieldEditTextSpinnerBrand();
        loadFieldEditTextModel();
        loadFieldEditTextRegistryNumber();
        loadFieldEditTextDateITV();
        loadFieldButtonAcceptRegistry();
        loadFieldButtonCancelRegistry();

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

    public void loadFieldEditTextSpinnerBrand(){

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
                spinnerBrand.setSelection( 0 );//<-- No puede seleccionar la opción primera, es el mensaje

            }
        });

        //Cargo los datos del fichero temporal.
        String brand = vehicleTemp.getVehicleBrand();




        //La primera vez que se carga la actividad brand será nula,
        if ( ( brand == null) || ( brand.equals( "" ) ) ) {
            brand = "";
        }
        else {
            for( int i = 0; i < brands.length; i++ ){

                if ( brands[i].equals( brand ) ){
                    spinnerBrand.setSelection( i );

                }
            }
        }

    }

    public void loadFieldEditTextModel(){

        editTextModel.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String text = editTextModel.getText().toString();
                vehicleTemp.setVehicleModel( text );
                return false;
            }
        } );

        //Cargo los datos del fichero temporal.
        String model = vehicleTemp.getVehicleModel();
        editTextModel.setText( model );
    }

    public void loadFieldEditTextRegistryNumber(){

        editTextRegistryNumber.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String text = editTextRegistryNumber.getText().toString();
                vehicleTemp.setVehicleRegistrationNumber( text );
                return false;
            }
        } );

        //Cargo los datos del fichero temporal.
        String registrationNumber = vehicleTemp.getVehicleRegistrationNumber();
        editTextRegistryNumber.setText( registrationNumber );

    }

    public void loadFieldEditTextDateITV(){

        editTextDateITV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        } );

        editTextDateITV.setText( vehicleTemp.getVehicleDateITV() );
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

    public void loadFieldButtonAcceptRegistry(){

        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        } );

    }

    public void loadFieldButtonCancelRegistry(){

        buttonCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicleTemp.removeExpenseTemp();
                finish();
            }
        } );
    }

    public boolean validateFieldTypeEditText( EditText editText ){

        String textForValidate = editText.getText().toString();

        if (TextUtils.isEmpty( textForValidate ) || textForValidate.equals( "0.0" ) ){
            //Cambio color
            editText.setBackgroundColor( Color.parseColor("#A1F9A825"));

            //Posiciono el foco sobre el editText
            editText.requestFocus();

            Toast.makeText(this, "Ha dejado el campo vacio", Toast.LENGTH_LONG).show();

            return false;
        }
        else{
            return true;
        }
    }

    public boolean validateFieldTypeSpinner( Spinner spinner ){

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
    public void saveDatesFormForDB( Vehicle vehicle ){

        ControllerDBStatus controllerDBStatus = new ControllerDBStatus( getApplicationContext(), TAG );
        controllerDBStatus.setStatusVehicle( vehicle );
    }
}

