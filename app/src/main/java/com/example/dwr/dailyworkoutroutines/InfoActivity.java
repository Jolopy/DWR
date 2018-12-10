package com.example.dwr.dailyworkoutroutines;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.method.ScrollingMovementMethod;
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
            workout = extras.getString("workout");
        }else{
            workout = "unknown";
        }


        imageView = (ImageView) findViewById(R.id.gifImageView);
        mWorkoutText = (TextView) findViewById(R.id.infoName);
        mWorkoutInfo = (TextView) findViewById(R.id.infoText);
        mWorkoutInfo.setMovementMethod(new ScrollingMovementMethod());

        mWorkoutText.setText(workout.toUpperCase());


        switch (workout){
            //* <----------------------CHEST WORKOUTS---------------------->*//*
            //* <----------------------CHEST WORKOUTS---------------------->*//*
            //* <----------------------CHEST WORKOUTS---------------------->*//*
            //* <----------------------CHEST WORKOUTS---------------------->*//*
            //* <----------------------CHEST WORKOUTS---------------------->*//*

            case "Barbell Bench Press":
                Glide.with(this)
                        .load(R.raw.chest_one)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[0]);
                break;
            case "Flat Bench Dumbbell Press":
                Glide.with(this)
                        .load(R.raw.chest_two)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[1]);

                break;
            case "Incline Barbell Bench Press":
                Glide.with(this)
                        .load(R.raw.chest_three)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[2]);

                break;
            case "Incline Dumbbell Press":
                Glide.with(this)
                        .load(R.raw.chest_four)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[3]);

                break;
            case "Dips":
                Glide.with(this)
                        .load(R.raw.chest_five)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[4]);

                break;
            case "Reverse Cable Flys":
                Glide.with(this)
                        .load(R.raw.chest_six)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[5]);

                break;
            case "Incline Dumbbell Pullover":
                Glide.with(this)
                        .load(R.raw.chest_seven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[6]);

                break;
            case "Standing Cable Flys":
                Glide.with(this)
                        .load(R.raw.chest_eight)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[7]);

                break;

            //* <----------------------BICEPS WORKOUTS---------------------->*//*
            //* <----------------------BICEPS WORKOUTS---------------------->*//*
            //* <----------------------BICEPS WORKOUTS---------------------->*//*
            //* <----------------------BICEPS WORKOUTS---------------------->*//*
            //* <----------------------BICEPS WORKOUTS---------------------->*//*

            case "Incline Dumbbell hammer Curl":
                Glide.with(this)
                        .load(R.raw.biceps_one)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[8]);

                break;
            case "Incline Inner Bicep Curl":
                Glide.with(this)
                        .load(R.raw.biceps_two)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[9]);

                break;
            case "Seated Concentration Dumbbell Curl":
                Glide.with(this)
                        .load(R.raw.biceps_three)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[10]);

                break;
            case "Ez-bar Curl":
                Glide.with(this)
                        .load(R.raw.biceps_four)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[11]);

                break;
            case "Wide-Grip Standing Barbell Curl":
                Glide.with(this)
                        .load(R.raw.biceps_five)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[12]);

                break;
            case "Reverse-Grip Barbell Curl":
                Glide.with(this)
                        .load(R.raw.biceps_six)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[13]);

                break;
            case "Standing Dumbbell Biceps Curl":
                Glide.with(this)
                        .load(R.raw.biceps_seven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[14]);

                break;
            case "Hammer Curl":
                Glide.with(this)
                        .load(R.raw.biceps_eight)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[15]);

                break;


            //* <----------------------TRICEPS WORKOUTS---------------------->*//*
            //* <----------------------TRICEPS WORKOUTS---------------------->*//*
            //* <----------------------TRICEPS WORKOUTS---------------------->*//*
            //* <----------------------TRICEPS WORKOUTS---------------------->*//*
            //* <----------------------TRICEPS WORKOUTS---------------------->*//*

            case "Close-Grip Barbell Bench Press":
                Glide.with(this)
                        .load(R.raw.triceps_one)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[16]);

                break;
            case "Dip Machine":
                Glide.with(this)
                        .load(R.raw.triceps_two)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[17]);

                break;
            case "Ez-bar Skullcrusher":
                Glide.with(this)
                        .load(R.raw.triceps_three)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[18]);

                break;
            case "Tricep Pushdowns - Rope":
                Glide.with(this)
                        .load(R.raw.triceps_four)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[19]);

                break;
            case "Triceps Overhead Extension - Rope":
                Glide.with(this)
                        .load(R.raw.triceps_five)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[20]);

                break;
            case "Tricep Dumbbell KickBack":
                Glide.with(this)
                        .load(R.raw.triceps_six)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[21]);

                break;
            case "Standing Overhead Dumbbell Tricep Extension":
                Glide.with(this)
                        .load(R.raw.triceps_seven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[22]);

                break;
            case "Seated One-Arm Dumbbell Tricep Extension":
                Glide.with(this)
                        .load(R.raw.triceps_eight)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[23]);

                break;

            //* <----------------------SHOULDER WORKOUTS---------------------->*//*
            //* <----------------------SHOULDER WORKOUTS---------------------->*//*
            //* <----------------------SHOULDER WORKOUTS---------------------->*//*
            //* <----------------------SHOULDER WORKOUTS---------------------->*//*
            //* <----------------------SHOULDER WORKOUTS---------------------->*//*

            case "Dumbbell Shoulder Press":
                Glide.with(this)
                        .load(R.raw.shoulder_one)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[24]);

                break;
            case "Arnold Dumbbell Press":
                Glide.with(this)
                        .load(R.raw.shoulder_two)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[25]);

                break;
            case "Standing Barbell Press":
                Glide.with(this)
                        .load(R.raw.shoulder_three)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[26]);

                break;
            case "Front Dumbbell Raises":
                Glide.with(this)
                        .load(R.raw.shoulder_four)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[27]);

                break;
            case "Side Laterals":
                Glide.with(this)
                        .load(R.raw.shoulder_five)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[28]);

                break;
            case "Upright Dumbell Rows":
                Glide.with(this)
                        .load(R.raw.shoulder_six)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[29]);

                break;
            case "Reverse Flys":
                Glide.with(this)
                        .load(R.raw.shoulder_seven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[30]);

                break;
            case "Dumbbell Shrugs":
                Glide.with(this)
                        .load(R.raw.shoulder_eight)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[31]);

                break;

            //* <----------------------ABS WORKOUTS---------------------->*//*
            //* <----------------------ABS WORKOUTS---------------------->*//*
            //* <----------------------ABS WORKOUTS---------------------->*//*
            //* <----------------------ABS WORKOUTS---------------------->*//*
            //* <----------------------ABS WORKOUTS---------------------->*//*

            case "Hanging Leg Raise or Knee Raise":
                Glide.with(this)
                        .load(R.raw.abs_one)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[32]);

                break;
            case "Machine Crunch":
                Glide.with(this)
                        .load(R.raw.abs_two)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[33]);

                break;
            case "Cable Pallof Press":
                Glide.with(this)
                        .load(R.raw.abs_three)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[34]);

                break;
            case "Kneeling Cable Crunch":
                Glide.with(this)
                        .load(R.raw.abs_four)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[35]);

                break;
            case "Decline Russian Twist w/ Weight":
                Glide.with(this)
                        .load(R.raw.abs_five)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[36]);

                break;
            case "Ab-Wheel Roll-out":
                Glide.with(this)
                        .load(R.raw.abs_six)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[37]);

                break;
            case "Plank":
                Glide.with(this)
                        .load(R.raw.abs_seven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[38]);

                break;
            case "Crunches":
                Glide.with(this)
                        .load(R.raw.abs_eight)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[39]);

                break;

            //* <----------------------BACK WORKOUTS---------------------->*//*
            //* <----------------------BACK WORKOUTS---------------------->*//*
            //* <----------------------BACK WORKOUTS---------------------->*//*
            //* <----------------------BACK WORKOUTS---------------------->*//*
            //* <----------------------BACK WORKOUTS---------------------->*//*

            case "Barbell Deadlift":
                Glide.with(this)
                        .load(R.raw.back_one)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[40]);

                break;
            case "Wide Grip Pull-Up":
                Glide.with(this)
                        .load(R.raw.back_two)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[41]);

                break;
            case "Standing T-Bar row":
                Glide.with(this)
                        .load(R.raw.back_three)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[42]);

                break;
            case "Seated Cable Row":
                Glide.with(this)
                        .load(R.raw.back_four)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[43]);

                break;
            case "Wide-Grip Bar Pulldown":
                Glide.with(this)
                        .load(R.raw.back_five)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[44]);

                break;
            case "Single Arm Dumbbell Row":
                Glide.with(this)
                        .load(R.raw.back_six)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[45]);

                break;
            case "Back Extensions":
                Glide.with(this)
                        .load(R.raw.back_seven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[46]);

                break;
            case "Close-Grip Pulldown":
                Glide.with(this)
                        .load(R.raw.back_eight)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[47]);

                break;

            //* <----------------------LEGS WORKOUTS---------------------->*//*
            //* <----------------------LEGS WORKOUTS---------------------->*//*
            //* <----------------------LEGS WORKOUTS---------------------->*//*
            //* <----------------------LEGS WORKOUTS---------------------->*//*
            //* <----------------------LEGS WORKOUTS---------------------->*//*

            case "Squat":
                Glide.with(this)
                        .load(R.raw.legs_one)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[48]);

                break;
            case "Front Squat":
                Glide.with(this)
                        .load(R.raw.legs_two)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[49]);

                break;
            case "Leg Press":
                Glide.with(this)
                        .load(R.raw.legs_three)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[50]);

                break;
            case "Dumbbell Lunge":
                Glide.with(this)
                        .load(R.raw.legs_four)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[51]);

                break;
            case "Calf Raises":
                Glide.with(this)
                        .load(R.raw.legs_five)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[52]);

                break;
            case "Machine Squat":
                Glide.with(this)
                        .load(R.raw.legs_six)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[53]);

                break;
            case "Leg Curls":
                Glide.with(this)
                        .load(R.raw.legs_seven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[54]);

                break;
            case "Walking Lunge":
                Glide.with(this)
                        .load(R.raw.legs_eight)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[55]);

                break;
            case "Barbell Hip Thrust":
                Glide.with(this)
                        .load(R.raw.legs_nine)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[56]);


            case "Leg Extensions":
                Glide.with(this)
                        .load(R.raw.legs_ten)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[57]);

                break;
            case "Thigh Abductor":
                Glide.with(this)
                        .load(R.raw.legs_eleven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[58]);

                break;
            //* <----------------------CARDIO WORKOUTS---------------------->*//*
            //* <----------------------CARDIO WORKOUTS---------------------->*//*
            //* <----------------------CARDIO WORKOUTS---------------------->*//*
            //* <----------------------CARDIO WORKOUTS---------------------->*//*
            //* <----------------------CARDIO WORKOUTS---------------------->*//*

            case "Elliptical":
                Glide.with(this)
                        .load(R.raw.cardio_one)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[59]);

                break;
            case "Running":
                Glide.with(this)
                        .load(R.raw.cardio_two)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[60]);

                break;
            case "Stair Climber":
                Glide.with(this)
                        .load(R.raw.cardio_three)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[61]);

                break;
            case "Jump Roping":
                Glide.with(this)
                        .load(R.raw.cardio_four)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[62]);

                break;
            case "KettleBells":
                Glide.with(this)
                        .load(R.raw.cardio_five)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[63]);

                break;
            case "Cycling":
                Glide.with(this)
                        .load(R.raw.cardio_six)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[64]);

                break;
            case "Swimming":
                Glide.with(this)
                        .load(R.raw.cardio_seven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[65]);

                break;
            case "Rowing":
                Glide.with(this)
                        .load(R.raw.cardio_eight)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[66]);

                break;
            case "High-Intensity Interval Training":
                Glide.with(this)
                        .load(R.raw.cardio_nine)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[67]);

                break;
            case "Sprinting":
                Glide.with(this)
                        .load(R.raw.cardio_ten)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[68]);

                break;
            case "Boxing/Kick-Boxing":
                Glide.with(this)
                        .load(R.raw.cardio_eleven)
                        .into(imageView);
                mWorkoutInfo.setText(getResources().getStringArray(R.array.info)[69]);

                break;
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "Requesting Permissions Callback");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //do location stuff
                Log.d(TAG,"Location Enabled");
                Intent i = new Intent(getBaseContext(), TrackingActivity.class);
                startActivity(i);
            }
        }
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
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                Log.d(TAG, "Requesting Location Permissions");
            }
            else{
                Intent i = new Intent(getBaseContext(), TrackingActivity.class);
                startActivity(i);
            }
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
