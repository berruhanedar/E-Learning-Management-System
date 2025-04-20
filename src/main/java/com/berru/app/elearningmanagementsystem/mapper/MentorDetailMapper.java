package com.berru.app.elearningmanagementsystem.mapper;

import com.berru.app.elearningmanagementsystem.dto.NewMentorDetailRequestDto;
import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MentorDetailMapper {

    MentorDetailMapper INSTANCE = Mappers.getMapper(MentorDetailMapper.class);

    @Mapping(target = "profilePic", ignore = true)
    @Mapping(target = "id", ignore = true)
    MentorDetail toEntity(NewMentorDetailRequestDto dto);
}
