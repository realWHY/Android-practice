package com.example.hungyuwei.e200_chap11_6_3dwoodboxfog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MySurfaceView mGLSurfaceView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new MySurfaceView(this);
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
