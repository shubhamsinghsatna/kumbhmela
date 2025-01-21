package com.mahakumbh.dishanirdesh.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.database.EntityLocationManager;
import com.mahakumbh.dishanirdesh.fragment.Location.EntitiesListBottomSheetFragment;
import com.mahakumbh.dishanirdesh.fragment.Location.MarkerDetailsBottomSheet;
import com.mahakumbh.dishanirdesh.models.EntityLocationModel;

import java.util.List;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private String category;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentLocationMarker;

    private LocationCallback locationCallback;
    private EntityLocationManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);
        dbManager = new EntityLocationManager(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        category = getIntent().getStringExtra("category");
        Log.e("cate","Category is:  "+category);
        setUpToolBar();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Initialize LocationRequest and LocationCallback
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000); // Update location every 5 seconds
        locationRequest.setFastestInterval(2000); // Get the location as quickly as possible (2 seconds)
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull com.google.android.gms.location.LocationResult locationResult) {
                if (locationResult != null && locationResult.getLocations() != null) {
                    for (Location location : locationResult.getLocations()) {
                        if (location != null) {
                            //updateLocationMarker(location); // TODO
                        }
                    }
                }
            }
        };

        // Start receiving location updates
        startLocationUpdates(locationRequest);

        ImageView iv_currentLocation = findViewById(R.id.iv_currentLocation);
        iv_currentLocation.setOnClickListener(v->showCurrentLocation());


    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        this.googleMap = map;


        // Enable MyLocation layer to show the blue dot
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }

        for (EntityLocationModel locationModel : getLocationsByCategory(category)) {
            // Get the LatLng from the model's getLocation method
            LatLng latLng = locationModel.getLocation();

            Log.e("ds","line 115: "+latLng.toString());
            // Add marker with the title
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(locationModel.getTitle()));

            // Set the tag with the whole model or relevant data for later retrieval
            marker.setTag(locationModel);
        }


        googleMap.setOnMarkerClickListener(marker -> {
            // Retrieve the EntityLocationModel from the marker's tag
            EntityLocationModel locationModel = (EntityLocationModel) marker.getTag();


            if(locationModel==null)
                return true;

            // Pass the data to the BottomSheet
            MarkerDetailsBottomSheet bottomSheet = new MarkerDetailsBottomSheet(
                    locationModel.getTitle(),
                    locationModel.getDescription(),
                    locationModel.getAddress(),
                    locationModel.getMobileNumber()
            );

            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());

            return true;
        });
        // Show live location with a moving marker
        showCurrentLocation();
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

                        // Move the camera to the current location
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 20f));
                    }
                });
    }

    private void updateLocationMarker(Location location) {
        if (googleMap != null) {
            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

            // If the marker is not already set, add a new marker
            if (currentLocationMarker == null) {
                currentLocationMarker = googleMap.addMarker(new MarkerOptions()
                        .position(currentLatLng)
                        .title("You are here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))); // Blue marker
            } else {
                // Update the position of the existing marker
                currentLocationMarker.setPosition(currentLatLng);
            }

            // Move the camera to the current location while preserving the zoom level
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        }
    }

    private void setUpToolBar() {
        ImageView back = findViewById(R.id.iv_back);
        back.setOnClickListener(v -> onBackPressed());

        TextView pageTitle = findViewById(R.id.tv_page_title);
        pageTitle.setText(category);
    }

    private void startLocationUpdates(LocationRequest locationRequest) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates(LocationRequest.create());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop location updates when the activity is destroyed
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }


    private List<EntityLocationModel> getLocationsByCategory(String category){
        List<EntityLocationModel> modelList =dbManager.getLocationsByCategory(category);
        new EntitiesListBottomSheetFragment(modelList).show(getSupportFragmentManager(),"bottom sheet dialog");

        return modelList;
    }

}
