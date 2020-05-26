package com.example.secureapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListeLocaux#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListeLocaux extends Fragment implements OnMapReadyCallback {
    private ArrayList<Local> locaux;
    private View myFragmentView;
    SquareMapView mapView;
    GoogleMap map;
    User user;
    Button addLocal;

    public FragmentListeLocaux() {
        // Required empty public constructor
    }

    public static FragmentListeLocaux newInstance() {
        FragmentListeLocaux fragment = new FragmentListeLocaux();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity ctx = getActivity();

        locaux = new ArrayList<Local>();
        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("database1", ctx.MODE_PRIVATE, null);
        Cursor c = mydatabase.query("Local", null, null, null, null, null, null, null);
        c.moveToFirst();

        do {
            locaux.add(new Local(c));
        } while (c.moveToNext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_liste_locaux, container, false);

        ListView lv = myFragmentView.findViewById(R.id.listLocaux);
        final ArrayAdapter<Local> arrayAdapter = new ArrayAdapter<Local>(getActivity(), android.R.layout.simple_list_item_1, locaux);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavController navController = Navigation.findNavController(getView());
                Local item = arrayAdapter.getItem(position);
                Bundle args = new Bundle();
                args.putString("local", item.getName());
                navController.navigate(R.id.nav_local, args);
            }
        });

        MyApplication app = (MyApplication) getActivity().getApplication();
        user = app.getCurrentUser();

        mapView = (SquareMapView) myFragmentView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(FragmentListeLocaux.this);

        addLocal = (Button) myFragmentView.findViewById(R.id.add_local);

        if(user.isAdmin()){
            addLocal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(getView());
                    navController.navigate(R.id.nav_add_local, new Bundle());
                }
            });
        }else{
            addLocal.setVisibility(View.GONE);
        }

        return myFragmentView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMinZoomPreference(10);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        for (Local local : locaux) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(local.getLatitude(), local.getLongitude()))
                    .title(local.getName()));
        }

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            Criteria crit = new Criteria();
            String provider = locationManager.getBestProvider(crit, true);

            assert locationManager != null;
            Location localisation = locationManager.getLastKnownLocation(provider);

            if (localisation != null){
                map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(localisation.getLatitude(), localisation.getLongitude())));
            }else{
                map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(locaux.get(0).getLatitude(), locaux.get(0).getLongitude())));
            }
        }else{
            map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(locaux.get(0).getLatitude(), locaux.get(0).getLongitude())));
        }
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
