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
import com.example.weichen.bookstore2.asyncTasd.GetBookList_AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAll_Activity extends Activity {

    private ListView lv;
    private TextView shopResult;
    private TextView searchParam;
    String userId;
    List<Map<String, String>> list;
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
        setContentView(R.layout.activity_listall);
        lv = (ListView) findViewById(R.id.lv);
        searchParam = (TextView) findViewById(R.id.searchParam);
//        shopResult = (TextView) findViewById(R.id.shopResult);

        new GetBookList_AsyncTask(this, lv, 0).execute(searchParam.getText().toString());



    }
    public void expressitemClick(int position){
        Intent intent = new Intent(this,BookInfo_Activity.class);
        intent.putExtra("title",list.get(position).get("title") );
        startActivity(intent);
//        finish();//看你需不需要返回当前界面，如果点返回需要返回到当前界面，就不用这个
    }

    public void buy(View view) {
        Log.v("ListAll_Activity", "BUY按钮 = = start");//在LogCat中输出信息

//        把sql数组装成jsonArray
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject2 = new JSONObject();
        String sql = "";
//        ListAdapter listAdapter = lv.getAdapter();
        String isbn = "";
        String quantity = "";
        int j = 0;
        try{
        for(int i=0;i<lv.getChildCount();i++){

            View item = lv.getChildAt(i);
            CheckBox cb = (CheckBox)item.findViewById(R.id.ItemCB);
            if(cb.isChecked()){
                JSONObject jsonObject = new JSONObject();
                TextView t1 = (TextView)item.findViewById(R.id.ItemText);
                isbn = t1.getText().toString();
                EditText t2 = (EditText) item.findViewById(R.id.ItemEdit);
                quantity = t2.getText().toString();
                sql = "insert into shopping_history (customerId,bookId,quentity) " +
                        "values ("+userId+",'"+isbn+"',"+quantity+")";
                jsonObject.put(""+j,sql);
                jsonArray.put(jsonObject);
                j++;
            }

        }
//        for(int i=0;i<lv.getChildCount();i++){
//            View item = lv.getChildAt(i);
//            EditText t = (EditText) item.findViewById(R.id.ItemEdit);
//                sql += t.getText() + "";
//        }

        jsonObject2 = new JSONObject();

            jsonObject2.put("sqlArray",jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.v("insert sql", jsonObject2.toString());//在LogCat中输出信息

        new BuyBookList_AsyncTask( this ).execute( jsonArray.toString());//insert books into data
        new GetBookList_AsyncTask(this, lv, 0).execute(searchParam.getText().toString());
    }

    public void accountLink(View view) {
//        String username = usernameField.getText( ).toString( );
//        String password = passwordField.getText( ).toString( );
        Intent intent = new Intent(this,Account_Activity.class);
        intent.putExtra("userId",userId);
        startActivity(intent);
    }

    public void search(View view) {
        new GetBookList_AsyncTask(this, lv, 0).execute(searchParam.getText().toString());
    }


}