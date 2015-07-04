package com.example.android.sunshine.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Arthur on 15-06-17.
 */
public class LocationEditTextPreference extends EditTextPreference {
    static final private int DEFAULT_MINIMUM_LOCATION_LENGTH=2;
    private int mMinLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a=context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.LocationEditTextPreference,
                0, 0);
        try{
            mMinLength=a.getInteger(R.styleable.LocationEditTextPreference_minLength, DEFAULT_MINIMUM_LOCATION_LENGTH);
        }
        finally {
            a.recycle();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);

        //Button button=this.get();

        EditText editText=this.getEditText();
         editText.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                             }

                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {

                                             }

                                             @Override
                                             public void afterTextChanged(Editable s) {

                                                 //onEditTextChanged();

                                                 Dialog dialog=getDialog();
                                                 if(dialog instanceof AlertDialog)
                                                 {

                                                     AlertDialog alertDialog=(AlertDialog)dialog;
                                                     Button button_positive=alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);

                                                     if(s.length()<mMinLength) {
                                                         button_positive.setEnabled(false);

                                                     }else {
                                                         // Re-enable the button.
                                                         button_positive.setEnabled(true);
                                                     }
                                                 }
                                                  }







                                         }
         );
    }

    //not using :
    protected void onEditTextChanged()
    {
        boolean enable = onCheckValue(getEditText().getText().toString());
        Dialog dlg = getDialog();
        if(dlg instanceof AlertDialog)
        {
            AlertDialog alertDlg = (AlertDialog)dlg;
            Button btn = alertDlg.getButton(AlertDialog.BUTTON_POSITIVE);
            btn.setEnabled(enable);
        }
    }
    protected boolean onCheckValue(String value)
    {
        return value.equals("a");
    }
}
