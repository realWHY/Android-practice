package com.example.hungyuwei.e200_chap6_6_sevicebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		try
		{
			Bundle bundle=intent.getExtras();//�إ�bundle
			String msg=bundle.getString("msg");//���o�T��
			Toast.makeText(
					context, 
					"This is message from service : "+msg,
					Toast.LENGTH_SHORT).show();			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
