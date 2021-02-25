package com.example.aldokana.Model;

public class ProductsResponse {
    ProductsListModel data;

    public ProductsResponse(ProductsListModel data) {
        this.data = data;
    }

    public ProductsListModel getData() {
        return data;
    }

    public void setData(ProductsListModel data) {
        this.data = data;
    }
}
