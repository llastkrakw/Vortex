package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vortex.PaiementStates.PaiementStateOrangeOrMtn;
import com.example.vortex.ProfileAndSetting.Setting;
import com.example.vortex.R;
import com.example.vortex.main.MainActivity;

public class Login extends AppCompatActivity {

    private Button btn_sign_in_login;
    private TextView register;
    private TextView forgot;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.buttonLogin);
        btn_sign_in_login = (Button) findViewById(R.id.buttonLogin);
        register = (TextView) findViewById(R.id.login_register);
        forgot = (TextView) findViewById(R.id.login_forgot);

        btn_sign_in_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(Login.this, MainActivity.class);
                startActivity(main);
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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, PaiementStateOrangeOrMtn.class);
                startActivity(i);
            }
        });
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );

    }
}
