package com.keccoredump.demo.dto;

import java.util.List;

import com.keccoredump.demo.entity.Questions;

public class UserResponce {

	private String name;
	
	private String email;
	
	private int id;
	
	private String rollNo;
	
	private int year;
	
	private int no_of_qns_answered;
	
	private int no_of_qns_posted;
	
	private List<Questions> questions;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
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

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	
	

}
