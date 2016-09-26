package com.example.hungyuwei.e200_chap3_18_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TwitterFragment extends Fragment {

	private String value = "";


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("=====>", "TwitterFragment onCreateView");
		return inflater.inflate(R.layout.frg_twitter, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("=====>", "TwitterFragment onActivityCreated");
		MainActivity mainActivity = new MainActivity();
		value = mainActivity.getTwitterData();
		TextView txtResult = (TextView) this.getView().findViewById(R.id.textView1);
		txtResult.setText(value);
	}

	
	
}
