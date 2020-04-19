package com.example.vortex.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vortex.paiementStates.PaiementState;
import com.example.vortex.R;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import static com.example.vortex.helper.ImageResize.decodeSampledBitmapFromResource;

public class TicketActivity extends AppCompatActivity {

    private SpaceNavigationView spaceNavigationView;
    private ImageView back;
    private Bundle extras = null;
    private ImageView img_agence;
    private TextView name_agence;
    private TextView name_passenger;
    private TextView name_ville1;
    private TextView name_ville2;
    private TextView boarding;
    private TextView travelId;
    private TextView date;
    private TextView classe;
    private TextView seat;
    private TextView number;
    private Button pay;
    private ImageView add_passenger;
    private ImageView change_place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.spaceticket);
        back = (ImageView) findViewById(R.id.backtobook);
        img_agence = (ImageView) findViewById(R.id.img_agence);
        name_agence = (TextView) findViewById(R.id.name_agence);
        name_passenger = (TextView) findViewById(R.id.name_passenger);
        name_ville1 = (TextView) findViewById(R.id.ville_1);
        name_ville2 = (TextView) findViewById(R.id.ville_2);
        boarding = (TextView) findViewById(R.id.boarding);
        travelId = (TextView) findViewById(R.id.travel_id);
        date = (TextView) findViewById(R.id.date);
        classe = (TextView) findViewById(R.id.classe);
        seat = (TextView) findViewById(R.id.seat);
        number = (TextView) findViewById(R.id.number_passenger);
        pay = (Button) findViewById(R.id.pay);
        add_passenger = (ImageView) findViewById(R.id.add_passenger);
        change_place = (ImageView) findViewById(R.id.change_place);

        extras = getIntent().getExtras();
        img_agence.setImageBitmap(decodeSampledBitmapFromResource(getResources(), extras.getInt("image_agence"), 32, 32));
        name_agence.setText(extras.getString("name_agence"));
        name_ville1.setText(extras.getString("depart"));
        name_ville2.setText(extras.getString("arrive"));
        boarding.setText(extras.getString("boarding"));
        date.setText(extras.getString("date"));
        classe.setText(extras.getString("classe"));

        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_airline_seat_individual_suite_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_local_taxi_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_star_half_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_history_black_24dp));

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(TicketActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                spaceNavigationView.setCentreButtonSelectable(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(TicketActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(TicketActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(TicketActivity.this, BookActivity.class);
                startActivity(main);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay = new Intent(TicketActivity.this, PaiementState.class);
                startActivity(pay);
            }
        });

        add_passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(TicketActivity.this);
                dialog.setContentView(R.layout.activity_popup5);
                Button ok = dialog.findViewById(R.id.btn_ok_popup5);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        change_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(TicketActivity.this);
                dialog.setContentView(R.layout.activity_popup7);
                Button ok = dialog.findViewById(R.id.btn_ok_popup7);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }
}
