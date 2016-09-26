package com.example.hungyuwei.progressbarandmutithread;

import android.os.Handler;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ProgressBar;

import java.util.Calendar;


/**
 * Created by Hungyu Wei on 2016/7/14.
 */
public class DoLenthyWork extends Thread {
    private Handler mHandler;
    private ProgressBar mProgressBar;

    void setProgressBar(ProgressBar proBar){
        mProgressBar = proBar;
    }

    void setHandler(Handler h){
        mHandler = h;
    }
    @Override
    public void run() {
        Calendar begin = Calendar.getInstance();
        while (true){
            Calendar now = Calendar.getInstance();
            Log.d("time","beginMinute"+begin.get(Calendar.MINUTE));
            Log.d("time","nowMinute"+now.get(Calendar.MINUTE));
            Log.d("time","beginSecond"+begin.get(Calendar.SECOND));
            Log.d("time","nowSecond"+now.get(Calendar.SECOND));
            final int iDiffSec = 60*(now.get(Calendar.MINUTE) - begin.get(Calendar.MINUTE))
                    +now.get(Calendar.SECOND)-begin.get(Calendar.SECOND);

            // main progress
            if(iDiffSec*2<100) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(iDiffSec * 2);
                    }
                });
            }else{
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(100);
                    }
                });
                break;
            }

            //secondprogress
            if(iDiffSec*4<100) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setSecondaryProgress(iDiffSec * 4);
                    }
                });
            }else{
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setSecondaryProgress(100);
                    }
                });
            }
        }
    }
}
