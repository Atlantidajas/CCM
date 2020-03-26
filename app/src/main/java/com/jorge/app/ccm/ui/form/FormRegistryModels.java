package com.jorge.app.ccm.ui.form;

import android.text.Layout;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmenForm;


public class FormRegistryModels extends DialogFragment{

    private DialogFragmenForm dialogFragmenForm;
    final int resourceLayot;
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;


    public FormRegistryModels(FragmentManager fragmentManager) {

        this.resourceLayot = R.layout.form_registry_model;
        this.textButtonPositive = R.string.form_registry_model_button_next;
        this.textButtonNegative = R.string.form_registry_model_button_return;
        this.cancelable = true;
        this.dialogFragmenForm = new DialogFragmenForm(
                this.resourceLayot,
                this.textButtonPositive,
                this.textButtonNegative,
                this.cancelable);
        this.dialogFragmenForm.show( fragmentManager, "RegistreVehicleActivity");

    }


}

