package com.example.hungyuwei.chap66_3d_draw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGLSurfaceView glSurfView = new MyGLSurfaceView(this);
        setContentView(glSurfView);
    }
}
