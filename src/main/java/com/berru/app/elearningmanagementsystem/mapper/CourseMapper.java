package com.berru.app.elearningmanagementsystem.mapper;


import com.berru.app.elearningmanagementsystem.dto.CourseResponseDto;
import com.berru.app.elearningmanagementsystem.dto.NewCourseRequestDto;
import com.berru.app.elearningmanagementsystem.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResponseDto toCourseDTO(Course course);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "mentorId", target = "mentor.id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "type", target = "type")
    Course toCourse(NewCourseRequestDto newCourseRequestDto);

 }
