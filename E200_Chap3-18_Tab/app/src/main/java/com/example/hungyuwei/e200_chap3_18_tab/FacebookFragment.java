package com.example.hungyuwei.e200_chap3_18_tab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FacebookFragment extends Fragment {

	private String value = "";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("=====>", "FacebookFragment onAttach");
		MainActivity mainActivity = (MainActivity)activity;
		value = mainActivity.getFacebookData();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("=====>", "FacebookFragment onCreateView");
		return inflater.inflate(R.layout.frg_facebook, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("=====>", "FacebookFragment onActivityCreated");
		TextView txtResult = (TextView) this.getView().findViewById(R.id.textView1);
		txtResult.setText(value);
	}
	
}
