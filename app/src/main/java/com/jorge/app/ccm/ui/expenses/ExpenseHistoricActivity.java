package com.jorge.app.ccm.ui.expenses;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.models.Expense;
import com.jorge.app.ccm.ui.sessionStatus.SessionStatusActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class ExpenseHistoricActivity extends AppCompatActivity {

    private final String TAG = "ExpenseHistoricActivity";
    private AdapterExpenseHistoric adapterExpenseHistoric;
    private Intent intentSessionStatus;
    private Intent intentForRegistryExpense;
    private TextView textView;
    private ListView listView;
    private ArrayList<Expense> expenses;
    public static final String EXPENSE_HISTORIC = "Expense";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_expense_historic );
        textView = findViewById(R.id.textView_expense_historic);
        listView = findViewById(R.id.listView_expense_historic);

        intentSessionStatus  = new Intent( ExpenseHistoricActivity.this, SessionStatusActivity.class );
        intentForRegistryExpense = new Intent( ExpenseHistoricActivity.this, ExpensesResgistryActivity.class );

        //Inizializao Adapter para mostrar lista de gastos
        adapterExpenseHistoric = new AdapterExpenseHistoric( getApplication(), textView, listView);
        expenses = new ArrayList<>(  );
        expenses = adapterExpenseHistoric.getListIntemExpense();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.expense_historic_list, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.resgistreExpense ) {
            startActivity(intentForRegistryExpense);
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }


    @Override
    public void onStart() {
        super.onStart();
        onclickItemList();
    }

    public void onclickItemList(){
        // Creo el listener para cuando se hace click en un item de la lista.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lst, View viewRow,
                                    final int position, long id) {

                WindowDialogFragment windowNotice = new WindowDialogFragment( R.string.windowNoticeSesionHistoricActivity );

                windowNotice.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                    @Override
                    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                        Log.i( TAG, expenses.get( position ).getVehicleRegistrationNumber() );
                        Log.i( TAG, expenses.get( position ).getVehicleRegistrationNumber() );

                        Expense expenseSelect = expenses.get( position );

                        intentSessionStatus.putExtra(EXPENSE_HISTORIC, (Serializable) expenseSelect );
                        startActivity(intentSessionStatus);

                    }

                    @Override
                    public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog) {
                        return;
                    }
                } );

                windowNotice.getDialogFragmentNotice().show( getSupportFragmentManager(), TAG );
            }
        });
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
