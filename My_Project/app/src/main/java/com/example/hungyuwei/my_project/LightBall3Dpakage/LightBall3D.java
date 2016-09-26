package com.example.hungyuwei.my_project.LightBall3Dpakage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hungyu Wei on 2016/8/30.
 */
public class LightBall3D extends AppCompatActivity {
    private LightBall3DView mGLSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new LightBall3DView(this);
        setContentView(mGLSurfaceView);
        mGLSurfaceView.requestFocus();//���o�J�I
        mGLSurfaceView.setFocusableInTouchMode(true);//�]�w���iĲ��
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

