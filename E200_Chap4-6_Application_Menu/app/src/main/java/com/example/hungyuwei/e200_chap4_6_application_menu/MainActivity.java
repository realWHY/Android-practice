package com.example.hungyuwei.e200_chap4_6_application_menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    final int MENU_GENDER_MALE=0;    //�C�ӿ�涵�ت��s��=======begin=========
    final int MENU_GENDER_FEMALE=1;
    final int MENU_HOBBY1=2;
    final int MENU_HOBBY2=3;
    final int MENU_HOBBY3=4;
    final int MENU_OK=5;
    final int MENU_GENDER=6;
    final int MENU_HOBBY=7;         //�C�ӿ�涵�ت��s��=======end============

    final int GENDER_GROUP=0;      //�ʧO�l��涵�s�ժ��s��
    final int HOBBY_GROUP=1;  	   //�R�n�l��涵�s�ժ��s��
    final int MAIN_GROUP=2;        //�~�h�`��涵�s�ժ��s��

    MenuItem[] miaHobby=new MenuItem[3];//�R�n��涵�s��
    MenuItem male=null;//�k�ʩʧO��涵

    //���o�ثe������A����k
    public void appendStateStr()
    {
        String result="�z������ʧO���G";
        if(male.isChecked())
        {
            result=result+"�k";
        }
        else
        {
            result=result+"�k";
        }

        String hobbyStr="";
        for(MenuItem mi:miaHobby)
        {
            if(mi.isChecked())
            {
                hobbyStr=hobbyStr+mi.getTitle()+"�B";
            }
        }

        if(hobbyStr.length()>0)
        {
            result=result+",�z���R�n���G"+hobbyStr.substring(0, hobbyStr.length()-1)+"�C\n";
        }
        else
        {
            result=result+"�C\n";
        }
        EditText et=(EditText)MainActivity.this.findViewById(R.id.EditText01);
        et.append(result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //�ʧO����涵�s��   ���Y�s�s�մN�O����涵�s��
        SubMenu subMenuGender = menu.addSubMenu(MAIN_GROUP,MENU_GENDER,0,R.string.gender);
        subMenuGender.setIcon(R.drawable.gender);
        male=subMenuGender.add(GENDER_GROUP, MENU_GENDER_MALE, 0, R.string.male);
        male.setChecked(true);
        subMenuGender.add(GENDER_GROUP, MENU_GENDER_FEMALE, 0, R.string.female);
        //�]�wGENDER_GROUP�s�լO�i������A������
        subMenuGender.setGroupCheckable(GENDER_GROUP, true,true);


        //�R�n�_���涵�s��
        SubMenu subMenuHobby = menu.addSubMenu(MAIN_GROUP,MENU_HOBBY,0,R.string.hobby);
        subMenuHobby.setIcon(R.drawable.hobby);
        miaHobby[0]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY1, 0, R.string.hobby1);
        miaHobby[1]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY2, 0, R.string.hobby2);
        miaHobby[2]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY3, 0, R.string.hobby3);
        miaHobby[0].setCheckable(true);//�]�w��涵���_���涵
        miaHobby[1].setCheckable(true);
        miaHobby[2].setCheckable(true);

        //�T�w��涵
        MenuItem ok=menu.add(GENDER_GROUP+2,MENU_OK,0,R.string.ok);
        MenuItem.OnMenuItemClickListener lsn=new MenuItem.OnMenuItemClickListener()
        {//��{��涵�I���ƥ��ť���f
            public boolean onMenuItemClick(MenuItem item) {
                appendStateStr();
                return true;
            }
        };
        ok.setOnMenuItemClickListener(lsn);//���T�w��涵�[�J��ť��
        //���T�w��涵�[�J�ֳt��
        ok.setAlphabeticShortcut('o');//�]�w�r���ֳt��
        //ok.setNumericShortcut('1');//�]�w�Ʀr�ֳt��
        //ok.setShortcut('a', '2');//�P�ɳ]�w��اֳt��
        //�n�`�N�A�P�ɳ]�w�h���ɥu���̫�@�ӳ]�w�_�@��

        return true;
    }

    @Override  //���δ_���涵�Ŀ窱�A�ܤƫ᪺�^�դ�k
    public boolean onOptionsItemSelected(MenuItem mi)
    {
        switch(mi.getItemId())
        {
            case MENU_GENDER_MALE://����涵���A�������n�ۦ�g�{���X����
            case MENU_GENDER_FEMALE:
                mi.setChecked(true);
                appendStateStr();//���ıM���ܤƮɰO���b��r�Ϥ�
                break;

            case MENU_HOBBY1://�_���涵���A�������n�ۦ�g�{���X����
            case MENU_HOBBY2:
            case MENU_HOBBY3:
                mi.setChecked(!mi.isChecked());
                appendStateStr();//���ıM���ܤƮɰO���b��r�Ϥ�
                break;
        }
        return true;
    }
}
