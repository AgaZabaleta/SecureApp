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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentOptionsChangeLogin extends Fragment {
    private View myFragmentView;
    private EditText firstnameEdit;
    private EditText lastnameEdit;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button saveButton;
    public FragmentOptionsChangeLogin() {
        // Required empty public constructor
    }

    public static FragmentOptionsChangeLogin newInstance() {
        FragmentOptionsChangeLogin fragment = new FragmentOptionsChangeLogin();
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
        myFragmentView = inflater.inflate(R.layout.fragment_options_change_login, container, false);

        MyApplication app = (MyApplication) getActivity().getApplication();
        User user = app.getCurrentUser();

        firstnameEdit = myFragmentView.findViewById(R.id.update_firstname);
        lastnameEdit = myFragmentView.findViewById(R.id.update_lastname);
        usernameEdit = myFragmentView.findViewById(R.id.update_username);
        passwordEdit = myFragmentView.findViewById(R.id.update_password);

        firstnameEdit.setText(user.getFirstName());
        lastnameEdit.setText(user.getLastName());
        usernameEdit.setText(user.getUsername());
        passwordEdit.setText(user.getPassword());

        saveButton = (Button) myFragmentView.findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase("database1",getActivity().MODE_PRIVATE,null);
                ContentValues cv = new ContentValues();
                cv.put("firstName", firstnameEdit.getText().toString());
                cv.put("lastName", lastnameEdit.getText().toString());
                cv.put("username", usernameEdit.getText().toString());
                cv.put("password", passwordEdit.getText().toString());
                MyApplication app = (MyApplication) getActivity().getApplication();
                String[] username = {app.getCurrentUser().getUsername()};
                mydatabase.update("User", cv, "username=?", username);
                Toast.makeText(getActivity(), "Utilisateur modifé", Toast.LENGTH_SHORT).show();

                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.nav_options, new Bundle());
            }
        });


        return myFragmentView;
    }
}
