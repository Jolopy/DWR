package com.example.dwr.dailyworkoutroutines;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;


public class RoutinesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG ="RoutinesActivity";
    private String day;
    private TextView mDay;
    private ListView workoutList,workoutDayList;

    private ArrayList<String> arrayList1;
    private ArrayList<String> arrayList2;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;

    private Button mChestButton,mBicepsButton,mTricepsButton,
        mShoulderButton,mAbsButton,mBackButton,mLegsButton,mCardioButton;

    private TextView mTextWorkout,mReps,mSets,mNumber1,mNumber2;
    private Button mButtonUp1,mButtonUp2,mButtonDown1,mButtonDown2;
    private EditText mEditText;
    private Button mSave;

    public int int1,int2 = 0;

    public String selected;

    private DatabaseHelper db;

    public ArrayList<Workout> today;
    public int indexer = 0;
    public Cursor result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);


        //Top Half
        mDay = (TextView)findViewById(R.id.dayText);
        workoutList = (ListView)findViewById(R.id.listOfWorkout);
        workoutDayList = (ListView)findViewById(R.id.listOfDay);
        mChestButton =(Button)findViewById(R.id.chestButton);
        mBicepsButton =(Button)findViewById(R.id.bicepsButton);
        mTricepsButton =(Button)findViewById(R.id.tricepsButton);
        mShoulderButton =(Button)findViewById(R.id.shoulderButton);
        mAbsButton =(Button)findViewById(R.id.absButton);
        mBackButton =(Button)findViewById(R.id.backButton);
        mLegsButton =(Button)findViewById(R.id.legsButton);
        mCardioButton =(Button)findViewById(R.id.cardioButton);

        //Bottom half
        mTextWorkout = (TextView) findViewById(R.id.savingWorkoutText);
        mReps = (TextView) findViewById(R.id.mReps);
        mSets = (TextView) findViewById(R.id.mSets);
        mNumber1 = (TextView) findViewById(R.id.mNumber1);
        mNumber2 = (TextView) findViewById(R.id.mNumber2);

        mButtonUp1 = (Button) findViewById(R.id.push_button1);
        mButtonDown1 =(Button) findViewById(R.id.push_button2);
        mButtonUp2 = (Button) findViewById(R.id.push_button3);
        mButtonDown2 =(Button) findViewById(R.id.push_button4);
        mEditText = (EditText) findViewById(R.id.editText);
        mSave = (Button) findViewById(R.id.saveButton);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        //I should of use a fragment i regret everything
        setInvisible();


        arrayList2 = new ArrayList<String>();

        adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.test_list_item, arrayList2);


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

        workoutDayList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selected = (workoutDayList.getItemAtPosition(position).toString());
                String[] array = selected.split(":");

                Log.d(TAG, "onItemClick: " +array[0]);
                db.removeWorkout(day, array[0]);

                adapter2.remove(selected);
                workoutDayList.setAdapter(adapter2);

                Toast.makeText(RoutinesActivity.this, "Removed Selected Item", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = (workoutList.getItemAtPosition(position).toString());
                mTextWorkout.setText(selected);
                setVisible();
            }
        });

        workoutList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selected = (workoutList.getItemAtPosition(position).toString());
                Log.d(TAG, "onNavigationItemSelected: Info");
                Intent i = new Intent(getBaseContext(), InfoActivity.class);
                i.putExtra("workout", selected);
                startActivity(i);

                return false;
            }
        });


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
                day = "Monday";
                break;
            case "tuesday":
                mDay.setText("Tuesday");
                day = "Tuesday";
                break;
            case "wednesday":
                mDay.setText("Wednesday");
                day = "Wednesday";
                break;
            case "thursday":
                mDay.setText("Thursday");
                day = "Thursday";
                break;
            case "friday":
                mDay.setText("Friday");
                day = "Friday";
                break;
            case "saturday":
                mDay.setText("Saturday");
                day = "Saturday";
                break;
            case "sunday":
                mDay.setText("Sunday");
                day = "Sunday";
                break;
            default:
                mDay.setText("Invalid Try again");
                break;
        }

        viewDay(day);

        mChestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(0);
                workoutList.setAdapter(adapter1);

            }
        });
        mBicepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(1);
                workoutList.setAdapter(adapter1);

            }
        });
        mTricepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(2);
                workoutList.setAdapter(adapter1);

            }
        });
        mShoulderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(3);
                workoutList.setAdapter(adapter1);

            }
        });
        mAbsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(4);
                workoutList.setAdapter(adapter1);

            }
        });
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(5);
                workoutList.setAdapter(adapter1);

            }
        });
        mLegsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(6);
                workoutList.setAdapter(adapter1);

            }
        });
        mCardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(7);
                workoutList.setAdapter(adapter1);

            }
        });


        mButtonUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int1++;
                mNumber1.setText(String.valueOf(int1));
            }
        });
        mButtonDown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(int1 == 0){
                    Toast.makeText(RoutinesActivity.this, "Cannot go lower", Toast.LENGTH_SHORT).show();
                }else{
                    int1--;
                    mNumber1.setText(String.valueOf(int1));

                }

            }
        });
        mButtonUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int2++;
                mNumber2.setText(String.valueOf(int2));
            }
        });
        mButtonDown2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(int2 == 0){
                    Toast.makeText(RoutinesActivity.this, "Cannot go lower", Toast.LENGTH_SHORT).show();
                }else{
                    int2--;
                    mNumber2.setText(String.valueOf(int2));

                }
            }
        });


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RoutinesActivity.this, "Workout Added to " + day, Toast.LENGTH_SHORT).show();
                adapter2.add(selected + ": " +int1+"x"+int2+" Lbs: " + mEditText.getText().toString());
                workoutDayList.setAdapter(adapter2);
                db.addWorkout(day, selected, int1+"x"+int2+" Lbs: " + mEditText.getText().toString());


                int1 = 0;
                int2 = 0;
                mNumber1.setText(String.valueOf(int1));
                mNumber2.setText(String.valueOf(int2));

                setInvisible();
            }
        });

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


    public void populateList(int x){
        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.test_list_item, arrayList1);

        if(x == 0){
            for(int i = 0; i <= 7; i++){
                adapter1.add(getResources().getStringArray(R.array.workouts)[i]);
            }
        }else if(x == 1){
            for(int i = 8; i <= 15; i++){
                adapter1.add(getResources().getStringArray(R.array.workouts)[i]);
            }
        } else if(x == 2){
            for(int i = 16; i <= 23; i++){
                adapter1.add(getResources().getStringArray(R.array.workouts)[i]);
            }

        }else if( x == 3){
            for(int i = 24; i <= 31; i++){
                adapter1.add(getResources().getStringArray(R.array.workouts)[i]);
            }
        }else if( x == 4){
            for(int i = 32; i <= 39; i++){
                adapter1.add(getResources().getStringArray(R.array.workouts)[i]);
            }

        }else if( x == 5){
            for(int i = 40; i <= 47; i++){
                adapter1.add(getResources().getStringArray(R.array.workouts)[i]);
            }

        }else if( x == 6){
            for(int i = 48; i <= 58; i++){
                adapter1.add(getResources().getStringArray(R.array.workouts)[i]);
            }

        }else if( x == 7) {
            for (int i = 59; i <= 69; i++) {
                adapter1.add(getResources().getStringArray(R.array.workouts)[i]);
            }
        }

    }

    public void setInvisible() {
        mTextWorkout.setVisibility(View.INVISIBLE);
        mReps.setVisibility(View.INVISIBLE);
        mSets.setVisibility(View.INVISIBLE);
        mNumber1.setVisibility(View.INVISIBLE);
        mNumber2.setVisibility(View.INVISIBLE);
        mButtonDown1.setVisibility(View.INVISIBLE);
        mButtonDown2.setVisibility(View.INVISIBLE);
        mButtonUp1.setVisibility(View.INVISIBLE);
        mButtonUp2.setVisibility(View.INVISIBLE);
        mEditText.setVisibility(View.INVISIBLE);
        mSave.setVisibility(View.INVISIBLE);
    }

    public void setVisible(){
        mTextWorkout.setVisibility(View.VISIBLE);
        mReps.setVisibility(View.VISIBLE);
        mSets.setVisibility(View.VISIBLE);
        mNumber1.setVisibility(View.VISIBLE);
        mNumber2.setVisibility(View.VISIBLE);
        mButtonDown1.setVisibility(View.VISIBLE);
        mButtonDown2.setVisibility(View.VISIBLE);
        mButtonUp1.setVisibility(View.VISIBLE);
        mButtonUp2.setVisibility(View.VISIBLE);
        mEditText.setVisibility(View.VISIBLE);
        mSave.setVisibility(View.VISIBLE);

    }
    public void viewDay(String day) {
        today = new ArrayList<Workout>();
        if(db == null) {
            Log.d(TAG, "viewDay: Do nothing");
        }else{
            Cursor result = db.getDay(day);
            while (result.moveToNext()) {
                if(result.getString(result.getColumnIndex(day)).equals("")){
                    //Log.d(TAG, "viewDay: NOTHING HERE");

                }else {
                    //Log.d(TAG, "viewDay: Workout name:" + getResources().getStringArray(R.array.workouts)[indexer] + "  REPS SETS:" + result.getString(result.getColumnIndex(day)));
                    adapter2.add(getResources().getStringArray(R.array.workouts)[indexer] + ": "  + result.getString(result.getColumnIndex(day)));

                }
                indexer++;
            }
            workoutDayList.setAdapter(adapter2);
        }
    }
}

