package com.example.secureapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        SQLiteDatabase mydatabase = openOrCreateDatabase("database1",MODE_PRIVATE,null);
        mydatabase.execSQL("DROP TABLE IF EXISTS User;");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "User(firstName VARCHAR," +
                " lastName VARCHAR," +
                " status VARCHAR," +
                " username VARCHAR PRIMARY KEY," +
                " password VARCHAR);");
        mydatabase.execSQL("INSERT INTO User VALUES('Bastian', 'Pouget', 'Admin', 'Baba', 'password');");

        mydatabase.execSQL("DROP TABLE IF EXISTS Local;");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "Local(name VARCHAR PRIMARY KEY," +
                " history VARCHAR," +
                " lat FLOAT," +
                " lon FLOAT, " +
                " tel VARCHAR);");
        mydatabase.execSQL("INSERT INTO Local VALUES('Montpellier', 'History : ', 43.60960279713611, 3.874064942530673, '060000000');");

        mydatabase.execSQL("DROP TABLE IF EXISTS Alerte;");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "Alerte(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "local VARCHAR," +
                "state INTEGER," +
                "date VARCHAR);");
        SimpleDateFormat format = new SimpleDateFormat("EEE dd/MM/yyyy 'à' HH:mm:ss");
        String str_date = format.format(new Date());
        mydatabase.execSQL("INSERT INTO Alerte(local, state, date) VALUES('Montpellier', 1, '" + str_date + "');");


        Button clickButton = (Button) findViewById(R.id.start_button);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Connection.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
