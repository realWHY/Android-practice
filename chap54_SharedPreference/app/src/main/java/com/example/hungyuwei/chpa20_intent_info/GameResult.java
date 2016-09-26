package com.example.hungyuwei.chpa20_intent_info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hungyu Wei on 2016/7/21.
 */
public class GameResult extends AppCompatActivity {
    private EditText mEdtCountSet,
            mEdtCountPlayerWin ,
            mEdtCountComWin ,
            mEdtCountDraw ;
    private Button mbtnBacktoGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_game_result);
        mEdtCountSet =(EditText)findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText)findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText)findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText)findViewById(R.id.edtCountDraw);

        mbtnBacktoGame = (Button)findViewById(R.id.btnBack);
        mbtnBacktoGame.setOnClickListener(btnGpBackToGameOnClick);
        show();
    }
    private View.OnClickListener btnGpBackToGameOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    public void show(){
        Bundle bundle = getIntent().getExtras();
        int iset = bundle.getInt("KEY_COUNT_SET");
        int iplayerwin = bundle.getInt("KEY_COUNT_PLAYER_WIN");
        int icomwin = bundle.getInt("KEY_COUNT_COM_WIN");
        int idraw = bundle.getInt("KEY_COUNT_DRAW");
        mEdtCountSet.setText(Integer.toString(iset));
        mEdtCountPlayerWin.setText(Integer.toString(iplayerwin));
        mEdtCountComWin.setText(Integer.toString(icomwin));
        mEdtCountDraw.setText(Integer.toString(idraw));

    }
}
