package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class GestionLocal extends AppCompatActivity implements OnMapReadyCallback {
    MapView mapView;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_local);

        mapView = (MapView)findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(GestionLocal.this);
        //mapView.setLayoutParams(new LinearLayout.LayoutParams(500,500));

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map = googleMap;
        map.setMinZoomPreference(12);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(40.7143528, -74.0059731)));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
