package com.example.aldokana.Model;

import java.util.List;

public class BasketItemList {
    List<BasketItemDataModel> basket_items;

    public BasketItemList(List<BasketItemDataModel> basket_items) {
        this.basket_items = basket_items;
    }

    public List<BasketItemDataModel> getBasket_items() {
        return basket_items;
    }

    public void setBasket_items(List<BasketItemDataModel> basket_items) {
        this.basket_items = basket_items;
    }
}
