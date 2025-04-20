package com.berru.app.elearningmanagementsystem.mapper;

import com.berru.app.elearningmanagementsystem.dto.NewCourseRequestDto;
import com.berru.app.elearningmanagementsystem.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "mentor", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "notesFileName", ignore = true)
    @Mapping(target = "thumbnail", ignore = true)
    Course toEntity(NewCourseRequestDto dto);
}
