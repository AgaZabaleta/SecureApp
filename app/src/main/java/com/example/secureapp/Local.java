package com.example.secureapp;

import android.annotation.SuppressLint;
import android.database.Cursor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Local {
    private String name;
    private String historique;
    private double latitude;
    private double longitude;

    public Local(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.historique = "";
    }

    public Local(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() > 0) {
            this.name = c.getString(1);
            this.historique = c.getString(2);
            this.latitude = c.getDouble(3);
            this.longitude = c.getDouble(4);

        }
        //On ferme le cursor
        c.close();
    }


    public String getName() {
        return name;
    }
    public String getHistorique(){
        return historique;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Local : " + getName();
    }

    public void addUserHistorique(User user){
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("EEE dd/MM/yyyy 'à' HH:mm:ss");
        historique += ";" + format.format(new Date()) + " || " + user.toString();
    }
}
