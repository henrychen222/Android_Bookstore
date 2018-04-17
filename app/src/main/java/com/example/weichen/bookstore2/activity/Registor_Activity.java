package com.example.weichen.bookstore2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weichen.bookstore2.R;
import com.example.weichen.bookstore2.asyncTasd.Login_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.Registor_AsyncTask;


public class Registor_Activity extends Activity {
    private EditText user, password,confirm;
    private TextView status;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register);
        user = (EditText) findViewById( R.id.user );
        password = (EditText) findViewById( R.id.password );
        confirm = (EditText) findViewById( R.id.confirm );
        status = (TextView) findViewById( R.id.status );
    }


    public void register( View view ) {
        String passwordStr = password.getText().toString();
        String confirmStr = confirm.getText().toString();
        String userStr = user.getText().toString();
        if("".equals(userStr)){
            status.setText("need a name");
        }
        else if(!passwordStr.equals(confirmStr)){
            status.setText("password dif");
        }else{
            new Registor_AsyncTask( this,status, 0 ).execute( userStr, passwordStr);

        }

    }

}

