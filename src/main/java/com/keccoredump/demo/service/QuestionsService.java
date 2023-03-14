package com.keccoredump.demo.service;

import java.util.List;

import com.keccoredump.demo.dto.GetQuestionByIdDto;
import com.keccoredump.demo.entity.Questions;
import org.springframework.web.multipart.MultipartFile;

public interface QuestionsService {

	public List<Questions> getRecentQuestions();
	
	public void saveQuestion(String questionString, MultipartFile image);

	public List<Questions> getSearchResult(String query,String tags);

	public Questions findById(int id);

	public void reportQuestion(int id);

	public GetQuestionByIdDto getQuestionById(int id);

}
