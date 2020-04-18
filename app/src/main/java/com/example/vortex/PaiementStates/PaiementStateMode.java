package com.example.vortex.PaiementStates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.vortex.R;

public class PaiementStateMode extends AppCompatActivity implements View.OnClickListener {


    private ImageView paypal;
    private ImageView orange;
    private ImageView mtn;
    private ImageView bitcoin;
    private int image;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_state_mode);

        image = R.id.orange;

        paypal = (ImageView) findViewById(R.id.paypal);
        orange = (ImageView) findViewById(R.id.orange);
        mtn = (ImageView) findViewById(R.id.mtn);
        bitcoin = (ImageView) findViewById(R.id.bitcoin);

        paypal.setOnClickListener(this);
        orange.setOnClickListener(this);
        mtn.setOnClickListener(this);
        bitcoin.setOnClickListener(this);

        next = (Button) findViewById(R.id.btn_next_paiementstate_1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(PaiementStateMode.this, PaiementStateOrangeOrMtn.class);
                next.putExtra("image_id", image);
                startActivity(next);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.paypal :{
                paypal.setAlpha(1.0F);
                orange.setAlpha(0.4F);
                mtn.setAlpha(0.4F);
                bitcoin.setAlpha(0.4F);
                image = paypal.getId();
            }
                  break;
            case R.id.mtn :{
                mtn.setAlpha(1.0F);
                paypal.setAlpha(0.4F);
                orange.setAlpha(0.4F);
                bitcoin.setAlpha(0.4F);
                image = mtn.getId();
            }
                break;
            case R.id.bitcoin : {
                orange.setAlpha(0.4F);
                paypal.setAlpha(0.4F);
                mtn.setAlpha(0.4F);
                bitcoin.setAlpha(1.0F);
                image = bitcoin.getId();
            }
                break;
            default: {
                orange.setAlpha(1.0F);
                paypal.setAlpha(0.4F);
                mtn.setAlpha(0.4F);
                bitcoin.setAlpha(0.4F);
                    image = orange.getId();
            }
        }
    }
}
