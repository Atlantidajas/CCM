package com.jorge.app.ccm.ui.expenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private ImageView imageViewNumberTickect;
    private TextView textViewNumberTickect;
    //[2]
    private ImageView imageViewDateTickect;
    private TextView textViewDateTickect;
    //[3]
    private ImageView imageViewTotalExpenseTickect;
    private TextView textViewTotalExpenseTickect;
    //[4]
    private ImageView imageViewProviderNameProvider;
    private TextView textViewProviderNameProvider;
    //[5]
    private ImageView imageViewProviderCifNifProvider;
    private TextView textViewProviderCifNifProvider;
    //[6]
    private ImageView imageViewProviderTelephoneProvider;
    private TextView textViewProviderTelephoneProvider;
    //[7]
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
    private ImageView imageViewUserEmailUser;
    private TextView textViewUserEmailUser;
    //[11]
    private ImageView imageViewUserTelephoneNumberUser;
    private TextView textViewUserTelephoneNumberUser;
    //[12]
    private ImageView imageViewVehicleRegistrationNumberVehicle;
    private TextView textViewVehicleRegistrationNumberVehicle;
    //[13]
    private ImageView imageViewVehicleBrandVehicle;
    private TextView textViewVehicleBrandVehicle;
    //[14]
    private ImageView imageViewVehicleModelVehicle;
    private TextView textViewVehicleModelVehicle;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_expense_especific );

        user = new User(  );

        //[1]
        imageViewNumberTickect = findViewById( R.id.imageView_numberTickect_expense );
        textViewNumberTickect = findViewById( R.id.textView_numberTickect_expense );

        //[2]
        imageViewDateTickect = findViewById( R.id.imageView_dateTickect_expense );
        textViewDateTickect = findViewById( R.id.textView_dateTickect_expense );

        //[3]
        imageViewTotalExpenseTickect = findViewById( R.id.imageView_totalExpenseTickect_expense );
        textViewTotalExpenseTickect = findViewById( R.id.textView_TotalExpenseTickect_expense );

        //[4]
        imageViewProviderNameProvider = findViewById( R.id.imageView_providerNameProvider_expense );
        textViewProviderNameProvider = findViewById( R.id.textView_providerNameProvider_expense );

        //[5]
        imageViewProviderCifNifProvider = findViewById( R.id.imageView_providerCifNifProvider_expense );
        textViewProviderCifNifProvider = findViewById( R.id.textView_providerCifNifProvider_expense );

        //[6]
        imageViewProviderTelephoneProvider = findViewById( R.id.imageView_providerTelephoneProvider_expense );
        textViewProviderTelephoneProvider = findViewById( R.id.textView_providerTelephoneProvider_expense );

        //[7]
        imageViewMethodOfPlaymentNamePlayment = findViewById( R.id.imageView_methodOfPlaymentNamePlayment_expense );
        textViewMethodOfPlaymentNamePlayment = findViewById( R.id.textView_methodOfPlaymentNamePlayment_expense );

        //[8]
        layoutTypeExpensename = findViewById( R.id.layout_type_expensename_expense );
        imageViewTypeExpensename = findViewById( R.id.imageView_type_expensename_expense );
        textViewTypeExpensename = findViewById( R.id.textView_type_expensename_expense );

        //[9]
        layoutUserNameUser = findViewById( R.id.layout_userNameUser_expense );
        imageViewUserNameUser = findViewById( R.id.imageView_userNameUser_expense );
        textViewUserNameUser = findViewById( R.id.textView_userNameUser_expense );

        //[10]
        imageViewUserEmailUser = findViewById( R.id.imageView_userEmailUser_expense );
        textViewUserEmailUser = findViewById( R.id.textView_userEmailUser_expense );

        //[11]
        imageViewUserTelephoneNumberUser = findViewById( R.id.imageView_userTelephoneNumberUser_expense );
        textViewUserTelephoneNumberUser = findViewById( R.id.textView_userTelephoneNumberUser_expense );

        //[12]
        imageViewVehicleRegistrationNumberVehicle = findViewById( R.id.imageView_vehicleRegistrationNumberVehicle_expense );
        textViewVehicleRegistrationNumberVehicle = findViewById( R.id.textView_vehicleRegistrationNumberVehicle_expense );

        //[13]
        imageViewVehicleBrandVehicle = findViewById( R.id.imageView_vehicleBrandVehicle_expense );
        textViewVehicleBrandVehicle = findViewById( R.id.textView_vehicleBrandVehicle_expense );

        //[14]
        imageViewVehicleModelVehicle = findViewById( R.id.imageView_vehicleModelVehicle_expense );
        textViewVehicleModelVehicle = findViewById( R.id.textView_vehicleModelVehicle_expense );

        onActivityResult( EXPENSE_SELECT_REQUEST, RESULT_OK, getIntent() );
    }

    /*
     * Guardo comprueba la correcta recepción de los datos recibidos por medio de Items
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == EXPENSE_SELECT_REQUEST  && resultCode  == RESULT_OK) {

                Bundle objetIn = data.getExtras();
                Tickect expenseSelectTickect = null;
                TypeExpense expenseSelectTypeExpense = null;
                Vehicle expenseSelectVehicle = null;
                User user = new User( true );

                expenseSelectTickect = (Tickect) objetIn.getSerializable( EXPENSE_SELECT_TICKET );
                expenseSelectTypeExpense = (TypeExpense) objetIn.getSerializable( EXPENSE_SELECT_TYPE_EXPENSE );
                expenseSelectVehicle = (Vehicle) objetIn.getSerializable( EXPENSE_SELECT_VEHICLE );

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
                String stringTextViewUserNameUserExpense = getString( R.string.textView_userNameUser_expense );
                Glide.with( getApplicationContext() ).load( user.getUserPhotoUriString() ).into( imageViewUserNameUser );
                textViewUserNameUser.setText( stringTextViewUserNameUserExpense + user.getUserName() );

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