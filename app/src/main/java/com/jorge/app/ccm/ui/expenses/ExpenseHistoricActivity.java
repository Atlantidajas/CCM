package com.jorge.app.ccm.ui.expenses;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.models.Expense;
import com.jorge.app.ccm.models.Tickect;
import com.jorge.app.ccm.models.TypeExpense;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.Vehicle;


import java.io.Serializable;
import java.util.ArrayList;

public class ExpenseHistoricActivity extends AppCompatActivity{

    private final String TAG = "ExpenseHistoricActivity";
    private AdapterExpenseHistoric adapterExpenseHistoric;
    private Intent intentExpenseEspecific;
    private Intent intentForRegistryExpense;
    private LinearLayout linearLayout;
    private ListView listView;
    private ArrayList<Expense> expenses;
    public static final String EXPENSE_SELECT_TICKET = "com.jorge.app.ccm.ui.expense.ExpenseHistoricActivity.EXPENSE_SELECT_TICKET";
    public static final String EXPENSE_SELECT_TYPE_EXPENSE = "com.jorge.app.ccm.ui.expense.ExpenseHistoricActivity.EXPENSE_SELECT_TYPE_EXPENSE";
    public static final String EXPENSE_SELECT_VEHICLE = "com.jorge.app.ccm.ui.expense.ExpenseHistoricActivity.EXPENSE_SELECT_VEHICLE";
    static final int EXPENSE_SELECT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_expense_historic );
        linearLayout = findViewById(R.id.textView_expense_historic);
        listView = findViewById(R.id.listView_expense_historic);

        intentExpenseEspecific  = new Intent( ExpenseHistoricActivity.this, ExpenseEspecificActivity.class );
        intentForRegistryExpense = new Intent( ExpenseHistoricActivity.this, ExpensesResgistryActivity.class );

        //Inizializao Adapter para mostrar lista de gastos
        adapterExpenseHistoric = new AdapterExpenseHistoric( getApplication(), linearLayout, listView);
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

                WindowDialogFragment windowNotice = new WindowDialogFragment( R.string.windowNoticeExpenseHistoricActivity );

                windowNotice.getDialogFragmentNotice().setListener( new DialogFragmentNotice.DialogNoticeListerner() {
                    @Override
                    public void onDialogFragmentNoticePositiveClick(DialogFragment dialog) {

                        Tickect expenseSelectTickect = expenses.get( position ).getTickect();
                        TypeExpense expenseSelectTypeExpense = expenses.get( position ).getTypeExpense();
                        Vehicle expenseSelectTypeVehicle = expenses.get( position ).getVehicle();

                        Bundle bundle= new Bundle();
                        bundle.putSerializable( EXPENSE_SELECT_TICKET, (Serializable) expenseSelectTickect );
                        bundle.putSerializable( EXPENSE_SELECT_TYPE_EXPENSE, (Serializable) expenseSelectTypeExpense );
                        bundle.putSerializable( EXPENSE_SELECT_VEHICLE, (Serializable) expenseSelectTypeVehicle );

                        intentExpenseEspecific.putExtras( bundle );
                        setResult( RESULT_OK, intentExpenseEspecific );
                        startActivityForResult( intentExpenseEspecific, EXPENSE_SELECT_REQUEST );
                        finish();

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
