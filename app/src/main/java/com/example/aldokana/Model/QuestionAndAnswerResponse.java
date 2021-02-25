package com.example.aldokana.Model;

public class QuestionAndAnswerResponse {
    QuestionsAndAnswersModel data;

    public QuestionAndAnswerResponse(QuestionsAndAnswersModel data) {
        this.data = data;
    }

    public QuestionsAndAnswersModel getData() {
        return data;
    }

    public void setData(QuestionsAndAnswersModel data) {
        this.data = data;
    }
}
