package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vortex.R;

public class ResetPasswordBoth extends AppCompatActivity {

    private Button next2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_both);

        next2 = (Button) findViewById(R.id.buttonNext2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPasswordBoth.this, ResetPasswordThird.class);
                startActivity(intent);
            }
        });
    }
}
