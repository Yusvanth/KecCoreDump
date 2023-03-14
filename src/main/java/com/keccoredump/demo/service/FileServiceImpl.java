package com.keccoredump.demo.service;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keccoredump.demo.dto.CreateAnswerDto;
import com.keccoredump.demo.dto.CreateQuestionDto;
import com.keccoredump.demo.entity.Answers;
import com.keccoredump.demo.entity.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;




@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private AmazonS3Client amazonS3Client;

	@Override
	public String uploadFile(MultipartFile multipartFile) {
		if(multipartFile==null)
			return " ";
		String extention = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
		
		String key = UUID.randomUUID().toString()+"."+extention;
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(multipartFile.getSize());
		objectMetadata.setContentType(multipartFile.getContentType());
		
		try {
			amazonS3Client.putObject("kcd-documents", key, multipartFile.getInputStream(), objectMetadata);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		amazonS3Client.setObjectAcl("kcd-documents",key,CannedAccessControlList.PublicRead);
		return amazonS3Client.getResourceUrl("kcd-documents", key);
	}

	public CreateQuestionDto getQuestionFromRequestPart(String question){
		CreateQuestionDto questions = new CreateQuestionDto();
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			questions=objectMapper.readValue(question,CreateQuestionDto.class);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return questions;
	}

	@Override
	public CreateAnswerDto getAnswerFromRequestPart(String answer) {
		CreateAnswerDto answers = new CreateAnswerDto();
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			answers=objectMapper.readValue(answer,CreateAnswerDto.class);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return answers;
	}
}
