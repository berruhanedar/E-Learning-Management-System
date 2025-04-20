package com.berru.app.elearningmanagementsystem.mapper;

import com.berru.app.elearningmanagementsystem.dto.RegisterUserRequestDto;
import com.berru.app.elearningmanagementsystem.dto.UserDto;
import com.berru.app.elearningmanagementsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mentorDetail", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "status", ignore = true)
    User toEntity(RegisterUserRequestDto dto);

    // Entity → DTO
    @Mapping(target = "mentorDetail", source = "mentorDetail")
    @Mapping(target = "address", source = "address")
    UserDto toDto(User user);
}
