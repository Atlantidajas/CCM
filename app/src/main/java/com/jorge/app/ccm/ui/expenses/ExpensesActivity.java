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

import com.jorge.app.ccm.models.ExpensesVehicleForUser;
import com.jorge.app.ccm.models.typeExpense.TypeExpense;
import com.jorge.app.ccm.models.typeExpense.TypeExpenseTemp;
import com.jorge.app.ccm.models.vehicle.Vehicle;
import com.jorge.app.ccm.models.vehicle.VehicleTemp;
import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;
import com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity;
import com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity;
import com.jorge.app.ccm.utils.DatesTemp;

import java.util.ArrayList;

import static com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity.TYPE_EXPENSE;
import static com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity.VEHICLE_SELECT;

public class ExpensesActivity extends AppCompatActivity{

    private String TAG = "ExpenseActivity";
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
    private TypeExpenseTemp typeExpenseTemp;
    private VehicleTemp vehicleTemp;

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

        //Fichero para guardar datos de forma temporal de objetos tipo TypeExpense
        typeExpenseTemp = new TypeExpenseTemp( getApplicationContext(), TAG );

        //Fichero para guardar datos de forma temporal de objetos tipo Vehicle
        vehicleTemp = new VehicleTemp( getApplicationContext(), TAG );

        //Intens
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
                // Guardo los datos en fichero de forma temporar por si el usuario regresa o sale de la actividad para seleccionar typeExpense
                vehicleTemp.setvehicle( vehicleSelect );

            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleLogo" +
                    "Valor que se guardará: " + vehicleTemp.getLogoVehicle() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleRegistrationNumber" +
                    "Valor que se guardará: " + vehicleTemp.getRegistrationNumber() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleBrand" +
                    "Valor que se guardará: " + vehicleTemp.getBrand() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "getModel" +
                    "Valor que se guardará: " + vehicleTemp.getModel() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDateITV" +
                    "Valor que se guardará: " + vehicleTemp.getDateITV() );
            Log.i( TAG, "Índice con el que se guardará: " + VEHICLE_SELECT + "expenseVehicleDriving" +
                    "Valor que se guardará: " + vehicleTemp.getDriving() );

            loadFieldEditTextSelectVehicle();//Actualizo campo.
        }

        if (requestCode == 2 ){

            typeExpense = (TypeExpense) objetoRecibido.getSerializable(TYPE_EXPENSE);

            if ( typeExpense !=null )

                // Guardo los datos en fichero de forma temporar por si el usuario regresa o sale de la actividad para seleccionar Vehículo
                typeExpenseTemp.setTypeName( typeExpense.getTypeName() );

            Log.i( TAG, "Indice con el que se guardará: " + typeExpenseTemp.getKEY_TYPE_NAME() + "expenseTypeName" +
                    "Valor que se guardará: " + typeExpense.getTypeName() );

            loadFieldEditTextTypeExpense();//Actualizo campo.
        }

    }
    public void loadFieldEditTextSelectVehicle(){

        //Cargo los datos del fichero temporal.

        String registrationNumberVehicle = vehicleTemp.getRegistrationNumber();

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
        String typeExpense = typeExpenseTemp.getTypeName();

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
