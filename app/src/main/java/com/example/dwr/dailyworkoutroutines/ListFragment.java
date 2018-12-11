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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


public class ListFragment extends Fragment {
    private static final String TAG ="ListFragment";

    DatabaseHelper db;

    View view;

    ListFragmentListener m_ListFragmentListener;
    Context m_Context;

    // UI variables
    TextView date_TV;
    TextView todayworkoutTV;
    TextView progressTV;
    ProgressBar progressBar;
    int progress_val = 0;

    ListView m_exercisesLV;
    ArrayList<String> m_exerciseArrayList;
    ArrayAdapter<String> m_adapter;

    // All exercise information to parse
    ArrayList<String> m_exerciseInfoArrayList;

    // Completed exercises index
    ArrayList<Integer> m_CompletedExercises;



    public ListFragment() {
        // Required empty public constructor
    }

    public interface ListFragmentListener{
        public void goToExercise(String name, int index);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = new DatabaseHelper(getContext());

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressTV = (TextView) view.findViewById(R.id.progressTV);

        todayworkoutTV = (TextView) view.findViewById(R.id.workout_title_TV);

        date_TV = (TextView) view.findViewById(R.id.dateTV);
        m_exercisesLV = (ListView) view.findViewById(R.id.exercisesLV);
        // Display today's date and update ListView for today
        displayDate();

        m_exercisesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Go to exercise
                String exercise_name = m_exercisesLV.getItemAtPosition(position).toString();
                String infoStr = m_exerciseInfoArrayList.get(position);

                Log.i(TAG, "m_exerciseLV onitem click listener goToExercise(string, index) = " + infoStr + ", " + position);
                // Pass workout name, sets, reps, weight
                m_ListFragmentListener.goToExercise(infoStr, position);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        if(m_CompletedExercises != null && m_exerciseArrayList != null){
            Log.i(TAG, "none arraylist are null now setting progress");
            Log.i(TAG,"The updated progres should be " + (m_CompletedExercises.size()/m_exerciseArrayList.size()) * 100);
            progressBar.setProgress((m_CompletedExercises.size()/m_exerciseArrayList.size()) * 100);
        }
        progressBar.setProgress(progress_val);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG,"onResume");

        if(m_CompletedExercises != null && m_exerciseArrayList != null){
            Log.i(TAG,"m_CompletedExercises and m_exercisesArrayList != null which is good");
            Log.i(TAG, "m_CompletedExercises.size = " + m_CompletedExercises.size());
            Log.i(TAG, "m_exercisesArrayList.size = " + m_exerciseArrayList.size());
            Log.i(TAG,"The updated progres should be " + (m_CompletedExercises.size() * 100 / m_exerciseArrayList.size()));
            progress_val = m_CompletedExercises.size() * 100 / m_exerciseArrayList.size();
            progressBar.setProgress(progress_val);
            progressTV.setText("Workout Completion: " + progress_val + "%");

            m_adapter = new ArrayAdapter<String>(m_Context, android.R.layout.simple_list_item_checked, m_exerciseArrayList);
            m_exercisesLV.setAdapter(m_adapter);

            for(int i = 0; i < m_CompletedExercises.size(); i++){
                m_exercisesLV.setItemChecked(i, true);
            }

        }else{
            Log.i(TAG,"null ArrayLists in onResume");
        }
        Log.i(TAG, "end onResume");
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
        // Stores entire exercise string for sets, reps of specific exercise clicked in list view
        m_exerciseInfoArrayList = new ArrayList<String>();

        if(db == null){
            todayworkoutTV.setText("No Workouts Today");
        } else{
            Cursor result = db.getDay(day);
            int indexer = 0;
            while (result.moveToNext()) {
                if(result.getString(result.getColumnIndex(day)).equals("")){
                    //Log.d(TAG, "viewDay: NOTHING HERE");
                }else {
                    //Log.d(TAG, "viewDay: Workout name:" + getResources().getStringArray(R.array.workouts)[indexer]);
                    m_exerciseArrayList.add(getResources().getStringArray(R.array.workouts)[indexer]);
                    m_exerciseInfoArrayList.add(getResources().getStringArray(R.array.workouts)[indexer] + "," + result.getString(result.getColumnIndex(day)));
                }
                indexer++;
            }
            if(m_exerciseArrayList.size() == 0 ){
                Log.d(TAG, "viewDay: Nothing in here nub");
                todayworkoutTV.setText("No Workouts Today");

            }else{
                m_adapter = new ArrayAdapter<String>(m_Context, android.R.layout.simple_list_item_checked, m_exerciseArrayList);
                m_exercisesLV.setAdapter(m_adapter);
            }

        }
    }


    public void updateCompletedExercises(ArrayList<Integer> completed){
        Log.i(TAG, "in updateCompletedExercises");
        m_CompletedExercises = new ArrayList<Integer>();
        m_CompletedExercises = completed;
        Log.i(TAG, "m_CompletedExercises.size() = " + m_CompletedExercises.size());
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
