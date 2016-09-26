package com.example.hungyuwei.myapplication;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.util.*;


public class MainActivity extends AppCompatActivity {

    private TextView mTxtR, mTxtAge;
    private RadioGroup mRadGrpSex;
    //private RadioButton mRadBtnAgeRange1, mRadBtnAgeRange2, mRadBtnAgeRange3;
    private Button mBtnOk;
    private NumberPicker mNumPickerAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aaa","listener startss");
        setContentView(R.layout.activity_main);
        mRadGrpSex = (RadioGroup)findViewById(R.id.radGrpSex);
        mTxtAge = (TextView)findViewById(R.id.txtAge);
        mTxtAge.setText("25");
        mBtnOk = (Button)findViewById(R.id.btnOk);
        mNumPickerAge = (NumberPicker)findViewById(R.id.numPickerAge);
        mNumPickerAge.setMaxValue(200);
        mNumPickerAge.setMinValue(0);
        mNumPickerAge.setValue(21);
        mNumPickerAge.setOnValueChangedListener(numPickerChangeOnListener);

        mRadGrpSex.setOnCheckedChangeListener(radGrpSexCheckerChange);
        Log.d("aaa","listener start");
        mBtnOk.setOnClickListener(btnOKOnclick);
        Log.d("aaa","set text start");
        mTxtR = (TextView) findViewById(R.id.txtR);
    }

    private View.OnClickListener btnOKOnclick = new View.OnClickListener(){

        @Override
        public void onClick(View V) {
            String StrRes = getString(R.string.Result);
            int iAge = mNumPickerAge.getValue();
            if(iAge<23){
                StrRes += getString(R.string.sug_not_hurry);
            }
            else{
                StrRes += getString(R.string.sug_hurry);
            }
            mTxtR.setText(StrRes);
        }
    };


    private NumberPicker.OnValueChangeListener numPickerChangeOnListener =
            new NumberPicker.OnValueChangeListener(){

                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    mTxtAge.setText(String.valueOf(i1));
                }
            };

    private RadioGroup.OnCheckedChangeListener radGrpSexCheckerChange = new RadioGroup.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch(mRadGrpSex.getCheckedRadioButtonId()){
                case R.id.radBtnMale:
                    //mRadBtnAgeRange1.setText(getString(R.string.male_age_range1));
                    //mRadBtnAgeRange2.setText(getString(R.string.male_age_range2));
                    //mRadBtnAgeRange3.setText(getString(R.string.male_age_range3));
                    break;
                case R.id.radBtnFemale:
                    //mRadBtnAgeRange1.setText(getString(R.string.female_age_range1));
                    //mRadBtnAgeRange2.setText(getString(R.string.female_age_range2));
                    //mRadBtnAgeRange3.setText(getString(R.string.female_age_range3));
                    break;

            }
        }
    };


}
