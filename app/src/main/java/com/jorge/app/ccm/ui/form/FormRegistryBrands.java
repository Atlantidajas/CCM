package com.jorge.app.ccm.ui.form;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSelect;

public class FormRegistryBrands extends DialogFragment implements DialogFragmentSelect.DialogFragmentListener{

    private DialogFragmentSelect dialogFragmentSelect;
    private int title;
    final String items[];
    private int textButtonPositive;
    private int textButtonNegative;
    private boolean cancelable;
    private int itemResultSelect;

    public FormRegistryBrands(FragmentManager fragmentManager, String items[]) {
        this.title = R.string.form_registry_brans_title;
        this.cancelable = false;
        this.items = items;
        this.textButtonPositive = R.string.form_registry_brans_button_next;
        this.textButtonNegative = R.string.form_registry_brans_button_return;
        this.dialogFragmentSelect = new DialogFragmentSelect(this.title,
                this.items,
                this.textButtonPositive,
                this.textButtonNegative,
                this.cancelable);
        this.dialogFragmentSelect.show( fragmentManager, "RegistreVehicleActivity");
        this.itemResultSelect = this.dialogFragmentSelect.getItemResult();

    }

    public String[] getItems() {
        return items;
    }

    public int getItemResult() {
        return itemResultSelect = this.dialogFragmentSelect.getItemResult();
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
        // Pendiente de colocar lo que desea que haga al pulsar onclik
    }

    // Implemento para obligar a implementar cuando se cree el objeto
    @Override
    public void onDialogFragmentSelectNegativeClick(DialogFragment dialog) {
        // Pendiente de colocar lo que desea que haga al pulsar onclik
    }

}
