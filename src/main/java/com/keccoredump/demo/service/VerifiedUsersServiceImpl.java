package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.VerifiedUsers;
import com.keccoredump.demo.repository.VerifiedUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifiedUsersServiceImpl implements VerifiedUsersService{

    @Autowired
    VerifiedUsersRepo verifiedUsersRepo;

    @Override
    public void saveVerifiedUser(VerifiedUsers verifiedUsers) {
        if(verifiedUsersRepo.findByEmail(verifiedUsers.getEmail())==null)
            verifiedUsersRepo.save(verifiedUsers);
    }

    @Override
    public boolean isUserVerified(String email) {
        if(verifiedUsersRepo.findByEmail(email)!=null){
            return true;
        }
        return false;
    }
}
