package com.example.hungyuwei.e200_chap11_3_3dsphere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MySurfaceView mGLSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLSurfaceView = new MySurfaceView(this);
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
