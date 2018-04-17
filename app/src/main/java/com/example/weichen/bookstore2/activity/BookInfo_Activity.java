package com.example.weichen.bookstore2.activity;

/**
 * Created by Administrator on 2017/04/03.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.weichen.bookstore2.R;
import com.example.weichen.bookstore2.asyncTasd.GetBookInfo_AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookInfo_Activity extends Activity {

    private TextView title;
    private TextView ISBN;
    private TextView price;
    String titleName;
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
        titleName= intent.getStringExtra ("title");
        setContentView(R.layout.book_info);
        title = (TextView) findViewById(R.id.title);
        ISBN = (TextView) findViewById(R.id.ISBN);
        price = (TextView) findViewById(R.id.price);

        new GetBookInfo_AsyncTask(this, title,ISBN,price, 0).execute(titleName);

    }

}