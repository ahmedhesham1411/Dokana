package com.example.aldokana.Model;

public class User {
    UserDataModel user;

    public User(UserDataModel user) {
        this.user = user;
    }

    public UserDataModel getUser() {
        return user;
    }

    public void setUser(UserDataModel user) {
        this.user = user;
    }
}
