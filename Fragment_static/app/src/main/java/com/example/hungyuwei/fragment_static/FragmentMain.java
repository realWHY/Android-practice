package com.example.hungyuwei.fragment_static;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private TextView mEdtCountSet,
            mEdtCountPlayerWin,
            mEdtCountComWin,
            mEdtCountDraw;
    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;

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

        //fragmentResult
        mEdtCountSet = (EditText)getActivity().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText)getActivity().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText)getActivity().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText)getActivity().findViewById(R.id.edtCountDraw);

        mBtnScissors.setOnClickListener(btnScissorOnclick);
        mBtnPapper.setOnClickListener(btnPapperOnclick);
        mBtnStone.setOnClickListener(btnStoneOnclick);
    }


    private Button.OnClickListener btnScissorOnclick =
            new Button.OnClickListener(){
                //1: scissor 2.stone 3.papper
                @Override
                public void onClick(View view) {
                    int iComPlay = (int)(Math.random()*3+1);
                    Log.d("iComPlay","iComPlay = "+iComPlay);
                     miCountSet ++;
                     mEdtCountSet.setText(String.valueOf(miCountSet));

                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;
                        mEdtCountDraw.setText(String.valueOf(miCountDraw));
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;
                        mEdtCountComWin.setText(String.valueOf(miCountComWin));
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;
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
                    mEdtCountSet.setText(String.valueOf(miCountSet));
                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;
                        mEdtCountPlayerWin.setText(String.valueOf(miCountPlayerWin));
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;
                       mEdtCountDraw.setText(String.valueOf(miCountDraw));
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;
                        mEdtCountComWin.setText(String.valueOf(miCountComWin));
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
                    mEdtCountSet.setText(String.valueOf(miCountSet));
                    if(iComPlay == 1){
                        mTxtComplay.setImageResource(R.drawable.scissor);
                        mTxtResult.setText(R.string.player_lost);
                        miCountComWin++;
                        mEdtCountComWin.setText(String.valueOf(miCountComWin));
                    }
                    else if(iComPlay == 2){
                        mTxtComplay.setImageResource(R.drawable.stone);
                        mTxtResult.setText(R.string.player_win);
                        miCountPlayerWin++;
                        mEdtCountPlayerWin.setText(String.valueOf(miCountPlayerWin));
                    }
                    else{
                        mTxtComplay.setImageResource(R.drawable.papper);
                        mTxtResult.setText(R.string.player_draw);
                        miCountDraw++;
                        mEdtCountDraw.setText(String.valueOf(miCountDraw));
                    }
                }
            };
}
