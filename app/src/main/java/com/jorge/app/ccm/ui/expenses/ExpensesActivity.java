package com.jorge.app.ccm.ui.expenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jorge.app.ccm.R;

import com.jorge.app.ccm.gadget.GadgetSpinner;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentDatePincker;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;

import com.jorge.app.ccm.gadget.notices.DialogFragmentSpinner;
import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;
import com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity;
import com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity;
import java.util.ArrayList;

import static com.jorge.app.ccm.ui.typeExpenses.TypeExpensesActivity.TYPE_EXPENSE;
import static com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity.REGISTRATION_NUMBER_VEHICLE_SELECT;

public class ExpensesActivity extends AppCompatActivity {

    private String TAG = "ExpenseActivity";
    private Intent intentCreateVehicle;
    private Intent intentVehiclesSelectList;
    private Intent intentTypeExpenses;
    private EditText editTextSelectVehicle;
    private EditText editTextTypeExpense;
    private EditText editTextTickectExpenses;
    private EditText editTextDateExpenses;
    private EditText editTextMethodOfPlayment;
    private EditText editTextTotal;
    private Button buttonAcceptExpenses;
    private Button buttonCancelExpenses;
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
        editTextMethodOfPlayment = findViewById( R.id.editText_method_of_payment_expense_registry  );
        buttonAcceptExpenses = findViewById( R.id.button_accept_expense_registry  );
        buttonCancelExpenses = findViewById( R.id.button_cancel_expense_registry  );
//        GadgetSpinner spinnerMethodPlayment = new GadgetSpinner( getSupportFragmentManager(),  )

        intentCreateVehicle = new Intent( ExpensesActivity.this, RegistryVehiclesActivity.class );
        intentVehiclesSelectList = new Intent( ExpensesActivity.this, VehiclesSelectListActivity.class );
        intentTypeExpenses = new Intent( ExpensesActivity.this, TypeExpensesActivity.class );
        temp = getApplicationContext().getSharedPreferences("tempDates", MODE_PRIVATE);


        String registrationNumberVehicle = getIntent().getStringExtra(REGISTRATION_NUMBER_VEHICLE_SELECT);//<-- Matrícula regresada desde VehiclesSelectListActivity
        String typeExpense = getIntent().getStringExtra(TYPE_EXPENSE);//<-- Matrícula regresada desde VehiclesSelectListActivity

        loadFieldEditTextSelectVehicle( registrationNumberVehicle );
        loadFieldEditTextTypeExpense( typeExpense );
        loadFieldEditTextdateExpenses();
        loadFieldEditTextMethodOfPlayment();
        loadFieldButtonAcceptExpenses();
        loadFieldButtonCancelExpenses();


    }

    public void loadFieldEditTextSelectVehicle( String registrationNumberVehicle ){

        //La primera vez que se carga la actividad registrationNumberVehicle será nula,


        SharedPreferences.Editor editor;
        editor = temp.edit();
        editor.putString("a", registrationNumberVehicle);
        editor.commit();


        String resultTempDate = temp.getString("a", "");


        editTextSelectVehicle.setText( resultTempDate );

        editTextSelectVehicle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Envío al usuario a seleccionar un vehículo de la lista disponible
                startActivity( intentVehiclesSelectList );
                //editTextModel.requestFocus();//<-- Mando el foco a la siguiente posición

            }
        } );
    }

    public void loadFieldEditTextTypeExpense( String typeExpense ){

        //La primera vez que se carga la actividad typeExpense será nula,
        if ( typeExpense == null) {
            typeExpense = "";
        } else {
            editTextTypeExpense.setText( typeExpense );
        }

        //La primera vez que se carga la actividad typeExpense será nula,
        if ( typeExpense == null) {
            typeExpense = "";
        } else {
            editTextTypeExpense.setText( typeExpense );
        }
        editTextTypeExpense.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( intentTypeExpenses );
            }
        } );
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
        String[] methodPlayments = res.getStringArray(R.array.methodPlayment);
        GadgetSpinner spinnerMethodPlayment = new GadgetSpinner( getSupportFragmentManager(), R.string.spiner_title_records_vehicles,
                methodPlayments,
                TAG);
        spinnerMethodPlayment.getDialogFragmentSpinner().getItemResult();

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
