package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase mydatabase = openOrCreateDatabase("database1",MODE_PRIVATE,null);
        mydatabase.execSQL("DROP TABLE IF EXISTS Locaux;");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Locaux(name VARCHAR PRIMARY KEY, history VARCHAR, lat FLOAT, lon FLOAT);");
        mydatabase.execSQL("INSERT INTO Locaux VALUES('poitiers', 'action:date;action:date', 0.0, 0.0);");

        mydatabase.execSQL("DROP TABLE IF EXISTS User;");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS User(firstName VARCHAR, lastName VARCHAR, status VARCHAR, username VARCHAR PRIMARY KEY, password VARCHAR);");
        mydatabase.execSQL("INSERT INTO User VALUES('Bastian', 'Pouget', 'Admin', 'Baba', 'password');");


        Button clickButton = (Button) findViewById(R.id.start_button);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent myIntent = new Intent(MainActivity.this, Connection.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
