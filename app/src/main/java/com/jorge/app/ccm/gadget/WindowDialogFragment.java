package com.jorge.app.ccm.gadget;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.gadget.notices.DialogFragmentNotice;

public class WindowDialogFragment extends DialogFragmentNotice {

    DialogFragmentNotice dialogFragmentNotice;

    public WindowDialogFragment(String menssage ) {
        dialogFragmentNotice = new DialogFragmentNotice( R.string.fieldAttention,
                menssage,
                R.string.button_accept,
                R.string.button_cancel,
                false);
    }

    public WindowDialogFragment(int messageResorce ) {

        dialogFragmentNotice = new DialogFragmentNotice( R.string.fieldAttention,
                messageResorce,
                R.string.button_accept,
                R.string.button_cancel,
                false);
    }

    public DialogFragmentNotice getDialogFragmentNotice() {
        return dialogFragmentNotice;
    }
}
