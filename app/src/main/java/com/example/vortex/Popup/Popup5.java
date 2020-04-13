package com.example.vortex.Popup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vortex.R;

public class Popup5 extends AppCompatActivity {
    private Spinner spinner_code_phone;
    private String[] table_spinner_code ={"+237", "+224", "+221", "+91", "+1","+331"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup5);
        spinner_code_phone = findViewById(R.id.popup5_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, table_spinner_code);
        spinner_code_phone.setAdapter(arrayAdapter);
    }
}
