package com.example.hungyuwei.my_project.WoodBox3Dpakage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hungyu Wei on 2016/8/30.
 */
public class WoodBox3D extends AppCompatActivity {
    private WoodBox3DView mGLSurfaceView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new WoodBox3DView(this);
        mGLSurfaceView.requestFocus();//���o�J�I
        mGLSurfaceView.setFocusableInTouchMode(true);//�]�w���iĲ��
        setContentView(mGLSurfaceView);
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

