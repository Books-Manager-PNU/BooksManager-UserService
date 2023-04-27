package com.example.booksManager.service;

import com.example.booksManager.dto.UserRequestDto;
import com.example.booksManager.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponseDto create(UserRequestDto user);
    UserResponseDto get(Long id);
    UserResponseDto update(Long id, UserRequestDto user);
    void delete(Long id);
    List<UserResponseDto> getAll();
}
