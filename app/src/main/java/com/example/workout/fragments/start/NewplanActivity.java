package com.example.workout.fragments.start;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout.R;
import com.example.workout.database.DBHelper;
import com.example.workout.fragments.exercise.ExerciseDataSet;

import java.util.ArrayList;

public class NewplanActivity extends AppCompatActivity {

    ImageView back;
    Button btn_addtoplan;
    RecyclerView rv_exc, rv_current;
    DBHelper db;
    NewPlanExerciseListAdapter myNewAdapter;
    CurrentPlanExerciseListAdapter myCurrentAdapter;

    ArrayList<ExerciseDataSet> dataSets = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_newplan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Assign views by id
        btn_addtoplan = findViewById(R.id.button_addtoplan);
        back = findViewById(R.id.imageview_back);
        rv_exc = findViewById(R.id.rec_view);
        rv_current = findViewById(R.id.rec_current_plan);
        rv_exc.setLayoutManager(new LinearLayoutManager(this));
        rv_current.setLayoutManager(new LinearLayoutManager(this));

        db = new DBHelper(getApplicationContext(), "exerciseDB", null, 1);
        db.getReadableDatabase();

        dataSets.clear();
        dataSets.addAll(db.getAllExercises());

        if (dataSets.isEmpty()) {
            // The default Exercise datasets
            dataSets.add(new ExerciseDataSet("Bicep Curl", R.drawable.exercise_machine_photo, "A strength training exercise that targets the biceps.", "Biceps"));
            dataSets.add(new ExerciseDataSet("Squat", R.drawable.hiit_photo, "A lower body exercise that targets the quadriceps and glutes.", "Legs"));
            dataSets.add(new ExerciseDataSet("Push-up", R.drawable.sitting_bench, "A bodyweight exercise that targets the chest and triceps.", "Chest"));

            // save to database if empty
            for (ExerciseDataSet exercise : dataSets) {
                db.addExercise(exercise);
            }
        }

        // Set Adapter for Recycler view
        if (myNewAdapter == null) {
            myNewAdapter = new NewPlanExerciseListAdapter(dataSets, getApplicationContext());
            rv_exc.setAdapter(myNewAdapter);
        }
        else {
            myNewAdapter.notifyDataSetChanged();
        }




        btn_addtoplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCurrentAdapter == null) {
                    ArrayList<ExerciseDataSet> selected = new ArrayList<ExerciseDataSet>();
                    for(ExerciseDataSet e: myNewAdapter.getSelected())
                    {
                       selected.add(e);
                    }

                    myCurrentAdapter = new CurrentPlanExerciseListAdapter(selected, getApplicationContext());
                    rv_current.setAdapter(myCurrentAdapter);
                }
                else {
                    for(ExerciseDataSet e: myCurrentAdapter.dataList) {
                        Log.d("test", e.getName());
                    }
                    for(ExerciseDataSet e: myNewAdapter.getSelected())
                    {
                        if(!(myCurrentAdapter.dataList.contains(e)))
                        {
                            myCurrentAdapter.dataList.add(e);
                        }
                    }
                    for(ExerciseDataSet e: myCurrentAdapter.dataList) {
                        Log.d("test2", e.getName());
                    }
                    myCurrentAdapter.notifyDataSetChanged();
                }
            }
        });





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}