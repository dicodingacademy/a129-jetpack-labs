package com.dicoding.academies.ui.home;

import android.os.Bundle;

import com.dicoding.academies.R;
import com.dicoding.academies.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeBinding activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        activityHomeBinding.viewPager.setAdapter(sectionsPagerAdapter);
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}