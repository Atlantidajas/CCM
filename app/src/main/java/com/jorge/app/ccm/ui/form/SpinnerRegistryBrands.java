package com.jorge.app.ccm.ui.form;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSpinner;

public class SpinnerRegistryBrands extends DialogFragment implements DialogFragmentSpinner.DialogFragmentListener{

    private DialogFragmentSpinner dialogFragmentSpinner;
    private int title;
    final String items[];
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;
    private int itemResultSelect;

    public SpinnerRegistryBrands(FragmentManager fragmentManager, String items[]) {
        this.title = R.string.form_registry_brans_title;
        this.cancelable = false;
        this.items = items;
        this.textButtonPositive = R.string.form_registry_brans_button_next;
        this.textButtonNegative = R.string.form_registry_brans_button_return;
        this.dialogFragmentSpinner = new DialogFragmentSpinner(this.title,
                this.items,
                this.textButtonPositive,
                this.textButtonNegative,
                this.cancelable);
        this.dialogFragmentSpinner.show( fragmentManager, "RegistreVehicleActivity");
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


    //---- Interface (Implementada)
    // Implemento para obligar a implementar cuando se cree el objeto
    @Override
    public void onDialogItemClick(DialogFragment dialog) {
    }

    // Implemento para obligar a implementar cuando se cree el objeto
    @Override
    public void onDialogFragmentSelectPositiveClick(DialogFragment dialog) {
    }

    // Implemento para obligar a implementar cuando se cree el objeto
    @Override
    public void onDialogFragmentSelectNegativeClick(DialogFragment dialog) {
    }

}
