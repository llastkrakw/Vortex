package com.example.vortex.paiementStates;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.vortex.R;
import com.example.vortex.main.MainActivity;

import static com.example.vortex.helper.ImageResize.decodeSampledBitmapFromResource;

public class PaiementStateOrangeOrMtn extends AppCompatActivity {

   private Button btn_pay;
   private ImageView image_pay;
   private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paiement_state_orange_or_mtn);
        btn_pay = (Button) findViewById(R.id.btn_next_paiementstate_2);
        image_pay = (ImageView)  findViewById(R.id.image_pay);


        extras = getIntent().getExtras();

        if(extras != null)
            image_pay.setImageBitmap(decodeSampledBitmapFromResource(getResources(), extras.getInt("image_id"), 120, 60));

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(PaiementStateOrangeOrMtn.this);
                dialog.setContentView(R.layout.activity_popup1);
                Button ok = dialog.findViewById(R.id.btn_pop1);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent main = new Intent(PaiementStateOrangeOrMtn.this, MainActivity.class);
                        startActivity(main);
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }
}
