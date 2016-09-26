package com.example.hungyuwei.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtResult;
    private ImageView mTxtComplay;
    private ImageButton mBtnScissors,mBtnStone,mBtnPapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtComplay = (ImageView)findViewById(R.id.txtComplay);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mBtnPapper=(ImageButton)findViewById(R.id.btnPapper);
        mBtnScissors=(ImageButton)findViewById(R.id.btnScissors);
        mBtnStone=(ImageButton)findViewById(R.id.btnStone);

        mBtnScissors.setOnClickListener(btnScissorOnclick);
        mBtnStone.setOnClickListener(btnStoneOnclick);
        mBtnPapper.setOnClickListener(btnPapperOnclick);

    }

    private Button.OnClickListener btnScissorOnclick =
            new Button.OnClickListener(){

            //1: scissor 2.stone 3.papper
            @Override
            public void onClick(View view) {
                int iComPlay = (int)(Math.random()*3+1);
                if(iComPlay == 1){
                    mTxtComplay.setImageResource(R.drawable.scissor);
                    mTxtResult.setText(R.string.player_draw);
                }
                else if(iComPlay == 2){
                    mTxtComplay.setImageResource(R.drawable.stone);
                    mTxtResult.setText(R.string.player_lost);
                }
                else{
                    mTxtComplay.setImageResource(R.drawable.papper);
                    mTxtResult.setText(R.string.player_win);
                }
            }
    };

    private Button.OnClickListener btnStoneOnclick =
            new Button.OnClickListener(){

        //1: scissor 2.stone 3.papper
        @Override
        public void onClick(View view) {
            int iComPlay = (int)(Math.random()*3+1);
            if(iComPlay == 1){
                mTxtComplay.setImageResource(R.drawable.scissor);
                mTxtResult.setText(R.string.player_win);
            }
            else if(iComPlay == 2){
                mTxtComplay.setImageResource(R.drawable.stone);
                mTxtResult.setText(R.string.player_draw);
            }
            else{
                mTxtComplay.setImageResource(R.drawable.papper);
                mTxtResult.setText(R.string.player_lost);
            }
        }
    };

    private Button.OnClickListener btnPapperOnclick =
            new Button.OnClickListener(){

        //1: scissor 2.stone 3.papper
        @Override
        public void onClick(View view) {
            int iComPlay = (int)(Math.random()*3+1);
            if(iComPlay == 1){
                mTxtComplay.setImageResource(R.drawable.scissor);
                mTxtResult.setText(R.string.player_lost);
            }
            else if(iComPlay == 2){
                mTxtComplay.setImageResource(R.drawable.stone);
                mTxtResult.setText(R.string.player_win);
            }
            else{
                mTxtComplay.setImageResource(R.drawable.papper);
                mTxtResult.setText(R.string.player_draw);
            }
        }
    };

}
