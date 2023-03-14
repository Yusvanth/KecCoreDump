package com.keccoredump.demo.repository;

import com.keccoredump.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
	public User findById(int id);

	public boolean existsByEmailEqualsIgnoreCase(String email);

	public void deleteById(int id);
}
