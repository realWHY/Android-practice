package com.example.hungyuwei.chpa20_intent_info;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTxtResult;
    private ImageView mTxtComplay;
    private ImageButton mBtnScissors,mBtnStone,mBtnPapper;
    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;
    private Button mbtnShowResult;
    private static final int NOTI_ID = 100;

    private Button mbtnSaveResult,
            mbtnClearResult,
            mbtnLoadResult;
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

        mbtnShowResult = (Button)findViewById(R.id.btnShowResult);
        mbtnShowResult.setOnClickListener(btnShowResultOnclick);

        mbtnLoadResult = (Button)findViewById(R.id.btnLoadResult);
        mbtnSaveResult = (Button)findViewById(R.id.btnSaveResult);
        mbtnClearResult = (Button)findViewById(R.id.btnClearResult);

        mbtnSaveResult.setOnClickListener(mbtnSaveResultOnclick);
        mbtnLoadResult.setOnClickListener(mbtnLoadResultOnclick);
        mbtnClearResult.setOnClickListener(mbtnClearResultOnclick);
    }

    @Override
    protected void onDestroy() {
        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE))
                .cancel(NOTI_ID);
        super.onDestroy();
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
                        showNotification("draw " + Integer.toString(miCountDraw) + " set");
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;
                        showNotification("lost " + Integer.toString(miCountComWin) + " set");
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;
                        showNotification("win " + Integer.toString(miCountPlayerWin) + " set");
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
                        showNotification("win " + Integer.toString(miCountPlayerWin) + " set");
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;
                        showNotification("draw " + Integer.toString(miCountDraw) + " set");
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;
                        showNotification("lost " + Integer.toString(miCountComWin) + " set");
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
                        showNotification("lost " + Integer.toString(miCountComWin) + " set");
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;
                        showNotification("win " + Integer.toString(miCountPlayerWin) + " set");
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;
                        showNotification("draw " + Integer.toString(miCountDraw) + " set");
                    }
                }
            };

    private View.OnClickListener btnShowResultOnclick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent();
                    it.setClass(MainActivity.this, GameResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY_COUNT_SET",miCountSet);
                    bundle.putInt("KEY_COUNT_PLAYER_WIN",miCountPlayerWin);
                    bundle.putInt("KEY_COUNT_COM_WIN",miCountComWin);
                    bundle.putInt("KEY_COUNT_DRAW",miCountDraw);
                    it.putExtras(bundle);
                    startActivity(it);
                }
            };

    private void showNotification(String sMsg) {
        Intent it = new Intent(getApplicationContext(), GameResult.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_COUNT_SET", miCountSet);
        bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
        bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
        bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
        it.putExtras(bundle);

        PendingIntent penIt = PendingIntent.getActivity(getApplicationContext(),
                0, it, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification noti = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.btn_star_big_on)
                .setTicker(sMsg)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(sMsg)
                .setContentIntent(penIt)
                .build();

        NotificationManager notiMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//	    notiMgr.cancel(NOTI_ID);
        notiMgr.notify(NOTI_ID, noti);
    }

    private View.OnClickListener mbtnSaveResultOnclick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences gameResultData=
                            getSharedPreferences("Game_Result",0);

                    gameResultData.edit()
                            .putInt("COUNT_SET",miCountSet)
                            .putInt("COUNT_PLAYERWIN",miCountPlayerWin)
                            .putInt("COUNT_COM_WIN",miCountComWin)
                            .putInt("COUNT_DRAW",miCountDraw)
                            .commit();
                    Toast.makeText(MainActivity.this,"Save finish",
                            Toast.LENGTH_LONG)
                            .show();
                }
            };

    private View.OnClickListener mbtnLoadResultOnclick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences gameResultData=
                            getSharedPreferences("Game_Result",0);

                    miCountSet = gameResultData.getInt("COUNT_SET",0);
                    miCountPlayerWin = gameResultData.getInt("COUNT_PLAYERWIN",0);
                    miCountComWin = gameResultData.getInt("COUNT_COM_WIN",0);
                    miCountDraw = gameResultData.getInt("COUNT_DRAW",0);
                    Toast.makeText(MainActivity.this,"Load finish",
                            Toast.LENGTH_LONG)
                            .show();
                }
            };

    private View.OnClickListener mbtnClearResultOnclick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences gameResultData=
                            getSharedPreferences("Game_Result",0);

                    gameResultData.edit()
                          .clear()
                            .commit();
                    Toast.makeText(MainActivity.this,"Clear finish",
                            Toast.LENGTH_LONG)
                            .show();
                }
            };
}
