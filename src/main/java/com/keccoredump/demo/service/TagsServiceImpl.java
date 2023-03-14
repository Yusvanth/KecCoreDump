package com.keccoredump.demo.service;

import com.keccoredump.demo.entity.Questions;
import com.keccoredump.demo.entity.Tags;
import com.keccoredump.demo.repository.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagsServiceImpl implements TagsService{

    @Autowired
    TagsRepo tagsRepo;

    @Override
    public Set<Tags> getTagsForQuestion(String[] tags, Questions question) {
        Set<Tags>  tagsForQuestion= new HashSet<>();
        for(String tag:tags){
            if(tagsRepo.existsByTagEqualsIgnoreCase(tag)){
                tagsForQuestion.add(tagsRepo.findByTag(tag));
            }
            else{
                Tags newTag = new Tags();
                newTag.setTag(tag);
                Set<Questions> set =newTag.getQuestions();
                set.add(question);
                newTag.setQuestions(set);
                tagsForQuestion.add(newTag);
                tagsRepo.save(newTag);
            }
        }
        return tagsForQuestion;
    }

    @Override
    public Tags findByTag(String tag) {
        return tagsRepo.findByTag(tag);
    }

    @Override
    public List<String> getAllTags() {
        List<Tags> tags = tagsRepo.findAll();
        List<String> result = new ArrayList<>();
        for(Tags t: tags){
            result.add(t.getTag());
        }
        return result;
    }
}
