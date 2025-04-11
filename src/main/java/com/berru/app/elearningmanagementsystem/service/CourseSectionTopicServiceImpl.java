package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.CourseSectionTopic;
import com.berru.app.elearningmanagementsystem.repository.CourseSectionTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseSectionTopicServiceImpl implements CourseSectionTopicService {

    @Autowired
    private CourseSectionTopicRepository courseSectionTopicRepository;

    @Override
    public CourseSectionTopic add(CourseSectionTopic topic) {
        return courseSectionTopicRepository.save(topic);
    }
}
