package com.example.vortex.PaiementStates;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vortex.R;

public class PaiementStatePaypalOrBitcoin extends AppCompatActivity {
    Button btn_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_state_paypal_or_bitcoin);
        btn_pay = findViewById(R.id.btn_next_paiementstate_2);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(PaiementStatePaypalOrBitcoin.this);
                dialog.setContentView(R.layout.activity_popup1);
                Button ok = dialog.findViewById(R.id.btn_pop1);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.setContentView(R.layout.activity_popup2);
                        Button ok2 = dialog.findViewById(R.id.btn_pop2);
                        ok2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss(); }
                        });
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }
}
