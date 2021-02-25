package com.example.aldokana.Model;

public class CouponDataModel {
    int discount_percentage;

    public CouponDataModel(int discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public int getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(int discount_percentage) {
        this.discount_percentage = discount_percentage;
    }
}
