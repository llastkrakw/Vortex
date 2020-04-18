package com.example.vortex.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.vortex.PaiementStates.PaiementStateOrangeOrMtn;
import com.example.vortex.R;
import com.example.vortex.main.fragments.BookListFragment;
import com.example.vortex.main.fragments.CarteFragment;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;

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
    private ImageView filter;
    private FloatingSearchView searchView;
    private FrameLayout fragmentContainer;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.spacebook);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        fragmentManager = this.getSupportFragmentManager();
        back = (ImageView) findViewById(R.id.backtomain);
        list = (ImageView) findViewById(R.id.list_buttton);
        location = (ImageView) findViewById(R.id.location_button);
        start = (ImageView) findViewById(R.id.ratting_button);
        filter = (ImageView) findViewById(R.id.filter);
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

        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = BookListFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container,  fragment);
        fragmentTransaction.commit();


        Mapbox.getInstance(BookActivity.this, getString(R.string.map_token));

        if (savedInstanceState == null) {

            // Create fragment
            //final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Build mapboxMap
            MapboxMapOptions options = MapboxMapOptions.createFromAttributes(BookActivity.this, null);
            options.camera(new CameraPosition.Builder()
                    .target(new LatLng(-52.6885, -70.1395))
                    .zoom(9)
                    .build());

            // Create map fragment
            mapFragment = SupportMapFragment.newInstance(options);

            // Add map fragment to parent container
        } else {
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag("com.mapbox.map");
        }

        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull MapboxMap mapboxMap) {
                    mapboxMap.setStyle(Style.SATELLITE, new Style.OnStyleLoaded() {
                        @Override
                        public void onStyleLoaded(@NonNull Style style) {

                            // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                        }
                    });
                }
            });
        }else {
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag("com.mapbox.map");
        }


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(BookActivity.this);
                dialog.setContentView(R.layout.activity_popup3);
                ImageView ok = dialog.findViewById(R.id.close);
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


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.list_buttton:
                list.setBackground(getResources().getDrawable(R.drawable.button_list_bg));
                location.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                start.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                //
                //
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = BookListFragment.newInstance();
                fragmentTransaction.replace(R.id.fragment_container,  fragment);
                fragmentTransaction.commit();
                break;
            case R.id.location_button:
                list.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                location.setBackground(getResources().getDrawable(R.drawable.button_list_bg));
                start.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                //
                //
                final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_container, mapFragment, "com.mapbox.map");
                transaction.commit();
                break;
            case R.id.ratting_button:
                list.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                location.setBackground(getResources().getDrawable(R.drawable.button_list_bg2));
                start.setBackground(getResources().getDrawable(R.drawable.button_list_bg));
                break;
        }
    }
}
