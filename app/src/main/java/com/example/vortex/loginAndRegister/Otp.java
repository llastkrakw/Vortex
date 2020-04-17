package com.example.vortex.loginAndRegister;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vortex.R;
import com.example.vortex.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.mukesh.OtpView;

import java.util.Random;

public class Otp extends AppCompatActivity {

    private CircularImageView userprofile;
    private TextView username;
    private Button started;
    private OtpView otpview;
    private EditText user_number;
    private Button buttonCode;
    private String  API_KEY = "a1094647-6b4a-11ea-9fa5-0200cd936042";
    private String sessionId;

    String OTP = "";
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        userprofile = (CircularImageView) findViewById(R.id.profileVerif);
        username = (TextView) findViewById(R.id.usernameVerif);
        otpview = (OtpView) findViewById(R.id.otp_view);
        user_number = (EditText) findViewById(R.id.phoneverif);
        buttonCode = (Button) findViewById(R.id.buttonCode);

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

        for(int i=0; i<4; i++){
            OTP += random.nextInt(9);
        }

        buttonCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<MessageResponse> call = apiService.sentOTP(API_KEY, user_number.getText().toString(), OTP);
                call.enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        sessionId = response.body().getDetails();
                        Toast.makeText(Otp.this, "See your message", Toast.LENGTH_LONG).show();
                        Log.d("SenderID", sessionId);
                        //you may add code to automatically fetch OTP from messages.
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Log.e("ERROR", t.toString());
                    }

                });*/


              Intent main = new Intent(Otp.this, MainActivity.class);
              startActivity(main);

            }
        });


        //otp vefif
/*
        otpview.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<MessageResponse> call = apiService.verifyOTP(API_KEY, sessionId, otp);

                call.enqueue(new Callback<MessageResponse>() {

                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {

                        try {
                            if(response.body().getStatus().equals("Success")){
                                Toast.makeText(Otp.this, "Success", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(Otp.this,test.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(Otp.this, "Failed", Toast.LENGTH_LONG).show();
                                Log.d("Failure", response.body().getDetails()+"|||"+response.body().getStatus());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Log.e("ERROR", t.toString());
                    }

                });
            }
        });
*/

    }
}
