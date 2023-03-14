package com.keccoredump.demo.dto;

import org.springframework.web.multipart.MultipartFile;

public class CreateAnswerDto {

    private String answer;
    private int questionID;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
