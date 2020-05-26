package com.example.secureapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Local {
    private String name;
    private String history;
    private double latitude;
    private double longitude;

    public Local(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.history = "History : ";
    }

    public Local(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() > 0) {
            this.name = c.getString(0);
            this.history = c.getString(1);
            this.latitude = c.getDouble(2);
            this.longitude = c.getDouble(3);
        }
    }


    public String getName() {
        return name;
    }
    public String getHistory(){
        return history;
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

    public void addUserHistorique(User user, SQLiteDatabase myDB){
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("EEE dd/MM/yyyy 'à' HH:mm:ss");
        history += ";Consulté le : " + format.format(new Date()) + "\nPar : " + user.toString();
        ContentValues cv = new ContentValues();
        cv.put("history", history);
        String[] arg = {this.getName()};
        myDB.update("Local", cv, "name=?", arg);
    }
}
