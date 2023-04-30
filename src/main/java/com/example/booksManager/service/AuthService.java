package com.example.booksManager.service;

import com.example.booksManager.dto.UserRequestDto;
import com.example.booksManager.dto.auth.AuthRequest;
import com.example.booksManager.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(UserRequestDto user);
    AuthResponse authenticate(AuthRequest user);
}
