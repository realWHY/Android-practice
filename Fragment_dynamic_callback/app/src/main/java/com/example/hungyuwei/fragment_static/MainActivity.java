package com.example.hungyuwei.fragment_static;

import android.icu.text.DisplayContext;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
//import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentMain.CallbackInterface{

    private final static String Tag = "Result";
    private int mTagCount = 0;
    public FragmentMain.GameResultType mGameResultType;
    public Fragment fragResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void updateGameResult(int iCountSet, int iCountPlayerWin, int iCountComWin, int iCountDraw) {
        if(findViewById(R.id.frameLay).isShown()){
            switch (mGameResultType){
                case TYPE_1:
                    ((FragmentResult) fragResult).updateGameResult1(iCountSet, iCountPlayerWin, iCountComWin, iCountDraw);
                    break;

                case  TYPE_2:
                    ((FragmentResult_2) fragResult).updateGameResult2(iCountSet, iCountPlayerWin, iCountComWin, iCountDraw);
                    break;
            }
        }
    }

    @Override
    public void enableGameResult(FragmentMain.GameResultType type) {
        android.support.v4.app.FragmentTransaction fragTran;
        String sFragTag;

        switch(type){
            case TYPE_1:
                Log.d("Change","Type_1");
                FragmentResult frag = new FragmentResult();
                fragTran = getSupportFragmentManager().beginTransaction();
                mTagCount++;
                sFragTag = Tag + new Integer(mTagCount).toString();
                fragTran.replace(R.id.frameLay, frag, sFragTag);
                fragTran.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();
                break;

            case TYPE_2:
                Log.d("Change","Type_2");
                FragmentResult_2 frag2 = new FragmentResult_2();
                fragTran = getSupportFragmentManager().beginTransaction();
                mTagCount++;
                sFragTag = Tag + new Integer(mTagCount).toString();
                fragTran.replace(R.id.frameLay, frag2, sFragTag);
                fragTran.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();
                break;

            case TRUN_OFF:
                Log.d("Change","Hide");
                FragmentManager frgMgr = getSupportFragmentManager();
                sFragTag = Tag + new Integer(mTagCount).toString();
                Fragment fragNow = (Fragment)frgMgr.findFragmentByTag(sFragTag);
                fragTran = frgMgr.beginTransaction();
                fragTran.remove(fragNow);
                fragTran.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();
                break;
        }

    }
}
