package com.jorge.app.ccm.ui.expenses;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.models.Tickect;
import com.jorge.app.ccm.models.TypeExpense;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.Vehicle;

import java.io.Serializable;

import static com.jorge.app.ccm.ui.expenses.ExpenseHistoricActivity.EXPENSE_SELECT_TICKET;
import static com.jorge.app.ccm.ui.expenses.ExpenseHistoricActivity.EXPENSE_SELECT_TYPE_EXPENSE;
import static com.jorge.app.ccm.ui.expenses.ExpenseHistoricActivity.EXPENSE_SELECT_VEHICLE;
import static com.jorge.app.ccm.ui.expenses.ExpenseHistoricActivity.EXPENSE_SELECT_REQUEST;

public class ExpenseEspecificActivity extends AppCompatActivity implements Serializable {

    private String TAG = "ExpenseEspecificActivity";
    //[1]
    private LinearLayout layoutNumberTickect;
    private ImageView imageViewNumberTickect;
    private TextView textViewNumberTickect;
    //[2]
    private LinearLayout layoutDateTickect;
    private ImageView imageViewDateTickect;
    private TextView textViewDateTickect;
    //[3]
    private LinearLayout layoutTotalExpenseTickect;
    private ImageView imageViewTotalExpenseTickect;
    private TextView textViewTotalExpenseTickect;
    //[4]
    private LinearLayout layoutProviderNameProvider;
    private ImageView imageViewProviderNameProvider;
    private TextView textViewProviderNameProvider;
    //[5]
    private LinearLayout layoutProviderCifNifProvider;
    private ImageView imageViewProviderCifNifProvider;
    private TextView textViewProviderCifNifProvider;
    //[6]
    private LinearLayout layoutProviderTelephoneProvider;
    private ImageView imageViewProviderTelephoneProvider;
    private TextView textViewProviderTelephoneProvider;
    //[7]
    private LinearLayout layoutMethodOfPlaymentNamePlayment;
    private ImageView imageViewMethodOfPlaymentNamePlayment;
    private TextView textViewMethodOfPlaymentNamePlayment;
    //[8]
    private LinearLayout layoutTypeExpensename;
    private ImageView imageViewTypeExpensename;
    private TextView textViewTypeExpensename;
    //[9]
    private LinearLayout layoutUserNameUser;
    private ImageView imageViewUserNameUser;
    private TextView textViewUserNameUser;
    //[10]
    private LinearLayout layoutUserEmailUser;
    private ImageView imageViewUserEmailUser;
    private TextView textViewUserEmailUser;
    //[11]
    private LinearLayout layoutUserTelephoneNumberUser;
    private ImageView imageViewUserTelephoneNumberUser;
    private TextView textViewUserTelephoneNumberUser;
    //[12]
    private LinearLayout layoutVehicleRegistrationNumberVehicle;
    private ImageView imageViewVehicleRegistrationNumberVehicle;
    private TextView textViewVehicleRegistrationNumberVehicle;
    //[13]
    private LinearLayout layoutVehicleBrandVehicle;
    private ImageView imageViewVehicleBrandVehicle;
    private TextView textViewVehicleBrandVehicle;
    //[14]
    private LinearLayout layoutVehicleModelVehicle;
    private ImageView imageViewVehicleModelVehicle;
    private TextView textViewVehicleModelVehicle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_expense_especific );

        //[1]
        layoutNumberTickect = findViewById( R.id.layout_numberTickect_expense );
        imageViewNumberTickect = findViewById( R.id.imageView_numberTickect_expense );
        textViewNumberTickect = findViewById( R.id.textView_numberTickect_expense );
        //imageViewNumberTickect.setImageResource( 0 );
        //textViewNumberTickect.setText( "1" );

        //[2]
        layoutDateTickect = findViewById( R.id.layout_dateTickect_expense );
        imageViewDateTickect = findViewById( R.id.imageView_dateTickect_expense );
        textViewDateTickect = findViewById( R.id.textView_dateTickect_expense );
        //imageViewDateTickect.setImageResource( 0 );
        //textViewDateTickect.setText( "2" );

        //[3]
        layoutTotalExpenseTickect = findViewById( R.id.layout_totalExpenseTickect_expense );
        imageViewTotalExpenseTickect = findViewById( R.id.imageView_totalExpenseTickect_expense );
        textViewTotalExpenseTickect = findViewById( R.id.textView_TotalExpenseTickect_expense );
        // imageViewTotalExpenseTickect.setImageResource( 0 );
        //textViewTotalExpenseTickect.setText( "3" );

        //[4]
        layoutProviderNameProvider = findViewById( R.id.layout_providerNameProvider_expense );
        imageViewProviderNameProvider = findViewById( R.id.imageView_providerNameProvider_expense );
        textViewProviderNameProvider = findViewById( R.id.textView_providerNameProvider_expense );
        //imageViewProviderNameProvider.setImageResource( 0 );
        //textViewProviderNameProvider.setText( "4" );

        //[5]
        layoutProviderCifNifProvider = findViewById( R.id.layout_providerCifNifProvider_expense );
        imageViewProviderCifNifProvider = findViewById( R.id.imageView_providerCifNifProvider_expense );
        textViewProviderCifNifProvider = findViewById( R.id.textView_providerCifNifProvider_expense );
        //imageViewProviderCifNifProvider.setImageResource( 0 );
        //textViewProviderCifNifProvider.setText( "5" );

        //[6]
        layoutProviderTelephoneProvider = findViewById( R.id.layout_providerTelephoneProvider_expense );
        imageViewProviderTelephoneProvider = findViewById( R.id.imageView_providerTelephoneProvider_expense );
        textViewProviderTelephoneProvider = findViewById( R.id.textView_providerTelephoneProvider_expense );
        //imageViewProviderTelephoneProvider.setImageResource( 0 );
        //textViewProviderTelephoneProvider.setText( "6" );

        //[7]
        layoutMethodOfPlaymentNamePlayment = findViewById( R.id.layout_methodOfPlaymentNamePlayment_expense );
        imageViewMethodOfPlaymentNamePlayment = findViewById( R.id.imageView_methodOfPlaymentNamePlayment_expense );
        textViewMethodOfPlaymentNamePlayment = findViewById( R.id.textView_methodOfPlaymentNamePlayment_expense );
        //imageViewMethodOfPlaymentNamePlayment.setImageResource( 0 );
        //textViewMethodOfPlaymentNamePlayment.setText( "7" );

        //[8]
        layoutTypeExpensename = findViewById( R.id.layout_type_expensename_expense );
        imageViewTypeExpensename = findViewById( R.id.imageView_type_expensename_expense );
        textViewTypeExpensename = findViewById( R.id.textView_type_expensename_expense );
        // imageViewMethodOfPlaymentNameTypeExpense.setImageResource( 0 );
        //textViewMethodOfPlaymentNameTypeExpense.setText( "8" );

        //[9]
        layoutUserNameUser = findViewById( R.id.layout_userNameUser_expense );
        imageViewUserNameUser = findViewById( R.id.imageView_userNameUser_expense );
        textViewUserNameUser = findViewById( R.id.textView_userNameUser_expense );
        //imageViewUserNameUser.setImageResource( 0 );
        //textViewUserNameUser.setText( "9" );

        //[10]
        layoutUserEmailUser = findViewById( R.id.layout_userEmailUser_expense );
        imageViewUserEmailUser = findViewById( R.id.imageView_userEmailUser_expense );
        textViewUserEmailUser = findViewById( R.id.textView_userEmailUser_expense );
        //imageViewUserEmailUser.setImageResource( 0 );
        //textViewUserEmailUser.setText( "10" );

        //[11]
        layoutUserTelephoneNumberUser = findViewById( R.id.layout_userTelephoneNumberUser_expense );
        imageViewUserTelephoneNumberUser = findViewById( R.id.imageView_userTelephoneNumberUser_expense );
        textViewUserTelephoneNumberUser = findViewById( R.id.textView_userTelephoneNumberUser_expense );
        // imageViewUserTelephoneNumberUser.setImageResource( 0 );
        //textViewUserTelephoneNumberUser.setText( "11" );

        //[12]
        layoutVehicleRegistrationNumberVehicle = findViewById( R.id.layout_vehicleRegistrationNumberVehicle_expense );
        imageViewVehicleRegistrationNumberVehicle = findViewById( R.id.imageView_vehicleRegistrationNumberVehicle_expense );
        textViewVehicleRegistrationNumberVehicle = findViewById( R.id.textView_vehicleRegistrationNumberVehicle_expense );
        // imageViewVehicleRegistrationNumberVehicle.setImageResource( 0 );
        //textViewVehicleRegistrationNumberVehicle.setText( "12" );

        //[13]
        layoutVehicleBrandVehicle = findViewById( R.id.layout_vehicleBrandVehicle_expense );
        imageViewVehicleBrandVehicle = findViewById( R.id.imageView_vehicleBrandVehicle_expense );
        textViewVehicleBrandVehicle = findViewById( R.id.textView_vehicleBrandVehicle_expense );
        // imageViewVehicleBrandVehicle.setImageResource( 0 );
        //textViewVehicleBrandVehicle.setText( "13" );

        //[14]
        layoutVehicleModelVehicle = findViewById( R.id.layout_vehicleModelVehicle_expense );
        imageViewVehicleModelVehicle = findViewById( R.id.imageView_vehicleModelVehicle_expense );
        textViewVehicleModelVehicle = findViewById( R.id.textView_vehicleModelVehicle_expense );
        // imageViewVehicleModelVehicle.setImageResource( 0 );
        //textViewVehicleModelVehicle.setText( "14" );

        onActivityResult( EXPENSE_SELECT_REQUEST, RESULT_OK, getIntent() );
    }


    /*
     * Guardo comprueba la correcta recepción de los datos recibidos por medio de Items
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 1  && resultCode  == RESULT_OK) {

                Bundle objetoIn = data.getExtras();
                Tickect expenseSelectTickect = null;
                TypeExpense expenseSelectTypeExpense = null;
                Vehicle expenseSelectVehicle = null;
                User user = new User();

                expenseSelectTickect = (Tickect) objetoIn.getSerializable( EXPENSE_SELECT_TICKET );
                expenseSelectTypeExpense = (TypeExpense) objetoIn.getSerializable( EXPENSE_SELECT_TYPE_EXPENSE );
                expenseSelectVehicle = (Vehicle) objetoIn.getSerializable( EXPENSE_SELECT_VEHICLE );


                //[1]
                //layoutNumberTickect;

                String stringTextView_numberTickect_expense = getString( R.string.textView_numberTickect_expense );
                textViewNumberTickect.setText( stringTextView_numberTickect_expense + expenseSelectTickect.getTickectNumber() );

                //[2]
                //layoutDateTickect;
                String stringTextViewDateTickectExpense = getString( R.string.textView_dateTickect_expense );
                textViewDateTickect.setText( stringTextViewDateTickectExpense + expenseSelectTickect.getTickectDate() );

                //[3]
                //layoutTotalExpenseTickect;
                String stringTextViewTotalExpenseTickectExpense = getString( R.string.textView_TotalExpenseTickect_expense );
                textViewTotalExpenseTickect.setText( stringTextViewTotalExpenseTickectExpense + expenseSelectTickect.getTickectTotalExpense() );

                //[4]
                //layoutProviderNameProvider;
                String stringTextViewProviderNameProviderExpense = getString( R.string.textView_providerNameProvider_expense );
                textViewProviderNameProvider.setText( stringTextViewProviderNameProviderExpense + expenseSelectTickect.getProviderName() );

                //[5]
                //layoutProviderCifNifProvider;
                String stringTextViewProviderCifNifProviderExpense = getString( R.string.textView_providerCifNifProvider_expense );
                textViewProviderCifNifProvider.setText( stringTextViewProviderCifNifProviderExpense + expenseSelectTickect.getProviderCifNif() );

                //[6]
                //layoutProviderTelephoneProvider;
                String stringTextViewProviderTelephoneProviderExpense = getString( R.string.textView_providerTelephoneProvider_expense );
                textViewProviderTelephoneProvider.setText( stringTextViewProviderTelephoneProviderExpense + expenseSelectTickect.getProviderTelephone() );

                //[7]
                //layoutMethodOfPlaymentNamePlayment;
                String stringTextViewMethodOfPlaymentNamePlaymentExpense = getString( R.string.textView_methodOfPlaymentNamePlayment_expense );
                imageViewMethodOfPlaymentNamePlayment.setImageResource( expenseSelectTickect.getMethodOfPlaymentLogo() );
                textViewMethodOfPlaymentNamePlayment.setText( stringTextViewMethodOfPlaymentNamePlaymentExpense + expenseSelectTickect.getMethodOfPlaymentName() );

                //[8]
                //layoutTypeExpensename;
                String stringTextViewMethodOfPlaymentNameTypeExpenseExpense = getString( R.string.textView_methodOfPlaymentNameTypeExpense_expense );
                imageViewTypeExpensename.setImageResource( expenseSelectTypeExpense.getTypeExpenseLogo() );
                textViewTypeExpensename.setText( stringTextViewMethodOfPlaymentNameTypeExpenseExpense + expenseSelectTypeExpense.getTypeExpenseName() );

                //[9]
                //layoutUserNameUser;
                String StringTextViewUserNameUserExpense = getString( R.string.textView_userNameUser_expense );
                Glide.with( getApplicationContext() ).load( user.getUserPhotoUriString() ).into( imageViewUserNameUser );
                textViewUserNameUser.setText( StringTextViewUserNameUserExpense + user.getUserName() );

                //[10]
                //layoutUserEmailUser;
                String stringTextViewUserEmailUserExpense = getString( R.string.textView_userEmailUser_expense );
                textViewUserEmailUser.setText( stringTextViewUserEmailUserExpense + user.getUserEmail() );

                //[11]
                //layoutUserTelephoneNumberUser;
                String stringTextViewUserTelephoneNumberUserExpense = getString( R.string.textView_userTelephoneNumberUser_expense );
                textViewUserTelephoneNumberUser.setText( stringTextViewUserTelephoneNumberUserExpense + user.getUserTelephone() );

                //[12]
                //layoutVehicleRegistrationNumberVehicle;
                String stringTextViewVehicleRegistrationNumberVehicleExpense = getString( R.string.textView_vehicleRegistrationNumberVehicle_expense );
                textViewVehicleRegistrationNumberVehicle.setText( stringTextViewVehicleRegistrationNumberVehicleExpense + expenseSelectVehicle.getVehicleRegistrationNumber() );

                //[13]
                //layoutVehicleBrandVehicle;
                String stringTextViewVehicleBrandVehicleExpense = getString( R.string.textView_vehicleBrandVehicle_expense );
                imageViewVehicleBrandVehicle.setImageResource( expenseSelectVehicle.getVehiclelogo() );
                textViewVehicleBrandVehicle.setText( stringTextViewVehicleBrandVehicleExpense + expenseSelectVehicle.getVehicleBrand() );

                //[14]
                //layoutVehicleModelVehicle;
                String stringTextViewVehicleModelVehicleExpense = getString( R.string.textView_vehicleModelVehicle_expense );
                textViewVehicleModelVehicle.setText( stringTextViewVehicleModelVehicleExpense + expenseSelectVehicle.getVehicleModel() );

            }
        } catch (Exception ex) {
            Toast.makeText( ExpenseEspecificActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

}