package com.jorge.app.ccm.ui.alertsDialogos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DialogFragmentDatePincker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private int day;
    private int month;
    private int year;
    private Calendar currentDate;
    private DatePickerDialog datePickerDialog;

    public DialogFragmentDatePincker() {
        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
       this.day = day;
       this.month = month;
       this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}