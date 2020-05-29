package com.jorge.app.ccm.ui.typeExpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.models.TypeExpense;
import com.jorge.app.ccm.ui.expenses.ExpensesResgistryActivity;

public class TypeExpensesActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "TypeExpensesActivity";
    private Intent intentForExpensesActivity;
    private TextView textViewTitle;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private EditText editTextTypeExpenses;
    private Button buttonNext;
    public static final String TYPE_EXPENSE = "com.jorge.app.ccm.ui.typeExpenses.TypeExpenses.TYPE_EXPENSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_type_expenses );

        intentForExpensesActivity = new Intent( TypeExpensesActivity.this, ExpensesResgistryActivity.class );

        textViewTitle = findViewById( R.id.textView_1_activity_type_expenses );
        button1 = findViewById( R.id.button_1_activity_type_expenses );
        button2 = findViewById( R.id.button_2_activity_type_expenses );
        button3 = findViewById( R.id.button_3_activity_type_expenses);
        button4 = findViewById( R.id.button_4_activity_type_expenses );
        button5 = findViewById( R.id.button_5_activity_type_expenses );
        button6 = findViewById( R.id.button_6_activity_type_expenses );
        button7 = findViewById( R.id.button_7_activity_type_expenses );
        button8 = findViewById( R.id.button_8_activity_type_expenses );
        button9 = findViewById( R.id.button_9_activity_type_expenses );
        button10 = findViewById( R.id.button_10_activity_type_expenses );
        button11 = findViewById( R.id.button_11_activity_type_expenses );
        button12 = findViewById( R.id.button_12_activity_type_expenses );
        editTextTypeExpenses = findViewById( R.id.editText_activity_type_expenses );
        buttonNext = findViewById( R.id.button_next_activity_type_expenses );

        button1.setOnClickListener( this );
        button2.setOnClickListener( this );
        button3.setOnClickListener( this );
        button4.setOnClickListener( this );
        button5.setOnClickListener( this );
        button6.setOnClickListener( this );
        button7.setOnClickListener( this );
        button8.setOnClickListener( this );
        button9.setOnClickListener( this );
        button10.setOnClickListener( this );
        button11.setOnClickListener( this );
        button12.setOnClickListener( this );
        buttonNext.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_1_activity_type_expenses:
                effectsVisibleAndInvisibleField( button1.getText().toString() );
                break;

            case R.id.button_2_activity_type_expenses:
                effectsVisibleAndInvisibleField( button2.getText().toString() );
                break;

            case R.id.button_3_activity_type_expenses:
                effectsVisibleAndInvisibleField( button3.getText().toString() );
                break;

            case R.id.button_4_activity_type_expenses:
                effectsVisibleAndInvisibleField( button4.getText().toString() );
                break;

            case R.id.button_5_activity_type_expenses:
                effectsVisibleAndInvisibleField( button5.getText().toString() );
                break;

            case R.id.button_6_activity_type_expenses:
                effectsVisibleAndInvisibleField( button6.getText().toString() );
                break;

            case R.id.button_7_activity_type_expenses:
                //El usuario será el que lo cumplimente.
                effectsVisibleAndInvisibleField( "" );
                break;

            case R.id.button_8_activity_type_expenses:
                effectsVisibleAndInvisibleField( button8.getText().toString() );
                break;

            case R.id.button_9_activity_type_expenses:
                effectsVisibleAndInvisibleField( button9.getText().toString() );
                break;

            case R.id.button_10_activity_type_expenses:
                effectsVisibleAndInvisibleField( button10.getText().toString() );
                break;

            case R.id.button_11_activity_type_expenses:
                effectsVisibleAndInvisibleField( button11.getText().toString() );
                break;

            case R.id.button_12_activity_type_expenses:
                effectsVisibleAndInvisibleField( button12.getText().toString() );
                break;

            case R.id.button_next_activity_type_expenses:

                if( editTextTypeExpenses.getText().toString().equals( "" ) ){
                    Toast.makeText( getApplicationContext(), "Debe cumplimentar el campo para seguir.", Toast.LENGTH_SHORT ).show();
                    //editTextTypeExpenses.requestFocus();
                }else {

                    TypeExpense typeExpenseSelect = new TypeExpense();
                    typeExpenseSelect.setTypeExpenseName( editTextTypeExpenses.getText().toString() );
                    typeExpenseSelect.setTypeExpenseLogo( 0 );
                    Log.i( TAG, "editText -> (Texto) ->" + editTextTypeExpenses.getText().toString() );
                    Bundle bundle= new Bundle();
                    bundle.putSerializable( TYPE_EXPENSE, typeExpenseSelect );

                    intentForExpensesActivity.putExtras(bundle);
                    setResult( RESULT_OK, intentForExpensesActivity );
                    finish();
                }
                break;
        }
    }

    public void effectsVisibleAndInvisibleField( String text ){

        //Muestro en pantalla editTexta y botón de continuar
        if ( ( editTextTypeExpenses.getVisibility() == View.INVISIBLE ) && ( buttonNext.getVisibility() == View.INVISIBLE )){

            editTextTypeExpenses.setVisibility( View.VISIBLE );
            buttonNext.setVisibility( View.VISIBLE );

        }

        //Oculto textView
        if( textViewTitle.getVisibility() == View.VISIBLE ){
            textViewTitle.setVisibility( View.INVISIBLE );
        }
        editTextTypeExpenses.setText( text );

    }
}
