package com.example.aldokana.Model;

public class HomeItemModel {
    String title;
    String disc;
    String price;
    Boolean sale;
    String price_before_sale;
    int counter;

    public HomeItemModel(String title, String disc, String price, Boolean sale, String price_before_sale, int counter) {
        this.title = title;
        this.disc = disc;
        this.price = price;
        this.sale = sale;
        this.price_before_sale = price_before_sale;
        this.counter = counter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getSale() {
        return sale;
    }

    public void setSale(Boolean sale) {
        this.sale = sale;
    }

    public String getPrice_before_sale() {
        return price_before_sale;
    }

    public void setPrice_before_sale(String price_before_sale) {
        this.price_before_sale = price_before_sale;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
