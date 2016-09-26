package com.example.hungyuwei.my_project.Sphere3Dpakage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hungyu Wei on 2016/8/30.
 */
public class Sphere3D extends AppCompatActivity {
    private Sphere3DView mGLSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLSurfaceView = new Sphere3DView(this);
        setContentView(mGLSurfaceView);
        mGLSurfaceView.requestFocus();
        mGLSurfaceView.setFocusableInTouchMode(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}

