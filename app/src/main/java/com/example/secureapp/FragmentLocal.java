package com.example.secureapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLocal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLocal extends Fragment {
    MapView mapView;
    GoogleMap map;

    public FragmentLocal() {
        // Required empty public constructor
    }


    public static FragmentLocal newInstance(String param1, String param2) {
        FragmentLocal fragment = new FragmentLocal();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local, container, false);
    }
}
