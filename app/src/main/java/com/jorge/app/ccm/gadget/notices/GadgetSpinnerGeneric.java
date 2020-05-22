package com.jorge.app.ccm.gadget.notices;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class GadgetSpinnerGeneric extends DialogFragment {

    private final Context CONTEXT;
    private String title;
    private String items[];
    private AlertDialog.Builder builder;
    private int itemIndex;

    public GadgetSpinnerGeneric(Context CONTEXT, String title, String items[]) {
        this.CONTEXT = CONTEXT;
        this.title = title;
        this.items = items;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder( getActivity() );
        builder.setTitle( this.title )
                .setCancelable( false )
                // El -1 es para colocar indice de la misma forma que array
                .setSingleChoiceItems(items, -1,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                itemIndex = item;
                            }
                        })
                .setPositiveButton( "Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });


        return builder.create();
    }


    public AlertDialog.Builder getBuilder() {
        return builder;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public String valueItemSelect(){
        return items[itemIndex];
    }
}
