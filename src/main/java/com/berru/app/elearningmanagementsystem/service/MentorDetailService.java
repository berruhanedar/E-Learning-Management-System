package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.MentorDetail;

public interface MentorDetailService {
    MentorDetail addMentorDetail(MentorDetail detail);

    MentorDetail updateMentorDetail(MentorDetail detail);

    MentorDetail getById(int detailId);
}
