package com.example.aldokana.Model;

public class ProductDetailsStorageModel {
    int id;
    String product_serial_number;
    int storage;
    Boolean selected;

    public ProductDetailsStorageModel(int id, int storage, Boolean selected, String product_serial_number) {
        this.id = id;
        this.storage = storage;
        this.selected = selected;
        this.product_serial_number = product_serial_number;
    }

    public String getProduct_serial_number() {
        return product_serial_number;
    }

    public void setProduct_serial_number(String product_serial_number) {
        this.product_serial_number = product_serial_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
