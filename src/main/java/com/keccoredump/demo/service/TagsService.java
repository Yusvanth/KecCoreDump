package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.Questions;
import com.keccoredump.demo.entity.Tags;

import java.util.List;
import java.util.Set;

public interface TagsService {
    public Set<Tags> getTagsForQuestion(String[] tags, Questions question);

    public Tags findByTag(String tag);

    public List<String> getAllTags();
}
