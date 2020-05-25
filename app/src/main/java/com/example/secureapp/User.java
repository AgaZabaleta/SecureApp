package com.example.secureapp;

import android.database.Cursor;

class User {
    String lastName;
    String firstName;
    String status; // admin ou utilisateur
    String username;
    String password;

    public User(String lastName, String firstName, String status, String username, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.status = status;
        this.username = username;
        this.password = password;
    }

    public User(Cursor c) {
        this.lastName = c.getString(1);
        this.firstName = c.getString(2);
        this.status = c.getString(3);
        this.username = c.getString(4);
        this.password = c.getString(5);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString(){
        return status + " " + firstName + " " + lastName;
    }
}
