package com.example.secureapp;

class User {
    String lastName;
    String firstName;
    String status; // admin ou utilisateur

    public User(String lastName, String firstName, String status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.status = status;
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

    public String toString(){
        return status + " " + firstName + " " + lastName;
    }
}
