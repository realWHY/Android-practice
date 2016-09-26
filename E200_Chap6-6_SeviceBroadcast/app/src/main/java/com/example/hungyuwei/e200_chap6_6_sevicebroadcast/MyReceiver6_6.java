package com.example.hungyuwei.e200_chap6_6_sevicebroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyReceiver6_6 extends Service{

	int count=0;//�p�ƼЧӦ�
	static final String MSG="message";//�T���r��
	boolean flag=true;//���������ЧӦ�
	@Override
	public void onStart(Intent intent,int startId)
	{
		new Thread()
		{
			public void run()
			{
				while(flag)
				{
					count++;
					if(count==10)
					{
						Intent i=new Intent(MSG);//�إ�Intent����
						i.putExtra("msg",MSG);//�ʸ˰Ѽ�
						sendBroadcast(i);//�ǰe�s��
						count=0;//�p�ƾ��]��0
					}
					try
					{
						Thread.sleep(1000);//������ίv1���� 
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
		super.onStart(intent, startId);
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
	}
	@Override
	public void onDestroy()
	{
		flag=false;//�N�ЧӦ�]��FALSE
		super.onDestroy();
	}

}
