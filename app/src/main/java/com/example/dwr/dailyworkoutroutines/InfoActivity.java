package com.example.dwr.dailyworkoutroutines;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



public class InfoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG ="TrackingActivity";
    private String workout;
    private TextView mWorkoutText,mWorkoutInfo;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
            workout = extras.getString("day");
        }else{
            workout = "unknown";
        }


        imageView = (ImageView) findViewById(R.id.gifImageView);
        mWorkoutText = (TextView) findViewById(R.id.infoName);
        mWorkoutInfo = (TextView) findViewById(R.id.infoText);

        mWorkoutText.setText(workout.toUpperCase());

        switch (workout){

            case "Barbell Bench Press":
                Glide.with(this)
                        .load(R.raw.one)
                        .into(imageView);
                break;
            case "Flat Bench Dumbbell Press":
                Glide.with(this)
                        .load(R.raw.two)
                        .into(imageView);
                break;
            case "Incline Barbell Bench Press":
                Glide.with(this)
                        .load(R.raw.three)
                        .into(imageView);
                break;
            case "Incline Dumbbell Press":
                Glide.with(this)
                        .load(R.raw.four)
                        .into(imageView);
                break;
            case "Dips":
                Glide.with(this)
                        .load(R.raw.five)
                        .into(imageView);
                break;
            case "Reverse Cable Flys":
                Glide.with(this)
                        .load(R.raw.six)
                        .into(imageView);
                break;
            case "Incline Dumbbell Pullover":
                Glide.with(this)
                        .load(R.raw.seven)
                        .into(imageView);
                break;
            case "Standing Cable Flys":
                Glide.with(this)
                        .load(R.raw.eight)
                        .into(imageView);
                break;


            case "Incline Dumbbell hammer Curl":
                Glide.with(this)
                        .load(R.raw.one)
                        .into(imageView);
                break;
            case "Incline Inner Bicep Curl":
                Glide.with(this)
                        .load(R.raw.two)
                        .into(imageView);
                break;
            case "Seated Concentration Dumbbell Curl":
                Glide.with(this)
                        .load(R.raw.three)
                        .into(imageView);
                break;
            case "Ez-bar Curl":
                Glide.with(this)
                        .load(R.raw.four)
                        .into(imageView);
                break;
            case "Wide-Grip Standing Barbell Curl":
                Glide.with(this)
                        .load(R.raw.five)
                        .into(imageView);
                break;
            case "Reverse-Grip Barbell Curl":
                Glide.with(this)
                        .load(R.raw.six)
                        .into(imageView);
                break;
            case "Standing Dumbbell Biceps Curl":
                Glide.with(this)
                        .load(R.raw.seven)
                        .into(imageView);
                break;
            case "Hammer Curl":
                Glide.with(this)
                        .load(R.raw.eight)
                        .into(imageView);
                break;



            case "Close-Grip Barbell Bench Press":
                Glide.with(this)
                        .load(R.raw.one)
                        .into(imageView);
                break;
            case "Dip Machine":
                Glide.with(this)
                        .load(R.raw.two)
                        .into(imageView);
                break;
            case "Ez-bar Skullcrusher":
                Glide.with(this)
                        .load(R.raw.three)
                        .into(imageView);
                break;
            case "Tricep Pushdowns - Rope":
                Glide.with(this)
                        .load(R.raw.four)
                        .into(imageView);
                break;
            case "Triceps Overhead Extension - Rope":
                Glide.with(this)
                        .load(R.raw.five)
                        .into(imageView);
                break;
            case "Tricep Dumbbell KickBack":
                Glide.with(this)
                        .load(R.raw.six)
                        .into(imageView);
                break;
            case "Standing Overhead Dumbbell Tricep Extension":
                Glide.with(this)
                        .load(R.raw.seven)
                        .into(imageView);
                break;
            case "Seated One-Arm Dumbbell Tricep Extension":
                Glide.with(this)
                        .load(R.raw.eight)
                        .into(imageView);
                break;


            case "Dumbbell Shoulder Press":
                Glide.with(this)
                        .load(R.raw.one)
                        .into(imageView);
                break;
            case "Arnold Dumbbell Press":
                Glide.with(this)
                        .load(R.raw.two)
                        .into(imageView);
                break;
            case "Standing Barbell Press":
                Glide.with(this)
                        .load(R.raw.three)
                        .into(imageView);
                break;
            case "Front Dumbbell Raises":
                Glide.with(this)
                        .load(R.raw.four)
                        .into(imageView);
                break;
            case "Side Laterals":
                Glide.with(this)
                        .load(R.raw.five)
                        .into(imageView);
                break;
            case "Upright Dumbell Rows":
                Glide.with(this)
                        .load(R.raw.six)
                        .into(imageView);
                break;
            case "Reverse Flys":
                Glide.with(this)
                        .load(R.raw.seven)
                        .into(imageView);
                break;
            case "Dumbbell Shrugs":
                Glide.with(this)
                        .load(R.raw.eight)
                        .into(imageView);
                break;


            case "Hanging Leg Raise or Knee Raise":
                Glide.with(this)
                        .load(R.raw.one)
                        .into(imageView);
                break;
            case "Machine Crunch":
                Glide.with(this)
                        .load(R.raw.two)
                        .into(imageView);
                break;
            case "Cable Pallof Press":
                Glide.with(this)
                        .load(R.raw.three)
                        .into(imageView);
                break;
            case "Kneeling Cable Crunch":
                Glide.with(this)
                        .load(R.raw.four)
                        .into(imageView);
                break;
            case "Decline Russian Twist w/ Weight":
                Glide.with(this)
                        .load(R.raw.five)
                        .into(imageView);
                break;
            case "Ab-Wheel Roll-out":
                Glide.with(this)
                        .load(R.raw.six)
                        .into(imageView);
                break;
            case "Plank":
                Glide.with(this)
                        .load(R.raw.seven)
                        .into(imageView);
                break;
            case "Crunches":
                Glide.with(this)
                        .load(R.raw.eight)
                        .into(imageView);
                break;


            case "Barbell Deadlift":
                Glide.with(this)
                        .load(R.raw.one)
                        .into(imageView);
                break;
            case "Wide Grip Pull-Up":
                Glide.with(this)
                        .load(R.raw.two)
                        .into(imageView);
                break;
            case "Standing T-Bar row":
                Glide.with(this)
                        .load(R.raw.three)
                        .into(imageView);
                break;
            case "Seated Cable Row":
                Glide.with(this)
                        .load(R.raw.four)
                        .into(imageView);
                break;
            case "Wide-Grip Bar Pulldown":
                Glide.with(this)
                        .load(R.raw.five)
                        .into(imageView);
                break;
            case "Single Arm Dumbbell Row":
                Glide.with(this)
                        .load(R.raw.six)
                        .into(imageView);
                break;
            case "Back Extensions":
                Glide.with(this)
                        .load(R.raw.seven)
                        .into(imageView);
                break;
            case "Close-Grip Pulldown":
                Glide.with(this)
                        .load(R.raw.eight)
                        .into(imageView);
                break;


            case "Squat":
                Glide.with(this)
                        .load(R.raw.one)
                        .into(imageView);
                break;
            case "Front Squat":
                Glide.with(this)
                        .load(R.raw.two)
                        .into(imageView);
                break;
            case "Leg Press":
                Glide.with(this)
                        .load(R.raw.three)
                        .into(imageView);
                break;
            case "Dumbbell Lunge":
                Glide.with(this)
                        .load(R.raw.four)
                        .into(imageView);
                break;
            case "Calf Raises":
                Glide.with(this)
                        .load(R.raw.five)
                        .into(imageView);
                break;
            case "Machine Squat":
                Glide.with(this)
                        .load(R.raw.six)
                        .into(imageView);
                break;
            case "Leg Curls":
                Glide.with(this)
                        .load(R.raw.seven)
                        .into(imageView);
                break;
            case "Walking Lunge":
                Glide.with(this)
                        .load(R.raw.eight)
                        .into(imageView);
                break;
            case "Barbell Hip Thrust":
                Glide.with(this)
                        .load(R.raw.nine)
                        .into(imageView);

            case "Leg Extensions":
                Glide.with(this)
                        .load(R.raw.ten)
                        .into(imageView);
                break;
            case "Thigh Abductor":
                Glide.with(this)
                        .load(R.raw.eleven)
                        .into(imageView);
                break;

            case "Elliptical":
                Glide.with(this)
                        .load(R.raw.one)
                        .into(imageView);
                break;
            case "Running":
                Glide.with(this)
                        .load(R.raw.two)
                        .into(imageView);
                break;
            case "Stair Climber":
                Glide.with(this)
                        .load(R.raw.three)
                        .into(imageView);
                break;
            case "Jump Roping":
                Glide.with(this)
                        .load(R.raw.four)
                        .into(imageView);
                break;
            case "KettleBells":
                Glide.with(this)
                        .load(R.raw.five)
                        .into(imageView);
                break;
            case "Cycling":
                Glide.with(this)
                        .load(R.raw.six)
                        .into(imageView);
                break;
            case "Swimming":
                Glide.with(this)
                        .load(R.raw.seven)
                        .into(imageView);
                break;
            case "Rowing":
                Glide.with(this)
                        .load(R.raw.eight)
                        .into(imageView);
                break;
            case "High-Intensity Interval Training":
                Glide.with(this)
                        .load(R.raw.nine)
                        .into(imageView);
                break;
            case "Sprinting":
                Glide.with(this)
                        .load(R.raw.ten)
                        .into(imageView);
                break;
            case "Boxing/Kick-Boxing":
                Glide.with(this)
                        .load(R.raw.eleven)
                        .into(imageView);
                break;






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
            Log.d(TAG, "onNavigationItemSelected: CurrentWorkout");
            Intent i = new Intent(getBaseContext(), TrackingActivity.class);
            startActivity(i);

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
