package com.example.hungyuwei.e200_chap6_6_sevicebroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bStart;//�Ұʫ��s
    Button bStop;//������s
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStart=(Button)this.findViewById(R.id.Button01);
        bStop=(Button)this.findViewById(R.id.Button02);

        bStart.setOnClickListener//�Ұʫ��s��ť��
                (
                        new OnClickListener()
                        {
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                Intent intent=new Intent(MainActivity.this,MyReceiver6_6.class);//�إߪ���
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startService(intent);//�Ұ�Service�A��
                                Toast.makeText(
                                        MainActivity.this,
                                        "Start Service successfully",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                );
        bStop.setOnClickListener//������s��ť��
                (
                        new OnClickListener()
                        {
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                Intent intent=new Intent(MainActivity.this,MyReceiver6_6.class);//�إߪ���
                                if(stopService(intent)==true)
                                {
                                    Toast.makeText(
                                            MainActivity.this,
                                            "Stop Service successfully",
                                            Toast.LENGTH_SHORT).show();
                                }else
                                {
                                    Toast.makeText(
                                            MainActivity.this,
                                            "Stop Service failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                );
    }
    @Override
    public void onResume()
    {
        super.onResume();
        try
        {
            Log.d("Tag","MyReceiver6_6.MSG = "+MyReceiver6_6.MSG);
            IntentFilter intentFilter=new IntentFilter(MyReceiver6_6.MSG);//�ۭq�L�o�T��
            MyBroadcastReceiver mReceiver=new MyBroadcastReceiver();//�إ�MyBroadcastReceiver����
            registerReceiver(mReceiver,intentFilter);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public void onPause()
    {
        super.onPause();
        MyBroadcastReceiver mReceiver=new MyBroadcastReceiver();//�إ�MyBroadcastReceiver����
        unregisterReceiver(mReceiver);
    }
}
