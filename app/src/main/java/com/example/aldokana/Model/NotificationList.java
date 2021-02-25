package com.example.aldokana.Model;

import java.util.List;

public class NotificationList {
    List<NotificationDataModel> notifications;

    public NotificationList(List<NotificationDataModel> notifications) {
        this.notifications = notifications;
    }

    public List<NotificationDataModel> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationDataModel> notifications) {
        this.notifications = notifications;
    }
}
