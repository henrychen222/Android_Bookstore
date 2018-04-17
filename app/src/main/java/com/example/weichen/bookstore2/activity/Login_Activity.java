package com.example.weichen.bookstore2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weichen.bookstore2.R;
import com.example.weichen.bookstore2.asyncTasd.Login_AsyncTask;


//TODO: 接着看11.8 Login_AsyncTask.java 的代码解释
public class Login_Activity extends Activity {
    private EditText usernameField, passwordField;
    private TextView status, role, method;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login);
        usernameField = (EditText) findViewById( R.id.editText1 );
        passwordField = (EditText) findViewById( R.id.editText2 );
        status = (TextView) findViewById( R.id.textView6 );
        role   = (TextView) findViewById( R.id.textView7 );
        method = (TextView) findViewById( R.id.textView9 );
    }

        // Called when the button LOGIN-GET is pushed
    public void loginGet( View view ) {
        String username = usernameField.getText( ).toString( );
        String password = passwordField.getText( ).toString( );
        method.setText( "Get Method" );
//        method.setText( "终于有数据了" );
        new Login_AsyncTask( this, status, role, 0 ).execute( username, password );

    }
    // Called when the button LOGIN-POST is pushed

    public void register( View view ) {

        Intent intent = new Intent(this,Registor_Activity.class);
//        intent.putExtra("userId",userId);
        startActivity(intent);

    }

    public void cusOperate( View view ) {
        Intent intent = new Intent(this,Adminstrator_Activity.class);
//        intent.putExtra("userId",userId);
        startActivity(intent);
    }
//    public void accountLink(View view) {
////        String username = usernameField.getText( ).toString( );
////        String password = passwordField.getText( ).toString( );
//        Intent intent = new Intent(this,Account_Activity.class);
//        intent.putExtra("userId",userId);
//        startActivity(intent);
//    }
}

