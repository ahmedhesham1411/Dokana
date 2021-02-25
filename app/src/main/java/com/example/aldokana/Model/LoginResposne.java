package com.example.aldokana.Model;

public class LoginResposne {
    boolean status;
    Dataaa data;

    public LoginResposne(boolean status, Dataaa data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Dataaa getData() {
        return data;
    }

    public void setData(Dataaa data) {
        this.data = data;
    }
}

