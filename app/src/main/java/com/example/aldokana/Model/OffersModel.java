package com.example.aldokana.Model;

public class OffersModel {
    int image;
    String title;
    String disc;

    public OffersModel(int image, String title, String disc) {
        this.image = image;
        this.title = title;
        this.disc = disc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
}
