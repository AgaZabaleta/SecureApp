package com.example.secureapp;

import java.util.ArrayList;

public class Local {
    private String name;
    private ArrayList<String> historique;
    private double latitude;
    private double longitude;

    public Local(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.historique = new ArrayList<String>();
    }


    public String getName() {
        return name;
    }
    public ArrayList<String> getHistorique(){
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
}
