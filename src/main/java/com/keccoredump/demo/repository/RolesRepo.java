package com.keccoredump.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keccoredump.demo.entity.Roles;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer>{

	Roles findByRolesIsIgnoreCase(String role);

	boolean existsByRolesEqualsIgnoreCase(String role);

}
