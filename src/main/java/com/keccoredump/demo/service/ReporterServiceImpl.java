package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.Reporter;
import com.keccoredump.demo.repository.ReporterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReporterServiceImpl implements ReporterService{

    @Autowired
    ReporterRepo reporterRepo;

    @Override
    public void saveReporter(Reporter reporter) {
        reporterRepo.save(reporter);
    }
}
