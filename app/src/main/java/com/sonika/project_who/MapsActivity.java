package com.sonika.project_who;

import android.Manifest;

import android.content.pm.PackageManager;

import android.location.Location;

import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
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
import com.sonika.project_who.Services.LocationInfo;
import com.sonika.project_who.pojo.HospitalsPojo;


import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private GoogleMap mMap;//demo

    GoogleApiClient client;
    public final int REQUEST_LOCATION_CODE = 0;

    List<LatLng> latlonglist;
    public Marker curreentLocationMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        latlonglist = new ArrayList<LatLng>();
        client = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private void updatemarkersofhospitals(double latitude, double longitude)
    {
       ArrayList<HospitalsPojo> list = LocationInfo.getInstance().
               getHospitalLocations(latitude, longitude);

        for (int x = 0; x < list.size(); x++)
        {
            HospitalsPojo loc = list.get(x);

            MarkerOptions markerOptions = new MarkerOptions().position
                    (new LatLng(loc.getLatitude(), loc.getLongitude()));
            markerOptions.title(loc.getName());
            markerOptions.snippet("hospital");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        mMap.addPolyline(new PolylineOptions().add(
//                new LatLng(27.7062, 85.3151),
//                new LatLng(27.7029, 85.3223),
//                new LatLng(27.7048, 85.3137)
//
//        ).width(10)
//        .color(Color.RED));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        client.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        client.disconnect();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission is granted
                    Log.v("Hello", "permission granted, starting services");
                    startLocationServices();
                    }
                else //permission denied
                {
                    Log.v("Hello", "not granted permissions");
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
        Log.v("Hello", "requesting permissions");
        }
        else { Log.v("Hello", "starting location services in connected");
            startLocationServices();
        }
    }
    @Override
    public void onLocationChanged(Location location) {

        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        if (curreentLocationMarker != null)
        {
            curreentLocationMarker.remove();
        }
        LatLng latLng = new LatLng(latitude, longitude );

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("ur current location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        updatemarkersofhospitals(27.7048, 85.3137);
        curreentLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    public void startLocationServices()
    { Log.v("Hello", "starting location services");

        try {
            LocationRequest req  = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(client, req, this);

        }catch (SecurityException exception)
        {
            Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



}


