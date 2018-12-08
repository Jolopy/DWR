package com.example.dwr.dailyworkoutroutines;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
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

    ListFragmentListener m_ListFragmentListener;
    Context m_Context;

    // UI variables
    TextView date_TV;

    ListView m_exercisesLV;
    ArrayList<String> m_exerciseArrayList;
    ArrayAdapter<String> m_adapter;

    public ListFragment() {
        // Required empty public constructor
    }

    public interface ListFragmentListener{
        public void goToExercise(String name);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        date_TV = (TextView) view.findViewById(R.id.dateTV);
        displayDate();

        m_exercisesLV = (ListView) view.findViewById(R.id.exercisesLV);
        // Update ArrayList exercises
        getExercises();

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
