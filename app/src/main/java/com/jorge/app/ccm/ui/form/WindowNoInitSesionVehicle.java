package com.jorge.app.ccm.ui.form;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.notices.DialogFragmentNotice;

public class WindowNoInitSesionVehicle {

    DialogFragmentNotice dialogFragmentNotice;

    public WindowNoInitSesionVehicle(String menssage ) {
        dialogFragmentNotice = new DialogFragmentNotice( R.string.windows_init_session_vehicle_title,
                 menssage,
                 R.string.windows_init_session_vehicle_button_aceptar,
                 false);
    }

    public DialogFragmentNotice getDialogFragmentNotice() {
        return dialogFragmentNotice;
    }


}
