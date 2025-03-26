package com.example.workout.fragments.start;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout.R;
import com.example.workout.fragments.exercise.ExerciseDataSet;

import java.util.ArrayList;

public class CurrentPlanExerciseListAdapter extends RecyclerView.Adapter<CurrentPlanExerciseListAdapter.MyViewHolder>{
    // Member has Arraylist of Text n Image, Context and
    ArrayList<ExerciseDataSet> dataList;
    Context context;
    CheckBox cb;

    // Constructor
    public CurrentPlanExerciseListAdapter(ArrayList<ExerciseDataSet> data, Context context) {
        this.dataList = data;
        this.context = context;
    }

    // WHat is MyViewHolder (https://stackoverflow.com/questions/59919366/what-is-recyclerview-adaptermyadapter-myviewholder-and-how-it-is-different-fro) and (https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)
    // Basically set up by using the file card_view.xml, card_view's "view" is used below
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view2, parent, false); // take
        return new MyViewHolder(view);

    }

    // What is onBindViewHolder method (https://stackoverflow.com/questions/37523308/when-onbindviewholder-is-called-and-how-it-works)
    // Basically set the data up from ExerciseDataSet and optional function for the cardView
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ExerciseDataSet data = dataList.get(position);
        holder.textView.setText(data.getName());
        holder.cb.setVisibility(View.GONE);
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
        TextView textView;
        CheckBox cb;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_card);
            cb = itemView.findViewById(R.id.cb_addtoplan);
            cardVIew = itemView.findViewById(R.id.card_view_test_2);
        }
    }
}
