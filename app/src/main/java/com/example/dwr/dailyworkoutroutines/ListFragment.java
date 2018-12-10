package com.example.dwr.dailyworkoutroutines;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


public class ListFragment extends Fragment {
    private static final String TAG ="ListFragment";


    DatabaseHelper db;

    ListFragmentListener m_ListFragmentListener;
    Context m_Context;

    // UI variables
    TextView date_TV;

    ListView m_exercisesLV;
    ArrayList<String> m_exerciseArrayList;
    ArrayAdapter<String> m_adapter;

    ArrayList<Workout> today;

    public ListFragment() {
        // Required empty public constructor
    }

    public interface ListFragmentListener{
        public void goToExercise(String name);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = new DatabaseHelper(getContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        date_TV = (TextView) view.findViewById(R.id.dateTV);
        displayDate();

        m_exercisesLV = (ListView) view.findViewById(R.id.exercisesLV);
        // Update ArrayList exercises
        //getExercises();

        m_exercisesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Go to exercise
                String exercise_name = m_exercisesLV.getItemAtPosition(position).toString();

                System.out.println("Clicked List View Item!");
                System.out.println(exercise_name);


                System.out.println("goToExercise fragment");
                m_ListFragmentListener.goToExercise(exercise_name);
            }
        });

       return view;
    }

    public void displayDate() {
        Date date = new Date();
        String day = (String) DateFormat.format("EEEE", date); // Saturday
        String month = (String) DateFormat.format("MMM",  date); // Dec
        String day_num = (String) DateFormat.format("dd",   date); // 08
        String date_str = day + ", " + month + " " + day_num;
        date_TV.setText(date_str);
        Log.d(TAG, "displayDate: " +date_str);
        viewDay(day);
    }

    public void viewDay(String day) {
        // Stores just the exercise names for the current day
        m_exerciseArrayList = new ArrayList<String>();
        // Stores Workout objects so I can get sets, reps of specific exercise clicked in list view
        today = new ArrayList<Workout>();

        Cursor result = db.getDay(day);
        int indexer = 0;

        while (result.moveToNext()) {
            if(result.getString(result.getColumnIndex(day)).equals("")){
                Log.d(TAG, "viewDay: NOTHING HERE");

            }else {

                // ** Need Workout Object into ArrayList today here
                // not sure how to do that
                Log.d(TAG, "viewDay: Workout name:" + getResources().getStringArray(R.array.workouts)[indexer]);
                m_exerciseArrayList.add(getResources().getStringArray(R.array.workouts)[indexer]);
                // ** Need just the exercise name into ArrayList m_exerciseArrayList here
                //m_exerciseArrayList.add(getResources().getStringArray(R.array.workouts)[indexer] + ": "  + result.getString(result.getColumnIndex(day)));
            }
            indexer++;
        }
        if(m_exerciseArrayList.size() == 0 ){
            Log.d(TAG, "viewDay: Nothing in here nub");
            //RICH
            //RICH
            //RICH
            //RICH
            // ADD THIS TO TODAY's WORKOUTS text. Like .setText("No Workouts Today");

        }else{
            m_adapter = new ArrayAdapter<String>(m_Context, android.R.layout.simple_list_item_multiple_choice, m_exerciseArrayList);
            m_exercisesLV.setAdapter(m_adapter);
        }
    }

    public void getExercises(){
        // contactArrayList = dbHandler.getContactsArrayList();
        m_exerciseArrayList = new ArrayList<String>();
        m_exerciseArrayList.add("Dips"); m_exerciseArrayList.add("Push-ups"); m_exerciseArrayList.add("Leg Curls"); m_exerciseArrayList.add("Jump Roping");
        m_adapter = new ArrayAdapter<String>(m_Context, android.R.layout.simple_list_item_multiple_choice, m_exerciseArrayList);
        m_exercisesLV.setAdapter(m_adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        m_Context = context;
        try{
            Activity activity = (Activity) context;
            m_ListFragmentListener = (ListFragmentListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must override");
        }
    }

}
