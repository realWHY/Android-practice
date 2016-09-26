package com.example.hungyuwei.e200_chap11_8_3dphotograph;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private MySurfaceView mGLSurfaceView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //�]�w�����ù�
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //�]�w�����
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        mGLSurfaceView = new MySurfaceView(this);

        mGLSurfaceView.screenHeight=dm.heightPixels;
        mGLSurfaceView.screenWidth=dm.widthPixels;
        mGLSurfaceView.ratio=mGLSurfaceView.screenWidth/mGLSurfaceView.screenHeight;

        mGLSurfaceView.requestFocus();//���o�J�I
        mGLSurfaceView.setFocusableInTouchMode(true);//�]�w���iĲ��
        setContentView(mGLSurfaceView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Constant.threadWork=true;
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Constant.threadWork=false;
        mGLSurfaceView.onPause();
    }
}
