package com.example.aldokana.Model;

import java.util.List;

public class DescriptionDataModel {
    int id;
    int product_id;
    String main_description;
    List<SubDescriptionDataModel> sub_description;

    public DescriptionDataModel(int id, int product_id, String main_description, List<SubDescriptionDataModel> sub_description) {
        this.id = id;
        this.product_id = product_id;
        this.main_description = main_description;
        this.sub_description = sub_description;
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

    public String getMain_description() {
        return main_description;
    }

    public void setMain_description(String main_description) {
        this.main_description = main_description;
    }

    public List<SubDescriptionDataModel> getSub_description() {
        return sub_description;
    }

    public void setSub_description(List<SubDescriptionDataModel> sub_description) {
        this.sub_description = sub_description;
    }
}
