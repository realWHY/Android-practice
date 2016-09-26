package com.example.hungyuwei.e200_chap5_9_vibration;

import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button myButton;//�ŧiButton
    EditText etUser;//�ϥΪ̦W��
    EditText etPwd;//�K�X
    String user;
    String pwd;
    private Vibrator vb;
    Handler hd=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 0:
                    Toast.makeText(
                            MainActivity.this,
                            "Cancel Vibration",
                            Toast.LENGTH_SHORT
                    ).show();
                    break;
            }
        }


    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vb=(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        myButton=(Button)this.findViewById(R.id.Button01);
        etUser=(EditText)this.findViewById(R.id.EditText01);
        etPwd=(EditText)this.findViewById(R.id.EditText02);
        myButton.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v) {
                                user=etUser.getText().toString().trim();//���o�ϥΪ̦W��
                                pwd=etPwd.getText().toString().trim();//���o�K�X

                                Log.d("user",user+"11111111111111111");
                                Log.d("pwd",pwd+"2222222222222222222222222222");
                                if(user.equals("123")&&pwd.equals("123"))
                                {
                                    gotoChange();//�i�J�t�@�Ӭɭ�
                                    //vb.vibrate(new long[]{100,10,100,10000}, -1);
                                    Toast.makeText(
                                            MainActivity.this,
                                            "Vibation 1111111111111",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }else
                                {
                                    Toast.makeText(
                                            MainActivity.this,
                                            "Error !!!!!",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }

                        }
                );

    }
    public void gotoChange()
    {
        setContentView(R.layout.change);
        new Thread()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                //vb.cancel();//�����_��
                hd.sendEmptyMessage(0);
            }
        }.start();


    }
}
