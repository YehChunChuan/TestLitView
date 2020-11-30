package com.example.testrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        initViews();


    }

    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Toast 快顯功能 第三個參數 Toast.LENGTH_SHORT 2秒  LENGTH_LONG 5秒
            Toast.makeText(MainActivity.this, "點選第 " + (position + 1) + " 個 \n內容：" + str[position], Toast.LENGTH_LONG).show();
        }
    };


    class Struct {
        public String iName;
        public String iDesc;

        Struct(String name, String desc) {
            iName = name;
            iDesc = desc;
        }
    }

    private Struct[] buildData(int length, String name, String desc) {
        Struct[] array = new Struct[length];
        for (int i = 0; i < length; i++) {
            array[i] = new Struct(name + ":" + i, desc + "," + i);
        }
        return array;
    }

    private void initViews() {
        //ListView mList = (ListView) findViewById(R.id.aliestView);
        //設定資料長度與資料內容得項目
        Struct[] mItems = buildData(30, "Name", "Desc");
        ListAdapter mAdapter =
                new ArrayAdapter<Struct>(this,
                        android.R.layout.simple_list_item_2,
                        android.R.id.text1,
                        mItems) {
                    @Override
                    public View getView(int pos, View convert, ViewGroup group) {
                        View v = super.getView(pos, convert, group);
                        TextView t1 = (TextView) v.findViewById(android.R.id.text1);
                        TextView t2 = (TextView) v.findViewById(android.R.id.text2);
                        t1.setText(getItem(pos).iName);
                        t2.setText(getItem(pos).iDesc);
                        return v;
                    }
                };
        listView.setAdapter(mAdapter);
        //listView.setOnItemClickListener(onClickListView);       //指定事件 Method

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                AbsListView list = (AbsListView)adapterView;
                int idx = list.getCheckedItemPosition();
                Struct checked = (Struct)adapterView.getAdapter().getItem(idx);
                Toast.makeText(MainActivity.this, "點選第 " + (pos + 1) + " 個 \niName內容：" + checked.iName+
                        "\niDesc內容："+checked.iDesc, Toast.LENGTH_LONG).show();
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