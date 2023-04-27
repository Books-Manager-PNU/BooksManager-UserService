package com.example.booksManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
    @NotBlank(message = "Username cannot be empty")
    String username,

    @Email(message = "Email is invalid")
    @NotBlank(message = "Email cannot be empty")
    String email,

    @NotBlank(message = "Password cannot be empty")
    String password
) {

}
