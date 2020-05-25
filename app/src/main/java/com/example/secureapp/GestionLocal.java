package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class GestionLocal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_local);

        Intent intent = getIntent();
        String value = intent.getStringExtra("local");
        String name[] = {value};

        SQLiteDatabase mydatabase = openOrCreateDatabase("database1",MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Locaux", null, "name = ?", name, null, null, null, null);
        c.moveToFirst();
        Local local = new Local(c);

        MyApplication app =(MyApplication)getApplication();
        local.addUserHistorique(app.getCurrentUser(), mydatabase);

        TextView textView = (TextView) findViewById(R.id.local_name);
        textView.setText("Local : " + local.getName());
    }
}
