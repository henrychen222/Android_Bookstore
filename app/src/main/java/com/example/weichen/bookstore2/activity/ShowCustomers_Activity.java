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
import com.example.weichen.bookstore2.asyncTasd.GetBookList_Admin_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetCustomerList_Admin_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetShoppingHistoryTotal_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetShoppingHistory_AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowCustomers_Activity extends Activity {

    private ListView lv;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
//        userId= intent.getStringExtra("userId");
        setContentView(R.layout.list_all_customers);
        lv = (ListView) findViewById(R.id.all_customers);

        new GetCustomerList_Admin_AsyncTask(this, lv, 0).execute();

    }





}