package com.example.aldokana.Model;

public class BasketItemsDataModel {
    int id;
    int product_id;
    String product;
    String count;
    String total_weight;
    String total_cost;

    public BasketItemsDataModel(int id,int product_id, String product, String count, String total_weight, String total_cost) {
        this.id = id;
        this.product_id=product_id;
        this.product = product;
        this.count = count;
        this.total_weight = total_weight;
        this.total_cost = total_cost;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(String total_weight) {
        this.total_weight = total_weight;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }
}
