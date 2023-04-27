package com.example.booksManager.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public record AuthRequest(
        @NotBlank(message = "Email cannot be empty")
        String email,

        @NotBlank(message = "Password cannot be empty")
        String password
) {
}
