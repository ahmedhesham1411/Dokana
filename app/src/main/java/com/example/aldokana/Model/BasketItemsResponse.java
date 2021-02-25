package com.example.aldokana.Model;

public class BasketItemsResponse {
    BasketItemsList data;

    public BasketItemsResponse(BasketItemsList data) {
        this.data = data;
    }

    public BasketItemsList getData() {
        return data;
    }

    public void setData(BasketItemsList data) {
        this.data = data;
    }
}
