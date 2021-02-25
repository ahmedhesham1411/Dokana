package com.example.aldokana.Model;

import java.util.List;

public class TermsListModel {
    List<TermsModel> terms;

    public TermsListModel(List<TermsModel> terms) {
        this.terms = terms;
    }

    public List<TermsModel> getTerms() {
        return terms;
    }

    public void setTerms(List<TermsModel> terms) {
        this.terms = terms;
    }
}
