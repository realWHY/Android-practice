package com.example.hungyuwei.e200_chap3_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //�Ҧ��귽�Ϥ��]andy�Bbill�Bedgar�Btorvalds�Bturing�^id���}�C
    int[] drawableIds=
            {R.drawable.andy,R.drawable.bill,R.drawable.edgar,R.drawable.torvalds,R.drawable.turing};
    //�Ҧ��귽�r��]andy�Bbill�Bedgar�Btorvalds�Bturing�^id���}�C
    int[] msgIds={R.string.andy,R.string.bill,R.string.edgar,R.string.torvalds,R.string.turing};
    int[] msgIds1={R.string.andy1,R.string.bill1,R.string.edgar1,R.string.torvalds1,R.string.turing1};
    @Override

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//���̨�D�ɭ�
        //�_�l��ListView
        ListView lv=(ListView)this.findViewById(R.id.ListView01);
        //��ListView�]�w�����d
        BaseAdapter ba=new BaseAdapter()
        {
            public int getCount()
            {
                return 5;
            }
            public Object getItem(int position)
            {
                return null;
            }
            public long getItemId(int position)
            {
                return 0;
            }
            public View getView(int arg0, View arg1, ViewGroup arg2)
            {
				/*
				 * �ʺA���ͨC�ӤU�Ԧ���������View�A�C�ӤU�Ԧ����View��LinearLayout
				 *���]�A�@��ImageView�Τ@��TextView�c��
				*/
                //�_�l��LinearLayout
                LinearLayout ll=new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);		//�]�w�¦V
                ll.setPadding(5,5,5,5);//�]�w�|�P�d��
                //�_�l��ImageView
                ImageView ii=new ImageView(MainActivity.this);
                ii.setImageDrawable(getResources().getDrawable(drawableIds[arg0]));//�]�w�Ϥ�
                ii.setScaleType(ImageView.ScaleType.FIT_XY);
                ii.setLayoutParams(new Gallery.LayoutParams(100,98));
                ll.addView(ii);//�s�W��LinearLayout��
                //�_�l��TextView
                TextView tv=new TextView(MainActivity.this);
                tv.setText(getResources().getText(msgIds[arg0]));//�]�w���e
                tv.setTextSize(24);//�]�w�r���j�p
                tv.setTextColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));//�]�w�r���m��
                tv.setPadding(5,5,5,5);//�]�w�|�P�d��
                tv.setGravity(Gravity.LEFT);
                ll.addView(tv);//�s�W��LinearLayout��

                TextView tv2=new TextView(MainActivity.this);
                tv2.setText(getResources().getText(msgIds1[arg0]));//�]�w���e
                tv2.setTextSize(12);//�]�w�r���j�p
                tv2.setTextColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));//�]�w�r���m��
                tv2.setPadding(5,5,5,5);//�]�w�|�P�d��
                tv.setGravity(Gravity.LEFT);
                ll.addView(tv2);//�s�W��LinearLayout��
                return ll;
            }
        };
        lv.setAdapter(ba);//��ListView�]�w���e�����d
        //�]�w�ﶵ�Ŀ諸��ť��
        lv.setOnItemSelectedListener
                (
                        new AdapterView.OnItemSelectedListener()
                        {
                            //@Override
                            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                       int arg2, long arg3) {//���s�w�q�ﶵ�Q�Ŀ�ƥ󪺳B�z��k
                                TextView tv=(TextView)findViewById(R.id.TextView01);//���o�D�ɭ�TextView
                                LinearLayout ll=(LinearLayout)arg1;//���o�ثe�Ŀ�ﶵ������LinearLayout
                                TextView tvn=(TextView)ll.getChildAt(1);//���o�䤤��TextView
                                Log.d("LOG","(TextView)ll.getChildAt(0)"+(TextView)ll.getChildAt(0));
                                Log.d("LOG","(TextView)ll.getChildAt(1)"+(TextView)ll.getChildAt(1));
                                StringBuilder sb=new StringBuilder();//��StringBuilder�ʺA���ͰT��
                                sb.append(getResources().getText(R.string.ys));//�[�J"�A����F�r��"
                                sb.append(":");//�[�J":"
                                sb.append(tvn.getText());//�[�JTextView����
                                String stemp=sb.toString();	//�NStringBuilder�ഫ��String���A
                                tv.setText(stemp.split("\\n")[0]);//�T���]�w�i�D�ɭ�TextView
                            }
                            //@Override
                            public void onNothingSelected(AdapterView<?> arg0) { }
                        }
                );

        //�]�w�ﶵ�Q�I�諸��ť��
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    //@Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {//���s�w�q�ﶵ�Q�I��ƥ󪺳B�z��k
                        TextView tv=(TextView)findViewById(R.id.TextView01);//���o�D�ɭ�TextView
                        LinearLayout ll=(LinearLayout)arg1;//���o�ثe�Ŀ�ﶵ������LinearLayout
                        TextView tvn=(TextView)ll.getChildAt(2);//���o�䤤��TextView
                        Log.d("LOG","(TextView)ll.getChildAt(0)"+(ImageView)ll.getChildAt(0));
                        Log.d("LOG","(TextView)ll.getChildAt(1)"+(TextView)ll.getChildAt(1));
                        StringBuilder sb=new StringBuilder();//��StringBuilder�ʺA���ͰT��
                        sb.append(getResources().getText(R.string.ys));//�[�J"�A����F�r��"
                        sb.append(":");//�[�J":"
                        sb.append(tvn.getText());//�[�JTextView����
                        String stemp=sb.toString();//�NStringBuilder�ഫ��String���A
                        tv.setText(stemp.split("\\n")[0]);//�T���]�w�i�D�ɭ�TextView
                    }
                }
        );
    }
}
