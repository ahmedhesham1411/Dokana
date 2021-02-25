package com.example.aldokana.Model;

public class AboutAppResponse {
    AboutAppListModel data;

    public AboutAppResponse(AboutAppListModel data) {
        this.data = data;
    }

    public AboutAppListModel getData() {
        return data;
    }

    public void setData(AboutAppListModel data) {
        this.data = data;
    }
}
