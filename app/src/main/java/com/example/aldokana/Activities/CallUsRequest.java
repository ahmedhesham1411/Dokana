package com.example.aldokana.Activities;

public class CallUsRequest {
    String name;
    String phone;
    String cause;
    String details;

    public CallUsRequest(String name, String phone, String cause, String details) {
        this.name = name;
        this.phone = phone;
        this.cause = cause;
        this.details = details;
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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
