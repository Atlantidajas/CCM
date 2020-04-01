package com.jorge.app.ccm.controllers;

import android.content.Context;
import android.widget.Toast;

import com.jorge.app.ccm.R;

public class ControllerResultOperating {

    private Context context;

    public ControllerResultOperating(Context context, Controller controller ) {
        this.context = context;
    }

    protected void getResultOperating( Boolean resultOperating){
        if( resultOperating == true){
            Toast.makeText( context, R.string.toast_message_si_registry, Toast.LENGTH_SHORT).show();
        }
        if( resultOperating == false ){
            Toast.makeText( context, R.string.toast_message_no_registry, Toast.LENGTH_SHORT).show();
        }
    }
}
