package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vortex.R;
import com.example.vortex.main.MainActivity;
import com.example.vortex.models.DAO.UserDAO;
import com.example.vortex.models.DTO.User;
import com.example.vortex.models.SaveDatabase;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = Login.this;

    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;

    private InputValidation inputValidation;
    private SaveDatabase appDatabase;
    private User user;
    private UserDAO userDAO;

    private Button btn_sign_in_login;
    private TextView register;
    private TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        appDatabase = SaveDatabase.getAppDatabase(activity);
        textInputEditTextEmail = (EditText) findViewById(R.id.login_mail);
        textInputEditTextPassword = (EditText) findViewById(R.id.login_password);
        btn_sign_in_login = (Button) findViewById(R.id.buttonLogin);
        register = (TextView) findViewById(R.id.login_register);
        forgot = (TextView) findViewById(R.id.login_forgot);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        btn_sign_in_login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgot.setOnClickListener(this);
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
            case R.id.buttonLogin:
                verifyFromSQLite();
                break;
            case R.id.login_register:
                // Navigate to RegisterActivity
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                break;

            case R.id.login_forgot:
                // Navigate to RegisterActivity
                Intent intent1 = new Intent(Login.this, ResetPasswordFirst.class);
                startActivity(intent1);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, getString(R.string.error_message_email))) {
            return;
        }

        User user = appDatabase.userDao().findByName(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim());

        if(user != null) {
            if (user.getEmail().equalsIgnoreCase(textInputEditTextEmail.getText().toString().trim()) &&
                    user.getPassword().equalsIgnoreCase(textInputEditTextPassword.getText().toString().trim())) {
                Intent accountsIntent = new Intent(activity, Otp.class);
                emptyInputEditText();
                startActivity(accountsIntent);

            } else {
                // Snack Bar to show success message that record is wrong

                Toast.makeText(Login.this, getString(R.string.error_valid_email_password), Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(Login.this, "User not available", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }




}
