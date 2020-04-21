package com.jorge.app.ccm.ui.session;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBStatus;
import com.jorge.app.ccm.controllers.ControllerVehicle;
import java.util.ArrayList;

public class SesionDrivingActivity extends AppCompatActivity{

    private ControllerVehicle controllerVehicle;


    private AdapterSession arrayAdapterSesion;
    private TextView textView;
    private ListView listView;
    private ArrayList<SesionDriving> sesionsDrivings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sesion_driving );
        textView = findViewById(R.id.textView_vehicles);
        listView = findViewById(R.id.listView_sessions);
        controllerVehicle = new ControllerVehicle( getApplicationContext() );

        // Cargo array adapte
        controllerVehicle.getDB_RF_SESIONS_CURRENT().addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    arrayAdapterSesion.setArrayAdapter(dataSnapshot);
                }
                else {
                    Toast.makeText( getApplicationContext(), R.string.toast_message_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Inizializao Adapter para mostrar lista de sesiones
        this.arrayAdapterSesion = new AdapterSession( getApplication(), textView, listView);
        sesionsDrivings = arrayAdapterSesion.getListIntemSesions();

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
            public void onItemClick( AdapterView<?> lst, View viewRow,
                                    int position, long id) {
                System.out.println( arrayAdapterSesion.getItem( position ) );
                final SesionDriving SESION_DRING_END = new SesionDriving( false, sesionsDrivings.get( position ).getVehicle() );
                controllerVehicle.setSesion( SESION_DRING_END );
                arrayAdapterSesion.getListIntemSesions().clear();//<-- Limpio por si retrosede
                arrayAdapterSesion.notifyDataSetChanged();//<-- Notifico cambios
                finish();
            }
        });
    }

}
