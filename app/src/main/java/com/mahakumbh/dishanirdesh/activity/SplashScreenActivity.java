package com.mahakumbh.dishanirdesh.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.database.SharedPrefs;
import com.mahakumbh.dishanirdesh.misc.DataInitializer;

import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIME_OUT = 2000;
    SharedPrefs shardPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        shardPrefs = new SharedPrefs(SplashScreenActivity.this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // This method is used so that your splash activity can cover the entire screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                switchActivity();
            }
        }, SPLASH_SCREEN_TIME_OUT);


        //Initializing Data
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                new DataInitializer(SplashScreenActivity.this);
            }
        });

    }

    private void switchActivity(){

        if(!shardPrefs.getLangsetup()){
            //goto Language Screen
            Intent i = new Intent(SplashScreenActivity.this, LanguageActivity.class);
            startActivity(i); // invoke the SecondActivity.
            finish();

            return;
        }

        Locale locale = new Locale(shardPrefs.getAppLanguage());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        Intent i = new Intent(SplashScreenActivity.this, DashboardActivity.class);
        startActivity(i); // invoke the SecondActivity.
        finish();


    }
}