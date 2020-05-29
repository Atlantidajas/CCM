package com.jorge.app.ccm.ui.expenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBExpense;
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.models.Expense;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.models.Tickect;
import com.jorge.app.ccm.models.TypeExpense;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.Vehicle;

import java.util.ArrayList;
import java.util.Iterator;

public class AdapterExpenseHistoric extends BaseAdapter {

    private ImageView imageViewTypeExpense;
    private TextView textViewNameTypeExpense;
    private TextView textViewDateExpense;
    private TextView textViewMenthodPlamentExpense;
    private TextView textViewTotalImportExpense;
    ImageView imageViewLogoUserExpense;
    private Context context;
    private ArrayList<Expense> listIntemExpense;
    private TextView textView;
    private ListView listView;
    private Expense expense;
    private ControllerDBExpense controllerDBExpense;
    private User user;


    public AdapterExpenseHistoric(){}

    public AdapterExpenseHistoric(final Context context, TextView textView, ListView listView) {
        this.context = context;
        this.textView = textView;
        this.listView = listView;
        this.controllerDBExpense = new ControllerDBExpense( context );
        user = new User( true );

        listIntemExpense = new ArrayList<>(  );

        //Eventos de cambios sobre el adaptador
        controllerDBExpense.getDatabaseReference().child( user.getIdUser() ).addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listIntemExpense.clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listIntemExpense.clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                listIntemExpense.clear();
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listIntemExpense.clear();
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        // Cargo array adapte

        ControllerDBExpense controllerDBExpense = new ControllerDBExpense( context );

        //Cargo con los datos de la db el adapter
        controllerDBExpense.getDatabaseReference().child( user.getIdUser() ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    setArrayAdapterHistoric(dataSnapshot);
                }
                else {
                    Toast.makeText( context, R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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

            imageViewTypeExpense = convertView.findViewById( R.id.imageView_item_logo_type_expense );
            textViewNameTypeExpense = convertView.findViewById( R.id.textView_type_expense_name_item_expense );
            textViewDateExpense = convertView.findViewById( R.id.textView_expense_date_item_expense );
            textViewMenthodPlamentExpense = convertView.findViewById( R.id.textView_method_playment_item_expense );
            textViewTotalImportExpense = convertView.findViewById( R.id.textView_total_import_expese_item_expense );
            imageViewLogoUserExpense = convertView.findViewById( R.id.imageView_user_logo_item_expense );

            imageViewTypeExpense.setImageResource( expense.getVehiclelogo() );
            textViewNameTypeExpense.setText( expense.getVehicleRegistrationNumber() );
            textViewDateExpense.setText( expense.getTickectNumber() );
            textViewMenthodPlamentExpense.setText( expense.getMethodOfPlaymentName() );
            textViewTotalImportExpense.setText( expense.getTickectTotalExpense() );
            Glide.with( context ).load( expense.getUserPhotoUriString() ).into( imageViewLogoUserExpense );

        return convertView;
    }


    /**
     * @Jorge.HL
     */

    public void setArrayAdapterHistoric(DataSnapshot dataSnapshot){

        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
        do{
            listIntemExpense.add( new Expense( dataSnapshots.next() ) );
        }while (dataSnapshots.hasNext());

        if ( this.getCount() <= 0 ){//<-- Controlo que tenga almenos un vehículo registrado, en caso contrario muestro mensaje
            Toast.makeText( context, R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
        }else{
            listView.setAdapter(this);
        }
    }

    public ArrayList<Expense> getListIntemExpense() {
        return listIntemExpense;
    }

}