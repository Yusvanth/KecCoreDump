package com.keccoredump.demo.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Reporter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "reporting_user_id")
    private int reportingUserId;

    @Column(name = "reported_user_id")
    private int reportedUserId;

    @Column(name = "message")
    private String message;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    public Reporter(){

    }

    public Reporter(String email, int reportingUserId, int reportedUserId, String message, Date date) {
        this.email = email;
        this.reportingUserId = reportingUserId;
        this.reportedUserId = reportedUserId;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReportingUserId() {
        return reportingUserId;
    }

    public void setReportingUserId(int reportingUserId) {
        this.reportingUserId = reportingUserId;
    }

    public int getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(int reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
