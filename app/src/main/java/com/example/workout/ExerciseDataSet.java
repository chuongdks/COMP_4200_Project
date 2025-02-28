package com.example.workout;

public class ExerciseDataSet {
    public String text;
    public int image;

    public ExerciseDataSet(String text, int image) {
        this.text = text;
        this.image = image;
    }

    // Getter for MyDataSet
    public int getImage() {return image;}
    public String getText() {return text;}
}
