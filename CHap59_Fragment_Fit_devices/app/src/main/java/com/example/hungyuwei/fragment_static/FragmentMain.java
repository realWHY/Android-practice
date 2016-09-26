package com.example.hungyuwei.fragment_static;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/*
 */
public class FragmentMain extends Fragment {

    public interface CallbackInterface {
        public void updateGameResult(int iCountSet,
                                     int iCountPlayerWin,
                                     int iCountComWin,
                                     int iCountDraw);
    };

    enum GameResultType{
        TYPE_1, TYPE_2, TRUN_OFF
    }

    private CallbackInterface mCallback;

    private TextView mTxtResult;
    private ImageView mTxtComplay;
    private ImageButton mBtnScissors,mBtnStone,mBtnPapper;
    private Button mBtnShowResult;

    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try{
            mCallback = (CallbackInterface)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+
            "must implement GameFragment.CallbackInteface");
        }
    }

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
        mBtnShowResult = (Button)getView().findViewById(R.id.btnShowResult);

        if (((MainActivity) getActivity()).UITypeFlag == MainActivity.UIType.TWO_FRAMES) {
            mBtnShowResult.setVisibility(View.GONE);
        } else {
            mBtnShowResult.setVisibility(View.VISIBLE);
        }

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
        mBtnShowResult.setOnClickListener(btnShowResultOnClick);
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

                    mCallback.updateGameResult(miCountSet,miCountPlayerWin,miCountComWin,
                            miCountDraw);
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
                    mCallback.updateGameResult(miCountSet,miCountPlayerWin,miCountComWin,
                            miCountDraw);
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
                    mCallback.updateGameResult(miCountSet,miCountPlayerWin,miCountComWin,
                            miCountDraw);
                }
            };

    private View.OnClickListener btnShowResultOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            getActivity().findViewById(R.id.frameLay1).setVisibility(View.GONE);
            getActivity().findViewById(R.id.frameLay2).setVisibility(View.VISIBLE);

            mCallback.updateGameResult(miCountSet, miCountPlayerWin,
                    miCountComWin, miCountDraw);
        }
    };
}
