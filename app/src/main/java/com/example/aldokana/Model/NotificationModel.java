package com.example.aldokana.Model;

public class NotificationModel {
    String title;
    String desc;
    int status;

    public NotificationModel(String title, String desc, int status) {
        this.title = title;
        this.desc = desc;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
