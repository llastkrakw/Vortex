package com.example.vortex.loginAndRegister;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InputValidation {

    private Context context;

    /**
     * constructor
     *
     * @param context
     */
    public InputValidation(Context context) {
        this.context = context;
    }

    /**
     * method to check InputEditText filled .
     *
     * @param textInputEditText
     * @param message
     * @return
     */
    public boolean isInputEditTextFilled(EditText textInputEditText, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputEditText.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }

        return true;
    }


    /**
     * method to check InputEditText has valid email .
     *
     * @param textInputEditText
     * @param message
     * @return
     */
    public boolean isInputEditTextEmail(EditText textInputEditText, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputEditText.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        return true;
    }

    public boolean isInputEditTextMatches(EditText textInputEditText1, EditText textInputEditText2, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            textInputEditText1.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        }
        return true;
    }

    /**
     * method to Hide keyboard
     *
     * @param view
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
