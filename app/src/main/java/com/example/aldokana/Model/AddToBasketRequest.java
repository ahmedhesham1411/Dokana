package com.example.aldokana.Model;

public class AddToBasketRequest {
    int product_id;
    int count;

    public AddToBasketRequest(int product_id, int count) {
        this.product_id = product_id;
        this.count = count;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
