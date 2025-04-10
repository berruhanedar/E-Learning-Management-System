package com.berru.app.elearningmanagementsystem.mapper;

import com.berru.app.elearningmanagementsystem.dto.NewMentorDetailRequestDto;
import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MentorDetailMapper {

    @Mapping(target = "profileImageUrl", source = "profileImageUrl")
    MentorDetail toMentorDetail(NewMentorDetailRequestDto newMentorDetailRequestDto);
}
