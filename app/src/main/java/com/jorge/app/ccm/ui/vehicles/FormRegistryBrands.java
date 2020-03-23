package com.jorge.app.ccm.ui.vehicles;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.ui.alertsDialogos.DialogFragmentSelect;

public class FormRegistryBrands extends DialogFragment implements DialogFragmentSelect.RegistryVehiclesBrandsDialogListener{

    private DialogFragmentSelect dialogFragmentSelect;
    private int title;
    final String items[] = {"Alfa Romero",
            "Audi",
            "BMW",
            "Cadillac",
            "Citroën",
            "Dacia",
            "Daewoo",
            "Fiat",
            "Ford",
            "Honda",
            "Hyunda",
            "Infiniti",
            "Isuzu",
            "Iveco",
            "Jaguar",
            "Jeep",
            "Kia",
            "Lada",
            "Lancia",
            "Lexus",
            "Lotus",
            "Mazda",
            "Mercedes",
            "Mini",
            "Mitsubishi",
            "Nissan",
            "Opel",
            "Peugeot",
            "Renault",
            "Rober",
            "Saab",
            "Sangyoung",
            "Saet",
            "Skoda",
            "Smart",
            "Subaru",
            "Suzuki",
            "Tata",
            "Tesla",
            "Toyota",
            "Volkswagen",
            "Volvo" };
    private boolean cancelable;
    private int itemResultSelect;

    public FormRegistryBrands(FragmentManager fragmentManager) {
        this.title = R.string.title_dialog_fragmen_registre_vehicles_list_brands;
        this.cancelable = false;
        this.dialogFragmentSelect = new DialogFragmentSelect(title, items, cancelable);
        this.itemResultSelect = this.dialogFragmentSelect.getItemResult();//<-- El resultado la selección
        this.dialogFragmentSelect.show( fragmentManager, "VehiclesListActivity");

    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setItemResult(int itemResult) {
        this.itemResultSelect = itemResult;
    }

    public int getTitle() {
        return title;
    }

    public String[] getItems() {
        return items;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public int getItemResult() {
        return itemResultSelect;
    }

    @Override
    public void onDialogItemClick(DialogFragment dialog) {
        // Pendiente de colocar lo que desea que haga al pulsar onclik
    }

}
