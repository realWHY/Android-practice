package com.example.hungyuwei.chap43_intent_broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnRegReceiver,
            mBtnUnregReceiver,
            mBtnSendBroadcast1,
            mBtnSendBroadcast2;

    private MyBroadcastReceiver2 mMyReceiver2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnRegReceiver = (Button)findViewById(R.id.btnRegReceiver);
        mBtnUnregReceiver = (Button)findViewById(R.id.btnUnregReceiver);
        mBtnSendBroadcast1 = (Button)findViewById(R.id.btnSendBroadcast1);
        mBtnSendBroadcast2 = (Button)findViewById(R.id.btnSendBroadcast2);

        mBtnRegReceiver.setOnClickListener(btnRegReceiverOnClick);
        mBtnUnregReceiver.setOnClickListener(btnUnregReceiverOnClick);
        mBtnSendBroadcast1.setOnClickListener(btnSendBroasdcast1OnCLick);
        mBtnSendBroadcast2.setOnClickListener(btnSendBroasdcast2OnCLick);
    }

    private View.OnClickListener btnRegReceiverOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            IntentFilter itFilter = new IntentFilter("com.android.MY_BROADCAST2");
            mMyReceiver2 = new MyBroadcastReceiver2();
            registerReceiver(mMyReceiver2, itFilter);
        }
    };

    private View.OnClickListener btnUnregReceiverOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            unregisterReceiver(mMyReceiver2);
        }
    };

    private View.OnClickListener btnSendBroasdcast1OnCLick
            = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent("com.android.MyBroadcast1");
            it.putExtra("sender_name","main_code");
            sendBroadcast(it);
        }
    };

    private View.OnClickListener btnSendBroasdcast2OnCLick
            = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent("com.android.MY_BROADCAST2");
            it.putExtra("sender_name","main_code");
            sendBroadcast(it);
        }
    };
}
