package com.keccoredump.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CreateQuestionDto {

	private String question;

	private MultipartFile image;

	private String tags;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
