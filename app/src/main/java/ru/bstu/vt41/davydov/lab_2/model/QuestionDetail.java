package ru.bstu.vt41.davydov.lab_2.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbook on 06.04.2018.
 */

public class QuestionDetail extends Question {
    private List<Answer> answers = new ArrayList<>();
    private String userName;
    private LocalDateTime publicationDate;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
