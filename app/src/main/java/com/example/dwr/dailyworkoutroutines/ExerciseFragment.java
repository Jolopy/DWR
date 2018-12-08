package com.example.dwr.dailyworkoutroutines;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


public class ExerciseFragment extends Fragment {

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


    public ExerciseFragment() {
        // Required empty public constructor
    }

    public interface ExerciseFragmentListener{

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Retrieve string from bundle
        Bundle bundle = this.getArguments();
        String exercise_name = bundle.getString("ex_name");


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        name = (TextView) view.findViewById(R.id.nameTV);
        name.setText(exercise_name);

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

        return view;
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
