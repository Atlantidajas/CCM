package com.jorge.app.ccm.ui.expenses;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
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
import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;
import com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity;
import com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity;

import static com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity.TYPE_EXPENSE;
import static com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity.VEHICLE_SELECT;

public class ExpensesActivity extends AppCompatActivity{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_expenses_registry );

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
        intentCreateVehicle = new Intent( ExpensesActivity.this, RegistryVehiclesActivity.class );
        intentVehiclesSelectList = new Intent( ExpensesActivity.this, VehiclesSelectListActivity.class );
        intentTypeExpenses = new Intent( ExpensesActivity.this, TypeExpensesActivity.class );

        final Expense expense = new Expense( );

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

    /**
     * Guardo comprueba la correcta recepción de los datos recibidos por medio de Items
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle objetoRecibido = data.getExtras();
        Vehicle vehicleSelect = null;
        TypeExpense typeExpense = null;

        if (requestCode == 1 ){

            vehicleSelect = (Vehicle) objetoRecibido.getSerializable(VEHICLE_SELECT);

            if ( vehicleSelect !=null )
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

            loadFieldEditTextRegistrationNumber();//Actualizo campo.
        }

        if (requestCode == 2 ){

            typeExpense = (TypeExpense) objetoRecibido.getSerializable(TYPE_EXPENSE);

            if ( typeExpense !=null )

                // Guardo los datos en fichero de forma temporar por si el usuario regresa o sale de la actividad para seleccionar Vehículo
                expenseTemp.setTypeExpenseName( typeExpense.getTypeExpenseName() );

            loadFieldEditTextTypeExpense();//Actualizo campo.
        }

    }

    public void loadFieldEditTextRegistrationNumber(){

        //Los datos temporales son grabados a la recepción del Inten en la función -> onActivityResult

        editTextSelectVehicle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentVehiclesSelectList,1);
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
                startActivityForResult(intentTypeExpenses,2);
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

        newFragment.show(getSupportFragmentManager(), "datePicker");
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
                        validateFieldTypeEditText( editTextDateExpenses ) &&
                        validateFieldTypeSpinner( spinnerMethodplayment ) &&
                        validateFieldTypeEditText( editTextTotalImport ) ){

                    Expense expenseVehicle = new Expense( expenseTemp.getVehicleTemp() );

                    System.out.println( "--------------------------------------------------------------------------------------------------" );
                    System.out.println(  String.valueOf( expenseVehicle.getVehiclelogo() ) );
                    System.out.println( expenseVehicle.getVehicleRegistrationNumber() );
                    System.out.println( expenseVehicle.getVehicleBrand() );
                    System.out.println( expenseVehicle.getVehicleModel() );
                    System.out.println( expenseVehicle.getVehicleDateITV() );
                    System.out.println( String.valueOf( expenseVehicle.getVehicleDriving() ) );
                    System.out.println( "--------------------------------------------------------------------------------------------------" );

                    expenseTemp.getTypeExpenseTemp();

                    Expense expenseType = new Expense( expenseTemp.getTypeExpenseTemp() );
                    System.out.println( "--------------------------------------------------------------------------------------------------" );

                    System.out.println( expenseTemp.getTypeExpenseLogo() );
                    System.out.println( expenseTemp.getTypeExpenseName() );
                    System.out.println( expenseType.getTypeExpenseLogo() );
                    System.out.println( expenseType.getTypeExpenseName() );
                    System.out.println( "--------------------------------------------------------------------------------------------------" );

                    System.out.println( "--------------------------------------------------------------------------------------------------" );
                    Expense expenseTickect = new Expense( expenseTemp.getTicketTemp() );
                    System.out.println( expenseTemp.getTypeExpenseLogo() );
                    System.out.println( expenseTemp.getTypeExpenseName() );
                    System.out.println( expenseTickect.getTickectTotalExpense() );
                    System.out.println( expenseTickect.getProviderName() );
                    System.out.println( "--------------------------------------------------------------------------------------------------" );








                   // saveDatesFormForDB( expense );//<-- Guarda db
                   // expenseTemp.removeTypeExpense();//<-- Borro datos del fichero temporal correspondientes al objeto expenseTemp
                   // finish();

                }
            }
        } );

    }

    public void loadFieldButtonCancelExpenses(){

        buttonCancelExpenses.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseTemp.removeExpenseTemp();
                restartActivity();
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
            editText.setBackgroundColor(Color.MAGENTA);

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
            spinner.setBackgroundColor(Color.MAGENTA);

            //Posiciono el foco sobre el editText
            spinner.requestFocus();

            Toast.makeText(this, "Ha dejado el campo vacio", Toast.LENGTH_LONG).show();

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
