package com.keccoredump.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keccoredump.demo.entity.Questions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions, Integer>{

	public List<Questions> findAllByOrderByDateDesc();

	@Query("SELECT q FROM Questions q WHERE "+"q.question LIKE CONCAT('%',:searchQuery,'%')")
	public List<Questions> searchQuestions(String searchQuery);

	public Questions findById(int id);
}
