package com.mahakumbh.dishanirdesh.activity;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.fragment.DatesFragment;
import com.mahakumbh.dishanirdesh.fragment.HomeFragment;
import com.mahakumbh.dishanirdesh.fragment.MelaMapFragment;
import com.mahakumbh.dishanirdesh.fragment.MoreFragment;
import com.mahakumbh.dishanirdesh.fragment.SOSFragment;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_location) {
                selectedFragment = new MelaMapFragment();
            } else if (item.getItemId() == R.id.nav_sos) {
                selectedFragment = new SOSFragment();
            } else if (item.getItemId() == R.id.nav_dates) {
                selectedFragment = new DatesFragment();
            } else if (item.getItemId() == R.id.nav_more) {
                selectedFragment = new MoreFragment();
            }

            if (selectedFragment != null) {


                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                    getSupportFragmentManager().popBackStack();
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        if(getIntent().hasExtra("default")){
            bottomNavigationView.setSelectedItemId(R.id.nav_more);
        }

    }
}