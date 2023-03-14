package com.keccoredump.demo.dto;

public class UserProfileDto {


    private int id;
    private String name;

    private String email;

    private int year;

    private int no_of_qns_answered;

    private int no_of_qns_posted;

    private String rollNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNo_of_qns_answered() {
        return no_of_qns_answered;
    }

    public void setNo_of_qns_answered(int no_of_qns_answered) {
        this.no_of_qns_answered = no_of_qns_answered;
    }

    public int getNo_of_qns_posted() {
        return no_of_qns_posted;
    }

    public void setNo_of_qns_posted(int no_of_qns_posted) {
        this.no_of_qns_posted = no_of_qns_posted;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
