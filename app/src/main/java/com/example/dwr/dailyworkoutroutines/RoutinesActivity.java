package com.example.dwr.dailyworkoutroutines;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;
import android.widget.Toast;

public class RoutinesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG ="RoutinesActivity";
    private String day;
    private TextView mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDay = (TextView)findViewById(R.id.dayText);

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


        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            day = extras.getString("day");
        }else{
            day = "monday";
        }

        switch (day) {
            case "monday":
                mDay.setText("Monday");
                break;
            case "tuesday":
                mDay.setText("Tuesday");
                break;
            case "wednesday":
                mDay.setText("Wednesday");
                break;
            case "thursday":
                mDay.setText("Thursday");
                break;
            case "friday":
                mDay.setText("Friday");
                break;
            case "saturday":
                mDay.setText("Saturday");
                break;
            case "sunday":
                mDay.setText("Sunday");
                break;
            default:
                mDay.setText("Invalid Try again");
                break;
        }


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
}
