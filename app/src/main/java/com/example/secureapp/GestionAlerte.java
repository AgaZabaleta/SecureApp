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

public class GestionAlerte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_alerte);

        Intent intent = getIntent();
        String value = intent.getStringExtra("id");
        String id[] = {value};

        SQLiteDatabase mydatabase = openOrCreateDatabase("database1",MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Alerte", null, "id = ?", id, null, null, null, null);
        c.moveToFirst();
        Alerte alerte = new Alerte(c);
        c.close();

        TextView textView = (TextView) findViewById(R.id.alerte_name);
        textView.setText("Alerte : " + alerte.getId());
    }
}
