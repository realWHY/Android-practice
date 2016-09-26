package com.example.hungyuwei.propertyanimation_2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mLinLayRoot;
    private TextView mTxtDemo;
    private Button mBtnRotate,mBtnTransparent, mBtnDrop,
            mBtnScale, mBtnShift, mBtnChangeColor;
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
        mBtnScale=(Button)findViewById(R.id.btnScale);
        mBtnShift=(Button)findViewById(R.id.btnShift);
        y = mTxtDemo.getY();
        yEnd = mLinLayRoot.getHeight();
        mBtnRotate.setOnClickListener(btnRotateOnclick);
        mBtnDrop.setOnClickListener(btnDropOnclick);
        mBtnScale.setOnClickListener(btnScaleOnClick);
        mBtnShift.setOnClickListener(btnShiftOnclick);
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

    private View.OnClickListener btnScaleOnClick =
            new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    ValueAnimator animTxtScale = ValueAnimator.ofInt(0,35);
                    animTxtScale.setDuration(2000);
                    animTxtScale.setRepeatCount(1);
                    animTxtScale.setRepeatMode(ObjectAnimator.REVERSE);
                    animTxtScale.setInterpolator(new LinearInterpolator());
                    animTxtScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer)valueAnimator.getAnimatedValue();
                            Log.d("Tag","val="+val);
                            mTxtDemo.setTextSize(TypedValue.COMPLEX_UNIT_SP,val+125);
                        }
                    });
                    animTxtScale.start();
                }
            };

    private View.OnClickListener btnShiftOnclick=
            new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    float x, xEnd1, xEnd2;
                    x = mTxtDemo.getX();
                    xEnd1 = 0;
                    xEnd2 = mLinLayRoot.getWidth()-mTxtDemo.getWidth();
                    Log.d("shift","x="+x);
                    Log.d("shift","layRootwidth"+mLinLayRoot.getWidth());
                    Log.d("shift","mTXT width"+mTxtDemo.getWidth());

                    ObjectAnimator aniTxtMove1 =
                            ObjectAnimator.ofFloat(mTxtDemo,"x",x,xEnd1);
                    aniTxtMove1.setDuration(2000);
                    aniTxtMove1.setInterpolator(new LinearInterpolator());

                    ObjectAnimator aniTxtMove2 =
                            ObjectAnimator.ofFloat(mTxtDemo,"x",xEnd1,xEnd2);
                    aniTxtMove2.setDuration(5000);
                    aniTxtMove2.setInterpolator(new AccelerateDecelerateInterpolator());

                    ObjectAnimator aniTxtMove3 =
                            ObjectAnimator.ofFloat(mTxtDemo,"x",xEnd2,x);
                    aniTxtMove3.setDuration(3000);
                    aniTxtMove3.setInterpolator(new AccelerateInterpolator());

                    AnimatorSet aniTxtMove = new AnimatorSet();
                    aniTxtMove.playSequentially(aniTxtMove1,aniTxtMove2,aniTxtMove3);
                    aniTxtMove.start();

                }
            };


}
