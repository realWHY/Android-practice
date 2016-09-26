package com.example.hungyuwei.chap72_mediaplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
        implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {

    private ImageButton mBtnMediaPlayPause,
            mBtnMediaStop,
            mBtnMediaGoto;

    private ToggleButton mBtnMediaRepeat;

    private EditText mEdtMediaGoto;

    private MediaPlayer mMediaPlayer = null;

    private boolean mbIsInitial = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnMediaPlayPause = (ImageButton)findViewById(R.id.btnMediaPlayPause);
        mBtnMediaStop = (ImageButton)findViewById(R.id.btnMediaStop);
        mBtnMediaRepeat = (ToggleButton)findViewById(R.id.btnMediaRepeat);
        mBtnMediaGoto = (ImageButton)findViewById(R.id.btnMediaGoto);
        mEdtMediaGoto = (EditText)findViewById(R.id.edtMediaGoto);

        mBtnMediaPlayPause.setOnClickListener(btnMediaPlayPauseOnClick);
        mBtnMediaStop.setOnClickListener(btnMediaStopOnClick);
        mBtnMediaRepeat.setOnClickListener(btnMediaRepeatOnClick);
        mBtnMediaGoto.setOnClickListener(btnMediaGotoOnClick);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mMediaPlayer = new MediaPlayer();

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.song);

        try {
            mMediaPlayer.setDataSource(this, uri);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(MainActivity.this, "���w�������ɿ��~�I", Toast.LENGTH_LONG)
                    .show();
        }

        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnCompletionListener(this);
    }

    private View.OnClickListener btnMediaPlayPauseOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (mMediaPlayer.isPlaying()) {
                mBtnMediaPlayPause.setImageResource(android.R.drawable.ic_media_play);
                mMediaPlayer.pause();
            } else {
                mBtnMediaPlayPause.setImageResource(android.R.drawable.ic_media_pause);

                if (mbIsInitial) {
                    mMediaPlayer.prepareAsync();
                    mbIsInitial = false;
                } else
                    mMediaPlayer.start();
            }
        }
    };

    private View.OnClickListener btnMediaStopOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mMediaPlayer.stop();

            // �����ᥲ���A���� prepareAsync()
            // �� prepare() �~�୫�s����C
            mbIsInitial = true;
            mBtnMediaPlayPause.setImageResource(android.R.drawable.ic_media_play);
        }
    };

    private View.OnClickListener btnMediaRepeatOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (((ToggleButton)v).isChecked())
                mMediaPlayer.setLooping(true);
            else
                mMediaPlayer.setLooping(false);
        }
    };

    private View.OnClickListener btnMediaGotoOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (mEdtMediaGoto.getText().toString().equals("")) {
                Toast.makeText(MainActivity.this,
                        "�Х���J�n���񪺦�m�]�H�����^",
                        Toast.LENGTH_LONG)
                        .show();
                return;
            }

            int seconds = Integer.parseInt(mEdtMediaGoto.getText().toString());
            mMediaPlayer.seekTo(seconds * 1000); // �H�@��]�d�����@��^�����
        }
    };

    @Override
    public void onCompletion(MediaPlayer mp) {
        // TODO Auto-generated method stub
        mBtnMediaPlayPause.setImageResource(android.R.drawable.ic_media_play);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        // TODO Auto-generated method stub
        mp.release();
        mp = null;

        Toast.makeText(MainActivity.this, "�o�Ϳ��~�A�����", Toast.LENGTH_LONG)
                .show();

        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // TODO Auto-generated method stub
        mp.seekTo(0);
        mp.start();

        Toast.makeText(MainActivity.this, "�}�l����", Toast.LENGTH_LONG)
                .show();
    }
}
