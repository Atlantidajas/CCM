package com.jorge.app.ccm.ui.session;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSesions;

import java.util.ArrayList;

public class SesionDrivingActivity extends AppCompatActivity{

    private ControllerDBSesions controllerDBSesions;

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
        controllerDBSesions = new ControllerDBSesions( getApplicationContext() );

        //Inizializao Adapter para mostrar lista de sesiones
        arrayAdapterSesion = new AdapterSession( getApplication(), textView, listView);
        // Cargo array adapte
        controllerDBSesions.setAdapter( arrayAdapterSesion );
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

                SesionDriving sesionDrivingEnd = new SesionDriving( false, sesionsDrivings.get( position ).getVehicle() );
                controllerDBSesions = new ControllerDBSesions( getApplicationContext() );
                controllerDBSesions.setSesion( sesionDrivingEnd );
                arrayAdapterSesion.getListIntemSesions().clear();//<-- Limpio por si retrosede
                arrayAdapterSesion.notifyDataSetChanged();//<-- Notifico cambios


            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
