package com.example.workout;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseListFragment extends Fragment {
    // Declare variable
    RecyclerView recyclerView;
    ArrayList<ExerciseDataSet> dataSets = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExerciseListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExerciseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseListFragment newInstance(String param1, String param2) {
        ExerciseListFragment fragment = new ExerciseListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_list, container, false);
    }

    // https://stackoverflow.com/questions/53579162/cannot-resolve-findviewbyid-in-fragment and https://www.repeato.app/how-to-use-findviewbyid-in-a-fragment/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // assign View by id
        recyclerView = view.findViewById(R.id.rec_view);

        // Layout???
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Adding text and image to the Recycler View
        dataSets.add(new ExerciseDataSet("Exercise 1", R.drawable.image1));
        dataSets.add(new ExerciseDataSet("Exercise 2", R.drawable.image2));
        dataSets.add(new ExerciseDataSet("Exercise 3", R.drawable.image3));
        dataSets.add(new ExerciseDataSet("Exercise 1", R.drawable.image1));
        dataSets.add(new ExerciseDataSet("Exercise 2", R.drawable.image2));
        dataSets.add(new ExerciseDataSet("Exercise 3", R.drawable.image3));
        dataSets.add(new ExerciseDataSet("Exercise 1", R.drawable.image1));
        dataSets.add(new ExerciseDataSet("Exercise 2", R.drawable.image2));
        dataSets.add(new ExerciseDataSet("Exercise 3", R.drawable.image3));
        dataSets.add(new ExerciseDataSet("Exercise 1", R.drawable.image1));
        dataSets.add(new ExerciseDataSet("Exercise 2", R.drawable.image2));
        dataSets.add(new ExerciseDataSet("Exercise 3", R.drawable.image3));
        dataSets.add(new ExerciseDataSet("Exercise 1", R.drawable.image1));
        dataSets.add(new ExerciseDataSet("Exercise 2", R.drawable.image2));
        dataSets.add(new ExerciseDataSet("Exercise 3", R.drawable.image3));

        // Set Adapter for Recycler view
        ExerciseAdapter myAdapter = new ExerciseAdapter(dataSets,  getActivity());
        recyclerView.setAdapter(myAdapter);
    }
}