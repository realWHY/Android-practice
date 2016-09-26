package com.example.hungyuwei.listview;

import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ExpandableListActivity {
    private static final String ITEM_NAME = "Item Name";
    private static final String ITEM_SUBNAME = "Item Subname";
    private ExpandableListAdapter mExpaListAdap;
    private TextView mTxtResult;
    List<Map<String, Object>> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        List<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childList2D = new ArrayList<List<Map<String, String>>>();

        for (int i = 0; i < 5; i++) {
            Map<String, String> group = new HashMap<String, String>();
            group.put(ITEM_NAME, "option group" + i);
            group.put(ITEM_SUBNAME, "description" + i);
            groupList.add(group);

            List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
            for (int j = 0; j < 2; j++) {
                Map<String, String> child = new HashMap<String, String>();
                child.put(ITEM_NAME, "option" + i + j);
                child.put(ITEM_SUBNAME, "description" + i + j);
                childList.add(child);
            }
            childList2D.add(childList);
        }

        mExpaListAdap = new SimpleExpandableListAdapter(
                this,
                groupList,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {ITEM_NAME, ITEM_SUBNAME},
                new int[] {android.R.id.text1, android.R.id.text2},
                childList2D,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {ITEM_NAME, ITEM_SUBNAME},
                new int[] {android.R.id.text1, android.R.id.text2}
        );

        setListAdapter( mExpaListAdap);
    }

    private AdapterView.OnItemClickListener listViewOnItemClick =
            new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mTxtResult.setText(((TextView) view.findViewById(R.id.txtView))
                            .getText().toString());
                }
            };
}
