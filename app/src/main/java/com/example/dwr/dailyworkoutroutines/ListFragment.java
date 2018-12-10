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

    View view;

    ListFragmentListener m_ListFragmentListener;
    Context m_Context;

    // UI variables
    TextView date_TV;
    TextView todayworkoutTV;

    ListView m_exercisesLV;
    ArrayList<String> m_exerciseArrayList;
    ArrayAdapter<String> m_adapter;

    // All exercise information to parse
    ArrayList<String> m_exerciseInfoArrayList;


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

                System.out.println("goToExercise fragment");

                // Pass workout name, sets, reps, weight
                m_ListFragmentListener.goToExercise(infoStr, position);
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
        // Stores entire exercise string for sets, reps of specific exercise clicked in list view
        m_exerciseInfoArrayList = new ArrayList<String>();

        if(db == null){
            todayworkoutTV.setText("No Workouts Today");
        } else{
            Cursor result = db.getDay(day);
            int indexer = 0;
            while (result.moveToNext()) {
                if(result.getString(result.getColumnIndex(day)).equals("")){
                    Log.d(TAG, "viewDay: NOTHING HERE");
                }else {
                    Log.d(TAG, "viewDay: Workout name:" + getResources().getStringArray(R.array.workouts)[indexer]);
                    m_exerciseArrayList.add(getResources().getStringArray(R.array.workouts)[indexer]);
                    m_exerciseInfoArrayList.add(getResources().getStringArray(R.array.workouts)[indexer] + "," + result.getString(result.getColumnIndex(day)));
                }
                indexer++;
            }
            if(m_exerciseArrayList.size() == 0 ){
                Log.d(TAG, "viewDay: Nothing in here nub");
                todayworkoutTV.setText("No Workouts Today");

            }else{
                m_adapter = new ArrayAdapter<String>(m_Context, android.R.layout.simple_list_item_single_choice, m_exerciseArrayList);
                m_exercisesLV.setAdapter(m_adapter);
            }

        }
    }


    public void updateCheckedExercise(int index){
        System.out.println("This is the index!!!" + index);
        if(view == null){
            System.out.println("View is still null");
        }else{
            m_exercisesLV.setItemChecked(index, true);
        }
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
