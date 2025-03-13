package com.example.workout.fragments.exercise;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.example.workout.R;
import com.example.workout.activities.WorkoutViewModel;
import com.example.workout.fragments.exercise.activities.ExerciseDetailActivity;
import java.util.ArrayList;

public class ExerciseFragmentAdapter extends RecyclerView.Adapter<ExerciseFragmentAdapter.MyViewHolder>{
    // Member has Arraylist of Text n Image, Context and
    ArrayList<ExerciseDataSet> dataList;
    Context context;
    WorkoutViewModel viewModel;

    // Constructor
    public ExerciseFragmentAdapter(ArrayList<ExerciseDataSet> data, Context context, Fragment fragment) {
        this.dataList = data;
        this.context = context;
        this.viewModel = new ViewModelProvider(fragment.requireActivity()).get(WorkoutViewModel.class);
    }

    // WHat is MyViewHolder (https://stackoverflow.com/questions/59919366/what-is-recyclerview-adaptermyadapter-myviewholder-and-how-it-is-different-fro) and (https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)
    // Basically set up by using the file card_view.xml, card_view's "view" is used below
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false); // take
        return new MyViewHolder(view);
    }

    // What is onBindViewHolder method (https://stackoverflow.com/questions/37523308/when-onbindviewholder-is-called-and-how-it-works)
    // Basically set the data up from ExerciseDataSet and optional function for the cardView
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ExerciseDataSet data = dataList.get(position);
        holder.imageView.setImageResource(data.getImage());
        holder.textView.setText(data.getName());

        holder.cardVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store the selected exercise inside the viewModel
                viewModel.setSelectedExercise(data);
                viewModel.addExerciseToMuscleMap(data);

                // Toast msg
                String msg = "You have added " + (position + 1) + " " + data.getName();
                Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        // Send data to Detail Activity
        holder.detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExerciseDetailActivity.class);
                intent.putExtra("EXERCISE_NAME", data.getName());
                intent.putExtra("EXERCISE_IMAGE", data.getImage());
                intent.putExtra("EXERCISE_DESCRIPTION", data.getDescription());
                intent.putExtra("EXERCISE_MUSCLE_GROUP", data.getMuscleGroup());
                context.startActivity(intent);
            }
        });
    }

    // getItemCount() doc: Returns the total number of items in the data set held by the adapter.
    @Override
    public int getItemCount() {
        return dataList.size(); // Math.min(dataList.size(), 2);
    }

    // ViewHolder class
    // Basically "main" class that has image, text and card info of each cardView
    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardVIew;
        ImageView imageView;
        TextView textView;
        Button detailButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.tv_card);
            cardVIew = itemView.findViewById(R.id.card_view_test);
            detailButton = itemView.findViewById(R.id.bt_detail);
        }
    }
}
