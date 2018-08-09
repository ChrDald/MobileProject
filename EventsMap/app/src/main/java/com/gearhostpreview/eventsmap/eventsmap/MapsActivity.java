package com.gearhostpreview.eventsmap.eventsmap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap map;
    private FusedLocationProviderClient mFusedLocationClient;

    //private static final LatLng SYDNEY = new LatLng(-33.88,151.21);
    //private static final LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // instance of fused location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }



    // clicking your location
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    // clicking the my location button
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
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

        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user.

        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        else {
            // Write you code here if permission already given.
        }

        map = googleMap;
        map.setMyLocationEnabled(true);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);

        Task t = mFusedLocationClient.getLastLocation();

        if (t.isSuccessful() == true) {
            Location loc = (Location) t.getResult();

            LatLng userPosition = new LatLng(loc.getLatitude(), loc.getLongitude());

//            // Zoom in, animating the camera.
//            map.animateCamera(CameraUpdateFactory.zoomIn());
//            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//            map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//            // Construct a CameraPosition focusing on currrent position and animate the camera to that position.
//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(userPosition)       // Sets the center of the map to Mountain View
//                    .zoom(17)                   // Sets the zoom
//                //    .bearing(90)                // Sets the orientation of the camera to east
//                    .tilt(15)                   // Sets the tilt of the camera to 30 degrees
//                    .build();                   // Creates a CameraPosition from the builder
//            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }


        // template code
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(45.5017, -73.5673);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



        // simplest(?) way that i found so far to load the map at user position
        //onMyLocationButtonClick();

        // Move the camera instantly to Sydney with a zoom of 15.
        //        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15));






    }
}
