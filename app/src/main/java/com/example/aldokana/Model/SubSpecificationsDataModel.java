package com.example.aldokana.Model;

public class SubSpecificationsDataModel {
    int id;
    int product_specification_id;
    String key;
    String value;

    public SubSpecificationsDataModel(int id, int product_specification_id, String key, String value) {
        this.id = id;
        this.product_specification_id = product_specification_id;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_specification_id() {
        return product_specification_id;
    }

    public void setProduct_specification_id(int product_specification_id) {
        this.product_specification_id = product_specification_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
