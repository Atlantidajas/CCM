package com.jorge.app.ccm.gadget;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;

public class WindowDialogFragment extends DialogFragmentNotice {

    DialogFragmentNotice dialogFragmentNotice;

    public WindowDialogFragment(String menssage ) {
        dialogFragmentNotice = new DialogFragmentNotice( R.string.windows_init_session_vehicle_title,
                menssage,
                R.string.windows_init_session_vehicle_button_aceptar,
                R.string.windows_init_session_vehicle_button_cancel,
                false);
    }

    public WindowDialogFragment(int messageResorce ) {

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
