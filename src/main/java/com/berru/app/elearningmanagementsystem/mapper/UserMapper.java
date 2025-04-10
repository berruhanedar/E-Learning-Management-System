package com.berru.app.elearningmanagementsystem.mapper;

import com.berru.app.elearningmanagementsystem.dto.RegisterUserRequestDto;
import com.berru.app.elearningmanagementsystem.dto.UserDto;
import com.berru.app.elearningmanagementsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "mentorDetail", ignore = true)
    User toUser(RegisterUserRequestDto dto);

    RegisterUserRequestDto toRegisterUserRequestDto(User user);
}
