package com.example.weichen.bookstore2.asyncTasd;

/**
 * Created by Administrator on 2017/04/02.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.weichen.bookstore2.activity.BookInfo_Activity;
import com.example.weichen.bookstore2.activity.CustomerBookInfo_Activity;
import com.example.weichen.bookstore2.adapter.BookList_Adm_ListView_Adapter;
import com.example.weichen.bookstore2.adapter.CustomerList_Adm_ListView_Adapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetCustomerList_Admin_AsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    private int byGetOrPost = 0;
    private ListView lv;
    Map<String, String> map = null;
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    // Flag 0 means GET and 1 means POST. (By default it is GET.)
    public GetCustomerList_Admin_AsyncTask(Context context, ListView lv, int flag) {
        this.context = context;
        this.lv = lv;
        byGetOrPost = flag;
    }

    protected void onPreExecute() {
    }

    @Override
//    which performs a computation on a background thread

//    It invoked on the background thread immediately after onPreExecute finishes executing. This step is used to
//    perform background computation that can take a long time. The specified parameters are the parameters
//    passed to execute(Params...) by the caller of this task. The result of the computation must be returned
//    by this step and will be passed back to the last step.
    protected String doInBackground(String... arg0) {
        String key ="false";
        try {
//            String searchParam = (String) arg0[0];
//            String username = (String) arg0[0];
//            String password = (String) arg0[1];
            String link     = "http://people.cs.und.edu/~wchen/457/2/";
//            String link     = "http://wenchen.cs.und.edu/course/457/11/Android/";
//            String link = "http://10.0.2.2/";
//           String link     = "http://192.168.1.105/";
            // Complete the URL.
            if (byGetOrPost == 0) { // Get method
                link += "android_getAllCustomerList.php";
//              Translates a string into application/x-www-form-urlencoded format using the specific encoding scheme UTF-8.


//                link += "&password=" + URLEncoder.encode( password, "UTF-8" );
            } else { // Post method
            }

            // Connect to the server.
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15 * 1000);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);



            InputStream is = conn.getInputStream(); // 获取输入流
            byte[] data = readStream(is); // 把输入流转换成字符串组
            String json = new String(data); // 把字符串组转换成字符串

            if(json !=null && json.indexOf("null")==-1) {
                // 数据形式：{"total":2,"success":true,"arrayData":[{"id":1,"name":"张三"},{"id":2,"name":"李斯"}]}
                JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
//                int total = jsonObject.getInt("count");
//                String keywords = jsonObject.getString("keywords");
                // 里面有一个数组数据，可以用getJSONArray获取数组
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                    int customerId = item.getInt("customerId");
                    String amount = item.getString("amount");
                    String name = item.getString("name");
                    map = new HashMap<String, String>();
                    map.put("customerId", customerId + "");
                    map.put("amount", amount);
                    map.put("name", name);
                    list.add(map);
                }
            }
            key = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
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

    @Override
//    which runs on the UI thread after doInBackground.

//    Runs on the UI thread after doInBackground(Params...). The specified result is the value returned by
//  doInBackground(Params...).
    protected void onPostExecute(String result) {

        if ("success".equals(result)) {
            //use baseAdapter to fill listView with data(the follow method' list)
            CustomerList_Adm_ListView_Adapter mAdapter = new CustomerList_Adm_ListView_Adapter(context, list);//得到一个MyAdapter对象

            //TODO:创建listView‘item’点击事件，连接数本详细信息

            /*为ListView添加点击事件*/

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//                    Intent intent = new Intent(context,BookInfo_Activity.class);
//                    intent.putExtra("title",list.get(position).get("title") );
//                    context.startActivity(intent);
                expressitemClick(position);
            }
        });


        lv.setAdapter(mAdapter);// 为ListView绑定Adapter
        }
    }


    public void expressitemClick(int position){
        Intent intent = new Intent(context,CustomerBookInfo_Activity.class);
        intent.putExtra("customerId",list.get(position).get("customerId") );
        context.startActivity(intent);
//        finish();//看你需不需要返回当前界面，如果点返回需要返回到当前界面，就不用这个
    }

}

