package com.example.secureapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AlerteAdapter extends ArrayAdapter<Alerte> {
    private ArrayList<Alerte> alertes;
    AlerteAdapter (Context context, int textViewResourceId, ArrayList<Alerte> objects) {
        super(context, textViewResourceId, objects);
        alertes = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.layout_alerte_listview, null);

        TextView textView = (TextView) v.findViewById(R.id.text_alerte);
        textView.setText(alertes.get(position).toString());

        ImageView imageView = (ImageView) v.findViewById(R.id.icon_alerte);

        if (alertes.get(position).getState() == 1) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        return v;
    }
}
