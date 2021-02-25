package com.example.aldokana.Model;

import java.util.List;

public class ProductsListModel {
    List<ProductsDataModel> products;

    public ProductsListModel(List<ProductsDataModel> products) {
        this.products = products;
    }

    public List<ProductsDataModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDataModel> products) {
        this.products = products;
    }
}
