package com.example.workout.user;


import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workout.R;

public class NotificationActivity extends AppCompatActivity {
    private Switch switchNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        switchNotification = findViewById(R.id.switch_notification);

        // switch
        switchNotification.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Notifications ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notifications OFF", Toast.LENGTH_SHORT).show();
            }
        });
    }
}