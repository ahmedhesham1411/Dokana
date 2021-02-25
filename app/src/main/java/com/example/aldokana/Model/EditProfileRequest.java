package com.example.aldokana.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;

public class EditProfileRequest {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("photo")
    @Expose
    MultipartBody.Part photo;

    public EditProfileRequest(String name, String phone, String email, MultipartBody.Part photo) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photo = photo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartBody.Part getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartBody.Part photo) {
        this.photo = photo;
    }
}
