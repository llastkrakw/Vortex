package com.example.vortex.splah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vortex.Appli;
import com.example.vortex.Connectivity;
import com.example.vortex.R;

public class SplashScreen extends AppCompatActivity implements  Connectivity.ConnectivityListenner{

    Dialog ConnectDialog;
    private TextView connect;
    private TextView mess1;
    private TextView  mess2;
    private ImageView failed;
    private ImageView  fail;
    private Button clos;
    private Button  retr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ConnectDialog=new Dialog(this);

        connect=(TextView) findViewById(R.id.Connection);
        mess1=(TextView) findViewById(R.id.message1);
        mess2=(TextView) findViewById(R.id.message2);
        failed=(ImageView) findViewById(R.id.failed);
        fail=(ImageView) findViewById(R.id.fail);
        clos=(Button) findViewById(R.id.close);
        retr=(Button) findViewById(R.id.retry);

        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            |View.SYSTEM_UI_FLAG_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }

        checkInternet();


    }

   private  void checkInternet()
    {
        boolean isConnected= Connectivity.isConnected();
        ChangeActivity(isConnected);
    }

    private  void ChangeActivity(boolean isConnected)
    {

        if(isConnected)
        {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, IntroActivity.class));
                    finish();
                }
            },5000);
        }

        else
        {
            ConnectDialog.setContentView(R.layout.verify_connection);
            ConnectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ConnectDialog.show();

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        final IntentFilter intentfilt= new IntentFilter();
        intentfilt.addAction(ConnectivityManager.EXTRA_CAPTIVE_PORTAL);

        Connectivity receiver= new Connectivity();
        registerReceiver(receiver, intentfilt);

        Appli.getInstance().setConnectivity(this);
    }

    @Override
    public void onNetWorkConnectionChanged(boolean isConnected) {
        ChangeActivity(isConnected);
    }


}
