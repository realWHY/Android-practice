package com.example.hungyuwei.androidmanifest_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLaunchGame;
    final private  int LAUNCH_GAME = 0;
    private TextView mTxtResult;
    final String LOG_TAG = "logState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnLaunchGame = (Button)findViewById(R.id.btnLaunchGame);
        mBtnLaunchGame.setOnClickListener(btnLaunchGame);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"Main.onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,"Main.onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"Main.onPause()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG,"Main.onRestart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG,"Main.onStop()");
    }

    private View.OnClickListener btnLaunchGame =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent  it = new Intent();
                    it.setClass(MainActivity.this,GameActivity.class);
                    startActivityForResult(it, LAUNCH_GAME);
                }
            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != LAUNCH_GAME){
            return;
        }

        switch(resultCode){
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                Log.d("hi", "here is switch");
                int iCountSet = bundle.getInt("KEY_COUNT_SET");
                int iCOuntPlayerWin = bundle.getInt("KEY_COUNT_PLAYER_WIN");
                int iCOuntComWin = bundle.getInt("KEY_COUNT_COM_WIN");
                int iCountDraw = bundle.getInt("KEY_COUNT_DRAW");
                //Log.d("hi","ts"+iCountSet+"pl"+iCOuntPlayerWin);
                String s = "you have played "+iCountSet+" total set"
                        +"\nand \nwin"+iCOuntPlayerWin+" set"+"\nbut\nlose "+
                        iCOuntComWin+" set"+"\nand"+"\nDraw "+iCountDraw+" set";
                mTxtResult.setText(s);
                //Log.d("hi","after set teext");
                break;
            case RESULT_CANCELED:
                mTxtResult.setText("you choose cancel");
                break;
        }
    }
}
