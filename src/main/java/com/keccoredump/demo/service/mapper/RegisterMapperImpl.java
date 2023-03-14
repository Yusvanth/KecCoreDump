package com.keccoredump.demo.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.keccoredump.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.keccoredump.demo.dto.UserSignUpDTO;
import com.keccoredump.demo.handlers.ApiException;
import com.keccoredump.demo.service.RegisterService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterMapperImpl implements RegisterMapper{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RegisterService registerService ;

	@Override
	public void addUser(UserSignUpDTO input) {
		
		if(registerService.checkUserByEmail(input.getEmail())) {
			throw new ApiException("TA8", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setEmail(input.getEmail());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		List<Roles> listOfRoles = new ArrayList<>();
		Roles role = registerService.getRoles(input.getRole());

		listOfRoles.add(role);
		System.out.println(role);
		user.setUserRoles(listOfRoles);
		
		//System.out.println("hi");
		UserDetails userDetails = new UserDetails();
		userDetails.setName(input.getName());
		userDetails.setNo_of_qns_answered(0);
		userDetails.setNo_of_qns_posted(0);
		userDetails.setQuestions(new ArrayList<>());
		userDetails.setAnswers(new ArrayList<>());
		userDetails.setRollNo(input.getRollNo());
		userDetails.setYear(input.getYear());
		userDetails.setUser(user);
		registerService.saveUserDetails(userDetails);
		user.setUserDetails(userDetails);
		
		registerService.saveUser(user);

		
	}


}
