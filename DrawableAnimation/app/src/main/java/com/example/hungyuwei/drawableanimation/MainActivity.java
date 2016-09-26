package com.example.hungyuwei.drawableanimation;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImgRolling;
    private TextView mTxtResult;
    private Button mButtonClick;
    private boolean btnStart = false;

    private Handler handler = new Handler(){

        public void handleMessage(Message message){
            super.handleMessage(message);

            int iRand = (int)(Math.random()*8+1);

            String s = getString(R.string.rolling_result);
            mTxtResult.setText(s+iRand);
            mImgRolling.setImageDrawable(null);
            switch(iRand) {
                case 1:
                    mImgRolling.setImageResource(R.drawable.img01);
                    break;
                case 2:
                    mImgRolling.setImageResource(R.drawable.img02);
                    break;
                case 3:
                    mImgRolling.setImageResource(R.drawable.img03);
                    break;
                case 4:
                    mImgRolling.setImageResource(R.drawable.img04);
                    break;
                case 5:
                    mImgRolling.setImageResource(R.drawable.img05);
                    break;
                case 6:
                    mImgRolling.setImageResource(R.drawable.img06);
                    break;
                case 7:
                    mImgRolling.setImageResource(R.drawable.img07);
                    break;
                case 8:
                    mImgRolling.setImageResource(R.drawable.img08);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgRolling = (ImageView)findViewById(R.id.imgRolling);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mButtonClick = (Button)findViewById(R.id.buttonClick);
        mButtonClick.setOnClickListener(btnRollimgClick);
    }

    private View.OnClickListener btnRollimgClick =
    new View.OnClickListener(){

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {
            mImgRolling.setImageDrawable(null);
            mImgRolling.setBackgroundResource(R.drawable.anim_img_roll);
            final AnimationDrawable frameAnimation = (AnimationDrawable) mImgRolling.getBackground();
            if(!btnStart){
                frameAnimation.start();
                btnStart = true;
            }else{
                frameAnimation.stop();
                btnStart = false;
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(5000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    frameAnimation.stop();
                    btnStart = false;
                    handler.sendMessage(handler.obtainMessage());
                }
            }).start();
        }
    };
}
