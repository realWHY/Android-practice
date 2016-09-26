package com.example.hungyuwei.propertyanimation_2;

import android.animation.ObjectAnimator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mLinLayRoot;
    private TextView mTxtDemo;
    private Button mBtnRotate,mBtnTransparent, mBtnDrop;
    private float y, yEnd;
    private boolean mIsfirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinLayRoot = (LinearLayout)findViewById(R.id.linLayRoot);
        mTxtDemo = (TextView)findViewById(R.id.txtDemo);
        mBtnRotate = (Button)findViewById(R.id.btnRotate);
        mBtnTransparent = (Button)findViewById(R.id.btnTransparent);
        mBtnDrop = (Button)findViewById(R.id.btnDrop);
        y = mTxtDemo.getY();
        yEnd = mLinLayRoot.getHeight();
        Log.d("tag", "Value: " + y); //it will convert to string
        mBtnRotate.setOnClickListener(btnRotateOnclick);
        mBtnDrop.setOnClickListener(btnDropOnclick);
    }

    private View.OnClickListener btnRotateOnclick =
            new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    ObjectAnimator animTxtRotate =
                            ObjectAnimator.ofFloat(mTxtDemo,"rotation",0,720);
                    animTxtRotate.setRepeatCount(3);
                    animTxtRotate.setDuration(3000);
                    animTxtRotate.setRepeatMode(ObjectAnimator.REVERSE);
                    animTxtRotate.setInterpolator(new AccelerateDecelerateInterpolator());
                    animTxtRotate.start();
                }
            };

    private View.OnClickListener btnDropOnclick =
            new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(mIsfirst) {
                        y = mTxtDemo.getY();

                        yEnd = mLinLayRoot.getHeight() - mTxtDemo.getHeight()-100;
                        mIsfirst = false;
                    }
                    Log.d("tag", "mTxtDemo.getHeight() " + mTxtDemo.getHeight());
                    Log.d("tag", "mTxtDemo.getY(): " + mTxtDemo.getY());
                    Log.d("tag", "mLinLayRoot.getHeight() " + mLinLayRoot.getHeight());
                    Log.d("tag", "mLinLayRoot.getY() " + mLinLayRoot.getY());
                    ObjectAnimator animTxtFalling =
                            ObjectAnimator.ofFloat(mTxtDemo,"Y",y,yEnd);
                    animTxtFalling.setDuration(5000);
                    //animTxtFalling.setRepeatCount(1);
                    animTxtFalling.setInterpolator(new BounceInterpolator());
                    animTxtFalling.start();
                }
            };

}
