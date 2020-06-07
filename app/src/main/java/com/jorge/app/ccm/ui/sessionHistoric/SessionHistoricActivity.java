package com.jorge.app.ccm.ui.sessionHistoric;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;
import com.jorge.app.ccm.gadget.WindowDialogFragment;
import com.jorge.app.ccm.models.SessionDriving;
import com.jorge.app.ccm.ui.expenses.ExpenseHistoricActivity;
import com.jorge.app.ccm.ui.home.HomeActivity;
import com.jorge.app.ccm.ui.sessionStatus.SessionStatusActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SessionHistoricActivity extends AppCompatActivity {

    private final String TAG = "SessionHistoricActivity";
    private AdapterSessionHistoric adapterSessionHistoric;
    private TextView textView;
    private ListView listView;
    private ArrayList<SessionDriving> sesionsDrivings;
    public Intent intentHome;
    private Intent intentSessionStatus;
    public static final String SESSION_HISTORIC = "SessionDriving"; //<-- Objeto Session

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sesion_historic );
        textView = findViewById(R.id.textView_Sesions_historic);
        listView = findViewById(R.id.listView_sessions_historic);
        intentHome = new Intent( SessionHistoricActivity.this, HomeActivity.class);
        intentSessionStatus  = new Intent( SessionHistoricActivity.this, SessionStatusActivity.class );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//<-- añado flecha retroseso

        //Inizializao Adapter para mostrar lista de sesiones
        adapterSessionHistoric = new AdapterSessionHistoric( getApplication(), textView, listView, TAG);
        sesionsDrivings = new ArrayList<>(  );
        sesionsDrivings = adapterSessionHistoric.getListIntemSesions();

    }


    @Override
    public void onStart() {
        super.onStart();
        onclickItemList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == android.R.id.home ){
            finish();
            startActivity( intentHome );
            return true;
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
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

                        Log.i( TAG, sesionsDrivings.get( position ).getSessionTypeSesion() );
                        Log.i( TAG, sesionsDrivings.get( position ).getVehicle().getVehicleRegistrationNumber() );

                        SessionDriving sessionDrivingSelect = sesionsDrivings.get( position );

                        intentSessionStatus.putExtra(SESSION_HISTORIC, (Serializable) sessionDrivingSelect.getSession() );
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
