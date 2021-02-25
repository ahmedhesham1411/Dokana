package com.example.aldokana.Model;

public class TermsResponse {
    TermsListModel data;

    public TermsResponse(TermsListModel data) {
        this.data = data;
    }

    public TermsListModel getData() {
        return data;
    }

    public void setData(TermsListModel data) {
        this.data = data;
    }
}
