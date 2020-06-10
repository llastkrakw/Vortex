package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vortex.R;
import com.example.vortex.dataAcess.DatabaseHandler;
import com.example.vortex.dataAcess.Encrypt;
import com.example.vortex.main.MainActivity;
import com.example.vortex.models.DTO.User;

import java.util.List;

public class Login extends AppCompatActivity {

    private DatabaseHandler bd;
    private EditText Password;
    private EditText Email;
    private Button btn_sign_in_login;
    private TextView register;
    private TextView forgot;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Password = (EditText) findViewById(R.id.login_password);
        Email = (EditText) findViewById(R.id.login_mail);
        login = findViewById(R.id.buttonLogin);
        register = (TextView) findViewById(R.id.login_register);
        forgot = (TextView) findViewById(R.id.login_forgot);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String securePassword = Encrypt.md5(Password.getText().toString());
                if(SignIn(Email.getText().toString(), securePassword)){
                    Intent main = new Intent(Login.this, MainActivity.class);
                    startActivity(main);
                }
                else{
                    Toast.makeText(Login.this, "Connection Impossible", Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,ResetPasswordFirst.class);
                startActivity(intent);
            }
        });

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );

    }

    private boolean SignIn(String email, String password){

        bd = new DatabaseHandler(Login.this);
        List<User> list = bd.getAll();

        for(User user : list){

            if((user.getEmail().equals(email) || user.getUsername().equals(email)) && user.getPassword().equals(password)){
                return true;
            }
        }


        bd.close();
        return false;
    }
}
