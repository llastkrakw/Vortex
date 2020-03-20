package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vortex.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.mukesh.OtpView;

public class Otp extends AppCompatActivity {

    CircularImageView userprofile;
    TextView username;
    Button started;
    OtpView otpview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        userprofile = (CircularImageView) findViewById(R.id.profileVerif);
        username = (TextView) findViewById(R.id.usernameVerif);
        started = (Button) findViewById(R.id.buttonCode);
        otpview = (OtpView) findViewById(R.id.otp_view);

        //otpview.setItemRadius(10);

        //
        // Set Color
        userprofile.setCircleColor(Color.WHITE);
// or with gradient
        userprofile.setCircleColorStart(Color.BLACK);
        userprofile.setCircleColorEnd(Color.RED);
        userprofile.setCircleColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Set Border
        userprofile.setBorderWidth(10f);
        userprofile.setBorderColor(Color.BLACK);
// or with gradient
        userprofile.setBorderColorStart(Color.BLACK);
        userprofile.setBorderColorEnd(Color.RED);
        userprofile.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Add Shadow with default param
        userprofile.setShadowEnable(true);
// or with custom param
        userprofile.setShadowRadius(10f);
        userprofile.setShadowColor(Color.RED);
        userprofile.setShadowGravity(CircularImageView.ShadowGravity.CENTER);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            username.setText(personName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(userprofile);
        }

        started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Otp.this, test.class);
                startActivity(intent);
            }
        });

    }
}
