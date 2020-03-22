package com.jorge.app.ccm.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
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
import com.jorge.app.ccm.FullscreenActivity;
import com.jorge.app.ccm.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseUser;
import com.jorge.app.ccm.ui.alertsDialogos.NoticeDialogFragment;
import com.jorge.app.ccm.ui.vehicles.VehiclesListActivity;

public class HomeActivity extends AppCompatActivity implements NoticeDialogFragment.NoticeDialogListener {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private String nameUser;
    private Button buttonLinkVehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonLinkVehicles = findViewById(R.id.button_link_vehicles);
        buttonLinkVehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent= new Intent (HomeActivity.this, VehiclesListActivity.class);
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

    // Implementación Interface en clase para NoticeDialogofrgments
    private void confirmAlertDialog( int title, int message, int textButtonPositive, boolean cancelable ) {

        DialogFragment newFragment = new NoticeDialogFragment( title, message, textButtonPositive, cancelable);
        newFragment.show( getSupportFragmentManager(), "RegistreVehicleActivity");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        Intent intent= new Intent (HomeActivity.this, FullscreenActivity.class);
        startActivity(intent);

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
