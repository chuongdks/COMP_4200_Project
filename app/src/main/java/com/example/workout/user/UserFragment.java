package com.example.workout.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.workout.R;

public class UserFragment extends Fragment {

    private View view;
    private TextView textNotification, textPrivacy, textAbout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user, container, false);


        textNotification = view.findViewById(R.id.text_notification);
        textPrivacy = view.findViewById(R.id.text_privacy);
        textAbout = view.findViewById(R.id.text_about);

        textNotification.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NotificationActivity.class);
            startActivity(intent);
        });

        textPrivacy.setOnClickListener(v ->
                showToast("Privacy clicked"));

        textAbout.setOnClickListener(v ->
                showToast("About clicked"));

        return view;
    }

    private void showToast(String message) {
        if (getContext() != null) {
            android.widget.Toast.makeText(getContext(), message, android.widget.Toast.LENGTH_SHORT).show();
        }
    }
}