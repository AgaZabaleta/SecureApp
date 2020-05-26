package com.example.secureapp;

import android.app.Application;
/*
MyApplication app = (MyApplication) getActivity().getApplication();
User user = app.getCurrentUser();
*/
public class MyApplication extends Application {
    User currentUser = null;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}