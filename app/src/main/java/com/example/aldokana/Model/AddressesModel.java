package com.example.aldokana.Model;

public class AddressesModel {
    String title;
    String name;
    String phone;

    public AddressesModel(String title, String name, String phone) {
        this.title = title;
        this.name = name;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
