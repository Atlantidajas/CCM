package com.jorge.app.ccm.ui.alertsDialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import com.jorge.app.ccm.ui.vehicles.RegistreVehicleActivity;

public class NoticeDialogFragment extends DialogFragment {

    NoticeDialogListener listener;
    private int title;
    private int message;
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;

    /*
     * Constructor para dos botones en DialogoFragment tanto positivo como negativo
     */
    public NoticeDialogFragment(int title, int message, int textButtonPositive, int textButtonNegative, boolean cancelable) {
        this.title = title;
        this.message = message;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = textButtonNegative;
        this.cancelable = cancelable;
    }

    /**
     * Constructor para solo botón positivo en DialogoFragment.
     */
    public NoticeDialogFragment(int title, int message, int textButtonPositive, boolean cancelable) {
        this.title = title;
        this.message = message;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = 0;
        this.cancelable = cancelable;
    }

    /*
    * Interace para asegurarme de la implenetación de los métodos
    * */
    public interface NoticeDialogListener {
        /*
         * Le da funcionalidad al evento onclick del botón positivo del NoticeDialogoFragment implementado mediante interface en la clase
         */
        public void onDialogPositiveClick(DialogFragment dialog);
        /**
         * Le da funcionalidad al evento onclick del botón negativo del NoticeDialogoFragment implementado mediante interface en la clase
         */
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(RegistreVehicleActivity.class.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle( this.title )
                .setMessage( this.message )
                .setCancelable( this.cancelable )
                .setPositiveButton( this.textButtonPositive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        listener.onDialogPositiveClick(NoticeDialogFragment.this);
                    }
                });
                // Si utilizo constructor sin el botón negatico no lo crea
                if( this.textButtonNegative != 0 ){
                    builder.setNegativeButton( this.textButtonNegative, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Send the negative button event back to the host activity
                            listener.onDialogNegativeClick(NoticeDialogFragment.this);
                        }
                    });
                }

        return builder.create();
    }
}
