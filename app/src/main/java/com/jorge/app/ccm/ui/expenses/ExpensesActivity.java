package com.jorge.app.ccm.ui.expenses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.jorge.app.ccm.R;

import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;

import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;
import com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity;
import java.util.ArrayList;


import static com.jorge.app.ccm.ui.vehiclesSelect.VehiclesSelectListActivity.REGISTRATION_NUMBER_VEHICLE_SELECT;

public class ExpensesActivity extends AppCompatActivity {

    private String TAG = "ExpenseActivity";
    private Intent intentCreateVehicle;
    private Intent intentVehiclesSelectList;
    private EditText editTextSelectVehicle;
    private EditText editTextTypeExpense;
    private EditText editTextTickectExpenses;
    private EditText dateExpenses;
    private EditText methodOfPlayment;
    private Button buttonAcceptExpenses;
    private Button buttonCancelExpenses;
    private ArrayList<String> listRegistrationNumberVehiclesDb = new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_expenses_registry );

        editTextSelectVehicle = findViewById( R.id.editText_vehicle_expense_registry );
        editTextTypeExpense = findViewById( R.id.editText_type_expense_expense_registry );
        editTextTickectExpenses = findViewById( R.id.editText_ticket_expense_registry  );
        dateExpenses = findViewById( R.id.editText_date_expense_registry   );
        methodOfPlayment = findViewById( R.id.editText_method_of_payment_expense_registry  );
        buttonAcceptExpenses = findViewById( R.id.button_accept_expense_registry  );
        buttonCancelExpenses = findViewById( R.id.button_cancel_expense_registry  );

        intentCreateVehicle = new Intent( ExpensesActivity.this, RegistryVehiclesActivity.class );
        intentVehiclesSelectList = new Intent( ExpensesActivity.this, VehiclesSelectListActivity.class );

        String registrationNumberVehicle = getIntent().getStringExtra(REGISTRATION_NUMBER_VEHICLE_SELECT);//<-- Matrícula regresada desde VehiclesSelectListActivity
        loadFieldEditTextSelectVehicle( registrationNumberVehicle );
        loadFieldEditTextTypeExpense();
        loadFieldEditTextTickectExpenses();
        loadFieldEditTextdateExpenses();
        loadFieldEditTextmethodOfPlayment();
        loadFieldButtonAcceptExpenses();
        loadFieldButtonCancelExpenses();

    }

    public void loadFieldEditTextSelectVehicle( String registrationNumberVehicle ){

        //La primera vez que se carga la actividad registrationNumberVehicle será nula,
        if (registrationNumberVehicle == null) {
            registrationNumberVehicle = "";
        } else {
            editTextSelectVehicle.setText( registrationNumberVehicle );
        }

        editTextSelectVehicle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Envío al usuario a seleccionar un vehículo de la lista disponible
                startActivity( intentVehiclesSelectList );
                //editTextModel.requestFocus();//<-- Mando el foco a la siguiente posición

            }
        } );
    }

    public void loadFieldEditTextTypeExpense(){}
    public void loadFieldEditTextTickectExpenses(){}
    public void loadFieldEditTextdateExpenses(){}
    public void loadFieldEditTextmethodOfPlayment(){}
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
