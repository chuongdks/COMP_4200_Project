package com.example.workout.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workout.R;
import com.example.workout.WorkoutViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticFragment extends Fragment {
    WorkoutViewModel viewModel;
    TextView statView, workoutCount;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatisticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticFragment newInstance(String param1, String param2) {
        StatisticFragment fragment = new StatisticFragment();
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
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Assign View by ID
        statView = view.findViewById(R.id.tv_stat);
        workoutCount = view.findViewById(R.id.tv_workout_count);
        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        viewModel.getMuscleGroupCount().observe(getViewLifecycleOwner(), counts -> {
            if (counts == null || counts.isEmpty()) {
                statView.setText("You haven't done anything");
                return;
            }

            // Find the least worked muscle group
            Map.Entry<String, Integer> leastWorkedMuscle = Collections.min(
                    counts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)
            );

            // Total exercises done
            int totalWorkouts = 0;

            // Build the workout stats string
            StringBuilder result = new StringBuilder("Workout Stats:\n");
            for (Map.Entry<String, Integer> entry : counts.entrySet()) {
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                totalWorkouts += entry.getValue();  // Sum up total workouts
            }

            result.append("\n💡 Suggestion: Train more ").append(leastWorkedMuscle.getKey());
            result.append("\nTotal Workouts: ").append(totalWorkouts);

            // Update UI
            Log.d("test", "Train more: " + result.toString());
            statView.setText(result.toString());
        });

        // Observe workout count
        viewModel.getWorkoutCount().observe(getViewLifecycleOwner(), count -> {
            workoutCount.setText("Total Workouts: " + count);
        });
    }
}