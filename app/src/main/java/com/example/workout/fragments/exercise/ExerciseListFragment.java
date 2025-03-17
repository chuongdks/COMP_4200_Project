package com.example.workout.fragments.exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.workout.R;
import com.example.workout.activities.WorkoutViewModel;
import com.example.workout.database.DBHelper;
import com.example.workout.database.ExerciseDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseListFragment extends Fragment {
    // Declare variable
    RecyclerView recyclerView;
    ArrayList<ExerciseDataSet> dataSets = new ArrayList<>();
    WorkoutViewModel viewModel;
    FloatingActionButton addExerciseButton;
    ExerciseFragmentAdapter myAdapter;
    DBHelper db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExerciseListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExerciseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseListFragment newInstance(String param1, String param2) {
        ExerciseListFragment fragment = new ExerciseListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_list, container, false);
    }

    // https://stackoverflow.com/questions/53579162/cannot-resolve-findviewbyid-in-fragment and https://www.repeato.app/how-to-use-findviewbyid-in-a-fragment/
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        // assign Recycler view by id and set the Layout
//        recyclerView = view.findViewById(R.id.rec_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        // Create an exercise database
//        db = new DBHelper(getContext(), "exerciseDB", null, 1);
//        db.getWritableDatabase();
//
//        refreshExerciseList();
//
//        // Intent: https://stackoverflow.com/questions/20114485/use-onactivityresult-android
//        addExerciseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentAdd = new Intent(getContext(), ExerciseDatabase.class);
//                startActivity(intentAdd);
//            }
//        });
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        refreshExerciseList();
//    }
//
//    // refresh the recyclerView Adapter
//    void refreshExerciseList() {
//        dataSets.clear();
//        dataSets.addAll(db.getAllExercises());
//
//        // Check if db is empty by checking dataSets
//        if (dataSets.isEmpty()) {
//            // The default Exercise datasets
//            dataSets.add(new ExerciseDataSet("Bicep Curl", R.drawable.image1, "A strength training exercise that targets the biceps.", "Biceps"));
//            dataSets.add(new ExerciseDataSet("Squat", R.drawable.image2, "A lower body exercise that targets the quadriceps and glutes.", "Legs"));
//            dataSets.add(new ExerciseDataSet("Push-up", R.drawable.image3, "A bodyweight exercise that targets the chest and triceps.", "Chest"));
//
//            // save to database if empty
//            for (ExerciseDataSet exercise : dataSets) {
//                db.addExercise(exercise);
//            }
//        }
//
//        // Set Adapter for Recycler view
//        if (myAdapter == null) {
//            myAdapter = new ExerciseFragmentAdapter(dataSets, getActivity(), this);
//            recyclerView.setAdapter(myAdapter);
//        }
//        else {
//            myAdapter.notifyDataSetChanged();
//        }
//    }
}