package com.example.workout;

import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    // Declare view for this scope
    Button buttonUp, buttonDown, buttonLeft, buttonRight, buttonCancel, buttonOK;
    TextView tvStrategem, tvInput, tvTargetStrategem;
    // Other variable
    ArrayList<String> inputSequence = new ArrayList<>();
    HashMap<String, String> strategemMap = new HashMap<>();
    SoundPool soundPool;
    int soundButton, soundSuccess, soundFail, soundConfirm;
    boolean gameActive = false;
    String targetSequence = "";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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

    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    // https://stackoverflow.com/questions/53579162/cannot-resolve-findviewbyid-in-fragment and https://www.repeato.app/how-to-use-findviewbyid-in-a-fragment/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Assign View by ID
        buttonUp = view.findViewById(R.id.bt_up);
        buttonDown = view.findViewById(R.id.bt_down);
        buttonLeft = view.findViewById(R.id.bt_left);
        buttonRight = view.findViewById(R.id.bt_right);
        tvStrategem = view.findViewById(R.id.tv_strategem);
        tvInput = view.findViewById(R.id.tv_input);
        tvTargetStrategem = view.findViewById(R.id.tv_target);

        // Sound Setup
        soundPool = new SoundPool.Builder().setMaxStreams(2).build();
        // Sound List (Add more Sounds here)
        soundButton = soundPool.load(requireActivity(), R.raw.button_click, 1);
        soundSuccess = soundPool.load(requireContext(), R.raw.correct1, 1);
        soundFail = soundPool.load(getActivity(), R.raw.error, 1);
        soundConfirm = soundPool.load(getContext(), R.raw.confirmdeploy, 1);

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
    public void onDestroy() {
        super.onDestroy();
        soundPool.release();
    }
}