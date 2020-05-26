package com.example.secureapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListeAlertes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListeAlertes extends Fragment {

    private ArrayList<Alerte> alertes;
    private View myFragmentView;

    public FragmentListeAlertes() {
        // Required empty public constructor
    }

    public static FragmentListeAlertes newInstance(String param1, String param2) {
        FragmentListeAlertes fragment = new FragmentListeAlertes();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity ctx = getActivity();

        alertes = new ArrayList<Alerte>();
        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("database1", ctx.MODE_PRIVATE,null);
        Cursor c = mydatabase.query("Alerte", null, null, null, null, null, null, null);
        c.moveToFirst();
        do{
            alertes.add(new Alerte(c));
        }while (c.moveToNext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_liste_alertes, container, false);

        ListView lv = myFragmentView.findViewById(R.id.listAlerte);

        final AlerteAdapter arrayAdapter = new AlerteAdapter(getActivity(), android.R.layout.simple_list_item_1, alertes);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavController navController = Navigation.findNavController(getView());
                Alerte item = arrayAdapter.getItem(position);
                Bundle args = new Bundle();
                args.putString("id", Integer.toString(item.getId()));
                navController.navigate(R.id.nav_alerte, args);
            }
        });

        return myFragmentView;
    }
}
