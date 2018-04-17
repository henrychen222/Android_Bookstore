package com.example.weichen.bookstore2.activity;

/**
 * Created by Administrator on 2017/04/03.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weichen.bookstore2.R;
import com.example.weichen.bookstore2.asyncTasd.GetAccountInfo_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetShoppingHistoryTotal_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetShoppingHistory_AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account_Activity extends Activity {

    private ListView lv;
    private TextView tv1;
    private TextView tv2;
    private TextView totalAcount;
    String userId;
    /*定义一个动态数组*/
    ArrayList<HashMap<String, Object>> listItem;
    List<Map<String, String>> bookList = new ArrayList<Map<String, String>>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userId= intent.getStringExtra("userId");
        setContentView(R.layout.account_detail);
        lv = (ListView) findViewById(R.id.lv);
        tv1 = (TextView) findViewById(R.id.name);
        tv2 = (TextView) findViewById(R.id.ID);
        totalAcount = (TextView) findViewById(R.id.totalAcount);

        new GetAccountInfo_AsyncTask(this, tv1,tv2, 0).execute(userId);
        new GetShoppingHistory_AsyncTask(this, lv, 0).execute(userId);
        new GetShoppingHistoryTotal_AsyncTask(this,totalAcount,0).execute(userId);


     /*   MyAdapter mAdapter = new MyAdapter(this);//得到一个MyAdapter对象
        lv.setAdapter(mAdapter);// 为ListView绑定Adapter
         *//*为ListView添加点击事件*//*
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Log.v("ListAll_Activity", "你点击了ListView条目" + arg2);//在LogCat中输出信息
            }
        });*/

    }





}