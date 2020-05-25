package com.example.secureapp;

import android.app.Application;

public class MyApplication extends Application {
    User currentUser = null;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}