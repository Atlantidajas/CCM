package com.jorge.app.ccm.gadget.notices;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class DialogFragmentNotice extends DialogFragment {
    private DialogNoticeListerner listener;
    private int title;
    private String message;
    private int messageResource;
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;
    private AlertDialog.Builder builder;

    /*
    * Constructor para iniciar desde Set
    */
    public DialogFragmentNotice(){
        this.textButtonNegative = 0;
        this.messageResource =0;
        this.message = null;
    }

    /*
     * Constructor para dos botones y mensaje tipo String
     */
    public DialogFragmentNotice( int title,
                                 String message,
                                 int textButtonPositive,
                                 int textButtonNegative,
                                 boolean cancelable) {
        this.title = title;
        this.message = message;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = textButtonNegative;
        this.cancelable = cancelable;
        this.messageResource = 0;
    }

    /*
     * Constructor para un botón y mensaje tipo String
     */

    public DialogFragmentNotice( int title,
                                 String message,
                                int textButtonPositive,
                                boolean cancelable) {
        this.title = title;
        this.message = message;
        this.textButtonPositive = textButtonPositive;
        this.cancelable = cancelable;
        this.textButtonNegative = 0;
    }

    /*
     * Constructor para dos botones y con tipo de mensaje desde recursos
     */
    public DialogFragmentNotice( int title,
                                 int messageResorce,
                                 int textButtonPositive,
                                 int textButtonNegative,
                                 boolean cancelable) {
        this.title = title;
        this.messageResource = messageResorce;
        this.textButtonPositive = textButtonPositive;
        this.textButtonNegative = textButtonNegative;
        this.cancelable = cancelable;
        this.message = null;
    }

    /*
     * Constructor para un botón y con tipo de mensaje desde recursos
     */
    public DialogFragmentNotice( int title,
                                 int messageResorce,
                                 int textButtonPositive,
                                 boolean cancelable) {
        this.title = title;
        this.messageResource = messageResorce;
        this.textButtonPositive = textButtonPositive;
        this.cancelable = cancelable;
        this.textButtonNegative = 0;
        this.message = null;
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
        builder.setTitle(this.title);
        if( this.message != null ){
            builder.setMessage( this.message );
        }
        if ( this.messageResource != 0 ){
            builder.setMessage( this.messageResource );
        }
        builder.setCancelable( this.cancelable )
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

    public void setListener(DialogNoticeListerner listener) {
        this.listener = listener;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTextButtonPositive(int textButtonPositive) {
        this.textButtonPositive = textButtonPositive;
    }

    public void setTextButtonNegative(int textButtonNegative) {
        this.textButtonNegative = textButtonNegative;
    }

    @Override
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setBuilder(AlertDialog.Builder builder) {
        this.builder = builder;
    }

    public DialogNoticeListerner getListener() {
        return listener;
    }

    public int getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getTextButtonPositive() {
        return textButtonPositive;
    }

    public int getTextButtonNegative() {
        return textButtonNegative;
    }

    @Override
    public boolean isCancelable() {
        return cancelable;
    }

    public AlertDialog.Builder getBuilder() {
        return builder;
    }
}