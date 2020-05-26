package com.example.secureapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentOptions extends Fragment {
    private View myFragmentView;
    private Button changeLoginButton;
    private Button changeAlertTypeButton;
    private Button addUserButton;

    public FragmentOptions() {
        // Required empty public constructor
    }

    public static FragmentOptions newInstance() {
        FragmentOptions fragment = new FragmentOptions();
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
        myFragmentView =  inflater.inflate(R.layout.fragment_options, container, false);

        changeLoginButton = (Button) myFragmentView.findViewById(R.id.change_login);
        changeLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.nav_options_change_login, new Bundle());
            }
        });

        changeAlertTypeButton = (Button) myFragmentView.findViewById(R.id.change_alert_type);
        changeAlertTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.nav_options_change_alert, new Bundle());
            }
        });

        addUserButton = (Button) myFragmentView.findViewById(R.id.add_user);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.nav_options_add_user, new Bundle());
            }
        });

        return myFragmentView;
    }
}
