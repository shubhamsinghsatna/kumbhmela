package com.mahakumbh.dishanirdesh.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.database.SharedPrefs;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    CardView cvContinue;
    Spinner dropdownMenu;


    SharedPrefs shardPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_language);
        shardPrefs= new SharedPrefs(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Spinner and TextView
        dropdownMenu = findViewById(R.id.dropdown_menu);

        cvContinue = findViewById(R.id.cvContinue);

        cvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shardPrefs.setLangsetup(true);

                Intent i =  new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(i);
            }
        });

        // Data for Dropdown Menu
        String[] items = {"English", "Hindi"};

        // Create an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownMenu.setAdapter(adapter);

        // Handle item selection
        dropdownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update TextView with selected item
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Switch language based on selection
                switch (selectedItem) {
                    case "English":
                        changeLanguage("en"); // ISO language code for English
                        break;
                    case "Hindi":
                        changeLanguage("hi"); // ISO language code for Hindi
                        break;

                    default:
                        changeLanguage("en"); // ISO language code for English
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle case when no item is selected
            }
        });
    }

    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        shardPrefs.setAppLanguage(languageCode);


    }
}