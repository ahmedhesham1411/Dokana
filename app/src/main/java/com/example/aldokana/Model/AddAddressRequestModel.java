package com.example.aldokana.Model;

public class AddAddressRequestModel {
    String first_name;
    String last_name;
    String address;
    String lat;
    String lng;
    String region_id;
    String zip_code;
    String phone;
    String city_id;

    public AddAddressRequestModel(String first_name, String last_name, String address, String lat, String lng, String region_id, String zip_code, String phone, String city_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.region_id = region_id;
        this.zip_code = zip_code;
        this.phone = phone;
        this.city_id = city_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
