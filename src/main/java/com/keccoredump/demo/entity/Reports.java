package com.keccoredump.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "reports")
    private int reports;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "banned_date")
    private Date bannedDate;

    public Reports(){

    }

    public Reports(String email, int reports) {
        this.email = email;
        this.reports = reports;
        this.isBlocked=false;
        this.bannedDate=null;
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

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Date getBannedDate() {
        return bannedDate;
    }

    public void setBannedDate(Date bannedDate) {
        this.bannedDate = bannedDate;
    }
}
