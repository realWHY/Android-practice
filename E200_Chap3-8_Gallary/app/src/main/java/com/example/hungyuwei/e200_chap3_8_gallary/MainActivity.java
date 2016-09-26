package com.example.hungyuwei.e200_chap3_8_gallary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int[] imageIDs=//�_�l�ƹϤ��귽
            {
                    R.drawable.a,R.drawable.b,R.drawable.c,
                    R.drawable.d,R.drawable.e,R.drawable.f,
                    R.drawable.g
            };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//���̨�D�ɭ�

        Gallery gl=(Gallery)this.findViewById(R.id.Gallery01);//�_�l��Gallery

        BaseAdapter ba=new BaseAdapter()//�إߤ����d
        {
            public int getCount() //�o��imageIDs������
            {
                return imageIDs.length;
            }
            public Object getItem(int arg0)
            {
                return null;
            }
            public long getItemId(int arg0)
            {
                return 0;
            }
            public View getView(int arg0, View arg1, ViewGroup arg2)
            {
                ImageView iv = new ImageView(MainActivity.this);//�إ�ImageView
                iv.setImageResource(imageIDs[arg0]);//��ImageView�]�w�Ϥ��ӷ�
                iv.setScaleType(ImageView.ScaleType.FIT_XY);//��ImageView�]�w��ҫ��A
                iv.setLayoutParams(new Gallery.LayoutParams(188,250));//�]�w�G���ݶ�
                return iv;
            }
        };
        gl.setAdapter(ba);//�[�J�����d
        gl.setOnItemClickListener//�]�w�I����ť
                (
                        new AdapterView.OnItemClickListener()
                        {
                            public void onItemClick(AdapterView<?> arg0, View arg1,
                                                    int arg2, long arg3)
                            {
                                Gallery gl=(Gallery)findViewById(R.id.Gallery01);//�_�l��Gallery
                                gl.setSelection(arg2);//�]�w�����
                            }
                        }
                );
    }
}
