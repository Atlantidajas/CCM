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
import android.widget.Toast;

import com.jorge.app.ccm.R;

import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentDatePincker;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;

import com.jorge.app.ccm.models.Expenses;
import com.jorge.app.ccm.models.ExpensesVehicleForUser;
import com.jorge.app.ccm.models.TypeExpense;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;
import com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity;
import com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity;
import com.jorge.app.ccm.utils.DatesTemp;

import java.io.Serializable;
import java.util.ArrayList;

import static com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity.TYPE_EXPENSE;
import static com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity.VEHICLE_SELECT;

public class ExpensesActivity extends AppCompatActivity{

    private String TAG = "ExpenseActivity";
    private final String DATES_TEMP_FILE_NAME = "temp" + TAG; //<-- Nombre del fichero con los datos temporales
    private Intent intentCreateVehicle;
    private Intent intentVehiclesSelectList;
    private Intent intentTypeExpenses;
    private Spinner spinner;
    private EditText editTextSelectVehicle;
    private EditText editTextTypeExpense;
    private EditText editTextTickectExpenses;
    private EditText editTextDateExpenses;
    private EditText editTextMethodOfPlayment;
    private EditText editTextTotal;
    private Button buttonAcceptExpenses;
    private Button buttonCancelExpenses;
    private DatesTemp tempExpensesActivity;

    private ArrayList<String> listRegistrationNumberVehiclesDb = new ArrayList<>(  );
    SharedPreferences temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_expenses_registry );

        editTextSelectVehicle = findViewById( R.id.editText_vehicle_expense_registry );
        editTextTypeExpense = findViewById( R.id.editText_type_expense_expense_registry );
        editTextTickectExpenses = findViewById( R.id.editText_ticket_expense_registry  );
        editTextDateExpenses = findViewById( R.id.editText_date_expense_registry   );
        editTextMethodOfPlayment = findViewById( R.id.etDescription  );
        spinner = findViewById( R.id.spinner  );

        buttonAcceptExpenses = findViewById( R.id.button_accept_expense_registry  );
        buttonCancelExpenses = findViewById( R.id.button_cancel_expense_registry  );

        //Fichero para guardar datos de forma temporal
        tempExpensesActivity = new DatesTemp(  getApplicationContext(), DATES_TEMP_FILE_NAME  );

        intentCreateVehicle = new Intent( ExpensesActivity.this, RegistryVehiclesActivity.class );
        intentVehiclesSelectList = new Intent( ExpensesActivity.this, VehiclesSelectListActivity.class );
        intentTypeExpenses = new Intent( ExpensesActivity.this, TypeExpensesActivity.class );

        ExpensesVehicleForUser expensesVehicleForUser = new ExpensesVehicleForUser(  );



        loadFieldEditTextSelectVehicle();
        loadFieldEditTextTypeExpense();
        loadFieldEditTextdateExpenses();
        loadFieldEditTextMethodOfPlayment();
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle objetoRecibido = data.getExtras();
        Vehicle vehicleSelect = null;
        TypeExpense typeExpense = null;

        if (requestCode == 1 ){

            vehicleSelect = (Vehicle) objetoRecibido.getSerializable(VEHICLE_SELECT);

            if ( vehicleSelect !=null )

                tempExpensesActivity.setDateInt( VEHICLE_SELECT + "expenseVehicleLogo", vehicleSelect.getLogoVehicle() );
                tempExpensesActivity.setDateString( VEHICLE_SELECT + "expenseVehicleRegistrationNumber", vehicleSelect.getRegistrationNumber() );
                tempExpensesActivity.setDateString( VEHICLE_SELECT + "expenseVehicleBrand", vehicleSelect.getBrand() );
                tempExpensesActivity.setDateString( VEHICLE_SELECT + "expenseVehicleModel", vehicleSelect.getModel() );
                tempExpensesActivity.setDateString( VEHICLE_SELECT + "expenseVehicleDateITV", vehicleSelect.getDateITV() );
                tempExpensesActivity.setDateInt( VEHICLE_SELECT + "expenseVehicleDriving", vehicleSelect.getDriving() );
                tempExpensesActivity.setDateString( VEHICLE_SELECT + "expenseVehicleDrivingCurrent", vehicleSelect.getDrivingCurrent() );

            Log.i( TAG, "Indice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleLogo" +
                    "Valor que se guardará: " + vehicleSelect.getLogoVehicle() );
            Log.i( TAG, "Indice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleRegistrationNumber" +
                    "Valor que se guardará: " + vehicleSelect.getRegistrationNumber() );
            Log.i( TAG, "Indice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleBrand" +
                    "Valor que se guardará: " + vehicleSelect.getBrand() );
            Log.i( TAG, "Indice con el que se guardará: " + VEHICLE_SELECT + "getModel" +
                    "Valor que se guardará: " + vehicleSelect.getModel() );
            Log.i( TAG, "Indice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDateITV" +
                    "Valor que se guardará: " + vehicleSelect.getDateITV() );
            Log.i( TAG, "Indice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDriving" +
                    "Valor que se guardará: " + vehicleSelect.getDriving() );
            Log.i( TAG, "Indice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDrivingCurrent" +
                    "Valor que se guardará: " + vehicleSelect.getDrivingCurrent() );

            loadFieldEditTextSelectVehicle();//Actualizo campo.
        }

        if (requestCode == 2 ){

            typeExpense = (TypeExpense) objetoRecibido.getSerializable(TYPE_EXPENSE);

            if ( typeExpense !=null )

                tempExpensesActivity.setDateString( TYPE_EXPENSE + "expenseTypeName", typeExpense.getTypeName() );

            Log.i( TAG, "Indice con el que se guardará: " + TYPE_EXPENSE + "expenseTypeName" +
                    "Valor que se guardará: " + typeExpense.getTypeName() );

            loadFieldEditTextTypeExpense();//Actualizo campo.
        }

    }
    public void loadFieldEditTextSelectVehicle(){

        //Cargo los datos del fichero temporal.
        String registrationNumberVehicle = tempExpensesActivity.getDateString( VEHICLE_SELECT + "expenseVehicleRegistrationNumber"  );

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
        String typeExpense = tempExpensesActivity.getDateString( TYPE_EXPENSE + "expenseTypeName"  );

        //La primera vez que se carga la actividad typeExpense será nula,
        if ( typeExpense == null) {
            typeExpense = "";
        }
        else {
            editTextTypeExpense.setText( typeExpense );
        }

    }

    public void loadFieldEditTextdateExpenses(){

        final DialogFragmentDatePincker dateExpensesDP = new DialogFragmentDatePincker();

        editTextDateExpenses.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateExpensesDP.show(getSupportFragmentManager(), TAG );
                editTextDateExpenses.setText( dateExpensesDP.getDateFotmat( 1 ) );
            }
        } );
    }

    public void loadFieldEditTextMethodOfPlayment(){

        Resources res = getResources();
        final String[] methodPlayments = res.getStringArray(R.array.methodPlayment);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, methodPlayments));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                Toast.makeText(adapterView.getContext(),
                        (String) adapterView.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

        //editTextMethodOfPlayment.setText( spinnerMethodPlayment.getItemResult() );

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
