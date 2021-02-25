package com.example.aldokana.Model;

import java.util.List;

public class UserAddressesModel {
    List<UserAddressModel> user_addresses;

    public UserAddressesModel(List<UserAddressModel> user_addresses) {
        this.user_addresses = user_addresses;
    }

    public List<UserAddressModel> getUser_addresses() {
        return user_addresses;
    }

    public void setUser_addresses(List<UserAddressModel> user_addresses) {
        this.user_addresses = user_addresses;
    }
}
