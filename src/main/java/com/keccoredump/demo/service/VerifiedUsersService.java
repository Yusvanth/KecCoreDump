package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.VerifiedUsers;

public interface VerifiedUsersService {

    public void saveVerifiedUser(VerifiedUsers verifiedUsers);

    public boolean isUserVerified(String email);
}
