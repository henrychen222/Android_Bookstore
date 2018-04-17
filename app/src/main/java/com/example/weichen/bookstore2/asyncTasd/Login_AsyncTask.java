package com.example.weichen.bookstore2.asyncTasd;

/**
 * Created by Administrator on 2017/04/02.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

//It allows to perform background operations and publish results on the UI thread without having to
//        manipulate threads and/or handlers. An asynchronous task is defined by 3 generic types:
//        Params, Progress, and Result, and 4 steps:
//        1、onPreExecute,
//        2、doInBackground,
//        3、onProgressUpdate,
//        4、and onPostExecute.
//It represents a Uniform Resource Locator, a pointer to a “resource” on the World Wide Web.
//It is the superclass of all classes that represent a communications link between the application and a URL.
//It is a utility class for HTML form encoding, which converts characters into a format that can be transmitted
// over the Internet. For example, using UTF-8 as the encoding scheme
//Reads text from a character-input stream, buffering characters so as to provide for
//the efficient reading of characters, arrays, and lines.

public class Login_AsyncTask extends AsyncTask<String, Void, String> {
    private TextView statusField, roleField;
    private Context context;
    private int byGetOrPost = 0;
    Map<String, String> map = null;
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        // Flag 0 means GET and 1 means POST. (By default it is GET.)
    public Login_AsyncTask(Context context, TextView statusField, TextView roleField, int flag ) {
        this.context     = context;
        this.statusField = statusField;
        this.roleField   = roleField;
        byGetOrPost      = flag;
    }

    protected void onPreExecute( ) { }

    @Override
//    which performs a computation on a background thread

//    It invoked on the background thread immediately after onPreExecute finishes executing. This step is used to
//    perform background computation that can take a long time. The specified parameters are the parameters
//    passed to execute(Params...) by the caller of this task. The result of the computation must be returned
//    by this step and will be passed back to the last step.
    protected String doInBackground( String... arg0 ) {
        try {
            String username = (String) arg0[0];
            String password = (String) arg0[1];
//            String link     = "http://wenchen.cs.und.edu/course/457/11/Android/";
            String link     = "http://people.cs.und.edu/~wchen/457/2/";
//            String link     = "http://10.0.2.2/";
//            String link     = "http://192.168.1.105/";
            // Complete the URL.
            if ( byGetOrPost == 0 ) { // Get method
                link += "android_login.php";
//              Translates a string into application/x-www-form-urlencoded format using the specific encoding scheme UTF-8.
                link += "?username=" + URLEncoder.encode( username, "UTF-8" );
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

            // Send the arguments via standard output for the POST method.
            if ( byGetOrPost == 1 ) { // Post method
                String data  = URLEncoder.encode( "username", "UTF-8" ) + "=";
                data += URLEncoder.encode( username,   "UTF-8" ) + "&";
                data += URLEncoder.encode( "password", "UTF-8" ) + "=";
                data += URLEncoder.encode( password,   "UTF-8" );
                OutputStreamWriter wr = new OutputStreamWriter(
//                      It returns an output stream that writes to this connection.
                        conn.getOutputStream( ) );
                wr.write( data );
//              Flushes the stream and forces any buffered output to be written out.
                wr.flush( );
            }

            // Read server response.
//            BufferedReader reader = new BufferedReader(
//                new InputStreamReader( conn.getInputStream( ) ));
//            StringBuilder sb = new StringBuilder( );
//            String      line = null;
//            while (( line = reader.readLine( ) ) != null ) {
//                sb.append( line );
//                break;
//            }
//            return sb.toString( );
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
                int id = -1;
                String isSuccess = item.getString("isSuccess");
                if("success".equals(isSuccess)){
                    id = item.getInt("id");
                }
                map = new HashMap<String, String>();
                map.put("id", id +"");
                map.put("isSuccess", isSuccess);
                list.add(map);
            }
            String key = list.get(0).get("isSuccess");
            return key;
        }
        catch( Exception e ) {
        e.printStackTrace();
            return new String( "Exception: " + e.getMessage( ) );
        }
    }

    @Override
//    which runs on the UI thread after doInBackground.

//    Runs on the UI thread after doInBackground(Params...). The specified result is the value returned by
//  doInBackground(Params...).
    protected void onPostExecute( String result ) {

        if("success".equals(result)){
            this.statusField.setText( "Login Successful" );
            this.roleField.setText  ( list.get(0).get("id") );
            Intent intent = new Intent(context,ListAll_Activity.class);
            intent.putExtra("userId",list.get(0).get("id") );
            context.startActivity(intent);

        }
        else{
            this.statusField.setText( "Login Fail" );
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

