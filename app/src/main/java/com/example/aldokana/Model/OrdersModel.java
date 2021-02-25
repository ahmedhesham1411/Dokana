package com.example.aldokana.Model;

public class OrdersModel {
    String items;
    int status;
    String orderNumber;
    String cost;
    String dte;

    public OrdersModel(String items, int status, String orderNumber, String cost, String dte) {
        this.items = items;
        this.status = status;
        this.orderNumber = orderNumber;
        this.cost = cost;
        this.dte = dte;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDte() {
        return dte;
    }

    public void setDte(String dte) {
        this.dte = dte;
    }
}
