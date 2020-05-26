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
    private String local;
    private int state;

    public Alerte() {
        this.date = new Date();
    }

    public Alerte(Cursor c) {
        if (c.getCount() > 0) {
            this.id = c.getInt(0);
            this.local = c.getString(1);
            this.state = c.getInt(2);
            String date_s = c.getString(3);
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

    public String getLocal() {
        return local;
    }

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("EEE dd/MM/yyyy 'à' HH:mm:ss");
        return "Alerte " + id + " à " + local + " : " + format.format(date);
    }

    public int changeState() {
        if(state == 1){
            return 0;
        }else{
            return 1;
        }
    }
}
