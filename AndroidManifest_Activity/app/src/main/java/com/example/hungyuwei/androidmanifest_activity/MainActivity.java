package com.example.hungyuwei.androidmanifest_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLaunchGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnLaunchGame = (Button)findViewById(R.id.btnLaunchGame);
        mBtnLaunchGame.setOnClickListener(btnLaunchGame);
    }
    private View.OnClickListener btnLaunchGame =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent  it = new Intent();
                    it.setClass(MainActivity.this,GameActivity.class);
                    startActivity(it);
                }
            };
}
