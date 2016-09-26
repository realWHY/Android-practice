package com.example.hungyuwei.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button mbtnSurfInternet,
                    mbtnMP3Play,
                    mbtnViewImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtnSurfInternet = (Button)findViewById(R.id.btnSurfInternet);
        mbtnMP3Play = (Button)findViewById(R.id.btnMP3Play);
        mbtnViewImg = (Button)findViewById(R.id.btnViewImg);

        mbtnSurfInternet.setOnClickListener(btnBroser);
        mbtnMP3Play.setOnClickListener(btnPlayMp3);
        mbtnViewImg.setOnClickListener(btnPlayImg);
    }

    private View.OnClickListener btnBroser =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://developer.android.com");
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);
                }
            };

    private View.OnClickListener btnPlayMp3=
            new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(Intent.ACTION_EDIT);
                    File file = new File("/sdcard/penguin.jpg");
                    it.setDataAndType(Uri.fromFile(file),"image/*");
                    startActivity(it);
                }
            };

    private View.OnClickListener btnPlayImg=
            new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(Intent.ACTION_VIEW);
                    File file = new File("/sdcard/penguin.jpg");
                    it.setDataAndType(Uri.fromFile(file),"image/*");
                    startActivity(it);
                }
            };
}
