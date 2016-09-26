package com.example.hungyuwei.e200_chap4_2_buttonreply;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final static int WRAP_CONTENT=-2;//���WRAP_CONTENT���`��
    final static int X_MODIFY=4;//�b�D���ù��Ҧ��UX�y�Ъ��ץ���
    final static int Y_MODIFY=222;//�b�D���ù��Ҧ��UY�y�Ъ��ץ���

    int xSpan;//�bĲ�����I�����s�����p�U�۹����s�ۤv�y�Шt��
    int ySpan;//X,Y��m

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bok=(Button)this.findViewById(R.id.Button01);
        bok.setOnTouchListener(
                new View.OnTouchListener()
                {
                    public boolean onTouch(View view, MotionEvent event) {
                        switch(event.getAction())
                        {
                            case MotionEvent.ACTION_DOWN://Ĳ�������U
                                xSpan=(int)event.getX();
                                ySpan=(int)event.getY();
                                break;
                            case MotionEvent.ACTION_MOVE://Ĳ�����h��
                                Button bok=(Button)findViewById(R.id.Button01);
                                //�����s�H��Ĳ�������h���@�_�h��
                                Log.d("LOG","getRawX" +event.getRawX());
                                Log.d("LOG","xSpan" +xSpan);
                                Log.d("LOG","event.getX" +event.getX());
                                ViewGroup.LayoutParams  lp=
                                        new AbsoluteLayout.LayoutParams
                                                (
                                                        WRAP_CONTENT,
                                                        WRAP_CONTENT,
                                                        (int)event.getRawX()-xSpan-X_MODIFY,
                                                        (int)event.getRawY()-ySpan-Y_MODIFY
                                                ) ;
                                bok.setLayoutParams(lp);
                                break;
                        }
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onKeyDown (int keyCode, KeyEvent event)
    {//�������U����k
        Button bok=(Button)this.findViewById(R.id.Button01);
        bok.setText(keyCode+" Down");
        return true;
    }

    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event)
    {//������_����k
        Button bok=(Button)this.findViewById(R.id.Button01);
        bok.setText(keyCode+" Up");
        return true;
    }

    public boolean onTouchEvent (MotionEvent event)
    {
        //�����s�H��Ĳ�������h���@�_�h��
        Button bok=(Button)this.findViewById(R.id.Button01);

        ViewGroup.LayoutParams  lp=
                new AbsoluteLayout.LayoutParams
                        (
                                WRAP_CONTENT,
                                WRAP_CONTENT,
                                (int)event.getRawX()-xSpan-X_MODIFY,
                                (int)event.getRawY()-ySpan-Y_MODIFY
                        ) ;
        bok.setLayoutParams(lp);
        return true;
    }

}
