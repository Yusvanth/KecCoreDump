package com.keccoredump.demo.service;

import com.keccoredump.demo.dto.UserDetailsDto;
import com.keccoredump.demo.dto.UserProfileDto;
import com.keccoredump.demo.entity.User;
import com.keccoredump.demo.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keccoredump.demo.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepo userRepo;

	
	@Override
	public User findByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}

	@Override
	public User findById(int id) {
		return userRepo.findById(id);
	}

	@Override
	public UserProfileDto getUserProfile(String email) {
		User user = findByEmail(email);
		UserDetails userDetails = user.getUserDetails();
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setEmail(email);
		userProfileDto.setName(userDetails.getName());
		userProfileDto.setRollNo(userDetails.getRollNo());
		userProfileDto.setNo_of_qns_answered(userDetails.getNo_of_qns_answered());
		userProfileDto.setNo_of_qns_posted(userDetails.getNo_of_qns_posted());
		userProfileDto.setId(userDetails.getUser().getId());
		userProfileDto.setYear(userDetails.getYear());
		return userProfileDto;
	}

	@Override
	public void deleteById(int id) {
		userRepo.deleteById(id);
	}

	@Override
	public void saveUser(User user) {
		userRepo.save(user);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepo.existsByEmailEqualsIgnoreCase(email);
	}

	@Override
	public User getCurrentUserDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsDto userDetailsDto = (UserDetailsDto) auth.getPrincipal();
		System.out.println(userDetailsDto);
		return userRepo.findByEmail(userDetailsDto.getEmail());
	}

}
