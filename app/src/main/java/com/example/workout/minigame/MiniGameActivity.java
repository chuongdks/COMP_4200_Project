package com.example.workout.minigame;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.workout.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MiniGameActivity extends AppCompatActivity {
    // Declare view for this scope
    Button buttonUp, buttonDown, buttonLeft, buttonRight, buttonCancel, buttonOK;
    TextView tvStrategem, tvInput, tvTargetStrategem;
    // Other variable
    ArrayList<String> inputSequence = new ArrayList<>();
    HashMap<String, String> strategemMap = new HashMap<>();
    SoundPool soundPool;
    int soundButton, soundSuccess, soundFail, soundConfirm;
    boolean gameActive = false;
    // boolean validStrategem = false;
    String targetSequence = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mini_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Assign View by ID
        buttonUp = findViewById(R.id.bt_up);
        buttonDown = findViewById(R.id.bt_down);
        buttonLeft = findViewById(R.id.bt_left);
        buttonRight = findViewById(R.id.bt_right);
        buttonCancel = findViewById(R.id.bt_cancel);
        buttonOK = findViewById(R.id.bt_ok);
        tvStrategem = findViewById(R.id.tv_strategem);
        tvInput = findViewById(R.id.tv_input);
        tvTargetStrategem = findViewById(R.id.tv_target);

        // Sound Setup
        soundPool = new SoundPool.Builder().setMaxStreams(2).build();
        // Sound List (Add more Sounds here)
        soundButton = soundPool.load(this, R.raw.button_click, 1);
        soundSuccess = soundPool.load(this, R.raw.correct1, 1);
        soundFail = soundPool.load(this, R.raw.error, 1);
        soundConfirm = soundPool.load(this, R.raw.confirmdeploy, 1);

        // Define List of Strategems Key Value pair(Add more strategem inputs here)
        strategemMap.put("up right down down down", "500 KG Bomb");
        strategemMap.put("up down right left", "Reinforcement");
        strategemMap.put("right down up right down", "Orbital Laser");
        strategemMap.put("down up right down up right down up", "Hell bomb");

        startNewGame(); // Start the first game

        // Directional Button click listener
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Foo
                handleInput("up");
            }
        });
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Foo
                handleInput("down");
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Foo
                handleInput("left");
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Foo
                handleInput("right");
            }
        });

        // Cancel the strategem
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetInput();
            }
        });

//        // Confirm Button (Use for Strategem App)
//        buttonOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (validStrategem) {
//                    // Play sounds depend on strategem value using switch case
//                    soundPool.play(soundConfirm, 1, 1, 0, 0, 1);
//                    inputSequence.clear();
//                    tvInput.setText("");
//                    tvStrategem.setText("");
//                    validStrategem = false;
//                }
//                else {
//                    soundPool.play(soundFail, 1, 1, 0, 0, 1);
//                }
//            }
//        });
    }

    // Start a new round by select random Strategem
    void startNewGame() {
        List<String> keys = new ArrayList<>(strategemMap.keySet()); // return a Set of keys, put them in a List
        Random random = new Random();
        targetSequence = keys.get(random.nextInt(keys.size()));     // Choose a random strategem for the TargetSequence

        // Clear the screen and set game flag to True
        tvTargetStrategem.setText(targetSequence);                  // Show the Target Strategem
        tvInput.setText("");
        tvStrategem.setText("");
        inputSequence.clear();
        gameActive = true;
    }

    // Handle the input sequence everytime a direction button is pressed funciton
    void handleInput(String directionInput) {
        // Check if game flag is active
        if (!gameActive) return;

        // Handle the Directional input
        inputSequence.add(directionInput);
        soundPool.play(soundButton, 1, 1, 0, 0, 1);
        String currentSequence = String.join(" ", inputSequence);
        tvInput.setText(currentSequence); // display Strategem name

        // Check the input during gameplay
        checkInput();

//        // Check if the sequence match the "strategemMap" Key value (Uncomment for strategem app)
//        if (strategemMap.containsKey(currentSequence)) {
//            tvStrategem.setText(strategemMap.get(currentSequence)); // display Strategem name
//            soundPool.play(soundSuccess, 1, 1, 0, 0, 1);
//            inputSequence.clear();  // Empty input sequence for the next one
//            tvInput.setText("");
//            // validStrategem = true;
//        }
//        else {
//            //
//        }
    }

    // Reset input sequence
    void resetInput() {
        soundPool.play(soundFail, 1, 1, 0, 0, 1);
        inputSequence.clear();
        tvInput.setText("");
        tvStrategem.setText("");
    }

    // Check input during gameplay, if missed input -> reset, if fully correct move to next strategem
    void checkInput() {
        String currentSequence = String.join(" ", inputSequence);

        //  Detect error in the current sequence when button is pressed, check for every step (Just like the game)
        if (!targetSequence.startsWith(currentSequence)) {
            resetInput();
            return;
        }

        if (currentSequence.equals(targetSequence)) {
            tvStrategem.setText("Correct!");
            soundPool.play(soundSuccess, 1, 1, 0, 0, 1);
            gameActive = false;

            // Start next sequence after delay
            tvStrategem.postDelayed(this::startNewGame, 2000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
    }
}