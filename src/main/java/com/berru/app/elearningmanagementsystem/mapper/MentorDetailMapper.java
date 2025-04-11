package com.berru.app.elearningmanagementsystem.mapper;

import com.berru.app.elearningmanagementsystem.dto.NewMentorDetailRequestDto;
import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface MentorDetailMapper {

    @Mapping(target = "profileImageUrl", source = "profileImageUrl")
    MentorDetail toMentorDetail(NewMentorDetailRequestDto newMentorDetailRequestDto);

    default String map(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        return file.getOriginalFilename();
    }
}