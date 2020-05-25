package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListeLocaux extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_locaux);
        ListView lv = findViewById(R.id.listLocaux);

        ArrayList<Local> locaux = new ArrayList<Local>();

        SQLiteDatabase mydatabase = openOrCreateDatabase("database1",MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Locaux", null, null, null, null, null, null, null);
        c.moveToFirst();
        do{
            locaux.add(new Local(c));
        }while (c.moveToNext());


        Local l1 = new Local("Local 1", 43.629071, 3.86506);
        Local l2 = new Local("Local 2", 43.61788740691996, 3.735627007421871);
        Local l3 = new Local("Local 3", 43.5997404646501, 3.790215325292965);
        locaux.add(l1);
        locaux.add(l2);
        locaux.add(l3);

        final ArrayAdapter<Local> arrayAdapter = new ArrayAdapter<Local>(this, android.R.layout.simple_list_item_1, locaux);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Local item = arrayAdapter.getItem(position);
                Intent intent = new Intent(ListeLocaux.this,GestionLocal.class);
                intent.putExtra("local", item.getName()); //Optional parameters
                startActivity(intent);
            }
        });
    }
}
