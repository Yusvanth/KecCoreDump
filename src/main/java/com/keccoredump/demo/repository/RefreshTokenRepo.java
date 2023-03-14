package com.keccoredump.demo.repository;

import com.keccoredump.demo.entity.RefreshToken;
import com.keccoredump.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Integer> {
    RefreshToken findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
