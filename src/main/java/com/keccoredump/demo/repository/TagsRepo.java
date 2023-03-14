package com.keccoredump.demo.repository;

import com.keccoredump.demo.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepo extends JpaRepository<Tags,Integer> {

    public Tags findByTag(String tag);

    public boolean existsByTagEqualsIgnoreCase(String tag);
}
