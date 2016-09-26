package com.example.hungyuwei.mydialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mBtnLoginDlg;
    private TextView mTxtResult;
    private Dialog mDlgLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnLoginDlg = (Button)findViewById(R.id.btnLoginDlg);
        mTxtResult = (TextView) findViewById(R.id.txtResult);
        mBtnLoginDlg.setOnClickListener(btnOnclickLogin);
        Log.d("addr",""+mTxtResult);
    }

    private View.OnClickListener btnOnclickLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mTxtResult.setText("");

            mDlgLogin = new Dialog(MainActivity.this);
            mDlgLogin.setTitle("Login in System");
            mDlgLogin.setCancelable(false);
            mDlgLogin.setContentView(R.layout.dlg_login);
            mDlgLogin.show();
        }
    };

}
