package com.jorge.app.ccm.ui.form;

import android.content.res.Resources;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;

public class WindowYesInitSesionVehicle extends DialogFragmentNotice {

    DialogFragmentNotice dialogFragmentNotice;

    public WindowYesInitSesionVehicle(String menssage ) {
        dialogFragmentNotice = new DialogFragmentNotice( R.string.windows_init_session_vehicle_title,
                menssage,
                R.string.windows_init_session_vehicle_button_aceptar,
                R.string.windows_init_session_vehicle_button_cancel,
                false);
    }

    public WindowYesInitSesionVehicle( int messageResorce ) {

        dialogFragmentNotice = new DialogFragmentNotice( R.string.windows_init_session_vehicle_title,
                messageResorce,
                R.string.windows_init_session_vehicle_button_aceptar,
                R.string.windows_init_session_vehicle_button_cancel,
                false);
    }

    public DialogFragmentNotice getDialogFragmentNotice() {
        return dialogFragmentNotice;
    }
}
