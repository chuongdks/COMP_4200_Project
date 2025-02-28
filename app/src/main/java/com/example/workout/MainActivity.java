package com.example.workout;

import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //
    BottomNavigationView bottomNavigationView;

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

            // It will help to replace the
            // one fragment to other.
            if (selectedFragment != null) {
                setCurrentFragment(selectedFragment);
            }

            return true;
        });
    }

    // Apple Fragment Function
    public void appleFoo(View view) {
        HomeFragment hf = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, hf);
        ft.addToBackStack(null);
        ft.commit();
    }

    //
    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }
}