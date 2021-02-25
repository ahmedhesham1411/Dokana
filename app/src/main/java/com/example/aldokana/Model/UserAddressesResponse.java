package com.example.aldokana.Model;

public class UserAddressesResponse {
    UserAddressesModel data;

    public UserAddressesResponse(UserAddressesModel data) {
        this.data = data;
    }

    public UserAddressesModel getData() {
        return data;
    }

    public void setData(UserAddressesModel data) {
        this.data = data;
    }
}
