package com.example.aldokana.Model;

import java.util.List;

public class ProductsDataModel {
    int id;
    String serial_number;
    String name;
    double price;
    String color;
    String storage;
    String company;
    int stock;
    int type;
    int discount;
    float rate;
    List<ImageDataModel> images;
    List<DescriptionDataModel> description;
    List<SpecificationsDataModel> specifications;
    List<ColorsModel> available_colors;
    List<ProductDetailsStorageModel> available_storages;
    int counter ;

    public List<ColorsModel> getAvailable_colors() {
        return available_colors;
    }

    public void setAvailable_colors(List<ColorsModel> available_colors) {
        this.available_colors = available_colors;
    }

    public List<ProductDetailsStorageModel> getAvailable_storages() {
        return available_storages;
    }

    public void setAvailable_storages(List<ProductDetailsStorageModel> available_storages) {
        this.available_storages = available_storages;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public List<ImageDataModel> getImages() {
        return images;
    }

    public void setImages(List<ImageDataModel> images) {
        this.images = images;
    }

    public List<DescriptionDataModel> getDescription() {
        return description;
    }

    public void setDescription(List<DescriptionDataModel> description) {
        this.description = description;
    }

    public List<SpecificationsDataModel> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<SpecificationsDataModel> specifications) {
        this.specifications = specifications;
    }
}
