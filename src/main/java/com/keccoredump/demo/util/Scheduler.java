package com.keccoredump.demo.util;

import com.keccoredump.demo.entity.Reports;
import com.keccoredump.demo.entity.User;
import com.keccoredump.demo.repository.ReportsRepo;
import com.keccoredump.demo.service.ReportService;
import com.keccoredump.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Scheduler {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ReportService reportService;

    @Scheduled(cron = "0 0 * * * *")
    public void removeReportedUsers(){
        List<Reports> reports = reportService.findByReportsGreaterThanEqual(10);
        for(Reports report: reports){
            String email = report.getEmail();
            User user = userService.findByEmail(email);
            user.getUserDetails().setIsDeactivated(true);
            userService.saveUser(user);
            report.setIsBlocked(true);
            report.setBannedDate(new Date());
            reportService.saveReport(report);
        }
    }

    @Scheduled(cron = "0 0 12 1/1 * ?")
    public void activateAllDeactivatedUsers() throws ParseException {
        List<Reports> reports = reportService.findAllByIsBanned();
        for(Reports report: reports){
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            Date reportedDate = sdf.parse(report.getBannedDate().toString());
            Date currentDate = sdf.parse(new Date().toString());
            long time_difference = reportedDate.getTime()-currentDate.getTime();
            long days_difference = (time_difference / (1000*60*60*24)) % 365;
            if(days_difference<=7){
                String email = report.getEmail();
                User user = userService.findByEmail(email);
                user.getUserDetails().setIsDeactivated(false);
                userService.saveUser(user);
                report.setIsBlocked(false);
                report.setBannedDate(null);
                reportService.saveReport(report);
            }
        }

    }


    @Scheduled(cron = "0 0 * * * *")
    public void cleanExpired(){
        jwtUtils.cleanExpired();
    }
}
