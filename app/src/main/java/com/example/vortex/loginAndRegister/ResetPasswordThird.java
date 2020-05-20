package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vortex.R;
import com.example.vortex.models.DAO.UserDAO;
import com.example.vortex.models.DTO.User;
import com.example.vortex.models.SaveDatabase;

public class ResetPasswordThird extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = ResetPasswordThird.this;

    private Button next;
    private EditText TextHoldPass;
    private EditText TextNewPass;

    private InputValidation inputValidation;
    private SaveDatabase appDatabase;
    private User user;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_third);

        initView();
        initListeners();
        initObjects();
    }

    public void initView()
    {
        appDatabase = SaveDatabase.getAppDatabase(activity);
        next = (Button) findViewById(R.id.buttonNext4);
        TextHoldPass = (EditText) findViewById(R.id.register_email);
        TextNewPass = (EditText) findViewById(R.id.register_email2);
    }

    private void initListeners() {
        next.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        user = new User();

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNext4:
                verifyFromSQLite();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {

        if (!inputValidation.isInputEditTextFilled(TextHoldPass, getString(R.string.error_message_email))) {
            return;
        } else if (!inputValidation.isInputEditTextFilled(TextNewPass, getString(R.string.error_message_email))) {
            return;
        } else {

            String Email = TextHoldPass.getText().toString().trim();

            User user = appDatabase.userDao().findByEmail(Email);
            if (user != null) {
                if (user.getEmail().equalsIgnoreCase(Email)) {
                    Intent accountsIntent = new Intent(activity, ResetPasswordBoth.class);
                    startActivity(accountsIntent);
                    emptyInputEditText();

                } else {
                    Toast.makeText(ResetPasswordThird.this, getString(R.string.error_message_email), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(ResetPasswordThird.this, getString(R.string.error_email), Toast.LENGTH_LONG).show();
            }

        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        TextHoldPass.setText(null);
        TextNewPass.setText(null);
    }
}
