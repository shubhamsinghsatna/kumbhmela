package com.mahakumbh.dishanirdesh.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.fragment.Location.EntityLocationDetailsFragment;
import com.mahakumbh.dishanirdesh.models.EntityLocationModel;

public class LocationDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentLocationMarker;

    private EntityLocationModel currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentLocation = (EntityLocationModel) getIntent().getSerializableExtra("currentLocation");
        setUpToolBar();


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


    }


    private void showCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null && googleMap != null) {
                        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                        // If the marker is not already set, add a new marker
                        if (currentLocationMarker == null) {
                            currentLocationMarker = googleMap.addMarker(new MarkerOptions()
                                    .position(currentLatLng)
                                    .title("You are here")); // Blue marker
                        } else {
                            // Update the position of the existing marker
                            currentLocationMarker.setPosition(currentLatLng);
                        }


                    }
                });
    }

    private void setUpToolBar() {
        ImageView back = findViewById(R.id.iv_back);
        back.setOnClickListener(v -> onBackPressed());

        TextView pageTitle = findViewById(R.id.tv_page_title);
        pageTitle.setText(currentLocation.getTitle());
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        this.googleMap = map;

        // Enable MyLocation layer to show the blue dot
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }

        //Setup location on map
        googleMap.addMarker(new MarkerOptions()
                .position(currentLocation.getLocation())
                .title(currentLocation.getTitle()));
        // Move the camera to the current location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation.getLocation(), 20f));

        showCurrentLocation();


        new EntityLocationDetailsFragment(currentLocation).show(getSupportFragmentManager(), "Location Details");
    }



}