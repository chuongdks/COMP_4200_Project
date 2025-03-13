package com.example.workout.activities.exercise;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout.R;
import com.example.workout.fragments.exercise.ExerciseDataSet;

import java.util.ArrayList;

public class ExerciseListActivity extends AppCompatActivity {
    // Declare variable
    RecyclerView recyclerView;

    // other variable
    ArrayList<ExerciseDataSet> dataSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // assign View by id
        recyclerView = findViewById(R.id.rec_view);

        // Layout???
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Adding ExerciseDataSets to the Recycler View (todo: add a class that have a list of exercise)
        dataSets.add(new ExerciseDataSet("Bicep Curl", R.drawable.image1, "A strength training exercise that targets the biceps.", "Biceps"));
        dataSets.add(new ExerciseDataSet("Squat", R.drawable.image2, "A lower body exercise that targets the quadriceps and glutes.", "Legs"));
        dataSets.add(new ExerciseDataSet("Push-up", R.drawable.image3, "A bodyweight exercise that targets the chest and triceps.", "Chest"));
        dataSets.add(new ExerciseDataSet("Bicep Curl", R.drawable.image1, "A strength training exercise that targets the biceps.", "Biceps"));
        dataSets.add(new ExerciseDataSet("Squat", R.drawable.image2, "A lower body exercise that targets the quadriceps and glutes.", "Legs"));
        dataSets.add(new ExerciseDataSet("Push-up", R.drawable.image3, "A bodyweight exercise that targets the chest and triceps.", "Chest"));
        dataSets.add(new ExerciseDataSet("Bicep Curl", R.drawable.image1, "A strength training exercise that targets the biceps.", "Biceps"));
        dataSets.add(new ExerciseDataSet("Squat", R.drawable.image2, "A lower body exercise that targets the quadriceps and glutes.", "Legs"));
        dataSets.add(new ExerciseDataSet("Push-up", R.drawable.image3, "A bodyweight exercise that targets the chest and triceps.", "Chest"));

        // Set Adapter for Recycler view
        ExerciseActivityAdapter myAdapter = new ExerciseActivityAdapter(dataSets, ExerciseListActivity.this);
        recyclerView.setAdapter(myAdapter);
    }
}