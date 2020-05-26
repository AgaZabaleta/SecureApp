package com.example.secureapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDeconnect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDeconnect extends Fragment {
    private View myFragmentView;

    public FragmentDeconnect() {
        // Required empty public constructor
    }

    public static FragmentDeconnect newInstance() {
        FragmentDeconnect fragment = new FragmentDeconnect();
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
        myFragmentView = inflater.inflate(R.layout.fragment_deconnect, container, false);

        MyApplication app = (MyApplication) getActivity().getApplication();
        app.setCurrentUser(null);

        Intent intent = new Intent(getActivity(),Connection.class);
        startActivity(intent);

        return myFragmentView;
    }
}
