package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.Reports;

import java.util.List;

public interface ReportService {

    public void saveReport(Reports reports);
    public Reports findByEmail(String email);

    public void updateReport(Reports reports);

    public List<Reports> findByReportsGreaterThanEqual(int n);

    public List<Reports> findAllByIsBanned();
}
