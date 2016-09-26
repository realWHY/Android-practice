package com.example.hungyuwei.chap44_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mbtnStartMyService,
                    mbtnStopMyService,
                    mbtnBindMyService,
                    mbtnUnbindMyService,
                    mbtnCallMyServiceMethod;

    private MyService mMyServ = null;

    private final String LOG_TAG = "service demo";

    private ServiceConnection mServConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(LOG_TAG, "onServiceConnected()"+ componentName.getClassName());
            mMyServ = ((MyService.LocalBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(LOG_TAG, "onServiceDisconnected()"+ componentName.getClassName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnStartMyService = (Button) findViewById(R.id.btnStartMyService);
        mbtnStopMyService = (Button) findViewById(R.id.btnStopMyService);
        mbtnBindMyService = (Button) findViewById(R.id.btnBindMyService);
        mbtnUnbindMyService = (Button) findViewById(R.id.btnUnbindMyService);
        mbtnCallMyServiceMethod = (Button) findViewById(R.id.btnCallMyServiceMethod);

        mbtnStartMyService.setOnClickListener(btnStartMyServiceOnClick);
        mbtnStopMyService.setOnClickListener(btnStopMyServiceOnClick);
        mbtnBindMyService.setOnClickListener(btnBindMyServiceOnlick);
        mbtnUnbindMyService.setOnClickListener(btnUnBindMyServiceOnlick);
        mbtnCallMyServiceMethod.setOnClickListener(btnCallMyServiceMethodOnClick);
    }

    private View.OnClickListener btnStartMyServiceOnClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMyServ = null;
                    Intent it = new Intent(MainActivity.this, MyService.class);
                    startService(it);
                }
            };

    private View.OnClickListener btnStopMyServiceOnClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMyServ = null;
                    Intent it = new Intent(MainActivity.this, MyService.class);
                    stopService(it);
                }
            };

    private View.OnClickListener btnBindMyServiceOnlick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMyServ = null;
                    Intent it = new Intent(MainActivity.this, MyService.class);
                    bindService(it, mServConn, BIND_AUTO_CREATE);
                }
            };

    private View.OnClickListener btnUnBindMyServiceOnlick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMyServ = null;
                    unbindService(mServConn);
                }
            };

    private View.OnClickListener btnCallMyServiceMethodOnClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(LOG_TAG,"start to call service method");
                    Log.d(LOG_TAG,"mMyServ is "+mMyServ);
                    if(mMyServ != null){
                        mMyServ.myMethod();
                    }
                }
            };
}
