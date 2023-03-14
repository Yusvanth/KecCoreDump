package com.keccoredump.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keccoredump.demo.dto.*;
import com.keccoredump.demo.entity.*;
import com.keccoredump.demo.handlers.ApiException;
import com.keccoredump.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.keccoredump.demo.handlers.ResponseHandler;
import com.keccoredump.demo.service.mapper.LoginMapper;
import com.keccoredump.demo.service.mapper.RegisterMapper;
import com.keccoredump.demo.util.JwtUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	QuestionsService questionsService;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	OtpService otpService;

	@Autowired
	EmailService emailService;



	@Autowired
	RegisterMapper registerMapper;

	@Autowired
	LoginMapper loginMapper;


	@Autowired
	AnswerService answerService;



	@Autowired
	VerifiedUsersService verifiedUsersService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RefreshTokenService refreshTokenService;

	@Autowired
	TagsService tagsService;

	@GetMapping(value = "/generate-otp", params = {"email"})
	public ResponseEntity<Object> generateOtp(@RequestParam String email) {

		//  Checking if user already exists

		if (userService.findByEmail(email) != null) {
			return ResponseHandler.generateResponse("User already signed up", HttpStatus.ALREADY_REPORTED);
		}

		// Check if the email is a kongu mail address

		if(!email.endsWith("@kongu.edu"))
			return ResponseHandler.generateResponse("Invalid Email address",HttpStatus.EXPECTATION_FAILED);

		// If mail is sent to user return email or report it
		if (emailService.sendMailForOtp(email,true))
			return ResponseHandler.generateResponse(email, HttpStatus.OK);

		return ResponseHandler.generateResponse("Otp not generated", HttpStatus.EXPECTATION_FAILED);

	}

	@PostMapping("/verify-otp")
	public ResponseEntity<Object> checkOtp(@RequestBody OtpDto otpDto) {

		// This user is for Forgot password

		if(userService.existsByEmail(otpDto.getEmail())){
			if(otpService.verifyOtpForForgotPassword(otpDto)){
				return ResponseHandler.generateResponse("Otp verified", HttpStatus.OK);
			}
			else
				return ResponseHandler.generateResponse("Incorrect otp", HttpStatus.EXPECTATION_FAILED);
		}

		//This if is for login
		if (otpService.verifyOtp(otpDto)) {
			return ResponseHandler.generateResponse("Otp verified", HttpStatus.OK);
		} else {
			return ResponseHandler.generateResponse("Incorrect otp", HttpStatus.EXPECTATION_FAILED);
		}

	}

	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody UserSignUpDTO userSignUpDTO) {
		;
		String email=userSignUpDTO.getEmail();

		// Check if the user has verified their email

		if(verifiedUsersService.isUserVerified(email)){
			registerMapper.addUser(userSignUpDTO);
			return ResponseHandler.generateResponse("User added!!!", HttpStatus.OK);
		}
		else{
			return ResponseHandler.generateResponse("User not verified",HttpStatus.EXPECTATION_FAILED);
		}
	}


	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpServletResponse) {

		User user = userService.findByEmail(loginDTO.getEmail());
		if(user==null){
			return ResponseHandler.generateResponse("User not found , please sign up again!!!",HttpStatus.BAD_REQUEST);
		}
		// Check if the user is blocked or not
		if(user.getUserDetails().isDeactivated())
			return ResponseHandler.generateResponse("User is blocked", HttpStatus.FORBIDDEN);

		// Authenticate user
		AuthenticationResponseDto responseDto = loginMapper.createAuthenticationToken(loginDTO, httpServletResponse);

		if (responseDto != null)
			return ResponseHandler.generateResponse("Login Successful", HttpStatus.OK, responseDto);
		else
			return ResponseHandler.generateResponse("Incorrect Password",HttpStatus.EXPECTATION_FAILED);

	}

	@PostMapping("/logout")
	public ResponseEntity<Object> logout(HttpServletRequest request) {
		jwtUtils.logout(request);
		return ResponseHandler.generateResponse("Logged out successfully !!!", HttpStatus.OK, "Token deleted");
	}

	@GetMapping("/get-user-profile")
	public ResponseEntity<Object> getUserDetails(HttpServletRequest request) {
		// Get user details to display in home page
		UserProfileDto userProfileDto = userService.getUserProfile(userService.getCurrentUserDetails().getEmail());
		return ResponseHandler.generateResponse("Retrieved user details", HttpStatus.OK, userProfileDto);

	}

	@GetMapping("/get-user-profile/{id}")
	public ResponseEntity<Object> getUserDetailsById(@PathVariable int id){
		UserProfileDto userProfileDto = userService.getUserProfile(userService.findById(id).getEmail());
		if(userProfileDto!=null)
			return ResponseHandler.generateResponse("Retrieved user details", HttpStatus.OK, userProfileDto);
		else
			throw new ApiException("Invalid User id",HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/search")
	public ResponseEntity<Object> getSearchedQuestion(@RequestParam(value = "query") String query,@RequestParam(value = "tags",required = false)String tags) {

		// Get the questions according to the given search string

		List<Questions> questionsList = questionsService.getSearchResult(query,tags);
		return ResponseHandler.generateResponse("search results", HttpStatus.OK, questionsList);
	}

	@PostMapping(value = "/save-question", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Object> saveQuestion(@RequestPart("question") String questionString, @RequestPart(value = "image",required = false) MultipartFile image, HttpServletRequest request) {


		questionsService.saveQuestion(questionString,image);

		return ResponseHandler.generateResponse("Question saved", HttpStatus.OK);
	}

	@GetMapping("/get-questions")
	public ResponseEntity<Object> getQuestions() {

		List<Questions> questions = questionsService.getRecentQuestions();

		return ResponseHandler.generateResponse("Generated list of questions", HttpStatus.OK, questions);
	}

	// reporting user id and ques/ans id
	@GetMapping("/report-answer")
	public ResponseEntity<Object> handleAnswerReport(@RequestParam("id") int id) {

		answerService.reportAnswer(id);

		return ResponseHandler.generateResponse("User Reported", HttpStatus.OK);
	}

	@GetMapping("/report-question")
	public ResponseEntity<Object> handleQuestionReport(@RequestParam("id") int id) {

		questionsService.reportQuestion(id);
		return ResponseHandler.generateResponse("User Reported", HttpStatus.OK);
	}

	@PostMapping(value = "/save-answer", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Object> createAnswer(@RequestPart("answer") String answerString, @RequestPart(value = "image",required = false) MultipartFile image, HttpServletRequest request) {

		answerService.saveAnswer(answerString,image);

		return ResponseHandler.generateResponse("Answer saved", HttpStatus.CREATED);
	}

	@PostMapping("/handle-upVote")
	public ResponseEntity<Object> handleUpvote(@RequestBody UpVoteDto upVoteDto, HttpServletRequest request) {

		answerService.handleUpvoteInAnswerService(upVoteDto);
		return ResponseHandler.generateResponse("Answer upvoted", HttpStatus.OK);
	}

	@GetMapping("/get-question-by-id")
	public ResponseEntity<Object> getQuestionById(@RequestParam("id")int id){
		GetQuestionByIdDto questions = questionsService.getQuestionById(id);
		if(questions!=null)
			return ResponseHandler.generateResponse("Question retrieved",HttpStatus.OK,questions);
		else
			throw new ApiException("Invalid question id",HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/get-user-questions")
	public ResponseEntity<Object> getUserQuestions(){
		User user = userService.getCurrentUserDetails();
		List<Questions> questions = user.getUserDetails().getQuestions();
		return ResponseHandler.generateResponse("Questions of the logged in user retrieved",HttpStatus.OK,questions);
	}

	@GetMapping("/forget-password")
	public ResponseEntity<Object> forgotPassword(@RequestParam("email")String email){
		if(!userService.existsByEmail(email)){
			ResponseHandler.generateResponse("User not signed up",HttpStatus.EXPECTATION_FAILED);
		}

		if (emailService.sendMailForOtp(email,false))
			return ResponseHandler.generateResponse(email, HttpStatus.OK);
		else
			return ResponseHandler.generateResponse("Otp not generated",HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<Object> resetPassword(@RequestBody LoginDTO loginDTO){

		if(!userService.existsByEmail(loginDTO.getEmail())){
			ResponseHandler.generateResponse("User not signed up",HttpStatus.EXPECTATION_FAILED);
		}

		User user = userService.findByEmail(loginDTO.getEmail());

		user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
		userService.saveUser(user);
		return ResponseHandler.generateResponse("Password Resetted",HttpStatus.OK);
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<Object> getRefreshToken(@RequestBody RefreshTokenDto refreshTokenDto){
		String requestRefreshToken = refreshTokenDto.getRefreshToken();

		RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken);

		if(refreshToken==null)
			return ResponseHandler.generateResponse("Refresh token is not is database",HttpStatus.EXPECTATION_FAILED);

		RefreshToken isExpiredToken = refreshTokenService.verifyExpiration(refreshToken);

		if(isExpiredToken==null)
			return ResponseHandler.generateResponse("Refresh token expired login again",HttpStatus.EXPECTATION_FAILED);

		String newJwtToken = jwtUtils.generateJwt(refreshToken.getUser().getEmail());

		return ResponseHandler.generateResponse("Access token created",HttpStatus.OK,new RefreshTokenResponceDto(newJwtToken,refreshToken.getToken()));

	}

	@GetMapping("/get-tags")
	public ResponseEntity<Object> getAllTags(){
		List<String> tags = tagsService.getAllTags();
		return ResponseHandler.generateResponse("List of tags",HttpStatus.OK,tags);
	}

}