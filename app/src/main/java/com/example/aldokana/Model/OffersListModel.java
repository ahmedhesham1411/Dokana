package com.example.aldokana.Model;

import java.util.List;

public class OffersListModel {
    List<OffersDataModel> companies;

    public OffersListModel(List<OffersDataModel> companies) {
        this.companies = companies;
    }

    public List<OffersDataModel> getCompanies() {
        return companies;
    }

    public void setCompanies(List<OffersDataModel> companies) {
        this.companies = companies;
    }
}
