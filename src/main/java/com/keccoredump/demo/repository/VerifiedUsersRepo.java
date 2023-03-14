package com.keccoredump.demo.repository;

import com.keccoredump.demo.entity.VerifiedUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifiedUsersRepo extends JpaRepository<VerifiedUsers,Integer> {

    public VerifiedUsers findByEmail(String email);
}
