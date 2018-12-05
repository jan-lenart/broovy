package com.lenart.jan.broovy;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lenart.jan.broovy.model.HopViewItem;
import com.lenart.jan.broovy.model.bootstrap.HopBootstrap;

import java.util.ArrayList;

public class HopTimerActivity extends AppCompatActivity {

    private static long MILIS_IN_HOUR = 3_600_000;
    private static long MILIS_IN_MINUTE = 60_000;
    private static long MILIS_IN_SECOND = 1_000;

    private int hoursLeft;
    private int minutesLeft;
    private int secondsLeft;

    private CountDownTimer timer;
    private boolean timerRunning;
    private long timeLeftInMs = MILIS_IN_HOUR;

    private TextView timerText;
    private Button timerStartButton;
    private Button resetButton;
    private Button removeButton;

    private ArrayList<HopViewItem> hopViewItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initTimer();

    }

    private void initRecyclerView() {
        HopBootstrap bootstrap = new HopBootstrap();
        hopViewItems = bootstrap.getItems();

        RecyclerView recyclerView = findViewById(R.id.hops_list);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(hopViewItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        removeButton = findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hopViewItems.isEmpty()) {
                    hopViewItems.remove(hopViewItems.size()-1);
                    adapter.notifyItemRemoved(hopViewItems.size());
                }
            }
        });
    }

    private void initTimer() {
        timerText = findViewById(R.id.timer_text);
        timerStartButton = findViewById(R.id.timer_button);
        resetButton = findViewById(R.id.reset_button);

        timerStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) stopTimer();
                timeLeftInMs = MILIS_IN_HOUR;
                updateTimer();
            }
        });

        updateTimer();
    }

    private void startStopTimer() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void stopTimer() {
        timer.cancel();
        timerStartButton.setText("START");
        timerRunning = false;
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMs, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMs = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                updateTimer();
                timerStartButton.setText("START");
                timerRunning = false;

            }
        }.start();

        timerStartButton.setText("PAUSE");
        timerRunning = true;
    }

    private void updateTimer() {
        hoursLeft = (int) (timeLeftInMs / MILIS_IN_HOUR);
        minutesLeft = (int) (timeLeftInMs % MILIS_IN_HOUR / MILIS_IN_MINUTE);
        secondsLeft = (int) (timeLeftInMs % MILIS_IN_HOUR % MILIS_IN_MINUTE / MILIS_IN_SECOND);

        String timeLeft;

        timeLeft = hoursLeft + ":";
        if (minutesLeft < 10) {
            timeLeft += "0";
        }
        timeLeft += minutesLeft + ":";

        if (secondsLeft < 10) {
            timeLeft += "0";
        }
        timeLeft += secondsLeft;

        timerText.setText(timeLeft);
    }
}
