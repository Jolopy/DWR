package com.example.dwr.dailyworkoutroutines;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


public class ExerciseFragment extends Fragment {

    private static final String TAG ="ExerciseFragment";

    ExerciseFragmentListener m_ExerciseFragmentListener;
    Context m_Context;

    // UI variables
    TextView name;
    TextView sets;
    TextView reps;
    TextView weight;
    Chronometer timer;
    Button startButton;
    Button stopButton;
    Button completeButton;

    int listview_index;


    public ExerciseFragment() {
        // Required empty public constructor
    }

    public interface ExerciseFragmentListener{
        public void markExerciseComplete(int index);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Retrieve string from bundle
        Bundle bundle = this.getArguments();
        String exercise_info = bundle.getString("ex_name");

        listview_index = bundle.getInt("index_bun");

        Log.i(TAG, "onCreateView retrieved bundle arguments");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        name = (TextView) view.findViewById(R.id.nameTV);
        sets = (TextView) view.findViewById(R.id.setsTV);
        reps = (TextView) view.findViewById(R.id.repsTV);
        weight = (TextView) view.findViewById(R.id.weightTV);

        timer = (Chronometer) view.findViewById(R.id.timer);

        startButton = (Button) view.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        stopButton = (Button) view.findViewById(R.id.endButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.stop();
            }
        });

        completeButton = (Button) view.findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "completeButton onClick passing index = " + listview_index);
                m_ExerciseFragmentListener.markExerciseComplete(listview_index);
            }
        });



        // Parse and display exercise TextView info
        splitString(exercise_info);

        return view;
    }

    public void splitString(String info){
        // Parse exercise_info here
        String[] parses = info.split("\\s*,\\s*");

        // Exercise name ***
        String exercise_name = parses[0];
        name.setText(exercise_name);

        String rest = parses[1];
        String parts[] = rest.split(" ", 2);

        String setsAndreps = parts[0];
        String[] sets_reps = setsAndreps.split("x");
        String actual_sets = sets_reps[1];
        sets.setText("Sets: " + actual_sets);
        String actual_reps = sets_reps[0];
        reps.setText("Reps: " + actual_reps);

        String lbsWeight = parts[1];
        String parse_weight[] = lbsWeight.split(" ", 2);

        // String weight ***
        String actual_weight = parse_weight[1];
        weight.setText("Weight: " + actual_weight);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        m_Context = context;
        try{
            Activity activity = (Activity) context;
            m_ExerciseFragmentListener = (ExerciseFragmentListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must override");
        }
    }

}
