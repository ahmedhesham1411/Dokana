package com.example.aldokana.Model;

import java.util.List;

public class QuestionsAndAnswersModel {
    List<QuestionAndAnswerModel> FAQ;

    public QuestionsAndAnswersModel(List<QuestionAndAnswerModel> FAQ) {
        this.FAQ = FAQ;
    }

    public List<QuestionAndAnswerModel> getFAQ() {
        return FAQ;
    }

    public void setFAQ(List<QuestionAndAnswerModel> FAQ) {
        this.FAQ = FAQ;
    }
}
