package com.example.hungyuwei.chap49_activity_menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_MUSIC = Menu.FIRST,
            MENU_PLAY_MUSIC = Menu.FIRST+1,
            MENU_STOP_PLAYING_MUSIC = Menu.FIRST+2,
            MENU_ABOUT = Menu.FIRST+3,
            MENU_EXIT = Menu.FIRST+4;

            private RelativeLayout mrelativeLayout;
            private TextView mtxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrelativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        registerForContextMenu(mrelativeLayout);
        mtxtView = (TextView)findViewById(R.id.txtView);
        registerForContextMenu(mtxtView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d("Tag","v = "+v);
        if(v == mrelativeLayout){
            if(menu.size() == 0){
                SubMenu subMenu = menu.addSubMenu(0,MENU_MUSIC,0,"BG");
                subMenu.add(0,MENU_PLAY_MUSIC,0,"play music");
                subMenu.add(0,MENU_STOP_PLAYING_MUSIC,1,"Stop playing");
                menu.add(0,MENU_ABOUT,1,"About this");
                menu.add(0,MENU_EXIT,2,"EXIT");
            }
        }else if(v == mtxtView){
            menu.add(0,MENU_ABOUT,1,"About this");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        Log.d("Debug","Debug region 0");
        Spinner spnRegion = (Spinner)
                menu.findItem(R.id.menuItemRegion).getActionView().findViewById(R.id.spnRegion);

        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        onOptionsItemSelected(item);
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuPlayMusic:
                Intent it = new Intent(MainActivity.this, MediaPlayService.class);
                startService(it);
                break;

            case R.id.menuStopMusic:
                it = new Intent(MainActivity.this, MediaPlayService.class);
                stopService(it);
                break;

            case R.id.menuAbout:
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

            case R.id.menuExit:
                finish();
                break;

            case R.id.menuItemRegion:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("region")
                        .setMessage("this is region box")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                })
                        .show();

                break;
            case R.id.menuItemSearch:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("search")
                        .setMessage("This is search box")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                })
                        .show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Spinner.OnItemSelectedListener spnRegionOnItemSelected =
            new Spinner.OnItemSelectedListener () {
                public void onItemSelected(AdapterView parent,
                                           View v,
                                           int position,
                                           long id) {
                    Toast.makeText(MainActivity.this, parent.getSelectedItem().toString(),
                            Toast.LENGTH_LONG).show();
                }
                public void onNothingSelected(AdapterView parent) {
                }
            };

    private SearchView.OnQueryTextListener searchViewOnQueryTextLis = new
            SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextChange(String newText) {
                    // TODO Auto-generated method stub
                    return false;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    // TODO Auto-generated method stub
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_LONG).show();

                    return true;
                }
            };
}
