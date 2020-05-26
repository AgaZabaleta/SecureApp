package com.example.secureapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListeLocaux#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListeLocaux extends Fragment {

    private ArrayList<Local> locaux;
    private View myFragmentView;

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
        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("database1", ctx.MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Local", null, null, null, null, null, null, null);
        c.moveToFirst();
        do{
            locaux.add(new Local(c));
        }while (c.moveToNext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_liste_locaux, container, false);

        ListView lv = myFragmentView.findViewById(R.id.listLocaux);
        final ArrayAdapter<Local> arrayAdapter = new ArrayAdapter<Local>(getActivity(), android.R.layout.simple_list_item_1, locaux);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.nav_local, new Bundle());
                Local item = arrayAdapter.getItem(position);
            }
        });

        return myFragmentView;
    }
}
