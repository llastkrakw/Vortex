package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vortex.R;
import com.example.vortex.models.DAO.UserDAO;
import com.example.vortex.models.DTO.User;
import com.example.vortex.models.SaveDatabase;

public class ResetPasswordFirst extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = ResetPasswordFirst.this;

    private Button next;
    private EditText TextEmail;

    private InputValidation inputValidation;
    private SaveDatabase appDatabase;
    private User user;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_first);

       initView();
       initListeners();
       initObjects();
    }

    public void initView()
    {
        appDatabase = SaveDatabase.getAppDatabase(activity);
        next = (Button) findViewById(R.id.buttonLogin);
        TextEmail=(EditText) findViewById(R.id.register_email);
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
            case R.id.buttonLogin:
                verifyFromSQLite();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {

        if (!inputValidation.isInputEditTextFilled(TextEmail, getString(R.string.error_message_email))) {
            return;
        } else if (!inputValidation.isInputEditTextEmail(TextEmail, getString(R.string.error_message_email))) {
            return;
        } else {

            String Email = TextEmail.getText().toString().trim();

            User user = appDatabase.userDao().findByEmail(Email);
            if (user != null) {
                if (user.getEmail().equalsIgnoreCase(Email)) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor =pref.edit();
                    editor.putString("Mail",Email);
                    editor.commit();
                    Intent accountsIntent = new Intent(activity, ResetPasswordBoth.class);
                    startActivity(accountsIntent);
                    emptyInputEditText();

                } else {
                    Toast.makeText(ResetPasswordFirst.this, getString(R.string.error_message_email), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(ResetPasswordFirst.this, getString(R.string.error_email), Toast.LENGTH_LONG).show();
            }

        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        TextEmail.setText(null);
    }
}
