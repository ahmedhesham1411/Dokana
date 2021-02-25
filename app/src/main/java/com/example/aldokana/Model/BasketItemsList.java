package com.example.aldokana.Model;

import java.util.List;

public class BasketItemsList {
    List<BasketItemsDataModel> basket_items;

    public BasketItemsList(List<BasketItemsDataModel> basket_items) {
        this.basket_items = basket_items;
    }

    public List<BasketItemsDataModel> getBasket_items() {
        return basket_items;
    }

    public void setBasket_items(List<BasketItemsDataModel> basket_items) {
        this.basket_items = basket_items;
    }
}
