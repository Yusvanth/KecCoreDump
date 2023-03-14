package com.keccoredump.demo.util;

import com.keccoredump.demo.entity.Reporter;
import com.keccoredump.demo.entity.Reports;
import com.keccoredump.demo.service.ReportService;
import com.keccoredump.demo.service.ReporterService;
import com.keccoredump.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportHandler {

    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;

    @Autowired
    ReporterService reporterService;
    public void handleReport(Reporter reporter){
        reporterService.saveReporter(reporter);
        String reportedUserEmail = userService.findById(reporter.getReportedUserId()).getEmail();
        Reports existingReport = reportService.findByEmail(reportedUserEmail);
        if(existingReport==null){
            Reports newReport = new Reports();
            newReport.setReports(1);
            newReport.setEmail(reportedUserEmail);
            reportService.saveReport(newReport);
        }
        else{
            existingReport.setReports(existingReport.getReports()+1);
            reportService.updateReport(existingReport);
        }
    }
}
