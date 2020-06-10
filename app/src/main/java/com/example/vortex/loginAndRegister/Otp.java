package com.example.vortex.loginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vortex.R;
import com.example.vortex.main.MainActivity;
import com.example.vortex.message.ApiClient;
import com.example.vortex.message.ApiInterface;
import com.example.vortex.message.MessageResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Otp extends AppCompatActivity {

    private CircularImageView userprofile;
    private TextView username;
    private Button started;
    private OtpView otpview;
    private EditText user_number;
    private Button buttonCode;
    private String number;
    private Bundle extras;
    private String  API_KEY = "a1094647-6b4a-11ea-9fa5-0200cd936042";
    private String sessionId;
    private static int RESULT_LOAD_IMAGE = 1;

    String OTP = "";
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        extras = getIntent().getExtras();

        assert extras != null;
        number = extras.getString("number");

        userprofile = (CircularImageView) findViewById(R.id.profileVerif);
        username = (TextView) findViewById(R.id.usernameVerif);
        otpview = (OtpView) findViewById(R.id.otp_view);
        user_number = (EditText) findViewById(R.id.phoneverif);
        buttonCode = (Button) findViewById(R.id.buttonCode);

        user_number.setText(number);

        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            assert selectedImage != null;
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            userprofile.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
