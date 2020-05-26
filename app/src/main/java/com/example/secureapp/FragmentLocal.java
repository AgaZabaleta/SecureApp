package com.example.secureapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLocal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLocal extends Fragment implements OnMapReadyCallback {
    private View myFragmentView;
    MapView mapView;
    GoogleMap map;
    Local local;
    ArrayList<String> history;

    public FragmentLocal() {
        // Required empty public constructor
    }


    public static FragmentLocal newInstance() {
        FragmentLocal fragment = new FragmentLocal();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity ctx = getActivity();

        String value = getArguments().getString("local");
        String name[] = {value};

        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("database1", ctx.MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Local", null, "name = ?", name, null, null, null, null);
        c.moveToFirst();
        local = new Local(c);
        c.close();

        MyApplication app =(MyApplication) ctx.getApplication();
        local.addUserHistorique(app.getCurrentUser(), mydatabase);

        history = new ArrayList<String>();

        String[] column = {"history"};
        String[] selectArg = {local.getName()};
        Cursor c2 = mydatabase.query("Local", column, "name=?", selectArg, null, null, null, null);
        c2.moveToFirst();
        //System.out.println(c2.getCount());

        String raw_history = c2.getString(0);
        c2.close();
        String[] array_history = raw_history.split(";");
        for(int i=0 ; i<array_history.length ; i++){
            history.add(array_history[i]);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.fragment_local, container, false);

        mapView = (MapView) myFragmentView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(FragmentLocal.this);
        //mapView.setLayoutParams(new LinearLayout.LayoutParams(500,500));

        TextView textView = (TextView) myFragmentView.findViewById(R.id.local_name);
        textView.setText("Local : " + local.getName());

        ListView lv = myFragmentView.findViewById(R.id.local_history);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, history);
        lv.setAdapter(arrayAdapter);
        return myFragmentView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map = googleMap;
        map.setMinZoomPreference(12);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(40.7143528, -74.0059731)));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
