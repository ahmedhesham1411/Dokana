package com.example.aldokana.Model;

public class RegisterationResponse {
    boolean status;
    UserModel user;

    public RegisterationResponse(boolean status, UserModel user) {
        this.status = status;
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}