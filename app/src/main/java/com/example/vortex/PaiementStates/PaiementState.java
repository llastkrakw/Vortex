package com.example.vortex.PaiementStates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.vortex.R;

public class PaiementState extends AppCompatActivity {

    private Button next;
    private Spinner spinner_code_phone;
    private String[] table_spinner_code ={"+237", "+224", "+221", "+91", "+1","+331"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_state);
        spinner_code_phone = findViewById(R.id.paiementstate_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, table_spinner_code);
        spinner_code_phone.setAdapter(arrayAdapter);


        next = (Button) findViewById(R.id.btn_next_paiementstate);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(PaiementState.this, PaiementStateMode.class);
                startActivity(next);
            }
        });
    }
}
