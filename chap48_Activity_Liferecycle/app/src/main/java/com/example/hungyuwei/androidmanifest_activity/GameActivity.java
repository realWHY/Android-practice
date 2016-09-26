package com.example.hungyuwei.androidmanifest_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Hungyu Wei on 2016/7/15.
 */
public class GameActivity extends AppCompatActivity {
    private TextView mTxtResult;
    private ImageView mTxtComplay;
    private ImageButton mBtnScissors,mBtnStone,mBtnPapper;
    private Button mBtnOk, mBtnCancel;
    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;
    final String LOG_TAG = "logState";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mTxtComplay = (ImageView)findViewById(R.id.txtComplay);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mBtnPapper=(ImageButton)findViewById(R.id.btnPapper);
        mBtnScissors=(ImageButton)findViewById(R.id.btnScissors);
        mBtnStone=(ImageButton)findViewById(R.id.btnStone);

        mBtnScissors.setOnClickListener(btnScissorOnclick);
        mBtnStone.setOnClickListener(btnStoneOnclick);
        mBtnPapper.setOnClickListener(btnPapperOnclick);

        mBtnOk = (Button)findViewById(R.id.btnOk);
        mBtnCancel = (Button)findViewById(R.id.btnCancel);

        mBtnOk.setOnClickListener(btnOKClick);
        mBtnCancel.setOnClickListener(btnCancelClick);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"Game.onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,"Game.onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"Game.onPause()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG,"Game.onRestart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG,"Game.onStop()");
    }
    private Button.OnClickListener btnScissorOnclick =
            new Button.OnClickListener(){
                //1: scissor 2.stone 3.papper
                @Override
                public void onClick(View view) {
                    int iComPlay = (int)(Math.random()*3+1);
                    Log.d("iComPlay","iComPlay = "+iComPlay);
                    miCountSet ++;

                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;
                    }
                }
            };

    private Button.OnClickListener btnStoneOnclick =
            new Button.OnClickListener(){

                //1: scissor 2.stone 3.papper
                @Override
                public void onClick(View view) {
                    int iComPlay = (int)(Math.random()*3+1);
                    Log.d("iComPlay","iComPlay = "+iComPlay);
                    miCountSet ++;
                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;
                    }
                }
            };

    private Button.OnClickListener btnPapperOnclick =
            new Button.OnClickListener(){

                //1: scissor 2.stone 3.papper
                @Override
                public void onClick(View view) {
                    int iComPlay = (int)(Math.random()*3+1);
                    Log.d("iComPlay","iComPlay = "+iComPlay);
                    miCountSet ++;
                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;
                    }
                }
            };

    private View.OnClickListener btnOKClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent();
                    Bundle bundle= new Bundle();
                    bundle.putInt("KEY_COUNT_SET",miCountSet);
                    bundle.putInt("KEY_COUNT_PLAYER_WIN",miCountPlayerWin);
                    bundle.putInt("KEY_COUNT_COM_WIN",miCountComWin);
                    bundle.putInt("KEY_COUNT_DRAW",miCountDraw);
                    it.putExtras(bundle);
                    setResult(RESULT_OK,it);
                    finish();
                }
            };

    private View.OnClickListener btnCancelClick =
            new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    setResult(RESULT_CANCELED);
                    finish();
                }
            };
}
