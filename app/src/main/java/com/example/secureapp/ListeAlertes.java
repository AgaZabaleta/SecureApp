package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ListeAlertes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_alertes);

        ListView lv = findViewById(R.id.listAlerte);
        ArrayList<Alerte> alertes = new ArrayList<Alerte>();

        SQLiteDatabase mydatabase = openOrCreateDatabase("database1",MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Alerte", null, null, null, null, null, null, null);
        c.moveToFirst();
        do{
            alertes.add(new Alerte(c));
        }while (c.moveToNext());

        final ArrayAdapter<Alerte> arrayAdapter = new ArrayAdapter<Alerte>(this, android.R.layout.simple_list_item_1, alertes);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alerte item = arrayAdapter.getItem(position);
                Intent intent = new Intent(ListeAlertes.this,GestionAlerte.class);
                intent.putExtra("id", Integer.toString(item.getId())); //Optional parameters
                startActivity(intent);
            }
        });
    }

    public void onClickToggleMenu(View view) {
        LinearLayout l = findViewById(R.id.drop_menu);

        if(l.getVisibility() == View.GONE) {
            l.setVisibility(View.VISIBLE);
        } else {
            l.setVisibility(View.GONE);
        }
    }
}
