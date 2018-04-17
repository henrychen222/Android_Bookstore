package com.example.weichen.bookstore2.asyncTasd;

/**
 * Created by Administrator on 2017/04/02.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import com.example.weichen.bookstore2.activity.Login_Activity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Registor_AsyncTask extends AsyncTask<String, Void, String> {
    private TextView status;
    private Context context;
    private int byGetOrPost = 0;
    Map<String, String> map = null;
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    public Registor_AsyncTask(Context context, TextView tv1,int flag ) {
        this.context     = context;
        this.status      = tv1;
        byGetOrPost      = flag;
    }

    protected void onPreExecute( ) { }

    @Override
    protected String doInBackground( String... arg0 ) {
        try {
            String user = (String) arg0[0];
            String password = (String) arg0[1];
            String link     = "http://people.cs.und.edu/~wchen/457/2/";
//            String link     = "http://192.168.1.105/";
            // Complete the URL.
            if ( byGetOrPost == 0 ) { // Get method
                link += "android_register.php";
//              Translates a string into application/x-www-form-urlencoded format using the specific encoding scheme UTF-8.
                link += "?user=" + URLEncoder.encode( user, "UTF-8" );
                link += "&password=" + URLEncoder.encode( password, "UTF-8" );
            }
            else { // Post method
                //link += "login_post.php";
            }

            // Connect to the server.
            URL            url = new URL( link );
//          Returns a URLConnection instance that represents a connection to the remote object referred to by the URL.
            HttpURLConnection conn = (HttpURLConnection)url.openConnection( );
//          Sets the value of the doOutput field for this URLConnection to the specified value. A URL connection
//          can be used for input and/or output. Set the DoOutput flag to true if you intend to use the URL connection
//          for output, false if not. The default is false.
            conn.setDoOutput( true );

            InputStream is = conn.getInputStream(); // 获取输入流
            byte[] data = readStream(is); // 把输入流转换成字符串组
            String json = new String(data); // 把字符串组转换成字符串


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
            this.status.setText( "success" );
            Intent intent = new Intent(context,Login_Activity.class);
            context.startActivity(intent);
        }
        else{
            this.status.setText( "register Fail" );
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

