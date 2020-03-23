package com.jorge.app.ccm.ui.alertsDialogos;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class DialogFragmentSelect extends DialogFragment {

    private DialogFragmentSelect.DialogFragmentListener listener;
    private int title;
    private String items[];
    private boolean cancelable;
    private int itemResult;

    /*
     * Constructor para dos botones en RegistryVehiclesBrandsDialogFragment tanto positivo como negativo
     */
    public DialogFragmentSelect(int title,
                                final String items[],
                                boolean cancelable) {
        this.title = title;
        this.items = items;
        this.cancelable = cancelable;
    }

    /*
     * Interface para asegurarme de la implenetación de los métodos
     * */
    public interface DialogFragmentListener {
        public void onDialogItemClick(DialogFragment dialog);
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
                                setItemResult( item);
                                listener.onDialogItemClick(DialogFragmentSelect.this);
                            }
                        });

        return builder.create();
    }

    public void setItemResult(int itemResult) {
        this.itemResult = itemResult;
    }

    public int getItemResult() {
        return itemResult;
    }
}
