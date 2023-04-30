package com.example.booksManager.util;

import com.example.booksManager.dto.UserRequestDto;
import com.example.booksManager.entity.User;
import com.example.booksManager.exception.WebException;
import com.example.booksManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonUtils {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void encodePassword(User userEntity, UserRequestDto user){
        userEntity.setPassword(passwordEncoder.encode(user.password()));
    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User getExistingUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User getExistingUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "User not found"));
    }
}