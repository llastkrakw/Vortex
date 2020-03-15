package com.example.vortex.splah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.vortex.loginAndRegister.Login;
import com.example.vortex.R;
import com.example.vortex.adapters.IntroViewPagerAdapter;
import com.example.vortex.models.forview.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {


    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btn_next;
    int position = 0 ;
    Button btnGetStarted;
    Button skip;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        //make the activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // when this activity is about to be launch we need to check if its openend before or not

        //if(restorePrefData()){

            //Intent mainactivity = new Intent(getApplicationContext(), Login.class);
           // startActivity(mainactivity);
           // fileList();
        //}

        setContentView(R.layout.activity_intro);



        //ini views

        btn_next = findViewById(R.id.btn_next);
        tabIndicator=findViewById(R.id.tab_indicator);
        btnGetStarted = findViewById(R.id.btn_get_started);
        skip = findViewById(R.id.btn_skip);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btton_animation);

        //fill list screen
       final List<ScreenItem>  mList = new ArrayList<>();
        mList.add(new ScreenItem(getResources().getString(R.string.onboarding_text1),getResources().getString(R.string.onboarding_body),R.drawable.image1));
        mList.add(new ScreenItem(getResources().getString(R.string.onboarding_text2),getResources().getString(R.string.onboarding_body2),R.drawable.image2));
        mList.add(new ScreenItem(getResources().getString(R.string.onboarding_text3),getResources().getString(R.string.onboarding_body3),R.drawable.image3));
        mList.add(new ScreenItem(getResources().getString(R.string.onboarding_text4),getResources().getString(R.string.onboarding_body4),R.drawable.image5));
        mList.add(new ScreenItem(getResources().getString(R.string.onboarding_text5),"",R.drawable.image6));

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(IntroActivity.this, Login.class);
                startActivity(i);
            }
        });

        //setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup tablayout with views pagers

        tabIndicator.setupWithViewPager(screenPager);

        //next button click listener
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if(position < mList.size()){

                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size()-1){

                    //TODO : show the GETSTARTED Button and hide the indicator and the next button
                    laodLastScreen();
                }

            }
        });

        // tablayout add change listener

        tabIndicator.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() ==  mList.size()-1){
                    laodLastScreen();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // Get started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //open main activity

                Intent mainActivity = new Intent(getApplicationContext(),Login.class);
                startActivity(mainActivity);

                // also we need to save a boolean value to storage so next time when the user run the app
                //we could knox that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process

                savePrefsData();
                finish();

            }
        });


    }

    private boolean restorePrefData() {
        SharedPreferences pref =getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore =pref.getBoolean("isIntroOpnend",false);
        return isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();

    }

    //show the GETSTARTED Button and hide the indicator and the next button

    private void laodLastScreen() {

        skip.setVisibility(View.INVISIBLE);
        btn_next.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
       // TODO : ADD an animation the getstarted button

        // set up animation

        btnGetStarted.setAnimation(btnAnim);
    }
}
