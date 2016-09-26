package com.example.hungyuwei.my_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.hungyuwei.my_project.LightBall3Dpakage.LightBall3D;
import com.example.hungyuwei.my_project.Sphere3Dpakage.Sphere3D;
import com.example.hungyuwei.my_project.WoodBox3Dpakage.WoodBox3D;

public class MainActivity extends AppCompatActivity {
    private ImageButton mSphere3DButton;
    private ImageButton mWoodBox3DButton;
    private ImageButton mLightBall3DButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSphere3DButton = (ImageButton)findViewById(R.id.Sphere3DButton);
        mWoodBox3DButton = (ImageButton)findViewById(R.id.WoodBox3DButton);
        mLightBall3DButton = (ImageButton)findViewById(R.id.LightBall3DButton);

        mSphere3DButton.setOnClickListener(mSphere3DOnClick);
        mWoodBox3DButton.setOnClickListener(mWoodBox3DOnClick);
        mLightBall3DButton.setOnClickListener(mLightBall3DOnClick);
    }

    private View.OnClickListener mSphere3DOnClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent();
                    it.setClass(MainActivity.this,Sphere3D.class);
                    startActivity(it);
                }
            };

    private View.OnClickListener mWoodBox3DOnClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent();
                    it.setClass(MainActivity.this,WoodBox3D.class);
                    startActivity(it);
                }
            };

    private View.OnClickListener mLightBall3DOnClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent();
                    it.setClass(MainActivity.this, LightBall3D.class);
                    startActivity(it);
                }
            };
}
