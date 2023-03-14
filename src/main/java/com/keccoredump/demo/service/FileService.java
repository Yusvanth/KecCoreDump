package com.keccoredump.demo.service;

import com.keccoredump.demo.dto.CreateAnswerDto;
import com.keccoredump.demo.dto.CreateQuestionDto;
import com.keccoredump.demo.entity.Answers;
import com.keccoredump.demo.entity.Questions;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public String uploadFile(MultipartFile multipartFile);
	public CreateQuestionDto getQuestionFromRequestPart(String question);

	public CreateAnswerDto getAnswerFromRequestPart(String answer);
}
