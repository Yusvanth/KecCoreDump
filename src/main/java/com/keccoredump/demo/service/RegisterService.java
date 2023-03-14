package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.*;

public interface RegisterService {

	void saveUserDetails(UserDetails userDetails);
	void saveUser(User user);
	Roles getRoles(String role);
	boolean checkRole(String role);
    boolean checkUserByEmail(String email);

}
