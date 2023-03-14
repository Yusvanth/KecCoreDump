package com.keccoredump.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keccoredump.demo.entity.Answers;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnswersRepo extends JpaRepository<Answers, Integer>{

    public Answers findById(int id);
}
