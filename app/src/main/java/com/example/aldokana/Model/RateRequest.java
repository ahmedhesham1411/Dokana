package com.example.aldokana.Model;

public class RateRequest {
    int product_id;
    float rate;

    public RateRequest(int product_id, float rate) {
        this.product_id = product_id;
        this.rate = rate;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
