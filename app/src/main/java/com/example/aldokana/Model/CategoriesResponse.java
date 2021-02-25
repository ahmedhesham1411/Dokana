package com.example.aldokana.Model;

public class CategoriesResponse {
    CategoriesListModel data;

    public CategoriesResponse(CategoriesListModel data) {
        this.data = data;
    }

    public CategoriesListModel getData() {
        return data;
    }

    public void setData(CategoriesListModel data) {
        this.data = data;
    }
}
