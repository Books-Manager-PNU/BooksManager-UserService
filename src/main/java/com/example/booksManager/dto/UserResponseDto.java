package com.example.booksManager.dto;

import com.example.booksManager.entity.Role;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        Role role
) {
}
