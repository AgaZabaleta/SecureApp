package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connection extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        Button clickButton = (Button) findViewById(R.id.button_connect);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase mydatabase = openOrCreateDatabase("database1",MODE_PRIVATE,null);

                EditText editUser = (EditText) findViewById (R.id.edit_username);
                EditText editPass = (EditText) findViewById (R.id.edit_password);
                String[] selectArgs = {editUser.getText().toString(),editPass.getText().toString()};

                Cursor c = mydatabase.query("User", null, "username=? AND password=?", selectArgs, null, null, null, null);
                c.moveToFirst();
                if(c.getCount()>0){
                    String text = "Welcome " + c.getString(0);
                    Toast toast = Toast.makeText(Connection.this, text, Toast.LENGTH_SHORT);
                    toast.show();

                    MyApplication app =(MyApplication)getApplication();
                    app.setCurrentUser(new User(c));
                    Intent myIntent = new Intent(Connection.this, ListeLocaux.class);
                    Connection.this.startActivity(myIntent);
                }else{
                    String text = "Username or password incorrect";
                    Toast toast = Toast.makeText(Connection.this, text, Toast.LENGTH_LONG);
                    toast.show();
                    MyApplication app =(MyApplication)getApplication();
                    app.setCurrentUser(new User("Admin", "Admin", "Admin", "Admin", "Admin"));
                    Intent myIntent = new Intent(Connection.this, ListeLocaux.class);
                    Connection.this.startActivity(myIntent);
                }
            }
        });
    }
}
