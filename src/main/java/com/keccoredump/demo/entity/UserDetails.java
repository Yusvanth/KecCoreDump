package com.keccoredump.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class UserDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "roll_no")
	private String rollNo;
	
	@Column(name = "name")
	private String name;

	@Column(name = "year")
	private int year;

	@Column(name = "no_of_qns_answered")
	private int no_of_qns_answered;
	
	@Column(name = "no_of_qns_posted")
	private int no_of_qns_posted;

	@Column(name = "is_deactivated")
	private boolean isDeactivated;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<Questions> questions;


	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<Answers> answers;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	public UserDetails() {
		
	}

	public UserDetails(String rollNo, String name, int no_of_qns_answered, int no_of_qns_posted, List<Questions> questions, List<Answers> answers,int year, User user) {
		this.rollNo = rollNo;
		this.name = name;
		this.no_of_qns_answered = no_of_qns_answered;
		this.no_of_qns_posted = no_of_qns_posted;
		this.questions = questions;
		this.answers = answers;
		this.user = user;
		this.isDeactivated=false;
		this.year=year;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDeactivated() {
		return isDeactivated;
	}

	public void setIsDeactivated(boolean deactivated) {
		isDeactivated = deactivated;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setDeactivated(boolean deactivated) {
		isDeactivated = deactivated;
	}
}
