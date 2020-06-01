package com.jorge.app.ccm.ui.expenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBExpense;
import com.jorge.app.ccm.models.Expense;
import com.jorge.app.ccm.models.User;

import java.util.ArrayList;
import java.util.Iterator;

public class AdapterExpenseHistoric extends BaseAdapter {

    private Context context;
    private ArrayList<Expense> listIntemExpense;
    private Expense expense;

    public AdapterExpenseHistoric(){}

    public AdapterExpenseHistoric(final Context context, ArrayList<Expense> listExpenses ) {
        this.context = context;
        listIntemExpense = listExpenses;
    }

    @Override
    public int getCount() {
        return listIntemExpense.size();
    }

    @Override
    public Object getItem(int position) {
        return listIntemExpense.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        this.expense = (Expense) getItem(position);
        convertView = LayoutInflater.from( context ).inflate(R.layout.list_item_view_expense, parent, false );

        ImageView  imageViewItemLogoTypeExpense = convertView.findViewById( R.id.imageView_item_logo_type_expense );
        ImageView  imageViewItemLogoVehicleExpense = convertView.findViewById( R.id.imageView_item_logo_vehicle_expense );
        TextView textViewRegistrationNumberVehicleItemExpense = convertView.findViewById( R.id.textView_registration_number_vehicle_item_expense );
        TextView  textViewExpenseDateItemExpense = convertView.findViewById( R.id.textView_expense_date_item_expense );
        TextView textViewMethodPlaymentItemExpense = convertView.findViewById( R.id.textView_method_playment_item_expense );
        ImageView imageViewUserLogoItemExpense = convertView.findViewById( R.id.imageView_user_logo_item_expense );

        imageViewItemLogoTypeExpense.setImageResource( expense.getTypeExpenseLogo() );
        imageViewItemLogoVehicleExpense.setImageResource( expense.getVehiclelogo() );
        textViewRegistrationNumberVehicleItemExpense.setText( expense.getVehicleRegistrationNumber() );
        textViewExpenseDateItemExpense.setText( expense.getTickectDate() );
        textViewMethodPlaymentItemExpense.setText( expense.getMethodOfPlaymentName() );
        Glide.with( context ).load( expense.getUserPhotoUriString() ).into( imageViewUserLogoItemExpense );

        return convertView;
    }

    public ArrayList<Expense> getListIntemExpense() {
        return listIntemExpense;
    }

}