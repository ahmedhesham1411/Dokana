package com.example.aldokana.Model;

public class NotificationResponse {
    NotificationList data;

    public NotificationResponse(NotificationList data) {
        this.data = data;
    }

    public NotificationList getData() {
        return data;
    }

    public void setData(NotificationList data) {
        this.data = data;
    }
}
