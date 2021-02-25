package com.example.aldokana.Model;

import java.util.List;

public class RegionsModel {
    int id;
    String name;
    List<CityModel> cities;

    public RegionsModel(int id, String name, List<CityModel> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityModel> getCities() {
        return cities;
    }

    public void setCities(List<CityModel> cities) {
        this.cities = cities;
    }
}
