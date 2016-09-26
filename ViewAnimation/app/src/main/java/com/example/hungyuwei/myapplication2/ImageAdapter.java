package com.example.hungyuwei.myapplication2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Hungyu Wei on 2016/6/27.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private Integer[] miImgArr;

    public ImageAdapter(Context context, Integer[] imgArr) {
        mContext = context;
        miImgArr = imgArr;
    }

    @Override
    public int getCount() {
        return miImgArr.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView v;

        if(convertView == null){
            v = new ImageView(mContext);
            v.setLayoutParams(new GridView.LayoutParams(110,110));
            v.setScaleType(ImageView.ScaleType.FIT_XY);
            v.setPadding(10,10,10,10);
        }
        else{
            v = (ImageView)convertView;
        }
        v.setImageResource(miImgArr[position]);
        return v;
    }
}
