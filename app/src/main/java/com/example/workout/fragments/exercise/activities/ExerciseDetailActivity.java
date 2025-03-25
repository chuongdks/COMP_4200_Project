package com.example.workout.fragments.exercise.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.workout.R;

public class ExerciseDetailActivity extends AppCompatActivity {
    //
    ImageView imageView;
    TextView nameTextView, descriptionTextView, muscleGroupTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // assign View by id
        imageView = findViewById(R.id.img_detail);
        nameTextView = findViewById(R.id.tv_detail_name);
        descriptionTextView = findViewById(R.id.tv_detail_desc);
        muscleGroupTextView = findViewById(R.id.tv_detail_muscle_group);

        // Getter data from Intent
        String name = getIntent().getStringExtra("EXERCISE_NAME");
        int image = getIntent().getIntExtra("EXERCISE_IMAGE", 0);
        String description = getIntent().getStringExtra("EXERCISE_DESCRIPTION");
        String muscleGroup = getIntent().getStringExtra("EXERCISE_MUSCLE_GROUP");

        // Setter data in UI
        nameTextView.setText(name);
        imageView.setImageResource(image);
        descriptionTextView.setText(description);
        muscleGroupTextView.setText(muscleGroup);
    }
}