package com.example.workout.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import com.example.workout.R;
import com.example.workout.activities.WorkoutViewModel;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticFragment extends Fragment {
    WorkoutViewModel viewModel;
    TextView statView, workoutCount;
    BarChart barChart;

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
        barChart = view.findViewById(R.id.barChart);
        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        // Observe any changes in getMuscleGroupCount
        viewModel.getMuscleGroupCount().observe(getViewLifecycleOwner(), muscleGroupMap -> {
            // Check empty initially
            if (muscleGroupMap == null || muscleGroupMap.isEmpty()) {
                statView.setText("You haven't done anything");
                return;
            }

            // Find the least worked muscle group in the muscleGroupCount Map in WorkoutViewModel (how this thing works: https://www.geeksforgeeks.org/comparator-comparingint-in-java-with-examples/)
            Map.Entry<String, Integer> leastWorkedMuscle = Collections.min(
                    muscleGroupMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)
            );

            // Total exercises done
            int totalWorkouts = 0;

            // Build the workout stats string
            //StringBuilder result = new StringBuilder("Workout Stats:\n");
            String stat = "Workout Stats: \n";
            for (Map.Entry<String, Integer> entry : muscleGroupMap.entrySet()) {
                // result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                stat += "" + entry.getKey() + ": " + entry.getValue() + "\n";
                totalWorkouts += entry.getValue();  // Sum up total workouts
            }

            //result.append("\n💡 Suggestion: Train more ").append(leastWorkedMuscle.getKey());
            stat += "\n💡 Suggestion: You should Train more " + leastWorkedMuscle.getKey();
            //result.append("\nTotal Workouts: ").append(totalWorkouts);
            stat += "\nTotal Workouts: " + totalWorkouts;

            // Update UI
            // Log.d("test", "Train more: " + leastWorkedMuscle.getKey());
            statView.setText(stat);
        });

        // https://github.com/PhilJay/MPAndroidChart?tab=readme-ov-file#documentation Example: https://www.youtube.com/watch?v=zH9-CvJT8II
        viewModel.getMuscleGroupCount().observe(getViewLifecycleOwner(), muscleGroupMap -> {
            // Declare some variables for the charts
            List<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<>();
            int index = 0;

            // Iterate the muscle group map to BarChart variables
            for (Map.Entry<String, Integer> entry : muscleGroupMap.entrySet()) {
                entries.add(new BarEntry(index, entry.getValue()));
                labels.add(entry.getKey());  // Store muscle group names
                index++;
            }

            // Set up the chart based on the variables
            BarDataSet dataSet = new BarDataSet(entries, "Muscle Groups Charts");
            BarData barData = new BarData(dataSet);
            barChart.setData(barData);
            barChart.getDescription().setEnabled(false);    // disable that weird description thing below

            // Customize Chart appearance
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setValueTextColor(Color.BLACK);
            dataSet.setValueTextSize(18f);
            // Set Muscle Group name below each bar (https://weeklycoding.com/mpandroidchart-documentation/xaxis/)
            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextSize(10f);
            xAxis.setDrawAxisLine(true);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));   // Set muscle group names on X-axis
            xAxis.setGranularity(1f);                                       // Avoid dup description
        });

        // Observe workout count (Need data from StartFragment maybe)
        viewModel.getWorkoutCount().observe(getViewLifecycleOwner(), count -> {
            workoutCount.setText("Total Workouts: " + count);
        });
    }
}