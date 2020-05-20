package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vortex.R;
import com.example.vortex.models.SaveDatabase;

import java.util.Random;

public class ResetPasswordBoth extends AppCompatActivity {
    private final AppCompatActivity activity = ResetPasswordBoth.this;
    private Button next2;
    private SaveDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_both);

        appDatabase = SaveDatabase.getAppDatabase(activity);
        next2 = (Button) findViewById(R.id.buttonNext2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
        /*Intent intent = new Intent(ResetPasswordBoth.this, ResetPasswordThird.class);
        startActivity(intent);*/
    }

    private void sendMail() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        String txt=pref.getString("Mail", null);
        String message = " The new password is " + Generator(7) +".";
        String subject ="New Password of account";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,txt,subject,message);

        javaMailAPI.execute();

    }

    public String Generator(int length)
    {
        char[] chars ="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890".toCharArray();
        StringBuilder builder= new StringBuilder();
        Random random=new Random();
        for(int i=0; i<length;i++)
        {
            char c= chars[random.nextInt(chars.length)];
            builder.append(c);
        }

        return builder.toString();
    }
}
