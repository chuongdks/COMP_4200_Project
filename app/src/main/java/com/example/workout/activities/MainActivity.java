package com.example.workout.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workout.R;
import com.example.workout.fragments.exercise.ExerciseListFragment;
import com.example.workout.fragments.HomeFragment;
import com.example.workout.fragments.StartFragment;
import com.example.workout.fragments.StatisticFragment;
import com.example.workout.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // add Global variables here
    BottomNavigationView bottomNavigationView;
    WorkoutViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // https://developer.android.com/guide/fragments/communicate#host-activity (Example in MainActivity)
        viewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        // Bottom Navigation (https://www.geeksforgeeks.org/bottomnavigationview-inandroid/)
        bottomNavigationView = findViewById(R.id.bottomNav);
        Fragment firstFragment = new HomeFragment();
        setCurrentFragment(firstFragment);

        bottomNavigationView.setOnItemSelectedListener (item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.home_menu) {
                selectedFragment = firstFragment;
            }
            else if (itemId == R.id.stat_menu) {
                selectedFragment = new StatisticFragment();
            }
            else if (itemId == R.id.start_menu) {
                selectedFragment = new StartFragment();
            }
            else if (itemId == R.id.list_menu) {
                selectedFragment = new ExerciseListFragment();
            }
            else if (itemId == R.id.user_menu) {
                selectedFragment = new UserFragment();
            }

            // Set the Fragment
            if (selectedFragment != null) {
                setCurrentFragment(selectedFragment);
            }

            return true;
        });
    }

    // Function to set the Fragment and activate it
    void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }
}