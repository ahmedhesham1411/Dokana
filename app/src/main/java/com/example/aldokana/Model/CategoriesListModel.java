package com.example.aldokana.Model;

import java.util.List;

public class CategoriesListModel {
    List<CategoriesDataModel> categories;

    public CategoriesListModel(List<CategoriesDataModel> categories) {
        this.categories = categories;
    }

    public List<CategoriesDataModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesDataModel> categories) {
        this.categories = categories;
    }
}
