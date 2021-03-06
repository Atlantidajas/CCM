package com.jorge.app.ccm.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.jorge.app.ccm.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseUser;
import com.jorge.app.ccm.ui.expenses.ExpenseHistoricActivity;
import com.jorge.app.ccm.ui.location.LocationActivity;
import com.jorge.app.ccm.ui.sessionHistoric.SessionHistoricActivity;
import com.jorge.app.ccm.ui.vehicleStatus.VehiclesStatusListActivity;

public class HomeActivity extends AppCompatActivity  {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private String nameUser;
    private Button buttonVehiclesStatus;
    private Button buttonExpensesHistoric;
    private Button getButtonHistoricSesions;
    private Button buttonLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonVehiclesStatus = findViewById(R.id.button_vehicles_status);
        buttonVehiclesStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent= new Intent (HomeActivity.this, VehiclesStatusListActivity.class);
               startActivity(intent);
            }
        });

        buttonExpensesHistoric = findViewById(R.id.button_expenses);
        buttonExpensesHistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (HomeActivity.this, ExpenseHistoricActivity.class);
                startActivity(intent);
            }
        });

        //Direcciono A
        getButtonHistoricSesions = findViewById( R.id.button_historic_sesions );
        getButtonHistoricSesions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (HomeActivity.this, SessionHistoricActivity.class);
                startActivity(intent);
            }
        });

        buttonLocation = findViewById(R.id.button_location);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (HomeActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        // [START config_signin]
        // Configure Google Sign In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI( currentUser );

    }

    private void updateUI( FirebaseUser currentUser ) {
        if (currentUser != null) {
            this.nameUser = currentUser.getDisplayName();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.home_toolbar, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.disconnectButton ) {//<-- Cierra sesion de usuario y el programa
            this.revokeAccess();
            finish();//<-- Cierra app
        }

        if ( id == R.id.signOutButtonToolbar ) {//<-- Cierra el programa
            this.signOut();
            moveTaskToBack(true); //<-- Minimiza app
        }
        return super.onOptionsItemSelected(item);//<-- Devuelve una opción de menú la pulsada (Método de la clase padre).
    }

    /**
     * Solo cierra sesión
     */
    private void signOut() {
        // Firebase sign out
        firebaseAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    /**
     * Revoca y cierra sesión
     */
    private void revokeAccess() {
        // Firebase sign out
        firebaseAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }


}
