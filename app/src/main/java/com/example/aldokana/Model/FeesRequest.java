package com.example.aldokana.Model;

public class FeesRequest {
    int address_id;

    public FeesRequest(int address_id) {
        this.address_id = address_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
