package com.example.aldokana.Model;

import java.util.List;

public class AboutAppListModel {
    List<AboutAppModel> about;

    public AboutAppListModel(List<AboutAppModel> about) {
        this.about = about;
    }

    public List<AboutAppModel> getAbout() {
        return about;
    }

    public void setAbout(List<AboutAppModel> about) {
        this.about = about;
    }
}
