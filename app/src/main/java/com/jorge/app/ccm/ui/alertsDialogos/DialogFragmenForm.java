package com.jorge.app.ccm.ui.alertsDialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.fragment.app.DialogFragment;

import com.jorge.app.ccm.R;

public class DialogFragmenForm extends DialogFragment {

    private int resourceLayout;
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;
    private int itemResult;

    /*
     * Constructor para dos botones en RegistryVehiclesBrandsDialogFragment tanto positivo como negativo
     */
    public DialogFragmenForm(   int resourceLayout,
                                int textButtonPositive,
                                int textButtonNegative,
                                boolean cancelable) {
        this.resourceLayout = resourceLayout;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = textButtonNegative;
        this.cancelable = cancelable;
    }

    public DialogFragmenForm(int resourceLayout,
                             final String items[],
                             int textButtonPositive,
                             boolean cancelable) {
        this.resourceLayout = resourceLayout;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = 0;
        this.cancelable = cancelable;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(this.resourceLayout, null))
                // Add action buttons
                .setPositiveButton( this.textButtonPositive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity

                    }
                });
        // Si utilizo constructor sin el botón negatico no lo crea
        if( this.textButtonNegative != 0 ){
            builder.setNegativeButton( this.textButtonNegative, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Send the negative button event back to the host activity
                }
            });
        }

        return builder.create();
    }

}
