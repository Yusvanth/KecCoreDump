package com.keccoredump.demo.repository;

import com.keccoredump.demo.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportsRepo extends JpaRepository<Reports,Integer> {

    public List<Reports> findByReportsGreaterThanEqual(Integer reports);

    public Reports findByEmail(String email);

    public List<Reports> findByIsBlockedTrue();


}
