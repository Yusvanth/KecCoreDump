package com.keccoredump.demo.service;

import com.keccoredump.demo.dto.LoginDTO;
import com.keccoredump.demo.dto.UserProfileDto;
import com.keccoredump.demo.entity.User;
import com.keccoredump.demo.entity.UserDetails;

public interface UserService {


	public User findByEmail(String email);
	
	public User findById(int id);

	public UserProfileDto getUserProfile(String email);

	public void deleteById(int id);

	public void saveUser(User user);

	public boolean existsByEmail(String email);

	public User getCurrentUserDetails();

}
