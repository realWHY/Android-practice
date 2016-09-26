package com.example.hungyuwei.fragment_static;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/*
 */
public class FragmentResult extends Fragment {


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
        return inflater.inflate(R.layout.fragment_fragment_result, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentMain frag =(FragmentMain)getFragmentManager().findFragmentById(R.id.fragment);
        frag.mEdtCountSet = (EditText)getActivity().findViewById(R.id.edtCountSet);
        frag.mEdtCountPlayerWin = (EditText)getActivity().findViewById(R.id.edtCountPlayerWin);
        frag.mEdtCountComWin = (EditText)getActivity().findViewById(R.id.edtCountComWin);
        frag.mEdtCountDraw = (EditText)getActivity().findViewById(R.id.edtCountDraw);
        getActivity().findViewById(R.id.frameLay).setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().findViewById(R.id.frameLay).setVisibility(View.GONE);
    }
}
