package com.example.booksManager.dto.auth;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token
) {
}
