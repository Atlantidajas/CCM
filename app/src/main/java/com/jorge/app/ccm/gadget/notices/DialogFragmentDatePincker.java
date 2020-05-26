package com.jorge.app.ccm.gadget.notices;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DialogFragmentDatePincker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private Context context;
    private String TAG;
    private int day;
    private int month;
    private int year;
    private Calendar currentDate;
    private DatePickerDialog datePickerDialog;

    public DialogFragmentDatePincker(Context context) {
        this.context = context;
        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        datePickerDialog = new DatePickerDialog( context, this, year, month, day);
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
       this.day = day;
       this.month = month;
       this.year = year;
    }

    public int getDayInt() {
        return day;
    }

    public int getMonthInt( int regulation ) {
        return month + regulation;
    }

    public int getYearInt() {
        return year;
    }

    public String getDayString() {
        return String.valueOf( day );
    }

    public String getMonthString( int regulation) {
        return String.valueOf( month + regulation);
    }

    public String getYearString() {
        return String.valueOf( year );
    }

    public String getDateFotmat( int regulationMonth ){
        return String.valueOf( day ) + "-" + String.valueOf( month + regulationMonth ) + "-" + String.valueOf( year );
    }

}