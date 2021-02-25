package com.example.aldokana.Model;

public class BasketResponse {
    BasketItemList data;

    public BasketResponse(BasketItemList data) {
        this.data = data;
    }

    public BasketItemList getData() {
        return data;
    }

    public void setData(BasketItemList data) {
        this.data = data;
    }
}
