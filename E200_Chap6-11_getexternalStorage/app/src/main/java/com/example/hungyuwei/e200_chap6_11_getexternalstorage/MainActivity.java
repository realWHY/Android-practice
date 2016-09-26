package com.example.hungyuwei.e200_chap6_11_getexternalstorage;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button button;//�d�߫��s
    EditText etTotal;//�`�e�q
    EditText etUsed;//�w�ϥζq
    EditText etAvailable;//���ϥζq
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)this.findViewById(R.id.Button01);//�d�߫��s
        etTotal=(EditText)this.findViewById(R.id.EditText01);//�`�e�q
        etUsed=(EditText)this.findViewById(R.id.EditText02);//�w�ϥζq
        etAvailable=(EditText)this.findViewById(R.id.EditText03);//���ϥζq

        button.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v) {
                                Log.d("Tag","Environment.getExternalStorageState() :"+Environment.getExternalStorageState());
                                Log.d("Tag","Environment.MEDIA_MOUNTED :"+Environment.MEDIA_MOUNTED);
                                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                                {//�P�_�x�s�d�O�_���J
                                    File path=Environment.getExternalStorageDirectory();//���o���|
                                    StatFs sf=new StatFs(path.getPath());//�إ�StatFs����
                                    long size=sf.getBlockSize();//SD�d���j�p
                                    long total=sf.getBlockCount();//�`�ƶq
                                    long available=sf.getAvailableBlocks();//�i�ϥΪ��ƶq
                                    DecimalFormat df=new DecimalFormat();//�إߪ���
                                    df.setGroupingSize(3);//�C3������@�s��
                                    Log.d("Tag","size :"+size);
                                    Log.d("Tag","total :"+total);
                                    Log.d("Tag","available :"+available);
                                    String totalSize=(size*total)/1024>=1024?//�`�e�q
                                            df.format((((size*total)/1024)/1024))+"MB":
                                            df.format((size*total)/1024)+"KB";
                                    String availableSize=(size*available)/1024>=1024?//���ϥζq
                                            df.format((((size*available)/1024)/1024))+"MB":
                                            df.format((size*available)/1024)+"KB";
                                    String usedSize=(size*(total-available))/1024>=1024?//�w�ϥζq
                                            df.format((((size*(total-available))/1024)/1024))+"MB":
                                            df.format((size*(total-available))/1024)+"KB";
                                    etTotal.setText(totalSize);//�`�e�q
                                    etUsed.setText(usedSize);//�w�ϥζq
                                    etAvailable.setText(availableSize);//���ϥζq
                                }else if(Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED))
                                {//SD�d�w����
                                    etTotal.setText(0);//�`�e�q
                                    etUsed.setText(0);//�w�ϥζq
                                    etAvailable.setText(0);//���ϥζq
                                }

                            }

                        }
                );
    }
}
