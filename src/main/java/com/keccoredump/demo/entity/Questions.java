package com.keccoredump.demo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "Questions")
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "question")
	private String question;
	
	@Column(name = "image")
	private String image;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;

	@JsonManagedReference
	@OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL)
	private List<Answers> answers;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetailId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "question_tags",
			joinColumns = @JoinColumn(name = "questions_id"),
			inverseJoinColumns = @JoinColumn(name = "tags_id")
	)
	private Set<Tags> tags=new HashSet<>();

	public Questions() {
		
	}

	public Questions(UserDetails userDetails, String question, String image, Date date, List<Answers> answers,Set<Tags> tags) {
		this.userDetailId=userDetails;
		this.question = question;
		this.image = image;
		this.date = date;
		this.answers = answers;
		this.tags=tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDetails getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(UserDetails userDetailId) {
		this.userDetailId = userDetailId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

	public Set<Tags> getTags() {
		return tags;
	}

	public void setTags(Set<Tags> tags) {
		this.tags = tags;
	}
}
