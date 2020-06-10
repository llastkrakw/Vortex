package com.example.vortex.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.vortex.R;
import com.example.vortex.main.fragments.BookListFragment;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookActivity extends AppCompatActivity implements View.OnClickListener, PermissionsListener {

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
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;

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

        fragmentTransaction = fragmentManager.beginTransaction();
        BookListFragment fragment = BookListFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container,  fragment);
        fragmentTransaction.commit();


        searchView = (FloatingSearchView) findViewById(R.id.search_view);



        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                Toast.makeText(BookActivity.this, currentQuery, Toast.LENGTH_LONG).show();

                fragment.RecyclerUpdater(currentQuery);
            }
        });


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
                    BookActivity.this.mapboxMap = mapboxMap;
                    mapboxMap.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
                        @Override
                        public void onStyleLoaded(@NonNull Style style) {

                            // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                            enableLocationComponent(style);

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

    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
      // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

        // Get an instance of the LocationComponent.
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

        // Activate the LocationComponent
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(this, loadedMapStyle).build());

         // Enable the LocationComponent so that it's actually visible on the map
            locationComponent.setLocationComponentEnabled(true);

        // Set the LocationComponent's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

      // Set the LocationComponent's render mode
            locationComponent.setRenderMode(RenderMode.NORMAL);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

}



