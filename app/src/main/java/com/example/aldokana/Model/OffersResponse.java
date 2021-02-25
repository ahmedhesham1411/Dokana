package com.example.aldokana.Model;

public class OffersResponse {
    OffersListModel data;

    public OffersResponse(OffersListModel data) {
        this.data = data;
    }

    public OffersListModel getData() {
        return data;
    }

    public void setData(OffersListModel data) {
        this.data = data;
    }
}
