package com.jorge.app.ccm.ui.form;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentNotice;

public class WindowsNoticeYesRegistryVehicle extends DialogFragment implements DialogFragmentNotice.NoticeDialogListener{

    DialogFragmentNotice dialogFragmentNotice;

    public WindowsNoticeYesRegistryVehicle(FragmentManager fragmentManager ) {
        this.dialogFragmentNotice = new DialogFragmentNotice(
                R.string.window_notice_registry_yes_vehicle_title,
                R.string.window_notice_registry_yes_vehicle_message,
                R.string.window_notice_registry_yes_vehicle_button_aceptar,
                true);
        this.dialogFragmentNotice.show( fragmentManager, "DialogFragmentNotice");

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        System.out.println("Estas avisado");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
