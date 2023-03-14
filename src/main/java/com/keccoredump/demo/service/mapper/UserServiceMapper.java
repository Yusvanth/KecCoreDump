package com.keccoredump.demo.service.mapper;

import com.keccoredump.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keccoredump.demo.dto.UserDetailsDto;
import com.keccoredump.demo.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceMapper implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userService.findByEmail(email);
		return new UserDetailsDto(user);
	}

}
