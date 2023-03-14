package com.keccoredump.demo.repository;

import com.keccoredump.demo.entity.Reporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporterRepo extends JpaRepository<Reporter,Integer> {
}
