package com.example.dwr.dailyworkoutroutines;


import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;


//*********************DATABASE SUMMARY*************************
//In your Activity initialize:
//DatabaseHelper db = new DatabaseHelper(this);

//Then when you wish to update the database call:
//db.addWorkout("Monday", getResources().getStringArray(R.array.workouts)[index], "3x30");
//To delete use:
//db.removeWorkout("Monday", getResources().getStringArray(R.array.workouts)[index]);

//Replace Monday with desired day of the week and 3x30 with your desired rep count, can also add \nWeight for weight value.
//The getResources nonsense retrieves the name of the workout from the strings.xml so look there and find the workout you want. This removes the risk of
//misspelling a workout since the spelling and format matters in this case. I organized them by grouping and numbered them.

//getAllData is pretty standard returns a cursor if you want to display the data
//getDay returns entries for just given day. Days that don't have a specific workout are saved as an empty string. this means that this will give you the rep count for every workout on
//the requested day, even empty entries. Put this in your activity to get an ArrayList of just active workouts on that day so you don't get a 70-long list full of mostly ""'s:
/*
  public void viewDay(String day) {
        today = new ArrayList<Workout>();
        Cursor result = db.getDay(day);
        while (result.moveToNext()) {
            if(result.getString(result.getColumnIndex(day)) != ""){
                 today.add(new Workout(result.getString(result.getColumnIndex(0)), result.getString(result.getColumnIndex(day))));
            }
        }

    }
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int numOfWorkouts = 70;
    Context context;

    public static final String DATABASE_NAME = "DailyWorkoutRoutines.db"; //name of database
    public static final String TABLE_NAME_SCHEDULE = "Workout_Schedule"; //each day of the week and its associated workouts
    //public static final String TABLE_NAME_SUMMARY = "Workout_Summary"; //stores quality/quantity of workout ex: #of reps or how many miles

    //columns of Workout_Schedule Table

    public static final String COL_0 = "WORKOUT";
    public static final String COL_1 = "Monday";
    public static final String COL_2 = "Tuesday";
    public static final String COL_3 = "Wednesday";
    public static final String COL_4 = "Thursday";
    public static final String COL_5 = "Friday";
    public static final String COL_6 = "Saturday";
    public static final String COL_7 = "Sunday";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String workout;
        db.execSQL("create table " + TABLE_NAME_SCHEDULE + " (WORKOUT TEXT PRIMARY KEY, Monday TEXT, Tuesday TEXT, " +
                "Wednesday TEXT, Thursday TEXT, Friday TEXT, Saturday TEXT, Sunday TEXT)");//create workout schedule database

        for(int i = 0; i < 70; i++){
            workout =  context.getResources().getStringArray(R.array.workouts)[i];
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_0, workout);
            contentValues.put(COL_1, "");
            contentValues.put(COL_2, "");
            contentValues.put(COL_3, "");
            contentValues.put(COL_4, "");
            contentValues.put(COL_5, "");
            contentValues.put(COL_6, "");
            contentValues.put(COL_7, "");
            long result = db.insert(TABLE_NAME_SCHEDULE, null, contentValues);
        }
        Toast.makeText(context, "Table Made", Toast.LENGTH_LONG).show();//i think this shit just displays
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SCHEDULE);
        onCreate(db);
    }
    //retrieve all data from Database
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME_SCHEDULE, null);
        return result;
    }

    //retrieve just a single days workouts ***returns every entry, even empty entrees.  see top
    public Cursor getDay(String day){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select " + day +  " from " + TABLE_NAME_SCHEDULE, null);
        return result;
    }

    public boolean addWorkout(String day, String WORKOUT, String Quantity){ //use this to alter database, if you want to add 3 sets of 30 dips on monday, make a call: updateData("Monday", "dips", "3x30");
        SQLiteDatabase db = this.getWritableDatabase();//instance of database class
        ContentValues contentValues = new ContentValues();
        switch(day){
            case("Monday"):
                contentValues.put(COL_1, Quantity);
                break;
            case("Tuesday"):
                contentValues.put(COL_2, Quantity);
                break;
            case("Wednesday"):
                contentValues.put(COL_3, Quantity);
                break;
            case("Thursday"):
                contentValues.put(COL_4, Quantity);
                break;
            case("Friday"):
                contentValues.put(COL_5, Quantity);
                break;
            case("Saturday"):
                contentValues.put(COL_6, Quantity);
                break;
            case("Sunday"):
                contentValues.put(COL_7, Quantity);
                break;
            default:
                contentValues.put(COL_1, "ERROR");
        }
        db.update(TABLE_NAME_SCHEDULE, contentValues, "WORKOUT = ?", new String[] { WORKOUT });
        Toast.makeText(context, "Workout Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean removeWorkout(String day, String WORKOUT) {
        SQLiteDatabase db = this.getWritableDatabase();//instance of database class
        ContentValues contentValues = new ContentValues();
        switch (day) {
            case ("Monday"):
                contentValues.put(COL_1, "");
                break;
            case ("Tuesday"):
                contentValues.put(COL_2, "");
                break;
            case ("Wednesday"):
                contentValues.put(COL_3, "");
                break;
            case ("Thursday"):
                contentValues.put(COL_4, "");
                break;
            case ("Friday"):
                contentValues.put(COL_5, "");
                break;
            case ("Saturday"):
                contentValues.put(COL_6, "");
                break;
            case ("Sunday"):
                contentValues.put(COL_7, "");
                break;
            default:
                contentValues.put(COL_1, "ERROR");
        }
        db.update(TABLE_NAME_SCHEDULE, contentValues, "WORKOUT = ?", new String[]{WORKOUT});
        Toast.makeText(context, "Workout Updated", Toast.LENGTH_LONG).show();
        return true;
    }


}


