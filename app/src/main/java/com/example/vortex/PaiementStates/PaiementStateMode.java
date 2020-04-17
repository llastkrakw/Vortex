package com.example.vortex.PaiementStates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.vortex.R;

public class PaiementStateMode extends AppCompatActivity {

    int[] moneyImages ={R.drawable.orange_money,R.drawable.mtn,R.drawable.paypal,R.drawable.bitcoin};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_state_mode);
    }
}
