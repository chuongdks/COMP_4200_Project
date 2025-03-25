package com.example.workout.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.workout.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private PieChart pieChart1;
    private PieChart pieChart2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        // Initialize pie charts
        pieChart1 = view.findViewById(R.id.delete_pie);
        pieChart2 = view.findViewById(R.id.delete_pie2);
        
        // Setup the pie charts
        setupPieChart1();
        setupPieChart2();
        
        return view;
    }
    
    private void setupPieChart1() {
        // Simple pie chart without legends or labels
        pieChart1.setUsePercentValues(true);
        pieChart1.getDescription().setEnabled(false);
        pieChart1.setDrawHoleEnabled(true);
        pieChart1.setHoleColor(Color.TRANSPARENT);
        pieChart1.setHoleRadius(30f);
        pieChart1.setTransparentCircleRadius(35f);
        pieChart1.setDrawCenterText(false);
        pieChart1.setRotationEnabled(false);
        pieChart1.setHighlightPerTapEnabled(false);
        pieChart1.getLegend().setEnabled(false);
        
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40f, ""));
        entries.add(new PieEntry(30f, ""));
        entries.add(new PieEntry(30f, ""));
        
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(getResources().getColor(R.color.mainColor1), 
                          getResources().getColor(R.color.mainColor2), 
                          getResources().getColor(R.color.viewColor));
        dataSet.setDrawValues(false);
        dataSet.setDrawIcons(false);
        
        PieData data = new PieData(dataSet);
        pieChart1.setData(data);
        pieChart1.invalidate();
    }
    
    private void setupPieChart2() {
        // Simple pie chart without legends or labels
        pieChart2.setUsePercentValues(true);
        pieChart2.getDescription().setEnabled(false);
        pieChart2.setDrawHoleEnabled(true);
        pieChart2.setHoleColor(Color.TRANSPARENT);
        pieChart2.setHoleRadius(30f);
        pieChart2.setTransparentCircleRadius(35f);
        pieChart2.setDrawCenterText(false);
        pieChart2.setRotationEnabled(false);
        pieChart2.setHighlightPerTapEnabled(false);
        pieChart2.getLegend().setEnabled(false);
        
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(70f, ""));
        entries.add(new PieEntry(30f, ""));
        
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(getResources().getColor(R.color.mainColor1), 
                          getResources().getColor(R.color.viewColor));
        dataSet.setDrawValues(false);
        dataSet.setDrawIcons(false);
        
        PieData data = new PieData(dataSet);
        pieChart2.setData(data);
        pieChart2.invalidate();
    }
}