package com.example.aldokana.Model;

public class SubDescriptionDataModel {
    int id;
    int product_description_id;
    String title;
    String description;

    public SubDescriptionDataModel(int id, int product_description_id, String title, String description) {
        this.id = id;
        this.product_description_id = product_description_id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_description_id() {
        return product_description_id;
    }

    public void setProduct_description_id(int product_description_id) {
        this.product_description_id = product_description_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
