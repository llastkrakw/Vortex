package com.example.vortex.PaiementStates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vortex.R;

public class PaiementState2 extends AppCompatActivity {
Button btn_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_state2);
        btn_pay = findViewById(R.id.btn_next_paiementstate_2);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PaiementState2.this,PaiementState3.class);
                startActivity(i);
            }
        });
    }
}
