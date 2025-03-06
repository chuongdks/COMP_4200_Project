package com.example.workout.fragments.exercise;

public class ExerciseDataSet {
    private String text;
    private int image;
    private String description;
    private String muscleGroup;

    // Constructor
    public ExerciseDataSet(String text, int image, String description, String muscleGroup) {
        this.text = text;
        this.image = image;
        this.description = description;
        this.muscleGroup = muscleGroup;
    }

    // Getter for MyDataSet
    public int getImage() { return image; }
    public String getText() { return text; }
    public String getDescription() { return description; }
    public String getMuscleGroup() { return muscleGroup; }
}
