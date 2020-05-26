package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
        Cursor c = mydatabase.query("Local", null, "name = ?", name, null, null, null, null);
        c.moveToFirst();
        Local local = new Local(c);
        c.close();

        MyApplication app =(MyApplication)getApplication();
        local.addUserHistorique(app.getCurrentUser(), mydatabase);

        TextView textView = (TextView) findViewById(R.id.local_name);
        textView.setText("Local : " + local.getName());

        ListView lv = findViewById(R.id.local_history);
        ArrayList<String> history = new ArrayList<String>();

        String[] column = {"history"};
        String[] selectArg = {local.getName()};
        Cursor c2 = mydatabase.query("Local", column, "name=?", selectArg, null, null, null, null);
        c2.moveToFirst();
        System.out.println(c2.getCount());

        String raw_history = c2.getString(0);
        c2.close();
        String[] array_history = raw_history.split(";");
        for(int i=0 ; i<array_history.length ; i++){
            history.add(array_history[i]);
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, history);

        lv.setAdapter(arrayAdapter);
    }
}
