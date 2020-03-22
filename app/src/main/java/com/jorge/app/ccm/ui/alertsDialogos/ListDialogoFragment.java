package com.jorge.app.ccm.ui.alertsDialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.jorge.app.ccm.ui.vehicles.RegistreVehicleActivity;

public class ListDialogoFragment extends DialogFragment{


    ListDialogoFragment.ListDialogListener listener;
    private int title;
    private int arrayItems;
    private boolean cancelable;


    public ListDialogoFragment(int title, int arrayItems, boolean cancelable) {
        this.title = title;
        this.arrayItems = arrayItems;
        this.cancelable = cancelable;
    }


    /*
     * Interace para asegurarme de la implenetación de los métodos
     * */
    public interface ListDialogListener {

        public void onDialogItemClick(DialogFragment dialog);

    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (ListDialogoFragment.ListDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(" must implement ListDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle( this.title )
                .setCancelable( this.cancelable )
                .setItems(this.arrayItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogItemClick(ListDialogoFragment.this);
                    }
                });

        return builder.create();
    }

}
