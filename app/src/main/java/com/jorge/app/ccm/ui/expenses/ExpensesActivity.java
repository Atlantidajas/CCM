package com.jorge.app.ccm.ui.expenses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.gadget.notices.DialogFragmentSpinner;
import com.jorge.app.ccm.models.Vehicle;
import com.jorge.app.ccm.ui.VehicleCu.RegistryVehiclesActivity;

import java.util.ArrayList;
import java.util.Iterator;

public class ExpensesActivity extends AppCompatActivity {

    private String TAG = "ExpenseActivity";
    private Intent intentCreateVehicle;
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

        loadVehiclesDB();

        loadFieldEditTextSelectVehicle();
        loadFieldEditTextTypeExpense();
        loadFieldEditTextTickectExpenses();
        loadFieldEditTextdateExpenses();
        loadFieldEditTextmethodOfPlayment();
        loadFieldButtonAcceptExpenses();
        loadFieldButtonCancelExpenses();

    }

    public void loadFieldEditTextSelectVehicle(){

        editTextSelectVehicle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( listRegistrationNumberVehiclesDb.size() == 0) {
                    windowNoticeCreateVehicle();
                } else {
                    DialogFragmentSpinner spinnerVehicles = new DialogFragmentSpinner(
                            R.string.spiner_title_records_vehicles,
                            listRegistrationNumberVehiclesDb,
                            R.string.spiner_buttom_next_records_vehicles_expense_registry,
                            R.string.spiner_buttom_neutral_records_vehicles_expense_registry,
                            R.string.spiner_buttom_return_records_vehicles_expense_registry,
                            true );

                    spinnerVehicles.show( getSupportFragmentManager(), TAG );
                    //editTextModel.requestFocus();//<-- Mando el foco a la siguiente posición
                }
            }
        } );
    }

    public void loadFieldEditTextTypeExpense(){}
    public void loadFieldEditTextTickectExpenses(){}
    public void loadFieldEditTextdateExpenses(){}
    public void loadFieldEditTextmethodOfPlayment(){}
    public void loadFieldButtonAcceptExpenses(){}
    public void loadFieldButtonCancelExpenses(){}

    public void loadVehiclesDB(){

        ControllerDBStatus controllerDBStatus = new ControllerDBStatus( getApplicationContext() );

        controllerDBStatus.getDatabaseReference().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ( dataSnapshot.exists() ){

                    Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();

                    do{

                        listRegistrationNumberVehiclesDb.add( new Vehicle( dataSnapshots.next() ).getRegistrationNumber() );
                        Log.i( TAG, "loadVehicleDB -> matrícula indice 0 ->"+ listRegistrationNumberVehiclesDb.get( 0 ) );

                    }while (dataSnapshots.hasNext());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

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
