package com.example.weichen.bookstore2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weichen.bookstore2.R;
import com.example.weichen.bookstore2.asyncTasd.AddBook_AsyncTask;
import com.example.weichen.bookstore2.asyncTasd.Login_AsyncTask;


//TODO: 接着看11.8 Login_AsyncTask.java 的代码解释
public class AddBooks_Activity extends Activity {
    private EditText ISBN, TITLE,PRICE;
    private TextView textView1, textView2;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.add_books);
        ISBN = (EditText) findViewById( R.id.editText1 );
        TITLE = (EditText) findViewById( R.id.editText2 );
        PRICE =  (EditText) findViewById( R.id.editText3 );
        textView1 = (TextView) findViewById( R.id.textView1 );
        textView2 = (TextView) findViewById( R.id.textView2 );
    }

        // Called when the button LOGIN-GET is pushed
    public void addBook( View view ) {

        String isbn = ISBN.getText( ).toString( );
        String title = TITLE.getText( ).toString( );
        String price = PRICE.getText( ).toString( );
        this.textView1.setText("");
        this.textView2.setText("");


        new AddBook_AsyncTask( this, ISBN,TITLE,PRICE,textView1,textView2, 0 ).execute( isbn, title,price );

    }


}

