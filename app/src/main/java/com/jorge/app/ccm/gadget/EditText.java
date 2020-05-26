package com.jorge.app.ccm.gadget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

public class EditText extends androidx.appcompat.widget.AppCompatEditText  {

    Context context;
    String messageFieldRequired;
    private boolean statusFieldRequired;

    public EditText( final Context context, final String messageFieldRequired ) {
        super( context );
        this.context = context;
        this.messageFieldRequired = messageFieldRequired;


    }



}
