package com.example.vortex.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.vortex.R;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    private SpaceNavigationView spaceNavigationView;
    private ImageView back;
    private ImageView list;
    private ImageView location;
    private ImageView start;
    private FloatingSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.spacebook);
        back = (ImageView) findViewById(R.id.backtomain);
        list = (ImageView) findViewById(R.id.list_buttton);
        location = (ImageView) findViewById(R.id.location_button);
        start = (ImageView) findViewById(R.id.ratting_button);
        list.setBackground(getResources().getDrawable(R.drawable.button_list_bg));
        location.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
        start.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
        list.setOnClickListener(this);
        location.setOnClickListener(this);
        start.setOnClickListener(this);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_airline_seat_individual_suite_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_local_taxi_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_star_half_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_history_black_24dp));

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(BookActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                spaceNavigationView.setCentreButtonSelectable(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(BookActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(BookActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(BookActivity.this, MainActivity.class);
                startActivity(main);
            }
        });

        searchView = (FloatingSearchView) findViewById(R.id.search_view);

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery

                Toast.makeText(BookActivity.this, newQuery, Toast.LENGTH_LONG).show();

                //pass them on to the search view
                //searchView.swapSuggestions();
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.list_buttton:
                list.setBackground(getResources().getDrawable(R.drawable.button_list_bg));
                location.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                start.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                break;
            case R.id.location_button:
                list.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                location.setBackground(getResources().getDrawable(R.drawable.button_list_bg));
                start.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                break;
            case R.id.ratting_button:
                list.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                location.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                start.setBackground(getResources().getDrawable(R.drawable.button_list_bg));
                break;
        }
    }
}
