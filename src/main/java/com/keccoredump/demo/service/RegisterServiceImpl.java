package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.*;
import com.keccoredump.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserDetailsRepo userDetailsRepo;
	
	@Autowired
	RolesRepo rolesRepo;


	@Override
	public void saveUserDetails(UserDetails userDetails) {
		userDetailsRepo.save(userDetails);
		
	}

	@Override
	public void saveUser(User user) {
		userRepo.save(user);
		
	}



	@Override
	public Roles getRoles(String role) {
		return rolesRepo.findByRolesIsIgnoreCase(role);
	}

	@Override
	public boolean checkRole(String role) {
		return rolesRepo.existsByRolesEqualsIgnoreCase(role);
	}

	@Override
	public boolean checkUserByEmail(String email) {
		return userRepo.existsByEmailEqualsIgnoreCase(email);
	}


}
