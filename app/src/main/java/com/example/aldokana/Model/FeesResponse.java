package com.example.aldokana.Model;

public class FeesResponse {
    FeesDataModel data;

    public FeesResponse(FeesDataModel data) {
        this.data = data;
    }

    public FeesDataModel getData() {
        return data;
    }

    public void setData(FeesDataModel data) {
        this.data = data;
    }
}
