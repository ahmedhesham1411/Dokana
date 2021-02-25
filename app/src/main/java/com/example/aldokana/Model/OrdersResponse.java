package com.example.aldokana.Model;

public class OrdersResponse {
    OrdersList data;

    public OrdersResponse(OrdersList data) {
        this.data = data;
    }

    public OrdersList getData() {
        return data;
    }

    public void setData(OrdersList data) {
        this.data = data;
    }
}
