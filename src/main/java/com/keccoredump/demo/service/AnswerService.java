package com.keccoredump.demo.service;

import com.keccoredump.demo.dto.UpVoteDto;
import com.keccoredump.demo.entity.Answers;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnswerService {
    public Answers findById(int id);
    public void saveAnswer(String answerString, MultipartFile image);

    public void reportAnswer(int id);

    void handleUpvoteInAnswerService(UpVoteDto upVoteDto);

}
