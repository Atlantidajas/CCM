package com.jorge.app.ccm.ui.expenses;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jorge.app.ccm.R;

import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentDatePincker;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;

import com.jorge.app.ccm.models.Expense.Expense;
import com.jorge.app.ccm.models.Expense.ExpenseTemp;
import com.jorge.app.ccm.models.typeExpense.TypeExpense;
import com.jorge.app.ccm.models.vehicle.Vehicle;
import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;
import com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity;
import com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity;

import java.util.ArrayList;

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
    private EditText editTextDateExpenses;
    private Spinner spinnerMethodPlayment;
    private EditText editTextMethodplayment;
    private EditText editTextTotalImport;
    private Button buttonAcceptExpenses;
    private Button buttonCancelExpenses;
    private ExpenseTemp expenseTemp;

    private ArrayList<String> listRegistrationNumberVehiclesDb = new ArrayList<>(  );
    SharedPreferences temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_expenses_registry );

        editTextSelectVehicle = findViewById( R.id.editText_vehicle_expense_registry );
        editTextTypeExpense = findViewById( R.id.editText_type_expense_expense_registry );
        editTextTickectNumber = findViewById( R.id.editText_ticket_expense_registry  );
        editTextDateExpenses = findViewById( R.id.editText_date_expense_registry   );
        spinnerMethodPlayment = findViewById( R.id.spinnerMethodplayment  );
        editTextMethodplayment = findViewById( R.id.editTextMethodplayment );
        editTextTotalImport = findViewById( R.id.editTextTotalImport );


        buttonAcceptExpenses = findViewById( R.id.button_accept_expense_registry  );
        buttonCancelExpenses = findViewById( R.id.button_cancel_expense_registry  );

        //Fichero para guardar datos de forma temporal objetos tipo expensesVehicleForUserTemp
        expenseTemp = new ExpenseTemp( getApplicationContext(), TAG );


        //Intens
        intentCreateVehicle = new Intent( ExpensesActivity.this, RegistryVehiclesActivity.class );
        intentVehiclesSelectList = new Intent( ExpensesActivity.this, VehiclesSelectListActivity.class );
        intentTypeExpenses = new Intent( ExpensesActivity.this, TypeExpensesActivity.class );

        Expense expense = new Expense(  );

        loadFieldEditTextSelectVehicle();
        loadFieldEditTextTypeExpense();
        loadFieldEditTextTickectNumber();
        loadFieldEditTextMethodOfPlayment();
        loadFieldEditTextdateExpenses();
        loadFieldEditTextTotalImport();
        loadFieldButtonAcceptExpenses();
        loadFieldButtonCancelExpenses();


        editTextSelectVehicle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentVehiclesSelectList,1);
            }
        } );

        editTextTypeExpense.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentTypeExpenses,2);
            }
        } );

        editTextDateExpenses.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragmentDatePincker.show( getSupportFragmentManager(), TAG );
            }
        } );

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
               expenseTemp.setvehicle( vehicleSelect );

            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleLogo" +
                    "Valor que se guardará: " + expenseTemp.getLogoVehicle() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleRegistrationNumber" +
                    "Valor que se guardará: " + expenseTemp.getRegistrationNumber() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleBrand" +
                    "Valor que se guardará: " + expenseTemp.getBrand() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "getModel" +
                    "Valor que se guardará: " + expenseTemp.getModel() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDateITV" +
                    "Valor que se guardará: " + expenseTemp.getDateITV() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDriving" +
                    "Valor que se guardará: " + expenseTemp.getDriving() );

            loadFieldEditTextSelectVehicle();//Actualizo campo.
        }

        if (requestCode == 2 ){

            typeExpense = (TypeExpense) objetoRecibido.getSerializable(TYPE_EXPENSE);

            if ( typeExpense !=null )

                // Guardo los datos en fichero de forma temporar por si el usuario regresa o sale de la actividad para seleccionar Vehículo
                expenseTemp.setTypeName( typeExpense.getTypeName() );

            Log.i( TAG, "Indice con el que se guardará: " + expenseTemp.getKEY_TYPE_NAME() + "expenseTypeName" +
                    "Valor que se guardará: " + typeExpense.getTypeName() );

            loadFieldEditTextTypeExpense();//Actualizo campo.
        }

    }

    public void loadFieldEditTextSelectVehicle(){

        //Cargo los datos del fichero temporal.

        String registrationNumberVehicle = expenseTemp.getRegistrationNumber();

        //La primera vez que se carga la actividad typeExpense será nula,
        if ( registrationNumberVehicle == null) {
            registrationNumberVehicle = "";
        }
        else {
            editTextSelectVehicle.setText( registrationNumberVehicle );
        }

    }

    public void loadFieldEditTextTypeExpense(){

        //Cargo los datos del fichero temporal.
        String typeExpense = expenseTemp.getTypeName();

        //La primera vez que se carga la actividad typeExpense será nula,
        if ( typeExpense == null) {
            typeExpense = "";
        }
        else {
            editTextTypeExpense.setText( typeExpense );
        }

    }

    public void loadFieldEditTextTickectNumber(){
        //Cargo los datos del fichero temporal.
        String ticketNumber = expenseTemp.getTickectNumber();


        //La primera vez que se carga la actividad typeExpense será nula,
        if ( ticketNumber == null) {
            ticketNumber = "";
        }
        else {
            editTextTotalImport.setText( String.valueOf( ticketNumber ) );
        }
    }

    public void loadFieldEditTextdateExpenses(){

        DialogFragmentDatePincker dialogFragmentDatePincker = new DialogFragmentDatePincker();



        String dateTickect = dialogFragmentDatePincker.getDateFotmat( 1 );

        //La primera vez que se carga la actividad typeExpense será nula,
        if ( dateTickect == null) {
            dateTickect = "";
        }
        else {
            expenseTemp.setDateITV( dateTickect );
        }

        editTextDateExpenses.setText( expenseTemp.getDate() );



    }

    public void loadFieldEditTextMethodOfPlayment(){

        Resources res = getResources();
        final String[] methodPlayments = res.getStringArray( R.array.methodPlayment );
        spinnerMethodPlayment.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, methodPlayments ) );

        spinnerMethodPlayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                expenseTemp.setMethodOfPlayment( methodPlayments[pos]  );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

        //Cargo los datos del fichero temporal.
        String methodOfPlayment = expenseTemp.getMethodOfPlayment();


        //La primera vez que se carga la actividad typeExpense será nula,
        if ( methodOfPlayment == null) {
            methodOfPlayment = "";
        }
        else {
            editTextMethodplayment.setText( methodOfPlayment );
        }

    }

    public void loadFieldEditTextTotalImport(){
        //Cargo los datos del fichero temporal.
        float totalImpot = expenseTemp.getTotalExpense();


        //La primera vez que se carga la actividad typeExpense será nula,
        if ( totalImpot == 0) {
            totalImpot = 0;
        }
        else {
            editTextTotalImport.setText( String.valueOf( totalImpot ) );
        }
    }

    public void loadFieldButtonAcceptExpenses(){}

    public void loadFieldButtonCancelExpenses(){}

    public void windowNoticeCreateVehicle(){

        WindowDialogFragment windowCreateVehicle = new WindowDialogFragment( "No existe ningún vehículo. ¿Desea crearlo?" );

        windowCreateVehicle.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
            @Override
            public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {
                startActivity( intentCreateVehicle );
            }

            @Override
            public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                return;
            }
        } );
        windowCreateVehicle.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );
    }



}
