package com.example.TestListView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    final Context context = this;
    final String[] items = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private String[] adapterData;
    String[] str = {"新北市", "台北市", "台中市", "台南市", "高雄市"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.aliestView);

        adapterData = new String[]{"你", "他", "媽", "的"};
//
//
//
//        BaseAdapter baseAdapter = new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return items.length;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return items[position];
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return position;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if(convertView == null) {
//                    convertView = new TextView(context);
//                }
//
//                ((TextView) convertView).setText(getItem(position).toString());
//                return convertView;
//            }
//        };
//        AlertDialog.Builder listView = null;
//        listView.setAdapter(baseAdapter);


        //ListView 要顯示的內容

        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
//        ArrayAdapter adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1,
//                str);
//         listView.setAdapter(adapter);

        customizedIteminitViews();


    }

//    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            // Toast 快顯功能 第三個參數 Toast.LENGTH_SHORT 2秒  LENGTH_LONG 5秒
//            Toast.makeText(MainActivity.this, "點選第 " + (position + 1) + " 個 \n內容：" + str[position], Toast.LENGTH_LONG).show();
//        }
//    };

    //**********************************************************************************************************
    //共用資料格式
    class Struct {
        public String iName;
        public String iDesc;

        Struct(String name, String desc) {
            iName = name;
            iDesc = desc;
        }
    }
    //**********************************************************************************************************
    //
//    private Struct[] buildData(int length, String name, String desc) {
//        Struct[] array = new Struct[length];
//        for (int i = 0; i < length; i++) {
//            array[i] = new Struct(name + ":" + i, desc + "," + i);
//        }
//        return array;
//    }
    //**************************************************************************************************************
//    //客製化Adapter，先指定Adapter的Item資料格式，再把Item塞到Adapter中去放置在View中
//    private void customizedAdapterViews() {
//        //ListView mList = (ListView) findViewById(R.id.aliestView);
//        //設定資料長度與資料內容得項目
//        Struct[] mItems = buildData(30, "Name", "Desc");
//        ListAdapter mAdapter =
//                new ArrayAdapter<Struct>(this,
//                        android.R.layout.simple_list_item_2,
//                        android.R.id.text1,
//                        mItems) {
//                    @Override
//                    public View getView(int pos, View convert, ViewGroup group) {
//                        View v = super.getView(pos, convert, group);
//                        TextView t1 = (TextView) v.findViewById(android.R.id.text1);
//                        TextView t2 = (TextView) v.findViewById(android.R.id.text2);
//                        t1.setText(getItem(pos).iName);
//                        t2.setText(getItem(pos).iDesc);
//                        return v;
//                    }
//                };
//        listView.setAdapter(mAdapter);
//        //listView.setOnItemClickListener(onClickListView);       //指定事件 Method
//
//        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                AbsListView list = (AbsListView)adapterView;
//                int idx = list.getCheckedItemPosition();
//                Struct checked = (Struct)adapterView.getAdapter().getItem(idx);
//                Toast.makeText(MainActivity.this, "點選第 " + (pos + 1) + " 個 \niName內容：" + checked.iName+
//                        "\niDesc內容："+checked.iDesc, Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//*************************************************************************************************************
    //客製化Item，先使用HashMap來存放資料格式，再將Item放到SimpleAdapter中放置到View中
private List<Map<String, Object>> buildData(int length, String name, String desc) {
    List<Map<String, Object>> list = new ArrayList<>();
    for (int i = 0; i < length; i++) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name + ":" + i);
        map.put("desc", desc + ":" + i);
        list.add(map);
    }
    return list;
}
    //客製化Item，先使用HashMap來存放資料格式，再將Item放到SimpleAdapter中放置到View中
    private void customizedIteminitViews() {
        ListView mList = (ListView) findViewById(R.id.aliestView);
        List<Map<String, Object>> mItems = buildData(30, "Name", "Desc");
        //使用SimpleAdapter客製化Item
        ListAdapter mAdapter =
                new SimpleAdapter(this,
                        mItems,
                        android.R.layout.simple_list_item_2,
                        new String[] {"name", "desc"},
                        new int[] {android.R.id.text1, android.R.id.text2});
        //較mAdapter將資料放到listView中
        listView.setAdapter(mAdapter);

        //資料欄位被點選時的事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                AbsListView list = (AbsListView)adapterView;
                //int idx = list.getCheckedItemPosition();
                Toast.makeText(MainActivity.this, "點選第 " + (pos + 1) +
                        " 個"+
                        "\nName內容：" + mItems.get(pos).get("name")+
                        "\nDesc內容：" + mItems.get(pos).get("desc"),Toast.LENGTH_LONG).show();
            }
        });
    }
}


//public class MainActivity extends AppCompatActivity {
//
//    //ListView 要顯示的內容　改到全域變數
//    public String[] str = {"新北市","台北市","台中市","台南市","高雄市"};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ListView listview = (ListView) findViewById(R.id.aliestView);
//
//        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
//        ArrayAdapter adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1,
//                str);
//        listview.setAdapter(adapter);
//        listview.setOnItemClickListener(onClickListView);       //指定事件 Method
//
//    }

//    /***
//     * 點擊ListView事件Method
//     */
//    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            // Toast 快顯功能 第三個參數 Toast.LENGTH_SHORT 2秒  LENGTH_LONG 5秒
//            Toast.makeText(MainActivity.this,"點選第 "+(position +1) +" 個 \n內容："+str[position], Toast.LENGTH_SHORT).show();
//        }
//    };
//
//}