package com.example.weichen.bookstore2.activity;

/**
 * Created by Administrator on 2017/04/03.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weichen.bookstore2.R;
import com.example.weichen.bookstore2.asyncTasd.BuyBookList_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetBookList_Admin_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetBookList_AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAll_Adm_Activity extends Activity {

    private ListView lv;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
//        userId= intent.getStringExtra("userId");
        setContentView(R.layout.list_all_books);
        lv = (ListView) findViewById(R.id.all_books);

        new GetBookList_Admin_AsyncTask(this, lv, 0).execute();

    }


}