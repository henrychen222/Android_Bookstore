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
import com.example.weichen.bookstore2.asyncTasd.GetBookInfo_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetCustomerBookInfo_AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerBookInfo_Activity extends Activity {

    private ListView lv;
    String customerId;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        customerId = intent.getStringExtra ("customerId");
        setContentView(R.layout.customerbook_info);
        lv = (ListView) findViewById(R.id.all_books);

        new GetCustomerBookInfo_AsyncTask(this, lv, 0).execute(customerId);

    }

}