package com.example.hungyuwei.fragment_static;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

//import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentMain.CallbackInterface{

    private FragmentMain fragMain;
    private FragmentResult fragGameResult;

    private boolean bUISettedOK = false;

    enum UIType {
        ONE_FRAME, TWO_FRAMES;
    }

    public UIType UITypeFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch (getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK)
        {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                UITypeFlag = UIType.ONE_FRAME;
                break;
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                UITypeFlag = UIType.TWO_FRAMES;
                break;
        }

        fragMain = new FragmentMain();
        fragGameResult = new FragmentResult();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        android.support.v4.app.FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.replace(R.id.frameLay1, fragMain, "Game");
        fragTran.replace(R.id.frameLay2, fragGameResult, "Game Result");
        fragTran.commit();

        if (bUISettedOK == false) {
            bUISettedOK = true;

            switch (UITypeFlag) {
                case ONE_FRAME:
                    findViewById(R.id.frameLay1).setVisibility(View.VISIBLE);
                    findViewById(R.id.frameLay2).setVisibility(View.GONE);
                    break;
                case TWO_FRAMES:
                    findViewById(R.id.frameLay1).setVisibility(View.VISIBLE);
                    findViewById(R.id.frameLay2).setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
    @Override
    public void updateGameResult(int iCountSet, int iCountPlayerWin, int iCountComWin, int iCountDraw) {
        if (findViewById(R.id.frameLay2).isShown()) {
            fragGameResult.updateGameResult(iCountSet, iCountPlayerWin,
                    iCountComWin, iCountDraw);
        }
    }

}
