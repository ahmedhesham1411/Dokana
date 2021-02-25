package com.example.aldokana.Model;

public class OrdersDataModel {
    int id;
    String code;
    double order_cost;
    double total_discount;
    double total_shipping_fees;
    double total_cost;
    double total_weight;
    String order_creation_date;
    String status;
    int order_items_count;

    public OrdersDataModel(int id, String code, double order_cost, double total_discount, double total_shipping_fees, double total_cost, double total_weight, String order_creation_date, String status, int order_items_count) {
        this.id = id;
        this.code = code;
        this.order_cost = order_cost;
        this.total_discount = total_discount;
        this.total_shipping_fees = total_shipping_fees;
        this.total_cost = total_cost;
        this.total_weight = total_weight;
        this.order_creation_date = order_creation_date;
        this.status = status;
        this.order_items_count = order_items_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getOrder_cost() {
        return order_cost;
    }

    public void setOrder_cost(double order_cost) {
        this.order_cost = order_cost;
    }

    public double getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(double total_discount) {
        this.total_discount = total_discount;
    }

    public double getTotal_shipping_fees() {
        return total_shipping_fees;
    }

    public void setTotal_shipping_fees(double total_shipping_fees) {
        this.total_shipping_fees = total_shipping_fees;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public double getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(double total_weight) {
        this.total_weight = total_weight;
    }

    public String getOrder_creation_date() {
        return order_creation_date;
    }

    public void setOrder_creation_date(String order_creation_date) {
        this.order_creation_date = order_creation_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrder_items_count() {
        return order_items_count;
    }

    public void setOrder_items_count(int order_items_count) {
        this.order_items_count = order_items_count;
    }
}
