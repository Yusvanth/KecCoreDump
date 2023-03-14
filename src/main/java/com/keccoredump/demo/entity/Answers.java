package com.keccoredump.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Answers")
public class Answers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "upvote")
	private int upVote;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "questions_id")
	private Questions questionId;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;




	public Answers() {
		
	}

	public Answers(String answer, String image, int upVote, UserDetails userId, Questions questionId) {
		this.answer = answer;
		this.image = image;
		this.upVote = upVote;
		this.userDetails = userId;
		this.questionId = questionId;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getUpVote() {
		return upVote;
	}

	public void setUpVote(int upVote) {
		this.upVote = upVote;
	}



	public Questions getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Questions questionId) {
		this.questionId = questionId;
	}
}
