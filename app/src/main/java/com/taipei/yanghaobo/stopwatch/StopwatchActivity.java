package com.taipei.yanghaobo.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    private int mSeconds;
    private boolean mRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if (savedInstanceState != null){

            mSeconds = savedInstanceState.getInt("seconds");
            mRunning = savedInstanceState.getBoolean("running");
        }

        rumTime();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", mSeconds);
        outState.putBoolean("running", mRunning);
    }

    public void onClickStart(View view){

        mRunning = true;
    }

    public void onClickStop(View view){

        mRunning = false;
    }

    public void onClickReset(View view){

        mRunning = false;
        mSeconds = 0;
    }

    private void rumTime(){

        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = mSeconds / 3600;
                int minutes = (mSeconds % 3600) / 60;
                int secs = mSeconds % 60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);
                if (mRunning){
                    mSeconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });

    }
}
