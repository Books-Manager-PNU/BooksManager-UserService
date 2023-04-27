package com.example.booksManager.mapper;

import com.example.booksManager.dto.UserRequestDto;
import com.example.booksManager.dto.UserResponseDto;
import com.example.booksManager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);
    User toEntity(UserRequestDto userDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UserRequestDto userDto, @MappingTarget User user);
}
