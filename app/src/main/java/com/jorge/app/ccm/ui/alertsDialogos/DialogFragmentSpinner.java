package com.jorge.app.ccm.ui.alertsDialogos;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class DialogFragmentSpinner extends DialogFragment {

    private DialogFragmentListener listener;
    private int title;
    private String items[];
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;
    private int itemResult;

    /*
     * Constructor para dos botones en RegistryVehiclesBrandsDialogFragment tanto positivo como negativo
     */
    public DialogFragmentSpinner(int title,
                                 final String items[],
                                 int textButtonPositive,
                                 int textButtonNegative,
                                 boolean cancelable) {
        this.title = title;
        this.items = items;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = textButtonNegative;
        this.cancelable = cancelable;
    }

    public DialogFragmentSpinner(int title,
                                 final String items[],
                                 int textButtonPositive,
                                 boolean cancelable) {
        this.title = title;
        this.items = items;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = 0;
        this.cancelable = cancelable;
    }

    /*
     * Interface para asegurarme de la implenetación de los métodos
     * */
    public interface DialogFragmentListener {
        public void onDialogItemClick(DialogFragment dialog);
        public void onDialogFragmentSelectPositiveClick(DialogFragment dialog);
        public void onDialogFragmentSelectNegativeClick(DialogFragment dialog);
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (DialogFragmentListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception

        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setTitle(this.title)
                .setCancelable( this.cancelable )
                // El -1 es para colocar indice de la misma forma que array
                .setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        setItemResult( item );
                        listener.onDialogItemClick(DialogFragmentSpinner.this);
                    }
                })
                .setPositiveButton( this.textButtonPositive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        listener.onDialogFragmentSelectPositiveClick(DialogFragmentSpinner.this);
                    }
                });
                // Si utilizo constructor sin el botón negatico no lo crea
                if( this.textButtonNegative != 0 ){
                    builder.setNegativeButton( this.textButtonNegative, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Send the negative button event back to the host activity
                            listener.onDialogFragmentSelectNegativeClick(DialogFragmentSpinner.this);
                        }
                    });
        }

        return builder.create();
    }

    public void setItemResult(int itemResult) {
        this.itemResult = itemResult;
    }

    public int getItemResult() {
        return itemResult;
    }
}
