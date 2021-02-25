package com.example.aldokana.Model;

import java.util.List;

public class SpecificationsDataModel {
    int id;
    int product_id;
    String title;
    List<SubSpecificationsDataModel> sub_specifications;

    public SpecificationsDataModel(int id, int product_id, String title, List<SubSpecificationsDataModel> sub_specifications) {
        this.id = id;
        this.product_id = product_id;
        this.title = title;
        this.sub_specifications = sub_specifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubSpecificationsDataModel> getSub_specifications() {
        return sub_specifications;
    }

    public void setSub_specifications(List<SubSpecificationsDataModel> sub_specifications) {
        this.sub_specifications = sub_specifications;
    }
}
