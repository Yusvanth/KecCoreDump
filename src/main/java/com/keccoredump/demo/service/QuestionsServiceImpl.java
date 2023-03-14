package com.keccoredump.demo.service;

import java.util.*;

import com.keccoredump.demo.dto.CreateQuestionDto;
import com.keccoredump.demo.dto.GetQuestionByIdDto;
import com.keccoredump.demo.entity.*;
import com.keccoredump.demo.handlers.ApiException;
import com.keccoredump.demo.util.ReportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.keccoredump.demo.repository.QuestionsRepo;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QuestionsServiceImpl implements QuestionsService{

	@Autowired
	QuestionsRepo questionsRepo ;

	@Autowired
	FileService fileService;

	@Autowired
	UserService userService;

	@Autowired
	RegisterService registerService;

	@Autowired
	ReportHandler reportHandler;

	@Autowired
	TagsService tagsService;
	
	@Override
	public List<Questions> getRecentQuestions() {
//		//List<Questions> list = questionsRepo.findAllByOrderByDateDesc();
//		Pageable paging = PageRequest.of(start,end, Sort.by("date"));
//		Page<Questions> list = questionsRepo.findAll(paging);
//		if(list.hasContent())
//			return list.getContent();
//		return new ArrayList<>();
		return questionsRepo.findAllByOrderByDateDesc();
	}


	@Override
	public void saveQuestion(String questionString, MultipartFile image) {

		CreateQuestionDto questions = fileService.getQuestionFromRequestPart(questionString);
		String s[]=questions.getTags().split("\\,");
		User user = userService.getCurrentUserDetails();
		UserDetails userDetails = user.getUserDetails();
		userDetails.setNo_of_qns_posted(userDetails.getNo_of_qns_posted() + 1);
		Questions question = new Questions();
		Set<Tags> tags = tagsService.getTagsForQuestion(s,question);
		question.setQuestion(questions.getQuestion());
		question.setAnswers(new ArrayList<>());
		question.setDate(new Date());
		question.setImage(fileService.uploadFile(image));
		question.setUserDetailId(user.getUserDetails());
		question.setTags(tags);
		questionsRepo.save(question);
		List<Questions> list=userDetails.getQuestions();
		list.add(question);
		userDetails.setQuestions(list);
		registerService.saveUserDetails(userDetails);
	}

	@Override
	public List<Questions> getSearchResult(String query,String tags) {
		boolean b=false;
		if(tags!=null)
			b=true;
		if(b) {
			String s[] = tags.split("\\,");
			List<Questions> result = new ArrayList<>();
			for (String tag : s) {
				Tags t = tagsService.findByTag(tag);
				if(t!=null)
					for (Questions q : t.getQuestions())
						result.add(q);
			}
			String filler[] = {"the", "what", "why", "who", "is", "a", "an", "has", "was", "have", "that", "in", "to", "how"};
			List<String> fillers = Arrays.asList(filler);
			List<Questions> result1 = new ArrayList<>();
			String arr[] = query.split(" ");
			for (String i : arr) {
				if (fillers.contains(i.toLowerCase()))
					continue;
				List<Questions> temp = questionsRepo.searchQuestions(i);
				Iterator iterator = temp.iterator();
				while (iterator.hasNext()) {
					Questions questions = (Questions) iterator.next();
					if (!result1.contains(questions) && result.contains(questions)) {
						result1.add(questions);
					}
				}
			}
			return result1;
		}
		else{
			List<Questions> result = new ArrayList<>();
			String filler[] = {"the", "what", "why", "who", "is", "a", "an", "has", "was", "have", "that", "in", "to", "how"};
			List<String> fillers = Arrays.asList(filler);
			String arr[] = query.split(" ");
			for (String i : arr) {
				if (fillers.contains(i.toLowerCase()))
					continue;
				List<Questions> temp = questionsRepo.searchQuestions(i);
				Iterator iterator = temp.iterator();
				while (iterator.hasNext()) {
					Questions questions = (Questions) iterator.next();
					if (!result.contains(questions)) {
						result.add(questions);
					}
				}
			}
			return result;
		}
	}

	@Override
	public Questions findById(int id) {
		return questionsRepo.findById(id);
	}

	@Override
	public void reportQuestion(int id) {
		User user = userService.getCurrentUserDetails();
		int reportingUserId = user.getId();
		Questions questions = findById(id);
		if(questions==null)
			throw new ApiException("Invalid question id", HttpStatus.BAD_REQUEST);
		String message = questions.getQuestion();
		int reportedUserId = 1;
		try {
			reportedUserId = questions.getUserDetailId().getUser().getId();
		} catch (NullPointerException e) {
			System.out.println(questions.getUserDetailId().getUser().getId());

		}
		Reporter reporter = new Reporter(user.getEmail(), reportingUserId, reportedUserId, message, new Date());

		reportHandler.handleReport(reporter);
	}

	@Override
	public GetQuestionByIdDto getQuestionById(int id) {
		Questions questions = findById(id);
		if(questions==null)
			return null;
		GetQuestionByIdDto getQuestionByIdDto = new GetQuestionByIdDto();
		getQuestionByIdDto.setId(questions.getId());
		getQuestionByIdDto.setAnswers(questions.getAnswers());
		getQuestionByIdDto.setDate(questions.getDate());
		getQuestionByIdDto.setQuestion(questions.getQuestion());
		getQuestionByIdDto.setUserId(questions.getUserDetailId().getUser().getId());
		getQuestionByIdDto.setImage(questions.getImage());
		return getQuestionByIdDto;
	}

}
