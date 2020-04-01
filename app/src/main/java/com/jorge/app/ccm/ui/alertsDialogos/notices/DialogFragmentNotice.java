package com.jorge.app.ccm.ui.alertsDialogos.notices;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


public class DialogFragmentNotice extends DialogFragment {
    private DialogNoticeListerner listener;
    private int title;
    private int message;
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;
    private AlertDialog.Builder builder;

    /*
     * Constructor para dos botones
     */
    public DialogFragmentNotice( int title,
                                 int message,
                                 int textButtonPositive,
                                 int textButtonNegative,
                                 boolean cancelable) {
         this.title = title;
        this.message = message;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = textButtonNegative;
        this.cancelable = cancelable;
    }

    /*
     * Constructor para un botón
     */

    public DialogFragmentNotice( int title,
                                int message,
                                int textButtonPositive,
                                boolean cancelable) {
        this.title = title;
        this.message = message;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = 0;
        this.cancelable = cancelable;
    }

    /*
     * Interface para asegurarme de la implenetación de los métodos
     * */
    public interface DialogNoticeListerner {
        public void onDialogFragmentNoticePositiveClick(DialogFragment dialog);
        public void onDialogFragmentNoticeNegativeClick(DialogFragment dialog);
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (DialogNoticeListerner) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception

        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder( getActivity() );
        builder.setTitle(this.title)
                .setMessage( this.message )
                .setCancelable( this.cancelable )
                .setPositiveButton( this.textButtonPositive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        listener.onDialogFragmentNoticePositiveClick( DialogFragmentNotice.this );
                    }
                });
        // Si utilizo constructor sin el botón negatico no lo crea
        if( this.textButtonNegative != 0 ){
            builder.setNegativeButton( this.textButtonNegative, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Send the negative button event back to the host activity
                    listener.onDialogFragmentNoticeNegativeClick( DialogFragmentNotice.this );
                    //listener.onDialogFragmentNoticeNegativeClick(DialogFragmentNotice.this);
                }
            });
        }
        return builder.create();
    }
}