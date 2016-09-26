package com.example.hungyuwei.e200_chap5_1_somefunc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText et;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)this.findViewById(R.id.TextView01);
        et=(EditText)this.findViewById(R.id.EditText01);
        et.setOnKeyListener
                (
                        new View.OnKeyListener()
                        {
                            public boolean onKey(View v, int keyCode, KeyEvent event) {
                                tv.setText(et.getText());
                                Linkify.addLinks
                                        (
                                                tv,
                                                Linkify.WEB_URLS|Linkify.EMAIL_ADDRESSES|Linkify.PHONE_NUMBERS
                                        );

                                return false;
                            }

                        }
                );

    }
}
