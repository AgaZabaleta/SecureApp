package com.example.secureapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAlerte#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAlerte extends Fragment  implements OnMapReadyCallback {

    private View myFragmentView;
    SquareMapView mapView;
    GoogleMap map;
    Alerte alerte;
    ArrayList<String> history;
    Button callButton;
    Button defenseButton;
    Button desactiveButton;

    public FragmentAlerte() {
        // Required empty public constructor
    }

    public static FragmentAlerte newInstance() {
        FragmentAlerte fragment = new FragmentAlerte();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity ctx = getActivity();

        String value = getArguments().getString("id");
        String id[] = {value};

        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("database1", ctx.MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Alerte", null, "id = ?", id, null, null, null, null);
        c.moveToFirst();
        alerte = new Alerte(c);
        c.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(R.layout.fragment_alerte, container, false);

        mapView = (SquareMapView) myFragmentView.findViewById(R.id.alerte_map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(FragmentAlerte.this);
        //mapView.setLayoutParams(new LinearLayout.LayoutParams(500,500));

        TextView alerte_state = (TextView) myFragmentView.findViewById(R.id.alerte_state);
        if(alerte.getState() == 1) {
            alerte_state.setVisibility(View.VISIBLE);
        } else {
            alerte_state.setVisibility(View.GONE);
        }

        TextView textView = (TextView) myFragmentView.findViewById(R.id.alerte_name);
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("EEE dd/MM/yyyy 'à' HH:mm:ss");
        textView.setText("Alerte " + alerte.getId() + " émise le : " + format.format(alerte.getDate()));


        callButton = (Button) myFragmentView.findViewById(R.id.call_responsable);
        if(alerte.getState() == 1){
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("database1", getActivity().MODE_PRIVATE,null);
                    Cursor c = mydatabase.query("Local", new String[] {"tel"}, "name = ?", new String[] {alerte.getLocal()}, null, null, null, null);
                    c.moveToFirst();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", c.getString(0), null));
                    startActivity(intent);
                }
            });
        }else{
            callButton.setVisibility(View.GONE);
        }


        defenseButton = (Button) myFragmentView.findViewById(R.id.trigger_defense);
        if(alerte.getState() == 1){
            defenseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Defense activée !", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            defenseButton.setVisibility(View.GONE);
        }


        desactiveButton = (Button) myFragmentView.findViewById(R.id.desactive_alarm);
        desactiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("database1",getActivity().MODE_PRIVATE,null);
                ContentValues cv = new ContentValues();
                cv.put("state", alerte.changeState());
                String[] arg = {Integer.toString(alerte.getId())};
                mydatabase.update("Alerte", cv, "id=?", arg);
                NavController navController = Navigation.findNavController(getView());
                Bundle args = new Bundle();
                args.putString("id", Integer.toString(alerte.getId()));
                navController.navigate(R.id.nav_alerte, args);
            }
        });


        return myFragmentView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map = googleMap;
        map.setMinZoomPreference(7);
        SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("database1", getActivity().MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Local", new String[] {"lat", "lon"}, "name = ?", new String[] {alerte.getLocal()}, null, null, null, null);
        c.moveToFirst();
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(c.getDouble(0), c.getDouble(1))));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
