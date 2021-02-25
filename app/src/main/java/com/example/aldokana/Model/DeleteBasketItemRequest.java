package com.example.aldokana.Model;

public class DeleteBasketItemRequest {
    int item_id;

    public DeleteBasketItemRequest(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}
