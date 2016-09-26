package com.example.hungyuwei.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {
    private ImageSwitcher mImageSwitcher;
    private GridView mGridView;
    private Integer[] miImgArr = {R.drawable.img01 ,R.drawable.scissor, R.drawable.img02,
            R.drawable.img03, R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07,R.drawable.img08
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitcher);
        mImageSwitcher.setFactory(this);

        //mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_trans_in));
        //mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_trans_out));

        ImageAdapter imgAdap = new ImageAdapter(this, miImgArr);

        mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setAdapter(imgAdap);
        mGridView.setOnItemClickListener(gridViewOnItemClick);

    }



    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        //v.setBackgroundColor(0xFF000000);
        //v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //v.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return v;
    }

    private AdapterView.OnItemClickListener gridViewOnItemClick =
            new AdapterView.OnItemClickListener(){


                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch ((int)(Math.random()*3+1)){
                        case 1:
                            mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_trans_in));
                            mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_trans_out));
                            break;
                        case 2:
                            mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_alpha_in));
                            mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_alpha_out));
                            break;
                        case 3:
                            mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_scale_rotate_in));
                            mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_scale_rotate_out));
                            break;
                    }
                    mImageSwitcher.setImageResource(miImgArr[i]);
                }
            };
}
