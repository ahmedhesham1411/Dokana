package com.example.aldokana.Model;

public class SaveOrderRequest {
    int user_address_id;
    String coupon_name;

    public SaveOrderRequest(int user_address_id, String coupon_name) {
        this.user_address_id = user_address_id;
        this.coupon_name = coupon_name;
    }

    public int getUser_address_id() {
        return user_address_id;
    }

    public void setUser_address_id(int user_address_id) {
        this.user_address_id = user_address_id;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }
}
