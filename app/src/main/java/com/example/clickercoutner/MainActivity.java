package com.example.clickercoutner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    public static final long TIMER_MILISECONDS = 5000;

    TextView tv_counter, tv_countDownWatch, tv_bestRecord;
    CardView cv_box, cv_boxRotate;
    int counter;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;

        tv_countDownWatch = findViewById(R.id.tv_countDown);
        tv_counter = findViewById(R.id.tv_counter);
        tv_bestRecord = findViewById(R.id.tv_bestRecord);

        cv_box = findViewById(R.id.cv_box);
        cv_boxRotate = findViewById(R.id.cv_box2);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        tv_bestRecord.setText("Your best record: " + dataBaseHelper.getHighestScore());

        cv_boxRotate.setOnClickListener(v -> {

            cv_boxRotate.animate()
                    .rotationY(90f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        cv_boxRotate.setVisibility(View.GONE);
                        cv_box.setEnabled(true);
                    });
        });

        cv_box.setOnClickListener(v -> {

            if (counter == 0) {
                new CountDownTimer(TIMER_MILISECONDS, 1) {

                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) (millisUntilFinished / 1000);
                        int miliSeconds = (int) ((millisUntilFinished % 1000) / 10);
                        tv_countDownWatch.setText(seconds + ":" + miliSeconds);
                    }

                    public void onFinish() {

                        cv_box.setEnabled(false);
                        cv_boxRotate.setVisibility(View.VISIBLE);
                        cv_boxRotate.animate()
                                .rotationY(0f)
                                .setDuration(100);

                        int highest = dataBaseHelper.getHighestScore();

                        Clicks click = new Clicks(-2, counter, getCurrentDate(), counter > highest);

                        dataBaseHelper.insertRecord(click);

                        tv_countDownWatch.setText("done!");
                        tv_counter.setText("Tap");
                        tv_bestRecord.setText("Your best record: " + dataBaseHelper.getHighestScore());

                        counter = 0;


                    }
                }.start();

            }
            minimzeAnimation(tv_counter);
            minimzeAnimation(cv_box);
            counter++;
            tv_counter.setText(String.valueOf(counter));
        });

    }

    private void minimzeAnimation(View view) {
        view.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(20).setStartDelay(20).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.animate().alpha(1f)
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(20);
                    }
                });
    }

    private String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = dateFormat.format(currentDate);
        return date;
    }
}