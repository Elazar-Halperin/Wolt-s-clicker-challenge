package com.example.clickercoutner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final long TIMER_MILISECONDS = 5000;

    TextView tv_counter;
    TextView tv_countDownWatch;
    CardView cv_box;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;

        tv_countDownWatch = findViewById(R.id.tv_countDown);
        tv_counter = findViewById(R.id.tv_counter);
        cv_box = findViewById(R.id.cv_box);


        cv_box.setOnClickListener( v -> {

            if(counter == 0) {
                new CountDownTimer(TIMER_MILISECONDS, 1) {

                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) (millisUntilFinished / 1000);
                        int miliSeconds = (int) ((millisUntilFinished % 1000) / 10);
                        tv_countDownWatch.setText(seconds + ":" + miliSeconds);
                    }

                    public void onFinish() {
                        tv_countDownWatch.setText("done!");
                        cv_box.setEnabled(false);
                    }
                }.start();

            }

            minimzeAnimation(cv_box);
            counter++;
            tv_counter.setText(String.valueOf(counter));
        });

    }

    private void minimzeAnimation(View view) {
        view.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
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
}