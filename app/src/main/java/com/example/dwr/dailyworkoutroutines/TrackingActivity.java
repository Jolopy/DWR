package com.example.dwr.dailyworkoutroutines;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TrackingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private static final String TAG ="TrackingActivity";

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
    boolean LOCATION_ENABLED = false;

    public Button start,end;
    public TextView timeCount,speedCount;
    public Chronometer timer;
    public double chrisDistance;
    public DecimalFormat df;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        start = (Button)findViewById(R.id.startbutton);
        end = (Button)findViewById(R.id.endbutton);
        timeCount = (TextView)findViewById(R.id.timeCount);
        speedCount = (TextView) findViewById(R.id.speedCount);
        timer = (Chronometer) findViewById(R.id.timerTracking);
        df = new DecimalFormat("0.00");





        /**
         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
        }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //* <---------------------- Continue OnCreate ---------------------->*//*
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.currentworkout) {
            Log.d(TAG, "onNavigationItemSelected: CurrentWorkout");
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);

        } else if (id == R.id.runningroutine) {
            Log.d(TAG, "onNavigationItemSelected: RuinningRoutine");
            Toast.makeText(this,"Already in Tracking Activity", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.monday) {
            Log.d(TAG, "onNavigationItemSelected: Monday");
            Intent i = new Intent(getBaseContext(), RoutinesActivity.class);
            i.putExtra("day", "monday");
            startActivity(i);

        } else if (id == R.id.tuesday) {
            Log.d(TAG, "onNavigationItemSelected: Tuesday");
            Intent i = new Intent(getBaseContext(), RoutinesActivity.class);
            i.putExtra("day", "tuesday");
            startActivity(i);

        } else if (id == R.id.wednesday) {
            Log.d(TAG, "onNavigationItemSelected: Wednesday");
            Intent i = new Intent(getBaseContext(), RoutinesActivity.class);
            i.putExtra("day", "wednesday");
            startActivity(i);

        } else if (id == R.id.thursday) {
            Log.d(TAG, "onNavigationItemSelected: Thursday");
            Intent i = new Intent(getBaseContext(), RoutinesActivity.class);
            i.putExtra("day", "thursday");
            startActivity(i);

        } else if (id == R.id.friday) {
            Log.d(TAG, "onNavigationItemSelected: Friday");
            Intent i = new Intent(getBaseContext(), RoutinesActivity.class);
            i.putExtra("day", "friday");
            startActivity(i);

        } else if (id == R.id.saturday) {
            Log.d(TAG, "onNavigationItemSelected: Saturday");
            Intent i = new Intent(getBaseContext(), RoutinesActivity.class);
            i.putExtra("day", "saturday");
            startActivity(i);

        } else if (id == R.id.sunday) {
            Log.d(TAG, "onNavigationItemSelected: Sunday");
            Intent i = new Intent(getBaseContext(), RoutinesActivity.class);
            i.putExtra("day", "sunday");
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "Requesting Permissions Callback");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //do location stuff
                Log.d(TAG,"Location Enabled");
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        mMap = googleMap;
        //init uisettings of map
        mMap.setMyLocationEnabled(true);
        //uisettings...
        UiSettings uiSettings = mMap.getUiSettings();
        //add my location button
        uiSettings.setMyLocationButtonEnabled(true);
        //add zoom controls
        uiSettings.setZoomControlsEnabled(true);
        //add compass
        uiSettings.setCompassEnabled(true);
        //get current location
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 0, new LocationListener() {
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
                    chrisDistance = (distanceRan*0.000621371);
                    speedCount.setText(df.format(chrisDistance) + " miles");
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
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Init time measure
                startTime = SystemClock.elapsedRealtime();
                System.out.println("time started");
                start.setVisibility(View.INVISIBLE);
                end.setVisibility(View.VISIBLE);
                speedCount.setVisibility(View.VISIBLE);
                timeCount.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();

                isStarted = true;
            }
        });
    }


    public void onEndClick() {
        end.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(startTime == 0){
                    return;
                }
                start.setVisibility(View.VISIBLE);
                speedCount.setVisibility(View.INVISIBLE);
                timeCount.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);
                end.setVisibility(View.INVISIBLE);
                timer.stop();
                chrisDistance = 0;
                isStarted = false;
                System.out.println("time ended");
                finishTime = SystemClock.elapsedRealtime();
                //calc elapsed run time
                double timeRanSeconds = (finishTime - startTime)/1000;
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
                System.out.println("time ran secs "+timeRanSeconds);
                System.out.println("time ran mins "+timeRanSeconds/60);
                System.out.println("pace "+(timeRanSeconds/60)/milesRan);
                System.out.println("dis "+milesRan);
                double minpermile = (timeRanSeconds/60)/milesRan;
                String split = String.format("%.3f", minpermile);
                if(milesRan > 0){
                    paceinfo += "with a run split of "+split+" minutes per mile";
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
