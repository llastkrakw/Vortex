package com.example.vortex.qrCode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vortex.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
    }
    public void btnnscan(View v) {
        mScannerView= new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

    }

    @Override
    public void handleResult(Result result) {
        Log.v("HandlerResult",result.getText());
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog=builder.create();

        alertDialog.show();

        mScannerView.resumeCameraPreview(this);

    }

    public void btngenere(View view) {
        Intent b=new Intent(this, GenerateActivity.class);
        startActivity(b);
    }
}
