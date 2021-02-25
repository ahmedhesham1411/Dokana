package com.example.aldokana.Model;

import java.util.List;

public class PrivacyPolicyListModel {
    List<PrivacyPolicyModel> privacy;

    public PrivacyPolicyListModel(List<PrivacyPolicyModel> privacy) {
        this.privacy = privacy;
    }

    public List<PrivacyPolicyModel> getPrivacy() {
        return privacy;
    }

    public void setPrivacy(List<PrivacyPolicyModel> privacy) {
        this.privacy = privacy;
    }
}
