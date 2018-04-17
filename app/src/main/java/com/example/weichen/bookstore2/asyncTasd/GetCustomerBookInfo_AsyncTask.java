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
import android.widget.TextView;

import com.example.weichen.bookstore2.activity.CustomerBookInfo_Activity;
import com.example.weichen.bookstore2.adapter.CustomerBookList_Adm_ListView_Adapter;
import com.example.weichen.bookstore2.adapter.CustomerList_Adm_ListView_Adapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetCustomerBookInfo_AsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    private int byGetOrPost = 0;
    ListView lv;
    String customerId;
    Map<String, String> map = null;
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    // Flag 0 means GET and 1 means POST. (By default it is GET.)
    public GetCustomerBookInfo_AsyncTask(Context context, ListView lv, int flag) {
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
        try {
            customerId = arg0[0];
//            String password = (String) arg0[1];
            String link     = "http://people.cs.und.edu/~wchen/457/2/";
//            String link     = "http://wenchen.cs.und.edu/course/457/11/Android/";
//            String link = "http://10.0.2.2/";
//            String link     = "http://192.168.1.105/";
            // Complete the URL.
            if (byGetOrPost == 0) { // Get method
                link += "android_getAllCustomerBookList.php";
//              Translates a string into application/x-www-form-urlencoded format using the specific encoding scheme UTF-8.
                link += "?customerId=" + URLEncoder.encode(customerId, "UTF-8");
//                link += "&password=" + URLEncoder.encode( password, "UTF-8" );
            } else { // Post method
//                link += "login_post.php";
            }

            // Connect to the server.
            URL url = new URL(link);
//          Returns a URLConnection instance that represents a connection to the remote object referred to by the URL.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 单位为毫秒，设置超时时间为5秒
            conn.setConnectTimeout(15 * 1000);
            // HttpURLConnection对象是通过HTTP协议请求path路径的，所以需要设置请求方式，可以不设置，因为默认为get
            conn.setRequestMethod("GET");

            conn.setDoOutput(true);

            InputStream is = conn.getInputStream(); // 获取输入流
            byte[] data = readStream(is); // 把输入流转换成字符串组
            String json = new String(data); // 把字符串组转换成字符串

            // 数据形式：{"total":2,"success":true,"arrayData":[{"id":1,"name":"张三"},{"id":2,"name":"李斯"}]}
            JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
//                int total = jsonObject.getInt("count");
//                String keywords = jsonObject.getString("keywords");
            // 里面有一个数组数据，可以用getJSONArray获取数组
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                String ISBN = item.getString("ISBN");
                String title = item.getString("title");
                String price = item.getString("quentity");
                map = new HashMap<String, String>();
                map.put("ISBN", ISBN);
                map.put("title", title);
                map.put("quentity", price);
                list.add(map);
            }

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
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

    @Override
//    which runs on the UI thread after doInBackground.

//    Runs on the UI thread after doInBackground(Params...). The specified result is the value returned by
//  doInBackground(Params...).
    protected void onPostExecute(String result) {

        if ("success".equals(result)) {
            //use baseAdapter to fill listView with data(the follow method' list)
            CustomerBookList_Adm_ListView_Adapter mAdapter = new CustomerBookList_Adm_ListView_Adapter(context, list);//得到一个MyAdapter对象

            //TODO:创建listView‘item’点击事件，连接数本详细信息

            /*为ListView添加点击事件*/

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    expressitemClick(position);
                }
            });


            lv.setAdapter(mAdapter);// 为ListView绑定Adapter
        }
    }
//
//    public void expressitemClick(int position){
//        Intent intent = new Intent(context,CustomerBookInfo_Activity.class);
//        intent.putExtra("customerId",list.get(position).get("customerId") );
//        context.startActivity(intent);
////        finish();//看你需不需要返回当前界面，如果点返回需要返回到当前界面，就不用这个
//    }

}

