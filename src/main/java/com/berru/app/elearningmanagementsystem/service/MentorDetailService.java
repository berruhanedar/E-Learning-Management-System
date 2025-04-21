package com.berru.app.elearningmanagementsystem.service;

import java.util.Optional;

import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import com.berru.app.elearningmanagementsystem.repository.MentorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorDetailService {

    @Autowired
    private MentorDetailRepository mentorDetailRepository;

    public MentorDetail addMentorDetail(MentorDetail detail) {
        return mentorDetailRepository.save(detail);
    }

    public MentorDetail updateMentorDetail(MentorDetail detail) {
        return mentorDetailRepository.save(detail);
    }

    public MentorDetail getById(int detailId) {

        Optional<MentorDetail> optional = this.mentorDetailRepository.findById(detailId);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
}