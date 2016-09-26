package com.example.hungyuwei.progressbarandmutithread;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        DoLenthyWork work = new DoLenthyWork();
        work.setHandler(mHandler);
        work.setProgressBar(progressBar);
        work.start();
    }
}
