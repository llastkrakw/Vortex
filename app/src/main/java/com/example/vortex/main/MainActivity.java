package com.example.vortex.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.vortex.R;
import com.example.vortex.adapters.TravelSmalAdapter;
import com.example.vortex.fakeForUi.TravelFake;
import com.example.vortex.main.fragments.DatePiker;
import com.example.vortex.main.fragments.MainFragment;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.vortex.main.fragments.AnimUtils.slideView;


public class MainActivity extends AppCompatActivity {

    private SpaceNavigationView spaceNavigationView;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private View historyContainer;
    private boolean isExpend = false;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        historyContainer = (RelativeLayout) findViewById(R.id.historyContainer);
        recyclerView = (RecyclerView) findViewById(R.id.smalhistoryRecycle);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_airline_seat_individual_suite_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_local_taxi_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_star_half_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_history_black_24dp));

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = MainFragment.newInstance();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();



        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(MainActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                spaceNavigationView.setCentreButtonSelectable(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

        //On long click listener

 /*       spaceNavigationView.setSpaceOnLongClickListener(new SpaceOnLongClickListener() {
            @Override
            public void onCentreButtonLongClick() {
                Toast.makeText(MainActivity.this,"onCentreButtonLongClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int itemIndex, String itemName) {
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });*/

        historyContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){

                    if(isExpend){
                        isExpend = false;
                        //historyContainer.bringToFront();
                        slideView(historyContainer, historyContainer.getLayoutParams().height, 350);
                    }
                    else{
                        isExpend = true;
                        //historyContainer.bringToFront();
                        slideView(historyContainer, historyContainer.getLayoutParams().height, 700);
                    }

                    return true;
                }

                return false;
            }
        });


        List<TravelFake> travels = new ArrayList<TravelFake>();
        TravelFake travel1 = new TravelFake(R.drawable.image_test, R.drawable.ic_star_gold_24dp,
                "1-10-2020", 10, "Douala", "Yaounde");
        TravelFake travel2 = new TravelFake(R.drawable.image_test, R.drawable.ic_star_gray_24dp,
                "4-3-2020", 10, "Yaounde", "Douala");
        TravelFake travel3 = new TravelFake(R.drawable.image_test, R.drawable.ic_star_gray_24dp,
                "24-3-2020", 10, "Yaounde", "Douala");
        TravelFake travel4 = new TravelFake(R.drawable.image_test, R.drawable.ic_star_gold_24dp,
                "28-5-2020", 10, "Douala", "Yaounde");

        travels.add(travel1);
        travels.add(travel2);
        travels.add(travel3);
        travels.add(travel4);

        RecyclerView.Adapter adapter = new TravelSmalAdapter(this, travels);


        recyclerView.setAdapter(adapter);

    }
}
