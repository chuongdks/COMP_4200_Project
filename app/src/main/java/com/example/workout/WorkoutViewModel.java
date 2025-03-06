package com.example.workout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workout.fragments.exercise.ExerciseDataSet;

// https://developer.android.com/guide/fragments/communicate#viewmodel
public class WorkoutViewModel extends ViewModel {
    private final MutableLiveData<ExerciseDataSet> selectedExercise = new MutableLiveData<>();      // Selected exercise
    private final MutableLiveData<Integer> workoutCount = new MutableLiveData<>();                  // # of workout session

    // Setter and Getter for Selected Exercise
    public void setSelectedExercise(ExerciseDataSet exercise) {
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


}
