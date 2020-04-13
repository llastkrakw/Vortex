package com.example.vortex.qrCode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vortex.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateActivity extends AppCompatActivity {
    private EditText etInput;
    private Button btnCreer;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);


        etInput=findViewById(R.id.etInput);
        btnCreer=findViewById(R.id.btnCreer);
        imageView=findViewById(R.id.imageView);
        btnCreer .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text=etInput.getText().toString().trim();
                if(text!=null){
                    MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix=multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
