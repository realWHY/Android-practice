package com.example.hungyuwei.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Hungyu Wei on 2016/7/21.
 */
public class MainImageActivity extends AppCompatActivity {
    private TextView mTxtResult;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image);

        mTxtResult = (TextView)findViewById(R.id.txtResult);
        showResult();
    }

    private void showResult(){
        Intent it = getIntent();
        String sAct = it.getAction();
        String sSccheme = it.getScheme();
        if(sSccheme.equals("http")){
            String s = "open"+it.getData().toString();
            mTxtResult.setText(s);
        }else if(sSccheme.equals("tel")){
            String s = "tel to"+it.getData().toString();
            mTxtResult.setText(s);
        }else if(sSccheme.equals("file")){
            if(sAct.equals("android.intent.action.VIEW")){
                String s="view111111111 "+it.getData().toString();
                mTxtResult.setText(s);
            } else if(sAct.equals("android.intent.action.EDIT")){
                String s = "Edit "+it.getData().toString();
                mTxtResult.setText(s);
            }

        }
    };
}
