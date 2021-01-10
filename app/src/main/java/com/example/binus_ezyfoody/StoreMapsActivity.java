package com.example.binus_ezyfoody;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.widget.Toast;

import com.example.binus_ezyfoody.EzFood.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StoreMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION = 1;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private Location getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                StoreMapsActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                StoreMapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

            return null;
        } else {
            LocationManager mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            assert mLocationManager != null;
            List<String> providers = mLocationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
            return bestLocation;
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                StoreMapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                StoreMapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = getLastKnownLocation();
            if (locationGPS != null) {
                latitude = locationGPS.getLatitude();
                longitude = locationGPS.getLongitude();

            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        getLocation();

        Location current = new Location("crntlocation");
        current.setLatitude(latitude);
        current.setLongitude(longitude);

        ArrayList<Store> StoreList = new ArrayList<>();
        Store Alsut = new Store("Alsut", -6.233327, 106.658563);
        Store bogor = new Store("Bogor",-6.585980,106.817982);
        Store BSD = new Store("BSD", -6.295910, 106.668673);
        Store GadingSerpong = new Store("Gading Serpong", -6.241927, 106.629550);
        Store Jakarta = new Store("Jakarta", -6.178922, 106.793484);
        Store Tangerang = new Store("Tangerang", -6.193426, 106.633153);

        StoreList.add(Alsut);
        StoreList.add(bogor);
        StoreList.add(BSD);
        StoreList.add(GadingSerpong);
        StoreList.add(Jakarta);
        StoreList.add(Tangerang);

        int n = StoreList.size();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                Location newLoc = new Location("newLoc");
                newLoc.setLatitude(StoreList.get(i).getLatitude());
                newLoc.setLongitude(StoreList.get(i).getLongitude());
                double curDistance = current.distanceTo(newLoc);

                Location newLoc2 = new Location("newLoc2");
                newLoc2.setLatitude(StoreList.get(i+1).getLatitude());
                newLoc2.setLongitude(StoreList.get(i+1).getLongitude());
                double curDistance2 = current.distanceTo(newLoc2);

                if(curDistance > curDistance2){
                    Store temp = StoreList.get(i);
                    StoreList.set(i, StoreList.get(i+1));
                    StoreList.set(i+1, temp);
                }
            }
        }

        LatLng currentLocation = new LatLng(latitude, longitude);
        LatLng newLocation1 = new LatLng(StoreList.get(0).getLatitude(), StoreList.get(0).getLongitude());
        LatLng newLocation2 = new LatLng(StoreList.get(1).getLatitude(), StoreList.get(1).getLongitude());
        LatLng newLocation3 = new LatLng(StoreList.get(2).getLatitude(), StoreList.get(2).getLongitude());

        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
        mMap.addMarker(new MarkerOptions().position(newLocation1).title("New Location 1"));
        mMap.addMarker(new MarkerOptions().position(newLocation2).title("New Location 2"));
        mMap.addMarker(new MarkerOptions().position(newLocation3).title("New Location 3"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLocation1, 12));
    }
}
