package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.Reports;
import com.keccoredump.demo.repository.ReportsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{

    @Autowired
    ReportsRepo reportsRepo;

    @Override
    public void saveReport(Reports reports) {
        reportsRepo.save(reports);
    }

    @Override
    public Reports findByEmail(String email) {
        return reportsRepo.findByEmail(email);
    }

    @Override
    public void updateReport(Reports reports) {
        reportsRepo.save(reports);
    }

    @Override
    public List<Reports> findByReportsGreaterThanEqual(int n) {
        return reportsRepo.findByReportsGreaterThanEqual(n);
    }

    @Override
    public List<Reports> findAllByIsBanned() {
        return reportsRepo.findByIsBlockedTrue();
    }
}
