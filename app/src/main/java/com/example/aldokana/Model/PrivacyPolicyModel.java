package com.example.aldokana.Model;

public class PrivacyPolicyModel {
    int id;
    String privacy;

    public PrivacyPolicyModel(int id, String privacy) {
        this.id = id;
        this.privacy = privacy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
}
