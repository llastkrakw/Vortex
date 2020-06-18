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

public class Setting extends AppCompatActivity{

    private CircularImageView userprofile;
    private TextView userName;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        userName = (TextView) findViewById(R.id.user_name_profile);
        userprofile = (CircularImageView) findViewById(R.id.profile_pic);
        back = (ImageView) findViewById(R.id.backspace_blanc);

        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(Setting.this, Profile.class);
                startActivity(profile);
            }
        });

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
            if(personPhoto != null)
                Glide.with(this).load(String.valueOf(personPhoto)).into(userprofile);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(Setting.this, MainActivity.class);
                startActivity(main);
            }
        });

    }

/*    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "élément 1 séléctionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "élément 2 séléctionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "élément 3 séléctionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Toast.makeText(this, "élément 4 séléctionné", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }*/
}
