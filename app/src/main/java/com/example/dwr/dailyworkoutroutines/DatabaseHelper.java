package com.example.dwr.dailyworkoutroutines;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int numOfWorkouts = 70;
    public static final String workouts[] = new String[numOfWorkouts];

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


    public DatabaseHelper(Context context) {super(context, DATABASE_NAME, null, 1); //Constructor
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_SCHEDULE + " (WORKOUT TEXT PRIMARY KEY, Monday TEXT, Tuesday TEXT, " +
                "Wednesday TEXT, Thursday TEXT, Friday TEXT, Saturday TEXT, Sunday TEXT)");//create workout schedule database
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {// i think this is in case table already exists?
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SCHEDULE);
        onCreate(db);
    }

    public boolean insertData(String workout){
        SQLiteDatabase db = this.getWritableDatabase();//instance of database class
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
        if(result == -1) {
            return false;
        }else{
            return true;
        }
    }
    public void initTable(){
        boolean isInserted;
        for(int i = 0; i < 70; i++){
            isInserted = insertData(Resources.getSystem().getStringArray(R.array.workouts)[i]);
        }
    }

    public Cursor getAllData() {//cursor supplies readwrite
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME_SCHEDULE, null);
        return result;
    }

    public boolean updateData(String ID, String name, String number){
        SQLiteDatabase db = this.getWritableDatabase();//instance of database class
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, ID);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, number);
        db.update(TABLE_NAME_SCHEDULE, contentValues, "ID = ?", new String[] { ID });
        return true;
    }

    public Integer deleteData(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_SCHEDULE, "ID = ?", new String[] {ID});
    }

}


