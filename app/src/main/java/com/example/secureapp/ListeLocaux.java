package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListeLocaux extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_locaux);
        ListView lv = findViewById(R.id.listLocaux);

        ArrayList<Local> locaux = new ArrayList<Local>();

        Local l1 = new Local("Local 1", 43.629071, 3.86506);
        Local l2 = new Local("Local 2", 43.61788740691996, 3.735627007421871);
        Local l3 = new Local("Local 3", 43.5997404646501, 3.790215325292965);
        locaux.add(l1);
        locaux.add(l2);
        locaux.add(l3);

        ArrayAdapter<Local> arrayAdapter = new ArrayAdapter<Local>(this, android.R.layout.simple_list_item_1, locaux);

        lv.setAdapter(arrayAdapter);
    }
}
