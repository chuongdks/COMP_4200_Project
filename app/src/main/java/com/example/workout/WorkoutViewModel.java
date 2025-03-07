package com.example.workout;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workout.fragments.exercise.ExerciseDataSet;

import java.util.HashMap;
import java.util.Map;

// https://developer.android.com/guide/fragments/communicate#viewmodel
public class WorkoutViewModel extends ViewModel {
    // Mutable Live Data for Start Fragment
    private final MutableLiveData<ExerciseDataSet> selectedExercise = new MutableLiveData<>();      // Selected exercise

    // Mutable Live Data for Statistic Fragment
    private final MutableLiveData<Integer> workoutCount = new MutableLiveData<>();                  // # of workout session
    private final MutableLiveData<Map<String, Integer>> muscleGroupCount = new MutableLiveData<>(); // new MutableLiveData<>(new HashMap<>())

    // Setter and Getter for Selected Exercise
    public void setSelectedExercise(ExerciseDataSet exercise) {
        Log.d("test", "Storing exercise: " + exercise.getName());
        selectedExercise.setValue(exercise);
    }
    public LiveData<ExerciseDataSet> getSelectedExercise() {
        return selectedExercise;
    }

    // Increment and Getter for number of Workout
    public void setWorkoutCount(Integer workout) {
        workoutCount.setValue(workout);
    }
    public void incrementWorkoutCount() {
        if (workoutCount.getValue() != null) {
            workoutCount.setValue(workoutCount.getValue() + 1);
        }
    }
    public LiveData<Integer> getWorkoutCount() {
        return workoutCount;
    }

    // Setter and Getter for Muscle Group count
    public LiveData<Map<String, Integer>> getMuscleGroupCount() {
        return muscleGroupCount;
    }

    public void addExercise(ExerciseDataSet exercise) {
        Map<String, Integer> counts = muscleGroupCount.getValue();
        if (counts == null) return;

        String muscleGroup = exercise.getMuscleGroup();
        counts.put(muscleGroup, counts.getOrDefault(muscleGroup, 0) + 1);
        muscleGroupCount.setValue(counts);
    }

    public String getLeastWorkedMuscleGroup() {
        Map<String, Integer> counts = muscleGroupCount.getValue();
        if (counts == null || counts.isEmpty()) return "Unknown";
        return Collections.min(counts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
}
