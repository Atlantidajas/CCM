package com.jorge.app.ccm.ui.form;

import android.content.DialogInterface;
import android.content.res.Resources;

import androidx.fragment.app.DialogFragment;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;

public class WindowInitSesionVehicle extends DialogFragmentNotice {


    public WindowInitSesionVehicle( String messagege ) {
        super();
        this.setTitle( R.string.windows_init_session_vehicle_title );
        this.setMessage( messagege );
        this.setTextButtonPositive( R.string.windows_init_session_vehicle_button_aceptar );
        this.setTextButtonNegative( R.string.windows_init_session_vehicle_button_cancel );
        this.setCancelable( false );
    }


}
