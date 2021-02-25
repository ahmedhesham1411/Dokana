package com.example.aldokana.Model;

import java.util.List;

public class OrdersList {
    List<OrdersDataModel> orders;

    public OrdersList(List<OrdersDataModel> orders) {
        this.orders = orders;
    }

    public List<OrdersDataModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersDataModel> orders) {
        this.orders = orders;
    }
}
