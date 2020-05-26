package com.example.secureapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentOptionsChangeAlert extends Fragment {
    public FragmentOptionsChangeAlert() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentOptionsChangeAlert newInstance() {
        FragmentOptionsChangeAlert fragment = new FragmentOptionsChangeAlert();
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
        return inflater.inflate(R.layout.fragment_options_change_alert, container, false);
    }
}
