package com.example.hungyuwei.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mHelloText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloText =(TextView)findViewById(R.id.helloText);
       AnimatorSet animRotate =
                (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.rotate_anim);
        animRotate.setTarget(mHelloText);
        animRotate.start();
    }
}
