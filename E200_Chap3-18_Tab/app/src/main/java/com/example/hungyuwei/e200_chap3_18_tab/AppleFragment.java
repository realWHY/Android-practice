package com.example.hungyuwei.e200_chap3_18_tab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class AppleFragment extends Fragment {
	
	private String value = "";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("=====>", "AppleFragment onAttach");
		MainActivity mainActivity = (MainActivity)activity;
		value = mainActivity.getAppleData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("=====>", "AppleFragment onCreateView");
		return inflater.inflate(R.layout.frg_apple, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("=====>", "AppleFragment onActivityCreated");
		TextView txtResult = (TextView) this.getView().findViewById(R.id.textView1);
		txtResult.setText(value);
		//取得ImageView元件並帶入指定圖片
		ImageView mImg = (ImageView) getActivity().findViewById(R.id.img);
		mImg.setImageResource(R.drawable.cloths);
	}
	
}
