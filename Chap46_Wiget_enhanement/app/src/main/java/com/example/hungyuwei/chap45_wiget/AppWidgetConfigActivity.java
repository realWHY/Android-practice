package com.example.hungyuwei.chap45_wiget;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;

/**
 * Created by Hungyu Wei on 2016/7/29.
 */
public class AppWidgetConfigActivity extends Activity {
    int mAppWidgetId;
    private final String LOG_TAG = "may app widget";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent itIn = getIntent();
        Bundle extras = itIn.getExtras();
        if(extras != null){
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                                        AppWidgetManager.INVALID_APPWIDGET_ID);
            Log.d(LOG_TAG, "mAppWidgetId = "+mAppWidgetId);
            Log.d(LOG_TAG, "AppWidgetManager.EXTRA_APPWIDGET_ID = "+AppWidgetManager.EXTRA_APPWIDGET_ID);
            Log.d(LOG_TAG, "AppWidgetManager.INVALID_APPWIDGET_ID = "+AppWidgetManager.INVALID_APPWIDGET_ID);
            if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID)
            {
                finish();
            }
            Intent itOut = new Intent("com.android.MY_OWN_WIDGET_UPDATE");
            PendingIntent penIt = PendingIntent.getBroadcast(this,0,itOut,0);
            AlarmManager alarMan = (AlarmManager)getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 5);
            alarMan.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                    20*1000,penIt);
            MyAppWidget.SaveAlarmManager(alarMan, penIt);

            RemoteViews viewAppWidget = new RemoteViews(getPackageName(),
                    R.layout.app_widget);
            viewAppWidget.setImageViewResource(R.id.imgViewAppWidget,
                    R.drawable.app_widget_icon);
            AppWidgetManager appWidgetMan = AppWidgetManager.getInstance(this);
            appWidgetMan.updateAppWidget(mAppWidgetId,viewAppWidget);

            Intent itAPPWidgetConfigResult = new Intent();
            itAPPWidgetConfigResult.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    mAppWidgetId);
            setResult(RESULT_OK,itAPPWidgetConfigResult);

            finish();
        }
    }
}
