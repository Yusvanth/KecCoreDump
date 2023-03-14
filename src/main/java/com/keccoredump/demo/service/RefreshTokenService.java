package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    public RefreshToken findByToken(String token);

    public RefreshToken createRefreshToken(int userId);

    public RefreshToken verifyExpiration(RefreshToken token);

    public int deleteByUserId(int userId);
}
