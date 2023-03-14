package com.keccoredump.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keccoredump.demo.entity.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer>{

}
