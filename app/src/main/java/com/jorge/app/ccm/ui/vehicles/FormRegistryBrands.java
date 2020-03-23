package com.jorge.app.ccm.ui.vehicles;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSelect;

public class FormRegistryBrands extends DialogFragment implements DialogFragmentSelect.DialogFragmentListener{

    private DialogFragmentSelect dialogFragmentSelect;
    private int title;
    final String items[];
    private boolean cancelable;
    private int itemResultSelect;

    public FormRegistryBrands(FragmentManager fragmentManager, String items[]) {
        this.title = R.string.title_dialog_fragmen_registre_vehicles_list_brands;
        this.cancelable = false;
        this.items = items;
        this.dialogFragmentSelect = new DialogFragmentSelect(this.title, this.items, this.cancelable);
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

    @Override
    public void onDialogItemClick(DialogFragment dialog) {
        // Pendiente de colocar lo que desea que haga al pulsar onclik
    }

}
