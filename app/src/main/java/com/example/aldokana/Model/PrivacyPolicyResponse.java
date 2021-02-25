package com.example.aldokana.Model;

public class PrivacyPolicyResponse {
    PrivacyPolicyListModel data;

    public PrivacyPolicyResponse(PrivacyPolicyListModel data) {
        this.data = data;
    }

    public PrivacyPolicyListModel getData() {
        return data;
    }

    public void setData(PrivacyPolicyListModel data) {
        this.data = data;
    }
}
