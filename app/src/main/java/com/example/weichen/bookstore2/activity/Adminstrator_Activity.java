package com.example.weichen.bookstore2.activity;

/**
 * Created by Administrator on 2017/04/03.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weichen.bookstore2.R;
import com.example.weichen.bookstore2.asyncTasd.GetAccountInfo_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetBookList_Admin_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetShoppingHistoryTotal_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.GetShoppingHistory_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.ResertSysterm_AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adminstrator_Activity extends Activity {

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
//        Intent intent = getIntent();
//        userId= intent.getStringExtra("userId");
        setContentView(R.layout.adminstrator_function);

    }

    public void AddBooks( View view ) {
        Intent intent = new Intent(this,AddBooks_Activity.class);
//        intent.putExtra("userId",userId);
        startActivity(intent);
    }
    public void showCustomer( View view ) {
        Intent intent = new Intent(this,ShowCustomers_Activity.class);
//        intent.putExtra("userId",userId);
        startActivity(intent);
    }
    public void deplayBooks( View view ) {
        Intent intent = new Intent(this,ListAll_Adm_Activity.class);
//        intent.putExtra("userId",userId);
        startActivity(intent);
    }
    public void resertSysterm( View view ) {
        new ResertSysterm_AsyncTask(this, 0).execute();
    }





}