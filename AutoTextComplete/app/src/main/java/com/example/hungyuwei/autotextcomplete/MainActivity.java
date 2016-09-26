package com.example.hungyuwei.autotextcomplete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnAddAutoCompText,
            mBtnClearAutoCompText;
    private AutoCompleteTextView mAutoCompTextView;
    private ArrayAdapter<String> mAdapAutoComText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnAddAutoCompText = (Button) findViewById(R.id.btnAddAutoCompText);
        mBtnClearAutoCompText = (Button) findViewById(R.id.btnClearAutoCompText);
        mAutoCompTextView = (AutoCompleteTextView) findViewById(R.id.autoCompTextView);
        mAdapAutoComText = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        mAutoCompTextView.setAdapter(mAdapAutoComText);
        mBtnAddAutoCompText.setOnClickListener(btnAddAutoCompleteTextOnClick);
        mBtnClearAutoCompText.setOnClickListener(btnClearAutoCompleteTextOnClick);
    }

    private Button.OnClickListener btnAddAutoCompleteTextOnClick =
            new Button.OnClickListener(){

                @Override
                public void onClick(View view) {
                    String s = mAutoCompTextView.getText().toString();
                    mAdapAutoComText.add(s);
                    //ssmAutoCompTextView.setText("");
                }
            };

    private Button.OnClickListener btnClearAutoCompleteTextOnClick =
            new Button.OnClickListener(){

                @Override
                public void onClick(View view) {
                    mAdapAutoComText.clear();
                }
            };
}
