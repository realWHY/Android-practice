package com.example.hungyuwei.listview;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    private TextView mTxtResult;
    List<Map<String, Object>> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mList = new ArrayList<Map<String, Object>>();
        String[] listFromResource = getResources().getStringArray(R.array.weekday);
        for(Object object1:listFromResource) {
            Log.d("listdebug", "" + object1);
        }
        for(int i=0; i<listFromResource.length; i++){
            Map<String,Object> item = new HashMap<String, Object>();
            item.put("tttView",listFromResource[i]);
            item.put("iiiView",android.R.drawable.ic_menu_week);

            mList.add(item);
        }

        for(Object object2:mList) {
            Log.d("listdebug", "" + object2);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, mList,
                R.layout.list_item,
                new String[]{"tttView","iiiView"},
                new int[]{R.id.txtView,R.id.imgView});
        setListAdapter(adapter);
        ListView listView = getListView();
        listView.setOnItemClickListener(listViewOnItemClick);
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
