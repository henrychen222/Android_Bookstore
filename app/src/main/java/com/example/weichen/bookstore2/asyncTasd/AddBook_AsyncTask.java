package com.example.weichen.bookstore2.asyncTasd;

/**
 * Created by Administrator on 2017/04/02.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weichen.bookstore2.activity.ListAll_Activity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBook_AsyncTask extends AsyncTask<String, Void, String> {
    private EditText ISBN, TITLE,PRICE;
    private TextView textView1,textView2;
    private Context context;
    private int byGetOrPost = 0;
    Map<String, String> map = null;
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        // Flag 0 means GET and 1 means POST. (By default it is GET.)
    public AddBook_AsyncTask(Context context, EditText isbn, EditText title,
                             EditText price,TextView textView1,TextView textView2, int flag ) {
        this.ISBN     = isbn;
        this.TITLE = title;
        this.PRICE   = price;
        this.textView1 = textView1;
        this.textView2 = textView2;
        byGetOrPost      = flag;
    }

    protected void onPreExecute( ) { }

    @Override
    protected String doInBackground( String... arg0 ) {
        try {
            String isbn = (String) arg0[0];
            String title = (String) arg0[1];
            String price = (String) arg0[2];
//            String link     = "http://wenchen.cs.und.edu/course/457/11/Android/";
            String link     = "http://people.cs.und.edu/~wchen/457/2/";
//            String link     = "http://10.0.2.2/";
//            String link     = "http://192.168.1.105/";

            // Complete the URL.
            link += "android_addBook.php";
            link += "?isbn=" + URLEncoder.encode( isbn, "UTF-8" );
            link += "&title=" + URLEncoder.encode( title, "UTF-8" );
            link += "&price=" + URLEncoder.encode( price, "UTF-8" );



            // Connect to the server.
            URL            url = new URL( link );
            HttpURLConnection conn = (HttpURLConnection)url.openConnection( );
            conn.setDoOutput( true );



            InputStream is = conn.getInputStream(); // 获取输入流
            byte[] data = readStream(is); // 把输入流转换成字符串组
            String json = new String(data); // 把字符串组转换成字符串

            // 数据形式：{"total":2,"success":true,"arrayData":[{"id":1,"name":"张三"},{"id":2,"name":"李斯"}]}
            JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
            Log.v("jsonObject",jsonObject.toString());
//                int total = jsonObject.getInt("total");
            String success = jsonObject.getString("data");
            String key = success;
            return key;
        }
        catch( Exception e ) {
            e.printStackTrace();
            return new String( "Exception: " + e.getMessage( ) );
        }
    }

    @Override
    protected void onPostExecute( String result ) {

        if("success".equals(result)){
            this.ISBN.setText("");
            this.TITLE.setText("");
            this.PRICE.setText("");


        }
        else if("false_error1".equals(result)){
            this.textView1.setText("already exists");
        }
        else if("false_error2".equals(result)){
            this.textView2.setText("already exists");
        }
        else if("false_error3".equals(result)){
            this.textView1.setText("already exists");
            this.textView2.setText("already exists");
        }

    }

    private static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();
        return bout.toByteArray();
    }
}

