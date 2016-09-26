package com.example.hungyuwei.chap43_intent_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Hungyu Wei on 2016/7/25.
 */
public class MyBroadcastReceiver1 extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String sender = intent.getStringExtra("sender_name");
        Toast.makeText(context, "BroadcastReceiver1 recieve" + sender + "information ",
                Toast.LENGTH_LONG).show();
    }
}
