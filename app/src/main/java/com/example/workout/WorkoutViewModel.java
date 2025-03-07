package com.example.workout;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workout.fragments.exercise.ExerciseDataSet;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// https://developer.android.com/guide/fragments/communicate#viewmodel
public class WorkoutViewModel extends ViewModel {
    // Mutable Live Data for Start Fragment
    private final MutableLiveData<ExerciseDataSet> selectedExercise = new MutableLiveData<>();      // Selected exercise
    // Mutable Live Data for Statistic Fragment
    private final MutableLiveData<Integer> workoutCount = new MutableLiveData<>();                  // # of workout session (1 Session can have many exercises)
    private final MutableLiveData<Map<String, Integer>> muscleGroupCount = new MutableLiveData<>(new HashMap<>());

    // Setter and Getter for Selected Exercise
    public LiveData<ExerciseDataSet> getSelectedExercise() {
        return selectedExercise;
    }
    public void setSelectedExercise(ExerciseDataSet exercise) {
        Log.d("test", "Storing exercise: " + exercise.getName());
        selectedExercise.setValue(exercise);
    }

    // Setter and Getter for Muscle Group count
    public LiveData<Map<String, Integer>> getMuscleGroupCount() {
        return muscleGroupCount;
    }
    public void addExerciseToMuscleMap(ExerciseDataSet exercise) {
        Map<String, Integer> counts = muscleGroupCount.getValue();
        if (counts == null) return;

        String muscleGroup = exercise.getMuscleGroup();
        counts.put(muscleGroup, counts.getOrDefault(muscleGroup, 0) + 1);           // getOrDefault() https://www.geeksforgeeks.org/hashmap-getordefaultkey-defaultvalue-method-in-java-with-examples/
        muscleGroupCount.setValue(counts);
    }
    public String getLeastWorkedMuscleGroup() {
        Map<String, Integer> counts = muscleGroupCount.getValue();
        if (counts == null || counts.isEmpty()) return "You havent done anything";

        // Return the muscle group with the lowest count (value number)
        Map.Entry<String, Integer> leastWorkedMuscle = Collections.min(
                counts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)
        );

        // Total exercises done
        int totalWorkouts = 0;

        // String Builder for the result
        StringBuilder result = new StringBuilder("Workout Stats:\n");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            totalWorkouts += entry.getValue();  // Add to total everytime an exercise is added
        }
        result.append("\n💡 Suggestion: Train more ").append(leastWorkedMuscle.getKey());
        result.append("\nTotal Workouts: ").append(totalWorkouts);

        return result.toString();
    }

    // Increment and Getter for number of Workout (Done nothing yet)
    public LiveData<Integer> getWorkoutCount() {
        return workoutCount;
    }
    public void setWorkoutCount(Integer workout) {
        workoutCount.setValue(workout);
    }
    public void incrementWorkoutCount() {
        if (workoutCount.getValue() != null) {
            workoutCount.setValue(workoutCount.getValue() + 1);
        }
    }
}
