package com.jorge.app.ccm.ui.expenses;

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
import android.widget.Spinner;

import android.widget.Toast;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBExpense;
import com.jorge.app.ccm.gadget.notices.DatePickerFragment;

import com.jorge.app.ccm.models.Expense;
import com.jorge.app.ccm.models.ExpenseTemp;

import com.jorge.app.ccm.models.TypeExpense;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.ui.vehicleStatus.RegistryVehiclesActivity;

import static com.jorge.app.ccm.ui.expenses.TypeExpensesActivity.TYPE_EXPENSE;
import static com.jorge.app.ccm.ui.expenses.ExpensesVehiclesListActivity.VEHICLE_SELECT;

public class ExpensesResgistryActivity extends AppCompatActivity{

    private String TAG = "ExpenseActivity";
    private Intent intentCreateVehicle;
    private Intent intentVehiclesSelectList;
    private Intent intentTypeExpenses;
    private EditText editTextSelectVehicle;
    private EditText editTextTypeExpense;
    private EditText editTextTickectNumber;
    private EditText editTextTicketProviderName;
    private EditText editTextDateExpenses;
    private Spinner spinnerMethodplayment;
    private EditText editTextTotalImport;
    private Button buttonAcceptExpenses;
    private Button buttonCancelExpenses;
    private ExpenseTemp expenseTemp;
    private static final int REQUEST_INTENT_VEHICLE_SELECT = 1;
    private static final int REQUEST_INTENT_TYPE_EXPENSE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_expenses_registry );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//<-- añado flecha retroseso

        editTextSelectVehicle = findViewById( R.id.editText_vehicle_expense_registry );
        editTextTypeExpense = findViewById( R.id.editText_type_expense_expense_registry );
        editTextTickectNumber = findViewById( R.id.editText_ticket_expense_registry  );
        editTextTicketProviderName = findViewById( R.id.editText_ticket_provider_name_expense_registry );
        editTextDateExpenses = findViewById( R.id.editText_date_expense_registry   );
        spinnerMethodplayment = findViewById( R.id.spinnerMethodplayment );
        editTextTotalImport = findViewById( R.id.editTextTotalImport );
        buttonAcceptExpenses = findViewById( R.id.button_accept_expense_registry  );
        buttonCancelExpenses = findViewById( R.id.button_cancel_expense_registry  );

        //Fichero para guardar datos de forma temporal objetos tipo expensesVehicleForUserTemp
        expenseTemp = new ExpenseTemp( getApplicationContext(), TAG );

        //Intens
        intentCreateVehicle = new Intent( ExpensesResgistryActivity.this, RegistryVehiclesActivity.class );
        intentVehiclesSelectList = new Intent( ExpensesResgistryActivity.this, ExpensesVehiclesListActivity.class );
        intentTypeExpenses = new Intent( ExpensesResgistryActivity.this, TypeExpensesActivity.class );

        loadFieldEditTextRegistrationNumber();
        loadFieldEditTextTypeExpense();
        loadFieldEditTextTickectNumber();
        loadFieldEditTextMethodOfPlayment();
        loadFieldEdittextProviderNameExpense();
        loadFieldEditTextdateExpenses();
        loadFieldEditTextTotalImport();
        loadFieldButtonAcceptExpenses();
        loadFieldButtonCancelExpenses();

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
     * Guardo comprueba la correcta recepción de los datos recibidos por medio de Items
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_INTENT_VEHICLE_SELECT  && resultCode  == RESULT_OK) {

                Bundle objetoIn = data.getExtras();
                Vehicle vehicleSelect = null;


                vehicleSelect = (Vehicle) objetoIn.getSerializable(VEHICLE_SELECT);

                // Guardo los datos en fichero de forma temporar por si el usuario regresa o sale de la actividad para seleccionar typeExpense
                expenseTemp.setVehicleTemp( this, TAG, vehicleSelect );

                Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleLogo" +
                        "Valor que se guardará: " + expenseTemp.getVehiclelogo() );
                Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleRegistrationNumber" +
                        "Valor que se guardará: " + expenseTemp.getVehicleRegistrationNumber() );
                Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleBrand" +
                        "Valor que se guardará: " + expenseTemp.getVehicleBrand() );
                Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "getModel" +
                        "Valor que se guardará: " + expenseTemp.getVehicleModel() );
                Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDateITV" +
                        "Valor que se guardará: " + expenseTemp.getVehicleDateITV() );
                Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDriving" +
                        "Valor que se guardará: " + expenseTemp.getVehicleDriving() );

                editTextSelectVehicle.setText( expenseTemp.getVehicleRegistrationNumber() );

            }

            if (requestCode == REQUEST_INTENT_TYPE_EXPENSE  && resultCode  == RESULT_OK) {

                Bundle objetIn = data.getExtras();
                Vehicle vehicleSelect = null;
                TypeExpense typeExpenseSelect = null;

                typeExpenseSelect = (TypeExpense) objetIn.getSerializable(TYPE_EXPENSE);

                if ( typeExpenseSelect !=null )

                    // Guardo los datos en fichero de forma temporar por si el usuario regresa o sale de la actividad para seleccionar Vehículo

                    expenseTemp.setTypeExpenseTemp( this, TAG, typeExpenseSelect );
                    editTextTypeExpense.setText( expenseTemp.getTypeExpenseName() );
            }


        } catch (Exception ex) {
            Toast.makeText( ExpensesResgistryActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void loadFieldEditTextRegistrationNumber(){

        //Los datos temporales son grabados a la recepción del Inten en la función -> onActivityResult

        editTextSelectVehicle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentVehiclesSelectList, REQUEST_INTENT_VEHICLE_SELECT);
            }
        } );

        //Cargo los datos del fichero temporal.
        String registrationNumberVehicle = expenseTemp.getVehicleRegistrationNumber();
        editTextSelectVehicle.setText( registrationNumberVehicle );

    }

    public void loadFieldEditTextTypeExpense(){

        //Los datos temporales son grabados a la recepción del Inten en la función -> onActivityResult
        editTextTypeExpense.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentTypeExpenses, REQUEST_INTENT_TYPE_EXPENSE);
            }
        } );

        //Cargo los datos del fichero temporal.
        String typeExpense = expenseTemp.getTypeExpenseName();
        editTextTypeExpense.setText( typeExpense );


    }

    public void loadFieldEditTextTickectNumber(){

        editTextTickectNumber.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String text = editTextTickectNumber.getText().toString();
                expenseTemp.setTickectNumber( text );
                return false;
            }
        } );

        //Cargo los datos del fichero temporal.
        String ticketNumber = expenseTemp.getTickectNumber();
        editTextTickectNumber.setText( ticketNumber );
    }

    public void loadFieldEdittextProviderNameExpense(){

        editTextTicketProviderName.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String text = editTextTicketProviderName.getText().toString();
                expenseTemp.setProviderName( text );
                return false;
            }
        } );

        //Cargo los datos del fichero temporal.
        String providerName = expenseTemp.getProviderName();
        editTextTicketProviderName.setText( providerName );


    }

    public void loadFieldEditTextdateExpenses(){

        editTextDateExpenses.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        } );

        editTextDateExpenses.setText( expenseTemp.getTickectDate() );
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance( new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 Enero es 0.
                final String selectedDate = day + "-" + ( month+1 ) + "-" + year;
                expenseTemp.setTickectDate( selectedDate );
                editTextDateExpenses.setText( expenseTemp.getTickectDate() );
            }
        });

        newFragment.show(getSupportFragmentManager(), TAG + "datePickerDateTickect");
    }

    public void loadFieldEditTextMethodOfPlayment(){

        Resources res = getResources();
        final String[] methodPlayments = res.getStringArray( R.array.methodPlayment );
        ArrayAdapter <String>adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, methodPlayments );
        spinnerMethodplayment.setAdapter( adapter );

        //Funciones ejecutadas al seleccionar un elemento del desplegable
        spinnerMethodplayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if( methodPlayments[i] != null ) {
                    expenseTemp.setMethodOfPlaymentValueName( methodPlayments[i] );
                    Log.i( TAG, "spinnerMethodplayment -> Item pulsado -> (Valor)" + methodPlayments[i] );
                    System.out.println( "spinnerMethodplayment -> Item pulsado -> (Valor)" + methodPlayments[i] );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerMethodplayment.setSelection( 0 );
            }
        });

        //Cargo los datos del fichero temporal.
        String methodOfPlayment = expenseTemp.getMethodOfPlaymentValueName();

        //La primera vez que se carga la actividad methodOfPlayment será nula,
        if ( ( methodOfPlayment == null) || ( methodOfPlayment.equals( "" ) ) ) {
            methodOfPlayment = "";
        }
        else {
            for( int i = 0; i < methodPlayments.length; i++ ){

                if ( methodPlayments[i].equals( methodOfPlayment ) ){
                    spinnerMethodplayment.setSelection( i );
                }
            }
        }

    }

    public void loadFieldEditTextTotalImport(){

        editTextTotalImport.setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String text = editTextTotalImport.getText().toString();
                expenseTemp.setTickectTotalExpense( text );
                return false;
            }
        } );

        //Cargo los datos del fichero temporal.
        String totalImportExpense = expenseTemp.getTickectTotalExpense();
        editTextTotalImport.setText( totalImportExpense );

    }

    public void loadFieldButtonAcceptExpenses(){

        buttonAcceptExpenses.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( validateFieldTypeEditText( editTextSelectVehicle ) &&
                        validateFieldTypeEditText( editTextTypeExpense ) &&
                        validateFieldTypeEditText( editTextTickectNumber ) &&
                        validateFieldTypeEditText( editTextTicketProviderName ) &&
                        validateFieldTypeEditText( editTextDateExpenses ) &&
                        validateFieldTypeSpinner( spinnerMethodplayment ) &&
                        validateFieldTypeEditText( editTextTotalImport ) ){

                    User user = new User( true );
                    Expense expense = new Expense( expenseTemp );

                    saveDatesFormForDB( expense );//<-- Guarda db
                    expenseTemp.removeExpenseTemp();//<-- Borro datos del fichero temporal correspondientes al objeto expenseTemp
                    finish();

                }
            }
        } );

    }

    public void loadFieldButtonCancelExpenses(){

        buttonCancelExpenses.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseTemp.removeExpenseTemp();
                finish();
            }
        } );
    }

    public void restartActivity(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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

        Resources resources = getResources();
        String methodPlayment[] = resources.getStringArray( R.array.methodPlayment );

        // Compruebo que no sea la primera posición. Ya que esta, es el mensaje de campo.
        if ( TextUtils.isEmpty( textForValidate ) || textForValidate.equals( methodPlayment[0] ) ){
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
     * Guarda objeto de tipo Expense en la DB
     */
    public void saveDatesFormForDB( Expense expense ){
        ControllerDBExpense controllerDBExpense = new ControllerDBExpense( getApplicationContext() );
        controllerDBExpense.setValue( expense );
    }

}
