package com.example.aldokana.Model;

public class AboutAppModel {
    int id;
    String about;

    public AboutAppModel(int id, String about) {
        this.id = id;
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
