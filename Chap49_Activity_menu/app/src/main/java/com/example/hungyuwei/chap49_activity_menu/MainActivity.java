package com.example.hungyuwei.chap49_activity_menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_MUSIC = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST+1,
            MENU_STOP_PLAYING_MUSIC = Menu.FIRST+2,
            MENU_ABOUT = Menu.FIRST+3,
            MENU_EXIT = Menu.FIRST+4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        SubMenu subMenu = menu.addSubMenu(0,MENU_MUSIC,0,"BG");
        subMenu.add(0,MENU_PLAY_MUSIC,0,"play music");
        subMenu.add(0,MENU_STOP_PLAYING_MUSIC,1,"Stop playing");
        menu.add(0,MENU_ABOUT,1,"About this");
        menu.add(0,MENU_EXIT,2,"EXIT");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_PLAY_MUSIC:
                Intent it = new Intent(MainActivity.this, MediaPlayService.class);
                startService(it);
                break;

            case MENU_STOP_PLAYING_MUSIC:
                it = new Intent(MainActivity.this, MediaPlayService.class);
                stopService(it);
                break;

            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About this proogram")
                        .setMessage("menu example")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                break;

            case MENU_EXIT:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
