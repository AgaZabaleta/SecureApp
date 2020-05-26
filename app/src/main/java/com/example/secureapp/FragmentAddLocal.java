package com.example.secureapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAddLocal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddLocal extends Fragment {
    private View myFragmentView;
    private EditText nameEdit;
    private EditText addressEdit;
    private EditText telEdit;
    private Button saveButton;

    public FragmentAddLocal() {
        // Required empty public constructor
    }

    public static FragmentAddLocal newInstance() {
        FragmentAddLocal fragment = new FragmentAddLocal();
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
        myFragmentView =  inflater.inflate(R.layout.fragment_add_local, container, false);

        saveButton = (Button) myFragmentView.findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("database1",getActivity().MODE_PRIVATE,null);

                nameEdit = myFragmentView.findViewById(R.id.add_name);
                addressEdit = myFragmentView.findViewById(R.id.add_address);
                LatLng address = getLocationFromAddress(getActivity(), addressEdit.getText().toString());
                telEdit = myFragmentView.findViewById(R.id.add_tel);


                mydatabase.execSQL("INSERT INTO Local VALUES('" + nameEdit.getText().toString() + "', 'Historique : ', '" + address.latitude + "', '" + address.longitude +"', '" + telEdit.getText().toString() + "');");
                Toast.makeText(getActivity(), "Local ajouté", Toast.LENGTH_SHORT).show();

                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.nav_liste_locaux, new Bundle());
            }
        });
        return myFragmentView;
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return p1;
    }

}
