package com.example.hungyuwei.fragment_static;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/*
 */
public class FragmentMain extends Fragment {

    private TextView mTxtResult;
    private ImageView mTxtComplay;
    private ImageButton mBtnScissors,mBtnStone,mBtnPapper;
    public EditText mEdtCountSet,
            mEdtCountPlayerWin,
            mEdtCountComWin,
            mEdtCountDraw;
    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;

    private Button  mBtnShowResult1,
                    mBtnShowResult2,
                    mBtnHideinfo;

    private boolean mShowResult = false;
    private int mTagCount = 0;

    private final static String TAG_FRAGMENT_RESULT_1 = "FragResult1",
                                TAG_FRAGMENT_RESULT_2 = "FragResult2";

    private static final String TagFlow = "Result";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //fragmentMain
        mTxtComplay = (ImageView)getView().findViewById(R.id.txtComplay);
        mTxtResult = (TextView)getView().findViewById(R.id.txtResult);
        mBtnPapper=(ImageButton)getView().findViewById(R.id.btnPapper);
        mBtnScissors=(ImageButton)getView().findViewById(R.id.btnScissors);
        mBtnStone=(ImageButton)getView().findViewById(R.id.btnStone);
        mBtnShowResult1 =(Button)getView().findViewById(R.id.btnShowResult1);
        mBtnShowResult2 =(Button)getView().findViewById(R.id.btnShowResult2);
        mBtnHideinfo = (Button)getView().findViewById(R.id.btnHideinfo);

        //fragmentResult
        /*
        mEdtCountSet = (EditText)getActivity().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText)getActivity().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText)getActivity().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText)getActivity().findViewById(R.id.edtCountDraw);
        */

        //setClicikListener
        mBtnScissors.setOnClickListener(btnScissorOnclick);
        mBtnPapper.setOnClickListener(btnPapperOnclick);
        mBtnStone.setOnClickListener(btnStoneOnclick);
        mBtnShowResult1.setOnClickListener(btnShowResult1OnClick);
        mBtnShowResult2.setOnClickListener(btnShowResult2OnClick);
        mBtnHideinfo.setOnClickListener(btnHideResultOnclick);
    }


    private Button.OnClickListener btnScissorOnclick =
            new Button.OnClickListener(){
                //1: scissor 2.stone 3.papper
                @Override
                public void onClick(View view) {
                    int iComPlay = (int)(Math.random()*3+1);
                    Log.d("iComPlay","iComPlay = "+iComPlay);
                     miCountSet ++;

                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;

                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;

                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;

                    }

                    if(mShowResult){
                        mEdtCountSet.setText(String.valueOf(miCountSet));
                        mEdtCountDraw.setText(String.valueOf(miCountDraw));
                        mEdtCountComWin.setText(String.valueOf(miCountComWin));
                        mEdtCountPlayerWin.setText(String.valueOf(miCountPlayerWin));
                    }
                }
            };

    private Button.OnClickListener btnStoneOnclick =
            new Button.OnClickListener(){

                //1: scissor 2.stone 3.papper
                @Override
                public void onClick(View view) {
                    int iComPlay = (int)(Math.random()*3+1);
                    Log.d("iComPlay","iComPlay = "+iComPlay);
                    miCountSet ++;

                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;

                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;

                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;

                    }
                    if(mShowResult){
                        mEdtCountSet.setText(String.valueOf(miCountSet));
                        mEdtCountDraw.setText(String.valueOf(miCountDraw));
                        mEdtCountComWin.setText(String.valueOf(miCountComWin));
                        mEdtCountPlayerWin.setText(String.valueOf(miCountPlayerWin));
                    }
                }
            };

    private Button.OnClickListener btnPapperOnclick =
            new Button.OnClickListener(){

                //1: scissor 2.stone 3.papper
                @Override
                public void onClick(View view) {
                    int iComPlay = (int)(Math.random()*3+1);
                    Log.d("iComPlay","iComPlay = "+iComPlay);
                    miCountSet ++;
                    if(mShowResult)mEdtCountSet.setText(String.valueOf(miCountSet));
                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;

                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;

                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;

                    }
                    if(mShowResult){
                        mEdtCountSet.setText(String.valueOf(miCountSet));
                        mEdtCountDraw.setText(String.valueOf(miCountDraw));
                        mEdtCountComWin.setText(String.valueOf(miCountComWin));
                        mEdtCountPlayerWin.setText(String.valueOf(miCountPlayerWin));
                    }
                }
            };

            private View.OnClickListener btnShowResult1OnClick =
                    new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            mTagCount++;
                            String sFragTag = TagFlow + String.valueOf(mTagCount);
                            FragmentResult fragmentResult =
                                    new FragmentResult();
                            FragmentTransaction fragTran =
                                    getFragmentManager().beginTransaction();
                            fragTran.replace(R.id.frameLay,fragmentResult,sFragTag);
                            fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                            fragTran.addToBackStack(null);
                            fragTran.commit();
                            mShowResult = true;
                        }
                    };

    private View.OnClickListener btnShowResult2OnClick =
            new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    mTagCount++;
                    String sFragTag = TagFlow + String.valueOf(mTagCount);
                    FragmentResult_2 fragmentResult2 =
                            new FragmentResult_2();
                    FragmentTransaction fragTran =
                            getFragmentManager().beginTransaction();
                    fragTran.replace(R.id.frameLay,fragmentResult2,sFragTag);
                    fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragTran.addToBackStack(null);
                    fragTran.commit();
                    mShowResult = true;
                }
            };

    private View.OnClickListener btnHideResultOnclick =
            new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    mShowResult = false;
                    FragmentManager fragMgr = getFragmentManager();
                    String sFragTag = TagFlow + String.valueOf(mTagCount);
                    Fragment fragmentResult =
                            (Fragment)fragMgr.findFragmentByTag(sFragTag);
                    if(fragmentResult != null){
                        FragmentTransaction fragTrans =
                                fragMgr.beginTransaction();
                        fragTrans.remove(fragmentResult);
                        fragTrans.commit();
                        return;
                    }

                }
            };

}
