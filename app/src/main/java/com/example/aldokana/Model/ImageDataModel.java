package com.example.aldokana.Model;

public class ImageDataModel {
    int id;
    String photo;
    int product_id;

    public ImageDataModel(int id, String photo, int product_id) {
        this.id = id;
        this.photo = photo;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
