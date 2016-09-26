package com.example.hungyuwei.e200_chap3_18_tab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentTabHost tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //1
        tabHost.addTab(tabHost.newTabSpec("Apple")
                        .setIndicator("Apple",getResources().getDrawable(R.drawable.cloths)),
                AppleFragment.class,
                null);
        //2
        tabHost.addTab(tabHost.newTabSpec("Google")
                        .setIndicator("Google"),
                GoogleFragment.class,
                null);
        //3
        tabHost.addTab(tabHost.newTabSpec("Facebook")
                        .setIndicator("Facebook"),
                FacebookFragment.class,
                null);
        //4
        tabHost.addTab(tabHost.newTabSpec("Twitter")
                        .setIndicator("Twitter"),
                TwitterFragment.class,
                null);
    }

    /**************************
     *
     * 		���l���ҩI�s��
     *
     **************************/

    public String getAppleData(){
        return "Apple 123";
    }

    public String getGoogleData(){
        return "Google 456";
    }

    public String getFacebookData(){
        return "Facebook 789";
    }

    public String getTwitterData(){
        return "Twitter abc";
    }
}
