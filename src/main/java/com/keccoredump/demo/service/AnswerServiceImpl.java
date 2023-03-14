package com.keccoredump.demo.service;

import com.keccoredump.demo.dto.CreateAnswerDto;
import com.keccoredump.demo.dto.UpVoteDto;
import com.keccoredump.demo.entity.*;
import com.keccoredump.demo.handlers.ApiException;
import com.keccoredump.demo.repository.AnswersRepo;
import com.keccoredump.demo.util.ReportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    AnswersRepo answersRepo;

    @Autowired
    UserService userService;

    @Autowired
    ReportHandler reportHandler;

    @Autowired
    FileService fileService;

    @Autowired
    RegisterService registerService;

    @Autowired
    QuestionsService questionsService;

    @Autowired
    EmailService emailService;

    @Override
    public Answers findById(int id) {
        return answersRepo.findById(id);
    }

    @Override
    public void saveAnswer(String answerString, MultipartFile image) {

        CreateAnswerDto answer = fileService.getAnswerFromRequestPart(answerString);
        User user = userService.getCurrentUserDetails();
        UserDetails userDetails = user.getUserDetails();
        userDetails.setNo_of_qns_answered(userDetails.getNo_of_qns_answered() + 1);
        registerService.saveUserDetails(userDetails);
        Answers answers = new Answers();
        Questions questions = questionsService.findById(answer.getQuestionID());
        answers.setAnswer(answer.getAnswer());
        answers.setImage(fileService.uploadFile(image));
        answers.setQuestionId(questions);
        answers.setUpVote(0);
        answers.setUserDetails(user.getUserDetails());
        answersRepo.save(answers);
        if(!emailService.sendMailForAnswerNotification(questions.getUserDetailId().getUser().getEmail(),answers.getUserDetails().getName(),answerString)){
            throw new RuntimeException("Email not sent to user .");
        }
    }

    @Override
    public void reportAnswer(int id) {
        User user = userService.getCurrentUserDetails();
        int reportingUserId = user.getId();
        Answers answers = findById(id);
        if(answers==null){
            throw new ApiException("Invalid answer id", HttpStatus.BAD_REQUEST);
        }
        String message = answers.getAnswer();
        int reportedUserId = answers.getUserDetails().getUser().getId();
        Reporter reporter = new Reporter(user.getEmail(), reportingUserId, reportedUserId, message, new Date());

        reportHandler.handleReport(reporter);
    }

    @Override
    public void handleUpvoteInAnswerService(UpVoteDto upVoteDto) {
        Answers answer = findById(upVoteDto.getAnswerId());
        answer.setUpVote(answer.getUpVote() + 1);
        answersRepo.save(answer);
    }

}
