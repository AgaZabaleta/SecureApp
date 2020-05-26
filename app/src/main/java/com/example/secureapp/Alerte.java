package com.example.secureapp;

import android.annotation.SuppressLint;
import android.database.Cursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Alerte {
    private int id;
    private Date date;

    public Alerte() {
        this.date = new Date();
    }

    public Alerte(Cursor c) {
        if (c.getCount() > 0) {
            this.id = c.getInt(0);
            String date_s = c.getString(1);
            SimpleDateFormat format = new SimpleDateFormat("EEE dd/MM/yyyy 'à' HH:mm:ss");
            try {
                this.date = format.parse(date_s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Date getDate() {
        return date;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("EEE dd/MM/yyyy 'à' HH:mm:ss");
        return "Alerte " + id + " : " + format.format(date);
    }
}
