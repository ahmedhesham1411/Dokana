package com.example.aldokana.Model;

public class TermsModel {
    int id;
    String terms;

    public TermsModel(int id, String terms) {
        this.id = id;
        this.terms = terms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}
