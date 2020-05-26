package com.example.secureapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FragmentOptionsAddUser extends Fragment {
    private View myFragmentView;
    private EditText firstnameEdit;
    private EditText lastnameEdit;
    private Spinner statusSpinner;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button saveButton;

    public FragmentOptionsAddUser() {
        // Required empty public constructor
    }

    public static FragmentOptionsAddUser newInstance() {
        FragmentOptionsAddUser fragment = new FragmentOptionsAddUser();
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
        myFragmentView =  inflater.inflate(R.layout.fragment_options_add_user, container, false);

        statusSpinner = myFragmentView.findViewById(R.id.add_status);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        saveButton = (Button) myFragmentView.findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("database1",getActivity().MODE_PRIVATE,null);

                firstnameEdit = myFragmentView.findViewById(R.id.add_firstname);
                lastnameEdit = myFragmentView.findViewById(R.id.add_lastname);
                statusSpinner = myFragmentView.findViewById(R.id.add_status);
                usernameEdit = myFragmentView.findViewById(R.id.add_username);
                passwordEdit = myFragmentView.findViewById(R.id.add_password);

                mydatabase.execSQL("INSERT INTO User VALUES('" + firstnameEdit.getText().toString() + "', '" + lastnameEdit.getText().toString() + "', '" + statusSpinner.getSelectedItem().toString() + "', '" + usernameEdit.getText().toString() + "', '" + passwordEdit.getText().toString() +"');");
                Toast.makeText(getActivity(), "Utilisateur ajouté", Toast.LENGTH_SHORT).show();

                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.nav_options, new Bundle());
            }
        });
        return myFragmentView;
    }
}
