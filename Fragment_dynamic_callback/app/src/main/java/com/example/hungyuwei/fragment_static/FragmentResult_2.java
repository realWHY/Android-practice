package com.example.hungyuwei.fragment_static;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class FragmentResult_2 extends Fragment {

    private EditText mEdtCountSet,
            mEdtCountPlayerWin,
            mEdtCountComWin,
            mEdtCountDraw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_result_2, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mEdtCountSet = (EditText)getActivity().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText)getActivity().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText)getActivity().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText)getActivity().findViewById(R.id.edtCountDraw);
        ((MainActivity)getActivity()).mGameResultType = FragmentMain.GameResultType.TYPE_2;
        ((MainActivity)getActivity()).fragResult = this;
        getActivity().findViewById(R.id.frameLay).setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().findViewById(R.id.frameLay).setVisibility(View.GONE);
    }


    public void updateGameResult2(int iCountSet,
                                  int iCountPlayerWin,
                                  int iCountComWin,
                                  int iCountDraw) {
        mEdtCountSet.setText(String.valueOf(iCountSet));
        mEdtCountPlayerWin.setText(String.valueOf(iCountPlayerWin));
        mEdtCountComWin.setText(String.valueOf(iCountComWin));
        mEdtCountDraw.setText(String.valueOf(iCountDraw));
    }

}
