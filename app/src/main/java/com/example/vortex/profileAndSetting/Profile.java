package com.example.vortex.profileAndSetting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vortex.R;
import com.example.vortex.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mikhaellopez.circularimageview.CircularImageView;

public class Profile extends AppCompatActivity {

    private CircularImageView userprofile;
    private TextView userName;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = (TextView) findViewById(R.id.user_name_profile);
        userprofile = (CircularImageView) findViewById(R.id.profile_pic);
        back = (ImageView) findViewById(R.id.backspace_blanc);
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

            userName.setText(personName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(userprofile);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(Profile.this, MainActivity.class);
                startActivity(main);
            }
        });

    }
}
