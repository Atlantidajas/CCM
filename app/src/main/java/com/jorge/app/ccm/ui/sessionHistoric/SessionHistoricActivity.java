package com.jorge.app.ccm.ui.sessionHistoric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerDBSessionsHistoric;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.models.User;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.ui.vehicleStatus.VehiclesListActivity;

import java.util.ArrayList;

public class SessionHistoricActivity extends AppCompatActivity {

    private final String TAG = "SessionHistoricActivity";
    private AdapterSessionHistoric adapterSessionHistoric;
    private TextView textView;
    private ListView listView;
    private ArrayList<SessionDriving> sesionsDrivings;
    private Intent intentCloseSesion;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sesion_driving );
        textView = findViewById(R.id.textView_Sesions);
        listView = findViewById(R.id.listView_sessions);

        intentCloseSesion  = new Intent( SessionHistoricActivity.this, VehiclesListActivity.class );

        //Inizializao Adapter para mostrar lista de sesiones
        adapterSessionHistoric = new AdapterSessionHistoric( getApplication(), textView, listView);
        sesionsDrivings = adapterSessionHistoric.getListIntemSesions();

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

                        startActivity( intentCloseSesion );
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
