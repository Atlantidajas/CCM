package com.jorge.app.ccm.gadget;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.gadget.notices.DialogFragmentSpinner;

import java.util.ArrayList;

public class GadgetSpinner extends DialogFragment {

    private DialogFragmentSpinner dialogFragmentSpinner;
    private int title;
    final String items[];
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;
    private int itemResultSelect;

    public GadgetSpinner( FragmentManager fragmentManager, int titleResource, String items[], final String TAG ) {
        this.title = titleResource;
        this.cancelable = false;
        this.items = items;
        this.textButtonPositive = R.string.button_next;
        this.textButtonNegative = R.string.button_return;
        this.dialogFragmentSpinner = new DialogFragmentSpinner(this.title,
                this.items,
                this.textButtonPositive,
                this.textButtonNegative,
                this.cancelable);
        this.dialogFragmentSpinner.show( fragmentManager, TAG);
        this.itemResultSelect = this.dialogFragmentSpinner.getItemResult();

    }

    public GadgetSpinner(FragmentManager fragmentManager, int titleResource, ArrayList<String>items, final String TAG ) {
        this.title = titleResource;
        this.cancelable = false;
        this.items =  new String[items.size()];
        for( int i = 0; i < items.size(); i++ ){
            this.items[i] = items.get( i );
        }
        this.textButtonPositive = R.string.button_next;
        this.textButtonNegative = R.string.button_return;
        this.dialogFragmentSpinner = new DialogFragmentSpinner(this.title,
                this.items,
                this.textButtonPositive,
                this.textButtonNegative,
                this.cancelable);
        this.dialogFragmentSpinner.show( fragmentManager, TAG);
        this.itemResultSelect = this.dialogFragmentSpinner.getItemResult();

    }

    public String[] getItems() {
        return items;
    }

    public int getItemResult() {
        return itemResultSelect = this.dialogFragmentSpinner.getItemResult();
    }

    public String textItem(int id){
        return this.items[id];
    }

    public DialogFragmentSpinner getDialogFragmentSpinner() {
        return dialogFragmentSpinner;
    }

}
