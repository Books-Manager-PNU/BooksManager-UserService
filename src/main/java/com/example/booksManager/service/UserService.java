package com.example.booksManager.service;

import com.example.booksManager.dto.UserRequestDto;
import com.example.booksManager.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto get(Long id);
    UserResponseDto update(Long id, UserRequestDto user);
    void delete(Long id);
    List<UserResponseDto> getAll();
}
