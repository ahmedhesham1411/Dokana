package com.example.aldokana.Model;

public class BasketItemDataModel {
    int id;
    int product_id;
    String product;
    String image;
    String description;
    int count;
    int total_weight;
    double total_cost;

    public BasketItemDataModel(int id, int product_id, String product, String image, String description, int count, int total_weight, double total_cost) {
        this.id = id;
        this.product_id = product_id;
        this.product = product;
        this.image = image;
        this.description = description;
        this.count = count;
        this.total_weight = total_weight;
        this.total_cost = total_cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(int total_weight) {
        this.total_weight = total_weight;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }
}
