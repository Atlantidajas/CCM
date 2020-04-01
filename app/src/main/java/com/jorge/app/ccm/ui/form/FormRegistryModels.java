package com.jorge.app.ccm.ui.form;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmenSpinner;


public class FormRegistryModels extends DialogFragment{

    private DialogFragmenSpinner dialogFragmenSpinner;
    final int resourceLayot;
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;


    public FormRegistryModels(FragmentManager fragmentManager) {

        this.resourceLayot = R.layout.form_vehicle_registry_model;
        this.textButtonPositive = R.string.form_registry_model_button_next;
        this.textButtonNegative = R.string.form_registry_model_button_return;
        this.cancelable = true;
        this.dialogFragmenSpinner = new DialogFragmenSpinner(
                this.resourceLayot,
                this.textButtonPositive,
                this.textButtonNegative,
                this.cancelable);
        this.dialogFragmenSpinner.show( fragmentManager, "RegistreVehicleActivity");

    }


}

