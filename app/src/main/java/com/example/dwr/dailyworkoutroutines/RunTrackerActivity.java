package com.example.dwr.dailyworkoutroutines;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RunTrackerActivity extends FragmentActivity implements OnMapReadyCallback{
    Context c = this;
    TextView timeRan;
    private double distanceRan;
    private long startTime;
    private long finishTime;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private ArrayList<LatLng> locations;
    private ArrayList<Marker> markers = new ArrayList<Marker>();
    boolean isStarted = false;
    Polyline polyline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_tracker);
        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //instantiate arraylist
        locations = new ArrayList<LatLng>();
        //init distance travelled at 0 meters
        distanceRan = 0;
        startTime = 0;
        onStartClicked();
        onEndClick();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println("Requesting Permissions Callback");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            System.out.println("Requesting Permissions");
            //return;
        }
        mMap.setMyLocationEnabled(true);
        //init uisettings of map
        UiSettings uiSettings = mMap.getUiSettings();
        //add zoom controls
        uiSettings.setZoomControlsEnabled(true);
        //add compass
        uiSettings.setCompassEnabled(true);
        //add my location button
        uiSettings.setMyLocationButtonEnabled(true);
        //get current location

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 3, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //current location
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                //add new latLng to arraylist for path drawing
                locations.add(latLng);
                //mov cam to curr pos
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20.0f));
                //Draw Path using gms polyline:
                redrawPolyline();
                //distance increment
                if(locations.size() < 2 || isStarted == false){
                    distanceRan = 0;
                }
                else{
                    Location a = new Location("point A");
                    a.setLatitude(locations.get(locations.size()-2).latitude);
                    a.setLongitude(locations.get(locations.size()-2).longitude);
                    Location b = new Location("point B");
                    b.setLatitude(locations.get(locations.size()-1).latitude);
                    b.setLongitude(locations.get(locations.size()-1).longitude);
                    distanceRan += a.distanceTo(b);          
                }


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    public void onStartClicked(){
        Button start = (Button)findViewById(R.id.startbutton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Init time measure
                startTime = SystemClock.elapsedRealtime();
                System.out.println("time started");
                isStarted = true;
            }
        });
    }
    public void onEndClick() {
        Button end = (Button)findViewById(R.id.endbutton);
        end.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(startTime == 0){
                    return;
                }
                isStarted = false;
                System.out.println("time ended");
                finishTime = SystemClock.elapsedRealtime();
                //calc elapsed run time
                long timeRanSeconds = (finishTime - startTime)/1000;
                int minutes = (int)timeRanSeconds/60;
                int seconds = (int)timeRanSeconds%60;
                String sec = "";
                if(seconds<10){
                    sec = "0"+seconds;
                }
                else
                    sec+=seconds;
                //calc distance travelled in miles
                double milesRan = 0.000621371 * distanceRan;
                String miles = String.format("%.3f", milesRan);
                //calc split
                String paceinfo = "";
                if(milesRan != 0){
                    double minpermile = (timeRanSeconds/60)/milesRan;
                    paceinfo += "at a pace of "+minpermile+" minutes per mile";
                }
                //display run data in a dialog
                AlertDialog dialog = new AlertDialog.Builder(c).create();
                dialog.setTitle("Congratulations!");
                dialog.setMessage("You ran "+miles+" miles in "+minutes+":"+sec+" "+paceinfo);
                dialog.show();
                redrawPolyline();
            }
        });
    }

    private void redrawPolyline() {
        mMap.clear();
        if(!isStarted)
            return;
        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        for (int i = 0; i < locations.size(); i++) {
            LatLng point = locations.get(i);
            options.add(point);
        }
        //add Marker in current position
        //Marker marker = mMap.addMarker(new MarkerOptions().position(locations.get(locations.size()-1)));
        polyline = mMap.addPolyline(options);
    }
}
